package com.revil.app.projectapp.controller;

import com.revil.app.projectapp.dto.personaje.PersonajeDto;
import com.revil.app.projectapp.exceptions.NotFoundException;
import com.revil.app.projectapp.service.personaje.PersonajeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/personajes")
public class PersonajeController {

    private final PersonajeService personajeService;

    public PersonajeController(PersonajeService personajeService) {
        this.personajeService = personajeService;
    }

    @GetMapping
    public ResponseEntity<List<PersonajeDto>> listarPersonajes() {
        return ResponseEntity.ok(personajeService.listarPersonajes());
    }

    @PostMapping
    public ResponseEntity<PersonajeDto> crear(@RequestBody PersonajeDto dto) {
        return new ResponseEntity<>(personajeService.crearPersonaje(dto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonajeDto> obtener(@PathVariable UUID id) throws NotFoundException, NotFoundException {
        return ResponseEntity.ok(personajeService.obtenerPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PersonajeDto> actualizar(@PathVariable UUID id, @RequestBody PersonajeDto dto) throws NotFoundException {
        return ResponseEntity.ok(personajeService.actualizarPersonaje(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable UUID id) throws NotFoundException {
        personajeService.eliminarPersonaje(id);
        return ResponseEntity.noContent().build();
    }
}

