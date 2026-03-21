package com.sunarp.consultasreplaqueamiento.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sunarp.consultasreplaqueamiento.model.Placa;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface PlacaRepository extends JpaRepository<Placa, String> {
    boolean existsByNumero(String numero);
}
