package com.curso.expediente.service;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.curso.expediente.model.dto.ExpedienteDTO;
import com.curso.expediente.model.entity.ExpedienteEntity;
import com.curso.expediente.model.mapper.ExpedienteMapper;
import com.curso.expediente.repository.ExpedienteRepository;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {ExpedienteService.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class ExpedienteServiceDiffblueTest {
    @MockBean
    private ExpedienteMapper expedienteMapper;

    @MockBean
    private ExpedienteRepository expedienteRepository;

    @Autowired
    private ExpedienteService expedienteService;

    /**
     * Test {@link ExpedienteService#getAllExpedientes()}.
     * <p>
     * Method under test: {@link ExpedienteService#getAllExpedientes()}
     */
    @Test
    @DisplayName("Test getAllExpedientes()")
    void testGetAllExpedientes() {
        // Arrange
        when(expedienteMapper.entityListToDtoList(Mockito.<List<ExpedienteEntity>>any())).thenReturn(new ArrayList<>());
        when(expedienteRepository.findAll()).thenReturn(new ArrayList<>());

        // Act
        List<ExpedienteDTO> actualAllExpedientes = expedienteService.getAllExpedientes();

        // Assert
        verify(expedienteMapper).entityListToDtoList(isA(List.class));
        verify(expedienteRepository).findAll();
        assertTrue(actualAllExpedientes.isEmpty());
    }

    /**
     * Test {@link ExpedienteService#getExpedientesByDNI(String)}.
     * <p>
     * Method under test: {@link ExpedienteService#getExpedientesByDNI(String)}
     */
    @Test
    @DisplayName("Test getExpedientesByDNI(String)")
    void testGetExpedientesByDNI() {
        // Arrange
        when(expedienteMapper.entityListToDtoList(Mockito.<List<ExpedienteEntity>>any())).thenReturn(new ArrayList<>());
        when(expedienteRepository.getByDni(Mockito.<String>any())).thenReturn(new ArrayList<>());

        // Act
        List<ExpedienteDTO> actualExpedientesByDNI = expedienteService.getExpedientesByDNI("Dni");

        // Assert
        verify(expedienteMapper).entityListToDtoList(isA(List.class));
        verify(expedienteRepository).getByDni(eq("Dni"));
        assertTrue(actualExpedientesByDNI.isEmpty());
    }

    /**
     * Test {@link ExpedienteService#getExpedientesByTipoPrestacion(Integer)}.
     * <p>
     * Method under test:
     * {@link ExpedienteService#getExpedientesByTipoPrestacion(Integer)}
     */
    @Test
    @DisplayName("Test getExpedientesByTipoPrestacion(Integer)")
    void testGetExpedientesByTipoPrestacion() {
        // Arrange
        when(expedienteMapper.entityListToDtoList(Mockito.<List<ExpedienteEntity>>any())).thenReturn(new ArrayList<>());
        when(expedienteRepository.getByTipoPrestacion(Mockito.<Integer>any())).thenReturn(new ArrayList<>());

        // Act
        List<ExpedienteDTO> actualExpedientesByTipoPrestacion = expedienteService.getExpedientesByTipoPrestacion(1);

        // Assert
        verify(expedienteMapper).entityListToDtoList(isA(List.class));
        verify(expedienteRepository).getByTipoPrestacion(eq(1));
        assertTrue(actualExpedientesByTipoPrestacion.isEmpty());
    }

    /**
     * Test {@link ExpedienteService#getExpedienteById(Long)}.
     * <ul>
     *   <li>Then return not Present.</li>
     * </ul>
     * <p>
     * Method under test: {@link ExpedienteService#getExpedienteById(Long)}
     */
    @Test
    @DisplayName("Test getExpedienteById(Long); then return not Present")
    void testGetExpedienteById_thenReturnNotPresent() {
        // Arrange
        Optional<ExpedienteEntity> emptyResult = Optional.empty();
        when(expedienteRepository.findById(Mockito.<Long>any())).thenReturn(emptyResult);

        // Act
        Optional<ExpedienteDTO> actualExpedienteById = expedienteService.getExpedienteById(1L);

        // Assert
        verify(expedienteRepository).findById(eq(1L));
        assertFalse(actualExpedienteById.isPresent());
    }

    /**
     * Test {@link ExpedienteService#getExpedienteById(Long)}.
     * <ul>
     *   <li>Then return Present.</li>
     * </ul>
     * <p>
     * Method under test: {@link ExpedienteService#getExpedienteById(Long)}
     */
    @Test
    @DisplayName("Test getExpedienteById(Long); then return Present")
    void testGetExpedienteById_thenReturnPresent() {
        // Arrange
        ExpedienteDTO expedienteDTO = new ExpedienteDTO();
        expedienteDTO.setCreateAt(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        expedienteDTO.setDni("Dni");
        expedienteDTO.setId(1L);
        expedienteDTO.setNotas("Notas");
        expedienteDTO.setTipoPrestacion(1);
        when(expedienteMapper.toDTO(Mockito.<ExpedienteEntity>any())).thenReturn(expedienteDTO);

        ExpedienteEntity expedienteEntity = new ExpedienteEntity();
        expedienteEntity.setCreateAt(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        expedienteEntity.setDni("Dni");
        expedienteEntity.setId(1L);
        expedienteEntity.setNotas("Notas");
        expedienteEntity.setTipoPrestacion(1);
        Optional<ExpedienteEntity> ofResult = Optional.of(expedienteEntity);
        when(expedienteRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        // Act
        Optional<ExpedienteDTO> actualExpedienteById = expedienteService.getExpedienteById(1L);

        // Assert
        verify(expedienteMapper).toDTO(isA(ExpedienteEntity.class));
        verify(expedienteRepository).findById(eq(1L));
        assertTrue(actualExpedienteById.isPresent());
        assertSame(expedienteDTO, actualExpedienteById.get());
    }

    /**
     * Test {@link ExpedienteService#createExpediente(ExpedienteDTO)}.
     * <p>
     * Method under test: {@link ExpedienteService#createExpediente(ExpedienteDTO)}
     */
    @Test
    @DisplayName("Test createExpediente(ExpedienteDTO)")
    void testCreateExpediente() {
        // Arrange
        ExpedienteEntity expedienteEntity = new ExpedienteEntity();
        expedienteEntity.setCreateAt(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        expedienteEntity.setDni("Dni");
        expedienteEntity.setId(1L);
        expedienteEntity.setNotas("Notas");
        expedienteEntity.setTipoPrestacion(1);

        ExpedienteDTO expedienteDTO = new ExpedienteDTO();
        expedienteDTO.setCreateAt(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        expedienteDTO.setDni("Dni");
        expedienteDTO.setId(1L);
        expedienteDTO.setNotas("Notas");
        expedienteDTO.setTipoPrestacion(1);
        when(expedienteMapper.toDTO(Mockito.<ExpedienteEntity>any())).thenReturn(expedienteDTO);
        when(expedienteMapper.toEntity(Mockito.<ExpedienteDTO>any())).thenReturn(expedienteEntity);

        ExpedienteEntity expedienteEntity2 = new ExpedienteEntity();
        expedienteEntity2
                .setCreateAt(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        expedienteEntity2.setDni("Dni");
        expedienteEntity2.setId(1L);
        expedienteEntity2.setNotas("Notas");
        expedienteEntity2.setTipoPrestacion(1);
        when(expedienteRepository.save(Mockito.<ExpedienteEntity>any())).thenReturn(expedienteEntity2);

        ExpedienteDTO expediente = new ExpedienteDTO();
        expediente.setCreateAt(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        expediente.setDni("Dni");
        expediente.setId(1L);
        expediente.setNotas("Notas");
        expediente.setTipoPrestacion(1);

        // Act
        ExpedienteDTO actualCreateExpedienteResult = expedienteService.createExpediente(expediente);

        // Assert
        verify(expedienteMapper).toDTO(isA(ExpedienteEntity.class));
        verify(expedienteMapper).toEntity(isA(ExpedienteDTO.class));
        verify(expedienteRepository).save(isA(ExpedienteEntity.class));
        assertSame(expedienteDTO, actualCreateExpedienteResult);
    }

    /**
     * Test {@link ExpedienteService#updateExpediente(Long, ExpedienteEntity)}.
     * <ul>
     *   <li>Then return {@link ExpedienteDTO} (default constructor).</li>
     * </ul>
     * <p>
     * Method under test:
     * {@link ExpedienteService#updateExpediente(Long, ExpedienteEntity)}
     */
    @Test
    @DisplayName("Test updateExpediente(Long, ExpedienteEntity); then return ExpedienteDTO (default constructor)")
    void testUpdateExpediente_thenReturnExpedienteDTO() {
        // Arrange
        ExpedienteDTO expedienteDTO = new ExpedienteDTO();
        expedienteDTO.setCreateAt(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        expedienteDTO.setDni("Dni");
        expedienteDTO.setId(1L);
        expedienteDTO.setNotas("Notas");
        expedienteDTO.setTipoPrestacion(1);
        when(expedienteMapper.toDTO(Mockito.<ExpedienteEntity>any())).thenReturn(expedienteDTO);

        ExpedienteEntity expedienteEntity = new ExpedienteEntity();
        expedienteEntity.setCreateAt(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        expedienteEntity.setDni("Dni");
        expedienteEntity.setId(1L);
        expedienteEntity.setNotas("Notas");
        expedienteEntity.setTipoPrestacion(1);
        when(expedienteRepository.save(Mockito.<ExpedienteEntity>any())).thenReturn(expedienteEntity);
        when(expedienteRepository.existsById(Mockito.<Long>any())).thenReturn(true);

        ExpedienteEntity expediente = new ExpedienteEntity();
        expediente.setCreateAt(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        expediente.setDni("Dni");
        expediente.setId(1L);
        expediente.setNotas("Notas");
        expediente.setTipoPrestacion(1);

        // Act
        ExpedienteDTO actualUpdateExpedienteResult = expedienteService.updateExpediente(1L, expediente);

        // Assert
        verify(expedienteMapper).toDTO(isA(ExpedienteEntity.class));
        verify(expedienteRepository).existsById(eq(1L));
        verify(expedienteRepository).save(isA(ExpedienteEntity.class));
        assertSame(expedienteDTO, actualUpdateExpedienteResult);
    }

    /**
     * Test {@link ExpedienteService#updateExpediente(Long, ExpedienteEntity)}.
     * <ul>
     *   <li>Then return {@code null}.</li>
     * </ul>
     * <p>
     * Method under test:
     * {@link ExpedienteService#updateExpediente(Long, ExpedienteEntity)}
     */
    @Test
    @DisplayName("Test updateExpediente(Long, ExpedienteEntity); then return 'null'")
    void testUpdateExpediente_thenReturnNull() {
        // Arrange
        when(expedienteRepository.existsById(Mockito.<Long>any())).thenReturn(false);

        ExpedienteEntity expediente = new ExpedienteEntity();
        expediente.setCreateAt(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        expediente.setDni("Dni");
        expediente.setId(1L);
        expediente.setNotas("Notas");
        expediente.setTipoPrestacion(1);

        // Act
        ExpedienteDTO actualUpdateExpedienteResult = expedienteService.updateExpediente(1L, expediente);

        // Assert
        verify(expedienteRepository).existsById(eq(1L));
        assertNull(actualUpdateExpedienteResult);
    }

    /**
     * Test {@link ExpedienteService#deleteExpediente(Long)}.
     * <ul>
     *   <li>Then return {@code false}.</li>
     * </ul>
     * <p>
     * Method under test: {@link ExpedienteService#deleteExpediente(Long)}
     */
    @Test
    @DisplayName("Test deleteExpediente(Long); then return 'false'")
    void testDeleteExpediente_thenReturnFalse() {
        // Arrange
        when(expedienteRepository.existsById(Mockito.<Long>any())).thenReturn(false);

        // Act
        boolean actualDeleteExpedienteResult = expedienteService.deleteExpediente(1L);

        // Assert
        verify(expedienteRepository).existsById(eq(1L));
        assertFalse(actualDeleteExpedienteResult);
    }

    /**
     * Test {@link ExpedienteService#deleteExpediente(Long)}.
     * <ul>
     *   <li>Then return {@code true}.</li>
     * </ul>
     * <p>
     * Method under test: {@link ExpedienteService#deleteExpediente(Long)}
     */
    @Test
    @DisplayName("Test deleteExpediente(Long); then return 'true'")
    void testDeleteExpediente_thenReturnTrue() {
        // Arrange
        doNothing().when(expedienteRepository).deleteById(Mockito.<Long>any());
        when(expedienteRepository.existsById(Mockito.<Long>any())).thenReturn(true);

        // Act
        boolean actualDeleteExpedienteResult = expedienteService.deleteExpediente(1L);

        // Assert
        verify(expedienteRepository).deleteById(eq(1L));
        verify(expedienteRepository).existsById(eq(1L));
        assertTrue(actualDeleteExpedienteResult);
    }
}
