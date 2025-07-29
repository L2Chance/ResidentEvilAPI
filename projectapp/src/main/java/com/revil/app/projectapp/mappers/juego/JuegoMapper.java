package com.revil.app.projectapp.mappers.juego;

import com.revil.app.projectapp.domain.Arma;
import com.revil.app.projectapp.domain.Enemigo;
import com.revil.app.projectapp.domain.Juego;
import com.revil.app.projectapp.domain.Personaje;
import com.revil.app.projectapp.dto.juego.JuegoDto;
import com.revil.app.projectapp.repository.ArmaRepository;
import com.revil.app.projectapp.repository.EnemigoRepository;
import com.revil.app.projectapp.repository.PersonajeRepository;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class JuegoMapper {

    private final PersonajeRepository personajeRepository;
    private final ArmaRepository armaRepository;
    private final EnemigoRepository enemigoRepository;

    public JuegoMapper(PersonajeRepository personajeRepository,
                       ArmaRepository armaRepository,
                       EnemigoRepository enemigoRepository) {
        this.personajeRepository = personajeRepository;
        this.armaRepository = armaRepository;
        this.enemigoRepository = enemigoRepository;
    }


    //Mapeo para convertir una entidad a DTO
    public JuegoDto toDTO(Juego juego) {
        return new JuegoDto(
                juego.getId(),
                juego.getTitulo(),
                juego.getCover(),
                juego.getArgumento(),
                juego.getAnioDeSalida(),
                juego.getDesarrollador(),
                juego.getDistribuidor(),
                juego.getPlataformas(),
                juego.getClasificacion(),
                juego.getPersonajes().stream().map(Personaje::getId).toList(),
                juego.getArmas().stream().map(Arma::getId).toList(),
                juego.getEnemigos().stream().map(Enemigo::getId).toList()
        );
    }

    //Mapeo para convertir el DTO que recibo del frontend a entidad
    public Juego toEntity(JuegoDto dto) {
        Juego juego = new Juego();
        juego.setId(dto.id());
        juego.setTitulo(dto.titulo());
        juego.setCover(dto.cover());
        juego.setArgumento(dto.argumento());
        juego.setAnioDeSalida(dto.anioDeSalida());
        juego.setDesarrollador(dto.desarrollador());
        juego.setDistribuidor(dto.distribuidor());
        juego.setPlataformas(dto.plataformas());
        juego.setClasificacion(dto.clasificacion());

        Set<Personaje> personajes = dto.personajesIds().stream()
                .map(personajeRepository::getReferenceById)
                .collect(Collectors.toSet());
        juego.setPersonajes(personajes);

        Set<Arma> armas = dto.armasIds().stream()
                .map(armaRepository::getReferenceById)
                .collect(Collectors.toSet());
        juego.setArmas(armas);

        Set<Enemigo> enemigos = dto.enemigosIds().stream()
                .map(enemigoRepository::getReferenceById)
                .collect(Collectors.toSet());
        juego.setEnemigos(enemigos);

        return juego;
    }
}