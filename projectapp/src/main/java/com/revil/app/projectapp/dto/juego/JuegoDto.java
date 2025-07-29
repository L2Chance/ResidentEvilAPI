package com.revil.app.projectapp.dto.juego;

import java.util.List;
import java.util.UUID;

public record JuegoDto(
        UUID id,
        String titulo,
        String cover,
        String argumento,
        int anioDeSalida,
        String desarrollador,
        String distribuidor,
        List<String> plataformas,
        String clasificacion,
        List<UUID> personajesIds,
        List<UUID> armasIds,
        List<UUID> enemigosIds
) {}
