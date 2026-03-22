package com.sunarp.consultasreplaqueamiento.controller;

import com.sunarp.consultasreplaqueamiento.model.dtos.PlacaRequest;
import com.sunarp.consultasreplaqueamiento.model.dtos.PlacaResponse;
import com.sunarp.consultasreplaqueamiento.model.entities.Placa;
import com.sunarp.consultasreplaqueamiento.service.interfaces.PlacaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/placas")
public class PlacaController {

    private final PlacaService placaService;

    public PlacaController(PlacaService placaService) {
        this.placaService = placaService;
    }

    @PostMapping(value = "/verificar", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PlacaResponse> verificar(@RequestBody PlacaRequest data) {
        PlacaResponse response = placaService.verificarReplaqueo(data.numPlaca());
        return switch (response.status()) {
            case OK -> ResponseEntity.ok(response);
            case NOT_FOUND -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            case BAD_REQUEST -> ResponseEntity.badRequest().body(response);
            case INTERNAL_SERVER_ERROR -> ResponseEntity.internalServerError().body(response);
            default -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        };
    }
}
