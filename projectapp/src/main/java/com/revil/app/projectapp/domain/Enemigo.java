package com.revil.app.projectapp.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "Enemigo")
@ToString(exclude = "juegos")
@Entity
public class Enemigo {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(length = 36, updatable = false, nullable = false)
    private UUID id;

    private String nombre;
    private String descripcion;
    private String imagen;

    @ManyToMany(mappedBy = "enemigos")
    private Set<Juego> juegos;
}
