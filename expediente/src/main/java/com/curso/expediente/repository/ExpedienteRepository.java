package com.curso.expediente.repository;

import com.curso.expediente.model.entity.ExpedienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpedienteRepository extends JpaRepository<ExpedienteEntity, Long> {
    // Puedes agregar consultas personalizadas si es necesario

    List<ExpedienteEntity> getByDni(String dni);

    List<ExpedienteEntity> getByTipoPrestacion(Integer tipoPrestacion);

    ExpedienteEntity getByDniAndTipoPrestacion(String dni,Integer tipoPrestacion);
}
