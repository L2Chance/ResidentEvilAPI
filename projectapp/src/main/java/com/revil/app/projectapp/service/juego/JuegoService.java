package com.revil.app.projectapp.service.juego;

import com.revil.app.projectapp.dto.juego.JuegoDto;
import com.revil.app.projectapp.exceptions.NotFoundException;

import java.util.List;
import java.util.UUID;

public interface JuegoService {
    List<JuegoDto> listarJuegos();
    JuegoDto crearJuego(JuegoDto juegoDto);
    JuegoDto obtenerPorId(UUID id) throws NotFoundException;
    JuegoDto actualizarJuego(UUID id, JuegoDto juegoDto) throws NotFoundException;
    void eliminarJuego(UUID id) throws NotFoundException;
}

