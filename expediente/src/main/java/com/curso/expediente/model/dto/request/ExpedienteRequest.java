package com.curso.expediente.model.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ExpedienteRequest {

        private Integer tipoPrestacion;
        private String notas;
        private Date createAt;
        private String dni;

}
