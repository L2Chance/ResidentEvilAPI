package com.revil.app.projectapp.mappers.arma;

import com.revil.app.projectapp.domain.Arma;
import com.revil.app.projectapp.domain.Juego;
import com.revil.app.projectapp.dto.arma.ArmaDto;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class ArmaMapper {

    // Mapear entidad a DTO
    public ArmaDto toDto(Arma arma) {
        Set<UUID> juegosIds = arma.getJuegos() == null
                ? Set.of()
                : arma.getJuegos().stream()
                .map(Juego::getId)
                .collect(Collectors.toSet());

        return new ArmaDto(
                arma.getId(),
                arma.getNombre(),
                arma.getImagen(),
                arma.getPoderDeFuego(),
                arma.getVelocidadDeFuego(),
                arma.getClaseDeArma(),
                juegosIds
        );
    }

    // Mapear DTO a entidad (con juegos asociados)
    public Arma toEntity(ArmaDto dto, Set<Juego> juegos) {
        Arma arma = new Arma();
        arma.setId(dto.id());
        arma.setNombre(dto.nombre());
        arma.setImagen(dto.imagen());
        arma.setPoderDeFuego(dto.poderDeFuego());
        arma.setVelocidadDeFuego(dto.velocidadDeFuego());
        arma.setClaseDeArma(dto.claseDeArma());
        arma.setJuegos(juegos);
        return arma;
    }
}
