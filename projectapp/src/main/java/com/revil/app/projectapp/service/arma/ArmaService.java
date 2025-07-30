package com.revil.app.projectapp.service.arma;

import com.revil.app.projectapp.dto.arma.ArmaDto;
import com.revil.app.projectapp.exceptions.NotFoundException;

import java.util.List;
import java.util.UUID;

public interface ArmaService {
    List<ArmaDto> listarArmas();
    ArmaDto crearArma(ArmaDto dto) throws NotFoundException;
    ArmaDto obtenerPorId(UUID id) throws NotFoundException;
    ArmaDto actualizarArma(UUID id, ArmaDto dto) throws NotFoundException;
    void eliminarArma(UUID id) throws NotFoundException;
}
