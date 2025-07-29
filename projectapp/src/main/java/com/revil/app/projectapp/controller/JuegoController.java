package com.revil.app.projectapp.controller;

import com.revil.app.projectapp.dto.juego.JuegoDto;
import com.revil.app.projectapp.exceptions.NotFoundException;
import com.revil.app.projectapp.service.juego.JuegoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/juegos")
public class JuegoController {

    private final JuegoService juegoService;

    public JuegoController(JuegoService juegoService) {
        this.juegoService = juegoService;
    }

    // GET /api/juegos
    @GetMapping
    public ResponseEntity<List<JuegoDto>> listarJuegos() {
        List<JuegoDto> juegos = juegoService.listarJuegos();
        return ResponseEntity.ok(juegos);
    }

    @PostMapping
    public ResponseEntity<JuegoDto> crearJuego(@RequestBody JuegoDto juegoDto) {
        JuegoDto nuevoJuego = juegoService.crearJuego(juegoDto);
        return new ResponseEntity<>(nuevoJuego, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<JuegoDto> obtenerJuegoPorId(@PathVariable UUID id) throws NotFoundException {
        JuegoDto juego = juegoService.obtenerPorId(id);
        return ResponseEntity.ok(juego);
    }

    @PutMapping("/{id}")
    public ResponseEntity<JuegoDto> actualizarJuego(@PathVariable UUID id, @RequestBody JuegoDto juegoDto) throws NotFoundException {
        JuegoDto juegoActualizado = juegoService.actualizarJuego(id, juegoDto);
        return ResponseEntity.ok(juegoActualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarJuego(@PathVariable UUID id) throws NotFoundException {
        juegoService.eliminarJuego(id);
        return ResponseEntity.noContent().build();
    }


}
