package com.revil.app.projectapp.service.juego;

import com.revil.app.projectapp.domain.Arma;
import com.revil.app.projectapp.domain.Enemigo;
import com.revil.app.projectapp.domain.Juego;
import com.revil.app.projectapp.domain.Personaje;
import com.revil.app.projectapp.dto.juego.JuegoDto;
import com.revil.app.projectapp.exceptions.NotFoundException;
import com.revil.app.projectapp.mappers.juego.JuegoMapper;
import com.revil.app.projectapp.repository.ArmaRepository;
import com.revil.app.projectapp.repository.EnemigoRepository;
import com.revil.app.projectapp.repository.JuegoRepository;
import com.revil.app.projectapp.repository.PersonajeRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class JuegoServiceImplements implements JuegoService {

    private final JuegoRepository juegoRepository;
    private final JuegoMapper juegoMapper;
    private final PersonajeRepository personajeRepository;
    private final ArmaRepository armaRepository;
    private final EnemigoRepository enemigoRepository;

    public JuegoServiceImplements(JuegoRepository juegoRepository, JuegoMapper juegoMapper,
                                  PersonajeRepository personajeRepository,
                                  ArmaRepository armaRepository,
                                  EnemigoRepository enemigoRepository) {
        this.juegoRepository = juegoRepository;
        this.juegoMapper = juegoMapper;
        this.personajeRepository = personajeRepository;
        this.armaRepository = armaRepository;
        this.enemigoRepository = enemigoRepository;
    }

    @Override
    public List<JuegoDto> listarJuegos() {
        List<Juego> juegos = juegoRepository.findAll();
        return juegos.stream()
                .map(juegoMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public JuegoDto crearJuego(JuegoDto juegoDto) {
        Juego juego = juegoMapper.toEntity(juegoDto);

        if (juego.getPersonajes() == null) juego.setPersonajes(new HashSet<>());
        if (juego.getArmas() == null) juego.setArmas(new HashSet<>());
        if (juego.getEnemigos() == null) juego.setEnemigos(new HashSet<>());

        Juego juegoGuardado = juegoRepository.save(juego);
        return juegoMapper.toDTO(juegoGuardado);
    }

    @Override
    public JuegoDto obtenerPorId(UUID id) throws NotFoundException {
        Juego juego = juegoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Juego no encontrado con id " + id));
        return juegoMapper.toDTO(juego);
    }

    @Override
    public JuegoDto actualizarJuego(UUID id, JuegoDto juegoDto) throws NotFoundException {
        Juego juegoExistente = juegoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Juego no encontrado con id " + id));

        juegoExistente.setTitulo(juegoDto.titulo());
        juegoExistente.setCover(juegoDto.cover());
        juegoExistente.setArgumento(juegoDto.argumento());
        juegoExistente.setAnioDeSalida(juegoDto.anioDeSalida());
        juegoExistente.setDesarrollador(juegoDto.desarrollador());
        juegoExistente.setDistribuidor(juegoDto.distribuidor());
        juegoExistente.setPlataformas(juegoDto.plataformas());
        juegoExistente.setClasificacion(juegoDto.clasificacion());

        // Actualizar relaciones
        Set<Personaje> personajes = juegoDto.personajesIds().stream()
                .map(personajeRepository::getReferenceById)
                .collect(Collectors.toSet());
        juegoExistente.setPersonajes(personajes);

        Set<Arma> armas = juegoDto.armasIds().stream()
                .map(armaRepository::getReferenceById)
                .collect(Collectors.toSet());
        juegoExistente.setArmas(armas);

        Set<Enemigo> enemigos = juegoDto.enemigosIds().stream()
                .map(enemigoRepository::getReferenceById)
                .collect(Collectors.toSet());
        juegoExistente.setEnemigos(enemigos);

        Juego juegoActualizado = juegoRepository.save(juegoExistente);
        return juegoMapper.toDTO(juegoActualizado);
    }

    @Override
    public void eliminarJuego(UUID id) throws NotFoundException {
        Juego juego = juegoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Juego no encontrado con id " + id));
        juegoRepository.delete(juego);
    }
}


