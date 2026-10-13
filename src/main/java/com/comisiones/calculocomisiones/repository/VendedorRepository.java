package com.comisiones.calculocomisiones.repository;

import com.comisiones.calculocomisiones.model.Vendedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VendedorRepository extends JpaRepository<Vendedor, Long> {}
