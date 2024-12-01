package com.cuso.ciudadanos.model.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
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
