package com.sunarp.consultasreplaqueamiento.service.implementation;
import com.sunarp.consultasreplaqueamiento.model.dtos.PlacaResponse;

import com.sunarp.consultasreplaqueamiento.service.interfaces.PlacaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.sunarp.consultasreplaqueamiento.repository.PlacaRepository;

@Slf4j
@Service
public class PlacaServiceImpl implements PlacaService {

    private final PlacaRepository placaRepository;

    public PlacaServiceImpl(PlacaRepository placaRepository) {
        this.placaRepository = placaRepository;
    }

    @Override
    public PlacaResponse verificarReplaqueo(String numero) {

        String response;

        if (numero == null || numero.trim().length() < 4 || numero.trim().length() > 7) {
            response = "El formato de la placa es inválido";
            return PlacaResponse.error(numero, response, HttpStatus.BAD_REQUEST);
        }

        try{

            String errorResponse = "La placa no existe en los registros";
            String successResponse = "Operación correcta";

            HttpStatus statusOk = HttpStatus.OK;
            HttpStatus statusNotFound = HttpStatus.NOT_FOUND;

            return placaRepository.findByNumeroPlaca(numero.toUpperCase().trim())
                    .map(p -> new PlacaResponse(
                            p.getNumeroPlaca(),
                            p.getMarca(),
                            p.getModelo(),
                            p.getColor(),
                            p.getAnioFabricacion(),
                            p.getPropietarioDni(),
                            p.getEstado(),
                            p.getFechaRegistro(),
                            p.getObservaciones(),
                            successResponse,
                            statusOk
                    ))
                    .orElseGet(() -> PlacaResponse.error(numero, errorResponse, statusNotFound));

        } catch (Exception e) {
            log.error("Error crítico al consultar placa {}", numero, e);
            response = "Hubo un error en la operación";
            return PlacaResponse.error(numero, response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
