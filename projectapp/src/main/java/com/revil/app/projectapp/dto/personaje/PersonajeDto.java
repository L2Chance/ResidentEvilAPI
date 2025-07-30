package com.revil.app.projectapp.dto.personaje;

import com.revil.app.projectapp.enums.GeneroEnum;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

public record PersonajeDto(
        UUID id,
        String nombre,
        String imagen,
        LocalDate nacimiento,
        GeneroEnum genero,
        String grupoSanguineo,
        Set<UUID> juegosIds,
        UUID primeraAparicionId
) {}
