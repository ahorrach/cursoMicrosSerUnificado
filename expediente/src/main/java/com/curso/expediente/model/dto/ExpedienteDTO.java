package com.curso.expediente.model.dto;

import lombok.Data;

import java.util.Date;

@Data
public class ExpedienteDTO {
    private Long id;
    private Integer tipoPrestacion;
    private String notas;
    private Date createAt;
    private String dni;

}
