package com.cuso.ciudadanos.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

@Entity
@Table(name = "CIUDADANOS")
@Data
public class CiudadanoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CIUDADANO_ID")
    private Long ciudadanoId;

    @NotNull
    @Size(max = 20)
    @Column(name = "NOMBRE", nullable = false, length = 20)
    private String nombre;

    @NotNull
    @Size(max = 30)
    @Column(name = "APELLIDO1", nullable = false, length = 30)
    private String apellido1;

    @NotNull
    @Size(max = 30)
    @Column(name = "APELLIDO2", nullable = false, length = 30)
    private String apellido2;

    @NotNull
    @Size(max = 9, min = 9)
    @Column(name = "DNINIE", nullable = false, length = 9, unique = true)
    private String dniNie;

    @NotNull
    @Email
    @Size(max = 40)
    @Column(name = "EMAIL", nullable = false, length = 40, unique = true)
    private String email;


}

