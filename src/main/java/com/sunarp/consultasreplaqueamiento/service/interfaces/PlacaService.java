package com.sunarp.consultasreplaqueamiento.service.interfaces;

import com.sunarp.consultasreplaqueamiento.model.dtos.PlacaResponse;

public interface PlacaService {
    PlacaResponse verificarReplaqueo(String numero);
}
