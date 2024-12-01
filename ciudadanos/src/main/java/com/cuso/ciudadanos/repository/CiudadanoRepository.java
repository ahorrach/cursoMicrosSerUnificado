package com.cuso.ciudadanos.repository;


import com.cuso.ciudadanos.model.entity.CiudadanoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CiudadanoRepository extends JpaRepository<CiudadanoEntity, Long> {

    CiudadanoEntity getByDniNie(String dni);
}
