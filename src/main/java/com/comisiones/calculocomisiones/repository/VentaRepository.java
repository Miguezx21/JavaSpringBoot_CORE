package com.comisiones.calculocomisiones.repository;

import com.comisiones.calculocomisiones.model.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface VentaRepository extends JpaRepository<Venta, Long> {

    List<Venta> findByFechaBetween(LocalDate fechaInicio, LocalDate fechaFin);
}
