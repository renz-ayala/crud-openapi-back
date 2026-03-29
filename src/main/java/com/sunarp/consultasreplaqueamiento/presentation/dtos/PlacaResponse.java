package com.sunarp.consultasreplaqueamiento.presentation.dtos;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public record PlacaResponse(
        String numPlaca,
        String marca,
        String modelo,
        String color,
        Integer anioFabricacion,
        String propietario,
        String estado,
        LocalDateTime fechaRegistro,
        String observaciones,
        String response,
        HttpStatus status
){
    public static PlacaResponse error(String numero, String mensaje, HttpStatus status) {
        return new PlacaResponse(numero, null, null, null, null, null, null, null, null, mensaje, status);
    }
}
