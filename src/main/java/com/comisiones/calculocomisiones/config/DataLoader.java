package com.comisiones.calculocomisiones.config;

import com.comisiones.calculocomisiones.model.Regla;
import com.comisiones.calculocomisiones.model.Vendedor;
import com.comisiones.calculocomisiones.model.Venta;
import com.comisiones.calculocomisiones.repository.ReglaRepository;
import com.comisiones.calculocomisiones.repository.VendedorRepository;
import com.comisiones.calculocomisiones.repository.VentaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final VendedorRepository vendedorRepository;
    private final VentaRepository ventaRepository;
    private final ReglaRepository reglaRepository;

    @Override
    public void run(String... args) {
        cargarVendedores();
        cargarReglas();
        cargarVentas();
        System.out.println("---- DATOS QUEMADOS CARGADOS CORRECTAMENTE ----");
    }

    private void cargarVendedores() {
        Vendedor juan  = vendedorRepository.save(new Vendedor("Juan Perez"));
        Vendedor maria = vendedorRepository.save(new Vendedor("Maria Garcia"));
        Vendedor pedro = vendedorRepository.save(new Vendedor("Pedro Lopez"));
        cargarVentasParaVendedores(juan, maria, pedro);
    }

    private void cargarReglas() {
        reglaRepository.saveAll(List.of(
            new Regla("Comisión 10",  new BigDecimal("10.0"), new BigDecimal("2000")),
            new Regla("Comisión 5",   new BigDecimal("5.0"),  new BigDecimal("1000")),
            new Regla("Comisión 2.5", new BigDecimal("2.5"),  new BigDecimal("500"))
        ));
    }

    private void cargarVentasParaVendedores(Vendedor juan, Vendedor maria, Vendedor pedro) {
        // Juan Perez — ventas mayo 2025
        ventaRepository.save(new Venta(LocalDate.of(2026, 10, 1),  new BigDecimal("800.00"),  juan));
        ventaRepository.save(new Venta(LocalDate.of(2026, 10, 5), new BigDecimal("300.50"),  juan));

        // Maria Garcia — ventas mayo 2025
        ventaRepository.save(new Venta(LocalDate.of(2026, 10, 3),  new BigDecimal("1500.00"), maria));
        ventaRepository.save(new Venta(LocalDate.of(2026, 10, 8), new BigDecimal("600.00"),  maria));

        // Pedro Lopez — ventas mayo 2025
        ventaRepository.save(new Venta(LocalDate.of(2026, 10, 6),  new BigDecimal("450.00"),  pedro));

        // Juan Perez — venta julio 2025 (fuera del rango de ejemplo)
        ventaRepository.save(new Venta(LocalDate.of(2026, 10, 12), new BigDecimal("5000.00"), juan));
    }

    // Método separado para que cargarVendedores no sea tan largo
    private void cargarVentas() {
        // Ventas adicionales ya están en cargarVentasParaVendedores
        // Este método queda disponible para futuras extensiones
    }
}
