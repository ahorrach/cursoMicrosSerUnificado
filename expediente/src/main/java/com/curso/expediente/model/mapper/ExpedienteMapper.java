package com.curso.expediente.model.mapper;

import com.curso.expediente.model.dto.ExpedienteDTO;
import com.curso.expediente.model.dto.request.ExpedienteRequest;
import com.curso.expediente.model.entity.ExpedienteEntity;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface ExpedienteMapper extends GenericMapper<ExpedienteEntity, ExpedienteDTO> {


    @Mapping(target = "id", ignore = true)
    ExpedienteDTO toExpedienteDTO(ExpedienteRequest expedienteRequest);


}
