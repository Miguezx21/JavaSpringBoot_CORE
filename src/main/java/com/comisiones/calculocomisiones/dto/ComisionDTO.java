package com.comisiones.calculocomisiones.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class ComisionDTO {
    private String vendedor;
    private BigDecimal totalVendido;
    private String reglaAplicada;
    private BigDecimal comisionCalculada;
}
