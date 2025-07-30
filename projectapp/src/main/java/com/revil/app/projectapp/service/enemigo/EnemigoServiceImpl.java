package com.revil.app.projectapp.service.enemigo;

import com.revil.app.projectapp.domain.Enemigo;
import com.revil.app.projectapp.domain.Juego;
import com.revil.app.projectapp.dto.enemigo.EnemigoDto;
import com.revil.app.projectapp.exceptions.NotFoundException;
import com.revil.app.projectapp.mappers.enemigo.EnemigoMapper;
import com.revil.app.projectapp.repository.EnemigoRepository;
import com.revil.app.projectapp.repository.JuegoRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class EnemigoServiceImpl implements EnemigoService {

    private final EnemigoRepository enemigoRepository;
    private final JuegoRepository juegoRepository;
    private final EnemigoMapper enemigoMapper;

    public EnemigoServiceImpl(EnemigoRepository enemigoRepository,
                              JuegoRepository juegoRepository,
                              EnemigoMapper enemigoMapper) {
        this.enemigoRepository = enemigoRepository;
        this.juegoRepository = juegoRepository;
        this.enemigoMapper = enemigoMapper;
    }

    @Override
    public List<EnemigoDto> listarEnemigos() {
        return enemigoRepository.findAll()
                .stream()
                .map(enemigoMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public EnemigoDto crearEnemigo(EnemigoDto enemigoDto) {
        Set<Juego> juegos = new HashSet<>(juegoRepository.findAllById(enemigoDto.juegosIds()));
        Enemigo enemigo = enemigoMapper.toEntity(enemigoDto, juegos);
        Enemigo enemigoGuardado = enemigoRepository.save(enemigo);
        return enemigoMapper.toDto(enemigoGuardado);
    }

    @Override
    public EnemigoDto obtenerPorId(UUID id) throws NotFoundException {
        Enemigo enemigo = enemigoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Enemigo no encontrado con id: " + id));
        return enemigoMapper.toDto(enemigo);
    }

    @Override
    public EnemigoDto actualizarEnemigo(UUID id, EnemigoDto enemigoDto) throws NotFoundException {
        Enemigo enemigoExistente = enemigoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Enemigo no encontrado con id: " + id));

        Set<Juego> juegos = new HashSet<>(juegoRepository.findAllById(enemigoDto.juegosIds()));

        enemigoExistente.setNombre(enemigoDto.nombre());
        enemigoExistente.setDescripcion(enemigoDto.descripcion());
        enemigoExistente.setImagen(enemigoDto.imagen());
        enemigoExistente.setJuegos(juegos);

        Enemigo enemigoActualizado = enemigoRepository.save(enemigoExistente);
        return enemigoMapper.toDto(enemigoActualizado);
    }

    @Override
    public void eliminarEnemigo(UUID id) throws NotFoundException {
        Enemigo enemigo = enemigoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Enemigo no encontrado con id: " + id));
        enemigoRepository.delete(enemigo);
    }
}



