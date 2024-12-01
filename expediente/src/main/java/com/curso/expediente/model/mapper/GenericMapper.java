package com.curso.expediente.model.mapper;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface GenericMapper<Entity,DTO> {

    DTO toDTO(Entity entity);

    Entity toEntity(DTO dto);

    List<DTO> entityListToDtoList(List<Entity> entityList);


}
