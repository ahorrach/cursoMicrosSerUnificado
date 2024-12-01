package com.curso.expediente.service;

import com.curso.expediente.model.dto.ExpedienteDTO;
import com.curso.expediente.model.dto.request.CreateExpedienteRequest;
import com.curso.expediente.model.entity.ExpedienteEntity;
import com.curso.expediente.model.mapper.ExpedienteMapper;
import com.curso.expediente.repository.ExpedienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ExpedienteService {

    private final CiudadanoApiService ciudadanoApiService;

    private final  ExpedienteRepository expedienteRepository;

    private final  ExpedienteMapper expedienteMapper;

    public ExpedienteService(CiudadanoApiService ciudadanoApiService, ExpedienteRepository expedienteRepository, ExpedienteMapper expedienteMapper) {
        this.ciudadanoApiService = ciudadanoApiService;
        this.expedienteRepository = expedienteRepository;
        this.expedienteMapper = expedienteMapper;
    }

    public List<ExpedienteDTO> getAllExpedientes() {
        return expedienteMapper.entityListToDtoList(expedienteRepository.findAll());
    }

    public List<ExpedienteDTO> getExpedientesByDNI(String dni) {
        return expedienteMapper.entityListToDtoList(expedienteRepository.getByDni(dni));
    }

    public List<ExpedienteDTO> getExpedientesByTipoPrestacion(Integer tipoPrestacion) {
        return expedienteMapper.entityListToDtoList(expedienteRepository.getByTipoPrestacion(tipoPrestacion));
    }

    public Optional<ExpedienteDTO> getExpedienteById(Long id) {
        Optional<ExpedienteEntity> expedienteEntityOptional =  expedienteRepository.findById(id);
        return expedienteEntityOptional.isPresent()
                ?Optional.of(expedienteMapper.toDTO(expedienteEntityOptional.get()))
                :Optional.empty();
    }

    public ExpedienteDTO createExpediente(CreateExpedienteRequest expediente) {
        // TODO comprobamos si existe
        ExpedienteEntity expedienteEntityExistent = expedienteRepository.getByDniAndTipoPrestacion(expediente.getExpediente().getDni(),expediente.getExpediente().getTipoPrestacion());
        if (Objects.nonNull(expedienteEntityExistent)) {
           throw new RuntimeException("Expedietne ya existe");
        } else {
            ciudadanoApiService.altaCiudadano(expediente.getCiudadano());
        }
        ExpedienteDTO expedienteDTO = expedienteMapper.toExpedienteDTO(expediente.getExpediente());
        ExpedienteEntity expedienteEntity = expedienteMapper.toEntity(expedienteDTO);

        return expedienteMapper.toDTO(expedienteRepository.save(expedienteEntity));
    }

    public ExpedienteDTO updateExpediente(Long id, ExpedienteEntity expediente) {
        if (expedienteRepository.existsById(id)) {
            expediente.setId(id);
            return expedienteMapper.toDTO(expedienteRepository.save(expediente));
        }
        return null; // O manejar con una excepción personalizada
    }

    public boolean deleteExpediente(Long id) {
        if (expedienteRepository.existsById(id)) {
            expedienteRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
