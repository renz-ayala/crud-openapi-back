package com.sunarp.consultasreplaqueamiento.repository;

import com.sunarp.consultasreplaqueamiento.repository.entities.Placa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlacaRepository extends JpaRepository<Placa, String> {
    Optional<Placa> findByNumeroPlaca(String numero);
}
