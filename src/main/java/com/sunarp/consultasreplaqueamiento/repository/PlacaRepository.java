package com.sunarp.consultasreplaqueamiento.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sunarp.consultasreplaqueamiento.model.entities.Placa;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlacaRepository extends JpaRepository<Placa, String> {
    Optional<Placa> findByNumeroPlaca(String numero);
}
