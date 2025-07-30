package com.revil.app.projectapp.controller;

import com.revil.app.projectapp.dto.arma.ArmaDto;
import com.revil.app.projectapp.exceptions.NotFoundException;
import com.revil.app.projectapp.service.arma.ArmaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/armas")
public class ArmaController {

    private final ArmaService armaService;

    public ArmaController(ArmaService armaService) {
        this.armaService = armaService;
    }

    @GetMapping
    public ResponseEntity<List<ArmaDto>> listarArmas() {
        return ResponseEntity.ok(armaService.listarArmas());
    }

    @PostMapping
    public ResponseEntity<ArmaDto> crearArma(@RequestBody ArmaDto dto) throws NotFoundException {
        ArmaDto armaCreada = armaService.crearArma(dto);
        return ResponseEntity.status(201).body(armaCreada);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArmaDto> obtenerPorId(@PathVariable UUID id) throws NotFoundException {
        return ResponseEntity.ok(armaService.obtenerPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ArmaDto> actualizarArma(@PathVariable UUID id, @RequestBody ArmaDto dto) throws NotFoundException {
        return ResponseEntity.ok(armaService.actualizarArma(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarArma(@PathVariable UUID id) throws NotFoundException {
        armaService.eliminarArma(id);
        return ResponseEntity.noContent().build();
    }
}
