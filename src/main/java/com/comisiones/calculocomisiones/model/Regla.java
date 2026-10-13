package com.comisiones.calculocomisiones.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Entity
@Table(name = "reglas")
@Data
@NoArgsConstructor
public class Regla {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private BigDecimal porcentaje;

    @Column(nullable = false)
    private BigDecimal montoMinimo;

    public Regla(String nombre, BigDecimal porcentaje, BigDecimal montoMinimo) {
        this.nombre = nombre;
        this.porcentaje = porcentaje;
        this.montoMinimo = montoMinimo;
    }
}
