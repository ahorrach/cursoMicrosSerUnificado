package com.curso.expediente.controller;

import com.curso.expediente.model.dto.ExpedienteDTO;
import com.curso.expediente.model.dto.request.CreateExpedienteRequest;
import com.curso.expediente.model.entity.ExpedienteEntity;
import com.curso.expediente.service.ExpedienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/api/expedientes")
@Tag(name = "Expedientes", description = "Operaciones CRUD para Expedientes")
public class ExpedienteController {

    @Autowired
    private ExpedienteService expedienteService;

    @GetMapping
    @Operation(summary = "Obtener todos los expedientes", description = "Devuelve una lista de todos los expedientes.")
    public List<ExpedienteDTO> getAllExpedientes() {
        return expedienteService.getAllExpedientes();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener expediente por ID", description = "Devuelve un expediente por su ID.")
    @ApiResponse(responseCode = "200", description = "Expediente encontrado")
    @ApiResponse(responseCode = "404", description = "Expediente no encontrado")
    public ResponseEntity<ExpedienteDTO> getExpedienteById(@PathVariable Long id) {
        Optional<ExpedienteDTO> expediente = expedienteService.getExpedienteById(id);
        return expediente.isPresent() ? ResponseEntity.ok(expediente.get()) : ResponseEntity.notFound().build();
    }

    @GetMapping("/buscarPorDni/{dni}")
    @Operation(summary = "Obtener expedientes por DNI", description = "Devuelve un expedientes por su DNI.")
    @ApiResponse(responseCode = "200", description = "Expediente encontrado")
    @ApiResponse(responseCode = "404", description = "Expediente no encontrado")
    public List<ExpedienteDTO> getExpedienteByIDNI(@PathVariable String dni) {
       return expedienteService.getExpedientesByDNI(dni);
    }

    @GetMapping("/buscarPorTipoPrestacion/{tipoPrestacion}")
    @Operation(summary = "Obtener expedientes por tipoPrestacion", description = "Devuelve un expedientes por su tipoPrestacion.")
    @ApiResponse(responseCode = "200", description = "Expediente encontrado")
    @ApiResponse(responseCode = "404", description = "Expediente no encontrado")
    public List<ExpedienteDTO> getExpedienteByTipoPrestacion(@PathVariable Integer tipoPrestacion) {
        return expedienteService.getExpedientesByTipoPrestacion(tipoPrestacion);
    }

    @PostMapping
    @Operation(summary = "Crear expediente", description = "Crea un nuevo expediente en la base de datos.")
    public ResponseEntity<ExpedienteDTO> createExpediente(@RequestBody CreateExpedienteRequest expediente) {
        ExpedienteDTO createdExpediente = expedienteService.createExpediente(expediente);
        return new ResponseEntity<>(createdExpediente, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar expediente", description = "Actualiza un expediente existente por su ID.")
    public ResponseEntity<ExpedienteDTO> updateExpediente(@PathVariable Long id, @RequestBody ExpedienteEntity expediente) {
        ExpedienteDTO updatedExpediente = expedienteService.updateExpediente(id, expediente);
        return Objects.nonNull(updatedExpediente) ? ResponseEntity.ok(updatedExpediente) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar expediente", description = "Elimina un expediente por su ID.")
    public ResponseEntity<Void> deleteExpediente(@PathVariable Long id) {
        return expedienteService.deleteExpediente(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
