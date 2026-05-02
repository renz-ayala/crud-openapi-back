package com.sunarp.consultasreplaqueamiento.service.implementation;

import com.sunarp.consultasreplaqueamiento.presentation.dtos.PlacaResponse;
import com.sunarp.consultasreplaqueamiento.service.interfaces.PlacaService;
import com.sunarp.consultasreplaqueamiento.service.interfaces.ReporteService;
import lombok.RequiredArgsConstructor;
import net.sf.jasperreports.engine.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.Map;
import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class ReporteServiceImpl implements ReporteService{

    private final PlacaService placaService;

    @Override
    public byte[] getRepotePlaca(String numeroPlaca) throws Exception {
        PlacaResponse respuesta = placaService.getDatosPlaca(numeroPlaca);
        if (respuesta != null && respuesta.status() == HttpStatus.OK) {

            Map<String, Object> parameters = getStringObjectMap(respuesta);

            InputStream reportStream = getClass().getClassLoader().getResourceAsStream("report/placa.jrxml");
            JasperReport jasperReport = JasperCompileManager.compileReport(reportStream);

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());

            return JasperExportManager.exportReportToPdf(jasperPrint);
        }
        throw new Exception();
    }

    private static Map<String, Object> getStringObjectMap(PlacaResponse respuesta) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("numPlaca", respuesta.numPlaca());
        parameters.put("marca", respuesta.marca());
        parameters.put("modelo", respuesta.modelo());
        parameters.put("color", respuesta.color());
        parameters.put("propietario", respuesta.propietario());
        parameters.put("observaciones", respuesta.observaciones());
        parameters.put("estado", respuesta.estado());
        parameters.put("anioFabricacion", respuesta.anioFabricacion());
        parameters.put("fechaRegistro", respuesta.fechaRegistro());
        return parameters;
    }
}
