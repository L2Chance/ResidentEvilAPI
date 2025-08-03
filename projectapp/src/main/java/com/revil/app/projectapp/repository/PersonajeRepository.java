package com.revil.app.projectapp.repository;

import com.revil.app.projectapp.domain.Personaje;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PersonajeRepository extends JpaRepository<Personaje, UUID> {
}