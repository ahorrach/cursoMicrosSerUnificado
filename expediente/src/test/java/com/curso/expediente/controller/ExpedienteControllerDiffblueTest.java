package com.curso.expediente.controller;

import static org.mockito.Mockito.when;

import com.curso.expediente.model.dto.ExpedienteDTO;
import com.curso.expediente.model.entity.ExpedienteEntity;
import com.curso.expediente.service.ExpedienteService;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.result.StatusResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {ExpedienteController.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class ExpedienteControllerDiffblueTest {
    @Autowired
    private ExpedienteController expedienteController;

    @MockBean
    private ExpedienteService expedienteService;

    /**
     * Test {@link ExpedienteController#getAllExpedientes()}.
     * <p>
     * Method under test: {@link ExpedienteController#getAllExpedientes()}
     */
    @Test
    @DisplayName("Test getAllExpedientes()")
    void testGetAllExpedientes() throws Exception {
        // Arrange
        when(expedienteService.getAllExpedientes()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/expedientes");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(expedienteController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Test {@link ExpedienteController#getExpedienteById(Long)}.
     * <ul>
     *   <li>Then status {@link StatusResultMatchers#isNotFound()}.</li>
     * </ul>
     * <p>
     * Method under test: {@link ExpedienteController#getExpedienteById(Long)}
     */
    @Test
    @DisplayName("Test getExpedienteById(Long); then status isNotFound()")
    void testGetExpedienteById_thenStatusIsNotFound() throws Exception {
        // Arrange
        Optional<ExpedienteDTO> emptyResult = Optional.empty();
        when(expedienteService.getExpedienteById(Mockito.<Long>any())).thenReturn(emptyResult);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/expedientes/{id}", 1L);

        // Act
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(expedienteController)
                .build()
                .perform(requestBuilder);

        // Assert
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    /**
     * Test {@link ExpedienteController#getExpedienteById(Long)}.
     * <ul>
     *   <li>Then status {@link StatusResultMatchers#isOk()}.</li>
     * </ul>
     * <p>
     * Method under test: {@link ExpedienteController#getExpedienteById(Long)}
     */
    @Test
    @DisplayName("Test getExpedienteById(Long); then status isOk()")
    void testGetExpedienteById_thenStatusIsOk() throws Exception {
        // Arrange
        ExpedienteDTO expedienteDTO = new ExpedienteDTO();
        expedienteDTO.setCreateAt(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        expedienteDTO.setDni("Dni");
        expedienteDTO.setId(1L);
        expedienteDTO.setNotas("Notas");
        expedienteDTO.setTipoPrestacion(1);
        Optional<ExpedienteDTO> ofResult = Optional.of(expedienteDTO);
        when(expedienteService.getExpedienteById(Mockito.<Long>any())).thenReturn(ofResult);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/expedientes/{id}", 1L);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(expedienteController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"id\":1,\"tipoPrestacion\":1,\"notas\":\"Notas\",\"createAt\":0,\"dni\":\"Dni\"}"));
    }

    /**
     * Test {@link ExpedienteController#getExpedienteByTipoPrestacion(Integer)}.
     * <p>
     * Method under test:
     * {@link ExpedienteController#getExpedienteByTipoPrestacion(Integer)}
     */
    @Test
    @DisplayName("Test getExpedienteByTipoPrestacion(Integer)")
    void testGetExpedienteByTipoPrestacion() throws Exception {
        // Arrange
        when(expedienteService.getExpedientesByTipoPrestacion(Mockito.<Integer>any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/expedientes/buscarPorTipoPrestacion/{tipoPrestacion}", 1);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(expedienteController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Test {@link ExpedienteController#getExpedienteByIDNI(String)}.
     * <p>
     * Method under test: {@link ExpedienteController#getExpedienteByIDNI(String)}
     */
    @Test
    @DisplayName("Test getExpedienteByIDNI(String)")
    void testGetExpedienteByIDNI() throws Exception {
        // Arrange
        when(expedienteService.getExpedientesByDNI(Mockito.<String>any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/expedientes/buscarPorDni/{dni}",
                "Dni");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(expedienteController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Test {@link ExpedienteController#createExpediente(ExpedienteDTO)}.
     * <p>
     * Method under test:
     * {@link ExpedienteController#createExpediente(ExpedienteDTO)}
     */
    @Test
    @DisplayName("Test createExpediente(ExpedienteDTO)")
    void testCreateExpediente() throws Exception {
        // Arrange
        ExpedienteDTO expedienteDTO = new ExpedienteDTO();
        expedienteDTO.setCreateAt(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        expedienteDTO.setDni("Dni");
        expedienteDTO.setId(1L);
        expedienteDTO.setNotas("Notas");
        expedienteDTO.setTipoPrestacion(1);
        when(expedienteService.createExpediente(Mockito.<ExpedienteDTO>any())).thenReturn(expedienteDTO);

        ExpedienteDTO expedienteDTO2 = new ExpedienteDTO();
        expedienteDTO2.setCreateAt(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        expedienteDTO2.setDni("Dni");
        expedienteDTO2.setId(1L);
        expedienteDTO2.setNotas("Notas");
        expedienteDTO2.setTipoPrestacion(1);
        String content = (new ObjectMapper()).writeValueAsString(expedienteDTO2);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/expedientes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(expedienteController)
                .build()
                .perform(requestBuilder);

        // Assert
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"id\":1,\"tipoPrestacion\":1,\"notas\":\"Notas\",\"createAt\":0,\"dni\":\"Dni\"}"));
    }

    /**
     * Test {@link ExpedienteController#updateExpediente(Long, ExpedienteEntity)}.
     * <p>
     * Method under test:
     * {@link ExpedienteController#updateExpediente(Long, ExpedienteEntity)}
     */
    @Test
    @DisplayName("Test updateExpediente(Long, ExpedienteEntity)")
    void testUpdateExpediente() throws Exception {
        // Arrange
        ExpedienteDTO expedienteDTO = new ExpedienteDTO();
        expedienteDTO.setCreateAt(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        expedienteDTO.setDni("Dni");
        expedienteDTO.setId(1L);
        expedienteDTO.setNotas("Notas");
        expedienteDTO.setTipoPrestacion(1);
        when(expedienteService.updateExpediente(Mockito.<Long>any(), Mockito.<ExpedienteEntity>any()))
                .thenReturn(expedienteDTO);

        ExpedienteEntity expedienteEntity = new ExpedienteEntity();
        expedienteEntity.setCreateAt(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        expedienteEntity.setDni("Dni");
        expedienteEntity.setId(1L);
        expedienteEntity.setNotas("Notas");
        expedienteEntity.setTipoPrestacion(1);
        String content = (new ObjectMapper()).writeValueAsString(expedienteEntity);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/expedientes/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(expedienteController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"id\":1,\"tipoPrestacion\":1,\"notas\":\"Notas\",\"createAt\":0,\"dni\":\"Dni\"}"));
    }

    /**
     * Test {@link ExpedienteController#deleteExpediente(Long)}.
     * <ul>
     *   <li>Then status {@link StatusResultMatchers#isNoContent()}.</li>
     * </ul>
     * <p>
     * Method under test: {@link ExpedienteController#deleteExpediente(Long)}
     */
    @Test
    @DisplayName("Test deleteExpediente(Long); then status isNoContent()")
    void testDeleteExpediente_thenStatusIsNoContent() throws Exception {
        // Arrange
        when(expedienteService.deleteExpediente(Mockito.<Long>any())).thenReturn(true);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/expedientes/{id}", 1L);

        // Act
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(expedienteController)
                .build()
                .perform(requestBuilder);

        // Assert
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    /**
     * Test {@link ExpedienteController#deleteExpediente(Long)}.
     * <ul>
     *   <li>Then status {@link StatusResultMatchers#isNotFound()}.</li>
     * </ul>
     * <p>
     * Method under test: {@link ExpedienteController#deleteExpediente(Long)}
     */
    @Test
    @DisplayName("Test deleteExpediente(Long); then status isNotFound()")
    void testDeleteExpediente_thenStatusIsNotFound() throws Exception {
        // Arrange
        when(expedienteService.deleteExpediente(Mockito.<Long>any())).thenReturn(false);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/expedientes/{id}", 1L);

        // Act
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(expedienteController)
                .build()
                .perform(requestBuilder);

        // Assert
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }
}
