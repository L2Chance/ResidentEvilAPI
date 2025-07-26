package com.revil.app.projectapp.repository;

import com.revil.app.projectapp.domain.Arma;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ArmaRepository extends JpaRepository<Arma, UUID> {
}
