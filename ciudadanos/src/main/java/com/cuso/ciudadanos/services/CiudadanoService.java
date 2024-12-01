package com.cuso.ciudadanos.services;

import com.cuso.ciudadanos.model.dto.CiudadanoDTO;
import com.cuso.ciudadanos.model.entity.CiudadanoEntity;
import com.cuso.ciudadanos.model.mapper.CiudadanoMapper;
import com.cuso.ciudadanos.model.request.CiudadanoRequest;
import com.cuso.ciudadanos.repository.CiudadanoRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Objects;

@Service
@Validated
public class CiudadanoService {

    private final CiudadanoRepository ciudadanoRepository;
    private final CiudadanoMapper ciudadanoMapper;

    public CiudadanoService(CiudadanoRepository ciudadanoRepository, CiudadanoMapper ciudadanoMapper) {
        this.ciudadanoRepository = ciudadanoRepository;
        this.ciudadanoMapper = ciudadanoMapper;
    }

    public List<CiudadanoDTO> findAll() {
        return ciudadanoRepository.findAll().stream()
                .map(ciudadanoMapper::toDTO)
                .toList();
    }

    public CiudadanoDTO findById(Long id) {
        return ciudadanoRepository.findById(id)
                .map(ciudadanoMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("Ciudadano no encontrado"));
    }

    public long createCiudadanoIfnotExist(@Valid CiudadanoRequest ciudadano) {
        CiudadanoEntity ciudadanoEntityExist = ciudadanoRepository.getByDniNie(ciudadano.getDniNie());
        if (Objects.nonNull(ciudadanoEntityExist)) {
            return ciudadanoEntityExist.getCiudadanoId();
        }
        CiudadanoDTO ciudadanoDTO = ciudadanoMapper.toCiudadanoDTO(ciudadano);
        CiudadanoEntity ciudadanoEntity = ciudadanoMapper.toEntity(ciudadanoDTO);
        return ciudadanoMapper.toDTO( ciudadanoRepository.save(ciudadanoEntity)).getCiudadanoId();
    }

    public CiudadanoDTO save(@Valid CiudadanoDTO ciudadanoDTO) {
        CiudadanoEntity ciudadano = ciudadanoMapper.toEntity(ciudadanoDTO);
        ciudadano.setCiudadanoId(null);
        return ciudadanoMapper.toDTO( ciudadanoRepository.save(ciudadano));
    }

    public void deleteById(Long id) {
        ciudadanoRepository.deleteById(id);
    }
}

