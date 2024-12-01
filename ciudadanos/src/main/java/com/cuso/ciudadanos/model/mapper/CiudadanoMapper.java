package com.cuso.ciudadanos.model.mapper;



import com.cuso.ciudadanos.model.dto.CiudadanoDTO;
import com.cuso.ciudadanos.model.entity.CiudadanoEntity;
import com.cuso.ciudadanos.model.request.CiudadanoRequest;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface CiudadanoMapper {

    CiudadanoDTO toDTO(CiudadanoEntity ciudadano);

    CiudadanoEntity toEntity(CiudadanoDTO ciudadanoDTO);

    @Mapping(target = "ciudadanoId", ignore = true)
    CiudadanoDTO toCiudadanoDTO(CiudadanoRequest expedienteRequest);
}

