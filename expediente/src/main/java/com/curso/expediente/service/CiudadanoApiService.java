package com.curso.expediente.service;

import com.curso.expediente.client.CiudadanoClient;
import com.curso.expediente.model.dto.request.CiudadanoRequest;
import org.springframework.stereotype.Service;

@Service
public class CiudadanoApiService {

    private final CiudadanoClient ciudadanoClient;


    public CiudadanoApiService(CiudadanoClient ciudadanoClient) {
        this.ciudadanoClient = ciudadanoClient;
    }

    public Long altaCiudadano(CiudadanoRequest ciudadanoRequest) {
        return ciudadanoClient.altaCiudadano(ciudadanoRequest);
    }


}
