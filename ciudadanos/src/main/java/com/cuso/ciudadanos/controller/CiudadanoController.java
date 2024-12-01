package com.cuso.ciudadanos.controller;


import com.cuso.ciudadanos.model.dto.CiudadanoDTO;
import com.cuso.ciudadanos.model.request.CiudadanoRequest;
import com.cuso.ciudadanos.services.CiudadanoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
@RequestMapping("/api/ciudadanos")
@Tag(name = "Ciudadanos", description = "Ciudadanos")
public class CiudadanoController {

    private final CiudadanoService ciudadanoService;

    public CiudadanoController(CiudadanoService ciudadanoService) {
        this.ciudadanoService = ciudadanoService;
    }

    @GetMapping
    @Operation(summary = "Obtener todos los ciudadanos")
    public List<CiudadanoDTO> getAll() {
        return ciudadanoService.findAll();
    }

    @GetMapping("/consultarCiudadano/{id}")
    @Operation(summary = "Obtener un ciudadano por ID")
    public ResponseEntity<CiudadanoDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(ciudadanoService.findById(id));
    }

    @PostMapping("/altaCiudadano")
    @Operation(summary = "Crear un ciudadano")
    public ResponseEntity<Long> create(@RequestBody @Valid CiudadanoRequest ciudadanoDTO) {
        return ResponseEntity.ok(ciudadanoService.createCiudadanoIfnotExist(ciudadanoDTO));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un ciudadano")
    public ResponseEntity<CiudadanoDTO> update(@PathVariable Long id, @RequestBody @Valid  CiudadanoDTO ciudadanoDTO) {
        ciudadanoDTO.setCiudadanoId(id);
        return ResponseEntity.ok(ciudadanoService.save(ciudadanoDTO));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un ciudadano")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        ciudadanoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}


