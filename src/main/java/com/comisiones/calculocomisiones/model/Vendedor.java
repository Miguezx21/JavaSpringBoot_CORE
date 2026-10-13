package com.comisiones.calculocomisiones.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Entity
@Table(name = "vendedores")
@Data
@NoArgsConstructor
public class Vendedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String nombre;

    @OneToMany(mappedBy = "vendedor")
    private List<Venta> ventas;

    public Vendedor(String nombre) {
        this.nombre = nombre;
    }
}
