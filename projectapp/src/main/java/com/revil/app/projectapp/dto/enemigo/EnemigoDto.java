package com.revil.app.projectapp.dto.enemigo;

import java.util.Set;
import java.util.UUID;

public record EnemigoDto(
        UUID id,
        String nombre,
        String descripcion,
        String imagen,
        Set<UUID> juegosIds
) {}

