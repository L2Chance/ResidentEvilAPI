package com.revil.app.projectapp.repository;

import com.revil.app.projectapp.domain.Enemigo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EnemigoRepository extends JpaRepository<Enemigo, UUID> {
}
