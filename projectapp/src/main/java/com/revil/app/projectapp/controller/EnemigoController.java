package com.revil.app.projectapp.controller;

import com.revil.app.projectapp.dto.enemigo.EnemigoDto;
import com.revil.app.projectapp.exceptions.NotFoundException;
import com.revil.app.projectapp.service.enemigo.EnemigoService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/enemigos")
public class EnemigoController {

    private final EnemigoService enemigoService;

    public EnemigoController(EnemigoService enemigoService) {
        this.enemigoService = enemigoService;
    }

    @GetMapping
    public List<EnemigoDto> listarEnemigos() {
        return enemigoService.listarEnemigos();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EnemigoDto crearEnemigo(@RequestBody EnemigoDto enemigoDto) throws NotFoundException {
        return enemigoService.crearEnemigo(enemigoDto);
    }

    @GetMapping("/{id}")
    public EnemigoDto obtenerPorId(@PathVariable UUID id) throws NotFoundException {
        return enemigoService.obtenerPorId(id);
    }

    @PutMapping("/{id}")
    public EnemigoDto actualizarEnemigo(@PathVariable UUID id, @RequestBody EnemigoDto enemigoDto) throws NotFoundException {
        return enemigoService.actualizarEnemigo(id, enemigoDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarEnemigo(@PathVariable UUID id) throws NotFoundException {
        enemigoService.eliminarEnemigo(id);
    }
}
