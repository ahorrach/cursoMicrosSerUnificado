package com.curso.expediente.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CiudadanoDTO {
    private Long ciudadanoId;
    private String nombre;
    private String apellido1;
    private String apellido2;
    private String dniNie;
    private String email;
}
