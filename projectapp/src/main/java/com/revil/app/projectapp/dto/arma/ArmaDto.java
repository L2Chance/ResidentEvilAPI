package com.revil.app.projectapp.dto.arma;

import java.util.Set;
import java.util.UUID;

public record ArmaDto(
        UUID id,
        String nombre,
        String imagen,
        String poderDeFuego,
        String velocidadDeFuego,
        String claseDeArma,
        Set<UUID> juegosIds
) {}
