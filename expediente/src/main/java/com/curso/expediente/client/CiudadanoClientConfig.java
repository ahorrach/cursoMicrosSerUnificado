package com.curso.expediente.client;

import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;

public class CiudadanoClientConfig {

    @Bean
    public ErrorDecoder errorDecoder() {
        return new CiudadanoErrorDecoder();
    }
}