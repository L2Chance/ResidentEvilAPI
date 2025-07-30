package com.revil.app.projectapp.service.personaje;

import com.revil.app.projectapp.dto.personaje.PersonajeDto;
import com.revil.app.projectapp.exceptions.NotFoundException;

import java.util.List;
import java.util.UUID;

public interface PersonajeService {
    List<PersonajeDto> listarPersonajes();
    PersonajeDto crearPersonaje(PersonajeDto personajeDto);
    PersonajeDto obtenerPorId(UUID id) throws NotFoundException;
    PersonajeDto actualizarPersonaje(UUID id, PersonajeDto personajeDto) throws NotFoundException, NotFoundException;
    void eliminarPersonaje(UUID id) throws NotFoundException;
}

