package com.sunarp.consultasreplaqueamiento.service;
import com.sunarp.consultasreplaqueamiento.model.Placa;
import com.sunarp.consultasreplaqueamiento.model.Response;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sunarp.consultasreplaqueamiento.repository.PlacaRepository;

@Service
public class PlacaService {

    private final PlacaRepository placaRepository;

    public PlacaService(PlacaRepository placaRepository) {
        this.placaRepository = placaRepository;
    }

    public Response verificarReplaqueo(String numero) {
        Response resp = new Response();
        if (numero == null || numero.trim().length() < 4 || numero.trim().length() > 7) {
            resp.setResp("Error: Formato inválido.");
        }else{
            boolean existe = placaRepository.existsByNumero(numero.toUpperCase().trim());
            String respString = existe
                    ? "Placa [" + numero.toUpperCase() + "], procedimiento de cambio de placa habilitado solo de manera presencial."
                    : "Placa [" + numero.toUpperCase() + "], procedimiento de cambio de placa habilitado vía SID-Sunarp.";

            resp.setResp(respString);
        }
        return resp;
    }
}
