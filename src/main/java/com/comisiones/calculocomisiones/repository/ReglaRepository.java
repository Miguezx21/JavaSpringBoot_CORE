package com.comisiones.calculocomisiones.repository;

import com.comisiones.calculocomisiones.model.Regla;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ReglaRepository extends JpaRepository<Regla, Long> {

    // Ordenadas de mayor a menor montoMinimo → se aplica la primera que califica
    List<Regla> findAllByOrderByMontoMinimoDesc();
}
