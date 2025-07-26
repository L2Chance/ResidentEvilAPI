package com.revil.app.projectapp.repository;


import com.revil.app.projectapp.domain.Juego;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JuegoRepository extends JpaRepository<Juego, UUID> {
}
