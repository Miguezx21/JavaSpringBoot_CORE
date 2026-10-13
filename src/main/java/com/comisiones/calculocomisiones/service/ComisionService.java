package com.comisiones.calculocomisiones.service;

import com.comisiones.calculocomisiones.dto.ComisionDTO;
import java.time.LocalDate;
import java.util.List;

public interface ComisionService {
    List<ComisionDTO> calcularComisiones(LocalDate fechaInicio, LocalDate fechaFin);
}
