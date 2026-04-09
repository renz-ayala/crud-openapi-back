package com.sunarp.consultasreplaqueamiento.presentation.controller;

import com.sunarp.consultasreplaqueamiento.presentation.dtos.PlacaRequest;
import com.sunarp.consultasreplaqueamiento.presentation.dtos.PlacaResponse;
import com.sunarp.consultasreplaqueamiento.service.interfaces.CaptchaService;
import com.sunarp.consultasreplaqueamiento.service.interfaces.PlacaService;
import com.sunarp.consultasreplaqueamiento.service.interfaces.ReporteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/placas")
@RequiredArgsConstructor
public class PlacaController {

    private final PlacaService placaService;
    private final ReporteService reporteService;
    private final CaptchaService captchaService;

    @PostMapping(value = "/verificar", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PlacaResponse> verificar(@RequestBody PlacaRequest data) {
        if(!captchaService.verifyCaptcha(data.idTransaction())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    PlacaResponse.error(data.numPlaca(), "El captcha es inválido/expirado", HttpStatus.BAD_REQUEST)
            );
        }
        PlacaResponse response = placaService.getDatosPlaca(data.numPlaca());
        return switch (response.status()) {
            case OK -> ResponseEntity.ok(response);
            case NOT_FOUND -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            case BAD_REQUEST -> ResponseEntity.badRequest().body(response);
            case INTERNAL_SERVER_ERROR -> ResponseEntity.internalServerError().body(response);
            default -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        };
    }

    @PostMapping(value = "/obtener-reporte", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> getReporte(@RequestBody PlacaRequest data){
        try{
            return ResponseEntity.ok(reporteService.getRepotePlaca(data.numPlaca()));
        }catch(Exception e){
            log.error("Error en el reporte", e);
            return ResponseEntity.internalServerError().build();
        }

    }
}
