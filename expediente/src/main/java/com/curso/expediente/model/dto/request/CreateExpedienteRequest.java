package com.curso.expediente.model.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class CreateExpedienteRequest {

        private ExpedienteRequest expediente;
        private CiudadanoRequest ciudadano;

}
