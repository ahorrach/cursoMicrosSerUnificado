package com.cuso.ciudadanos.model.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CiudadanoDTO {

   private Long ciudadanoId;

    @NotNull(message = "El nombre no puede ser nulo")
    @Size(max = 20, message = "El nombre debe tener como máximo 20 caracteres")
    private String nombre;

    @NotNull(message = "El primer apellido no puede ser nulo")
    @Size(max = 30, message = "El primer apellido debe tener como máximo 30 caracteres")
    private String apellido1;

    @NotNull(message = "El segundo apellido no puede ser nulo")
    @Size(max = 30, message = "El segundo apellido debe tener como máximo 30 caracteres")
    private String apellido2;

    @NotNull(message = "El DNI/NIE no puede ser nulo")
    @Size(min = 9, max = 9, message = "El DNI/NIE debe tener exactamente 9 caracteres")
    private String dniNie;

    @NotNull(message = "El email no puede ser nulo")
    @Email(message = "El formato del email no es válido")
    @Pattern(regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$", message = "El formato del email no es válido")
    @Size(max = 40, message = "El email debe tener como máximo 40 caracteres")
    private String email;
}
