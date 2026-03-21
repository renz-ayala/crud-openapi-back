package com.sunarp.consultasreplaqueamiento.controller;

import com.sunarp.consultasreplaqueamiento.model.Response;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import com.sunarp.consultasreplaqueamiento.service.PlacaService;

@RestController
@RequestMapping("/placas")
public class PlacaController {

    private final PlacaService placaService;

    public PlacaController(PlacaService placaService) {
        this.placaService = placaService;
    }

    @GetMapping(value = "/placa/{numero}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Response verificar(@PathVariable String numero) {
        return placaService.verificarReplaqueo(numero);
    }
}
