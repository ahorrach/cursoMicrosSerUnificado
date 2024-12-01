package com.curso.expediente.client;

import feign.Response;
import feign.codec.ErrorDecoder;

public class CiudadanoErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String methodKey, Response response) {
        if (response.status() != 200) {
            return new RuntimeException("Error en el servicio Ciudadano. Código de estado: " + response.status());
        }
        return new Exception("Error desconocido");
    }
}

