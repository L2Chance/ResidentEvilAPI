package com.revil.app.projectapp.mappers.personaje;

import com.revil.app.projectapp.domain.Juego;
import com.revil.app.projectapp.domain.Personaje;
import com.revil.app.projectapp.dto.personaje.PersonajeDto;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class PersonajeMapper {

    public PersonajeDto toDto(Personaje personaje) {
        Set<UUID> juegosIds = personaje.getJuegos() != null
                ? personaje.getJuegos().stream().map(Juego::getId).collect(Collectors.toSet())
                : new HashSet<>();

        UUID primeraAparicionId = personaje.getPrimeraAparicion() != null
                ? personaje.getPrimeraAparicion().getId()
                : null;

        return new PersonajeDto(
                personaje.getId(),
                personaje.getNombre(),
                personaje.getImagen(),
                personaje.getNacimiento(),
                personaje.getGenero(),
                personaje.getGrupoSanguineo(),
                juegosIds,
                primeraAparicionId
        );
    }

    public Personaje toEntity(PersonajeDto dto, List<Juego> juegos, Juego primeraAparicion) {
        Personaje personaje = new Personaje();
        personaje.setId(dto.id());
        personaje.setNombre(dto.nombre());
        personaje.setImagen(dto.imagen());
        personaje.setNacimiento(dto.nacimiento());
        personaje.setGenero(dto.genero());
        personaje.setGrupoSanguineo(dto.grupoSanguineo());
        personaje.setJuegos(new HashSet<>(juegos));
        personaje.setPrimeraAparicion(primeraAparicion);
        return personaje;
    }
}
