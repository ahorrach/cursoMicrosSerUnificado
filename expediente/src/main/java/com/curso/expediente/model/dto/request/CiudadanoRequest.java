package com.curso.expediente.model.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CiudadanoRequest {
    private String nombre;
    private String apellido1;
    private String apellido2;
    private String dniNie;
    private String email;
}
