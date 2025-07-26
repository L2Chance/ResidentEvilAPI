package com.revil.app.projectapp.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "Arma")
@ToString(exclude = "juegos")
@Entity
public class Arma {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(length = 36, updatable = false, nullable = false)
    private UUID id;

    private String nombre;
    private String imagen;
    private String poderDeFuego;
    private String velocidadDeFuego;
    private String claseDeArma;

    @ManyToMany(mappedBy = "armas")
    private Set<Juego> juegos;
}
