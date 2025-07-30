package com.revil.app.projectapp.mappers.enemigo;

import com.revil.app.projectapp.domain.Enemigo;
import com.revil.app.projectapp.domain.Juego;
import com.revil.app.projectapp.dto.enemigo.EnemigoDto;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class EnemigoMapper {

    public EnemigoDto toDto(Enemigo enemigo) {
        Set<UUID> juegosIds = enemigo.getJuegos() == null ? Set.of() :
                enemigo.getJuegos().stream()
                        .map(Juego::getId)
                        .collect(Collectors.toSet());
        return new EnemigoDto(
                enemigo.getId(),
                enemigo.getNombre(),
                enemigo.getDescripcion(),
                enemigo.getImagen(),
                juegosIds
        );
    }

    public Enemigo toEntity(EnemigoDto enemigoDto, Set<Juego> juegos) {
        Enemigo enemigo = new Enemigo();
        enemigo.setId(enemigoDto.id());
        enemigo.setNombre(enemigoDto.nombre());
        enemigo.setDescripcion(enemigoDto.descripcion());
        enemigo.setImagen(enemigoDto.imagen());
        enemigo.setJuegos(juegos);
        return enemigo;
    }
}
