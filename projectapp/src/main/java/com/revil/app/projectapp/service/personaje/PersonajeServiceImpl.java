package com.revil.app.projectapp.service.personaje;

import com.revil.app.projectapp.domain.Juego;
import com.revil.app.projectapp.domain.Personaje;
import com.revil.app.projectapp.dto.personaje.PersonajeDto;
import com.revil.app.projectapp.exceptions.NotFoundException;
import com.revil.app.projectapp.mappers.personaje.PersonajeMapper;
import com.revil.app.projectapp.repository.JuegoRepository;
import com.revil.app.projectapp.repository.PersonajeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PersonajeServiceImpl implements PersonajeService {

    private final PersonajeRepository personajeRepository;
    private final JuegoRepository juegoRepository;
    private final PersonajeMapper personajeMapper;

    public PersonajeServiceImpl(PersonajeRepository personajeRepository,
                                JuegoRepository juegoRepository,
                                PersonajeMapper personajeMapper) {
        this.personajeRepository = personajeRepository;
        this.juegoRepository = juegoRepository;
        this.personajeMapper = personajeMapper;
    }

    @Override
    public List<PersonajeDto> listarPersonajes() {
        return personajeRepository.findAll()
                .stream()
                .map(personajeMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public PersonajeDto crearPersonaje(PersonajeDto dto) {
        List<Juego> juegos = juegoRepository.findAllById(dto.juegosIds());
        Juego primeraAparicion = dto.primeraAparicionId() != null
                ? juegoRepository.findById(dto.primeraAparicionId()).orElse(null)
                : null;

        Personaje personaje = personajeMapper.toEntity(dto, juegos, primeraAparicion);
        personaje = personajeRepository.save(personaje);
        return personajeMapper.toDto(personaje);
    }

    @Override
    public PersonajeDto obtenerPorId(UUID id) throws NotFoundException {
        Personaje personaje = personajeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Personaje no encontrado"));
        return personajeMapper.toDto(personaje);
    }

    @Override
    public PersonajeDto actualizarPersonaje(UUID id, PersonajeDto dto) throws NotFoundException {
        if (!personajeRepository.existsById(id)) {
            throw new NotFoundException("Personaje no encontrado");
        }

        List<Juego> juegos = juegoRepository.findAllById(dto.juegosIds());
        Juego primeraAparicion = dto.primeraAparicionId() != null
                ? juegoRepository.findById(dto.primeraAparicionId()).orElse(null)
                : null;

        Personaje personaje = personajeMapper.toEntity(dto, juegos, primeraAparicion);
        personaje.setId(id);
        personaje = personajeRepository.save(personaje);
        return personajeMapper.toDto(personaje);
    }

    @Override
    public void eliminarPersonaje(UUID id) throws NotFoundException {
        if (!personajeRepository.existsById(id)) {
            throw new NotFoundException("Personaje no encontrado");
        }
        personajeRepository.deleteById(id);
    }
}

