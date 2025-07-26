package com.revil.app.projectapp.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Set;
import java.util.UUID;


@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"personajes", "armas", "enemigos"})
@Getter
@Setter
@Table(name = "Juego")
@Entity
public class Juego {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(length = 36, updatable = false, nullable = false)
    private UUID id;

    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false)
    private String cover;

    @Column(nullable = false)
    private String argumento;

    @Column(nullable = false)
    private int anioDeSalida;

    @Column(nullable = false)
    private String desarrollador;

    @Column(nullable = false)
    private String distribuidor;

    @ElementCollection
    @CollectionTable(name = "juego_plataformas", joinColumns = @JoinColumn(name = "juego_id"))
    @Column(name = "plataforma_nombre", nullable = false)
    private List<String> plataformas;

    @Column(nullable = false)
    private String clasificacion;

    @ManyToMany
    @JoinTable(
            name = "juego_personaje",
            joinColumns = @JoinColumn(name = "juego_id"),
            inverseJoinColumns = @JoinColumn(name = "personaje_id")
    )
    private Set<Personaje> personajes;

    @ManyToMany
    @JoinTable(
            name = "juego_arma",
            joinColumns = @JoinColumn(name = "juego_id"),
            inverseJoinColumns = @JoinColumn(name = "arma_id")
    )
    private Set<Arma> armas;

    @ManyToMany
    @JoinTable(
            name = "juego_enemigo",
            joinColumns = @JoinColumn(name = "juego_id"),
            inverseJoinColumns = @JoinColumn(name = "enemigo_id")
    )
    private Set<Enemigo> enemigos;

}
