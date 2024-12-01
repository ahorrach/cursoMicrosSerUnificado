package com.curso.expediente.client;

import com.curso.expediente.model.dto.request.CiudadanoRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "ciudadanoClient", url = "http://localhost:9090/ciudadanos/api/ciudadanos")
public interface CiudadanoClient {

    @PostMapping("/altaCiudadano")
    Long altaCiudadano(@RequestBody CiudadanoRequest ciudadanoRequest);
}

