package com.revil.app.projectapp.service.arma;

import com.revil.app.projectapp.domain.Arma;
import com.revil.app.projectapp.domain.Juego;
import com.revil.app.projectapp.dto.arma.ArmaDto;
import com.revil.app.projectapp.exceptions.NotFoundException;
import com.revil.app.projectapp.mappers.arma.ArmaMapper;
import com.revil.app.projectapp.repository.ArmaRepository;
import com.revil.app.projectapp.repository.JuegoRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ArmaServiceImpl implements ArmaService {

    private final ArmaRepository armaRepository;
    private final JuegoRepository juegoRepository;
    private final ArmaMapper armaMapper;

    public ArmaServiceImpl(ArmaRepository armaRepository,
                           JuegoRepository juegoRepository,
                           ArmaMapper armaMapper) {
        this.armaRepository = armaRepository;
        this.juegoRepository = juegoRepository;
        this.armaMapper = armaMapper;
    }

    @Override
    public List<ArmaDto> listarArmas() {
        return armaRepository.findAll()
                .stream()
                .map(armaMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public ArmaDto crearArma(ArmaDto dto) throws NotFoundException {
        Set<Juego> juegos = obtenerJuegosPorIds(dto.juegosIds());
        Arma arma = armaMapper.toEntity(dto, juegos);
        Arma armaGuardada = armaRepository.save(arma);
        return armaMapper.toDto(armaGuardada);
    }

    @Override
    public ArmaDto obtenerPorId(UUID id) throws NotFoundException {
        Arma arma = armaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Arma no encontrada con id: " + id));
        return armaMapper.toDto(arma);
    }

    @Override
    public ArmaDto actualizarArma(UUID id, ArmaDto dto) throws NotFoundException {
        Arma armaExistente = armaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Arma no encontrada"));

        armaExistente.setNombre(dto.nombre());
        armaExistente.setImagen(dto.imagen());
        armaExistente.setPoderDeFuego(dto.poderDeFuego());
        armaExistente.setVelocidadDeFuego(dto.velocidadDeFuego());
        armaExistente.setClaseDeArma(dto.claseDeArma());

        // Obtener juegos y asignar copia mutable
        List<Juego> juegos = juegoRepository.findAllById(dto.juegosIds());
        armaExistente.setJuegos(new HashSet<>(juegos));

        Arma armaActualizada = armaRepository.save(armaExistente);
        return armaMapper.toDto(armaActualizada);
    }

    @Override
    public void eliminarArma(UUID id) throws NotFoundException {
        Arma arma = armaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Arma no encontrada con id: " + id));
        armaRepository.delete(arma);
    }

    private Set<Juego> obtenerJuegosPorIds(Set<UUID> juegosIds) throws NotFoundException {
        if (juegosIds == null || juegosIds.isEmpty()) {
            return Set.of();
        }
        Set<Juego> juegos = new HashSet<>();
        for (UUID id : juegosIds) {
            Juego juego = juegoRepository.findById(id)
                    .orElseThrow(() -> new NotFoundException("Juego no encontrado con id: " + id));
            juegos.add(juego);
        }
        return juegos;
    }
}
