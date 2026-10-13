package com.comisiones.calculocomisiones.service.impl;

import com.comisiones.calculocomisiones.dto.ComisionDTO;
import com.comisiones.calculocomisiones.model.Regla;
import com.comisiones.calculocomisiones.model.Venta;
import com.comisiones.calculocomisiones.repository.ReglaRepository;
import com.comisiones.calculocomisiones.repository.VentaRepository;
import com.comisiones.calculocomisiones.service.ComisionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ComisionServiceImpl implements ComisionService {

    private final VentaRepository ventaRepository;
    private final ReglaRepository reglaRepository;

    @Override
    public List<ComisionDTO> calcularComisiones(LocalDate fechaInicio, LocalDate fechaFin) {

        // 1. Obtener ventas dentro del rango
        List<Venta> ventasEnRango = ventaRepository.findByFechaBetween(fechaInicio, fechaFin);

        // 2. Cargar reglas ordenadas de mayor a menor (se aplica la primera que califica)
        List<Regla> reglasOrdenadas = reglaRepository.findAllByOrderByMontoMinimoDesc();

        // 3. Agrupar ventas por vendedor y sumar montos
        Map<String, BigDecimal> totalPorVendedor = ventasEnRango.stream()
                .collect(Collectors.groupingBy(
                        venta -> venta.getVendedor().getNombre(),
                        Collectors.reducing(BigDecimal.ZERO, Venta::getMonto, BigDecimal::add)
                ));

        // 4. Calcular comisión para cada vendedor
        return totalPorVendedor.entrySet().stream()
                .map(entry -> buildComisionDTO(entry.getKey(), entry.getValue(), reglasOrdenadas))
                .sorted((a, b) -> b.getTotalVendido().compareTo(a.getTotalVendido()))
                .collect(Collectors.toList());
    }

    // ---- Métodos privados (Clean Code: responsabilidad única) ----

    private ComisionDTO buildComisionDTO(String nombreVendedor,
                                         BigDecimal totalVendido,
                                         List<Regla> reglasOrdenadas) {

        Regla reglaAplicable = encontrarRegla(totalVendido, reglasOrdenadas);

        String nombreRegla = "Sin comisión";
        BigDecimal comisionCalculada = BigDecimal.ZERO;

        if (reglaAplicable != null) {
            nombreRegla = reglaAplicable.getNombre();
            comisionCalculada = calcularComision(totalVendido, reglaAplicable.getPorcentaje());
        }

        return new ComisionDTO(nombreVendedor, totalVendido, nombreRegla, comisionCalculada);
    }

    private Regla encontrarRegla(BigDecimal totalVendido, List<Regla> reglasOrdenadas) {
        return reglasOrdenadas.stream()
                .filter(regla -> totalVendido.compareTo(regla.getMontoMinimo()) >= 0)
                .findFirst()
                .orElse(null);
    }

    private BigDecimal calcularComision(BigDecimal totalVendido, BigDecimal porcentaje) {
        return totalVendido
                .multiply(porcentaje)
                .divide(new BigDecimal("100"), 4, RoundingMode.HALF_UP);
    }
}
