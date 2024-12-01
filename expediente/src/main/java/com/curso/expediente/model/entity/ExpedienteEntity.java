package com.curso.expediente.model.entity;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.Data;


import java.util.Date;

@Data
@Entity
@Table(name = "EXPEDIENTE")
public class ExpedienteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //
    @Column(name = "ID")
    private Long id;

    @Column(name = "TIPO_PRESTACION", nullable = false)
    private Integer tipoPrestacion;

    @Column(name = "NOTAS", length = 80)
    private String notas;

    @Column(name = "CREATE_AT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createAt;

    @Column(name = "DNI", length = 9)
    private String dni;


}
