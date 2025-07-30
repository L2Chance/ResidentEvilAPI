package com.revil.app.projectapp.service.enemigo;

import com.revil.app.projectapp.dto.enemigo.EnemigoDto;
import com.revil.app.projectapp.exceptions.NotFoundException;

import java.util.List;
import java.util.UUID;

public interface EnemigoService {
    List<EnemigoDto> listarEnemigos();
    EnemigoDto crearEnemigo(EnemigoDto enemigoDto) throws NotFoundException;
    EnemigoDto obtenerPorId(UUID id) throws NotFoundException;
    EnemigoDto actualizarEnemigo(UUID id, EnemigoDto enemigoDto) throws NotFoundException;
    void eliminarEnemigo(UUID id) throws NotFoundException;
}
