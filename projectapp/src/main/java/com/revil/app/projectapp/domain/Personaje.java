package com.revil.app.projectapp.domain;

import com.revil.app.projectapp.enums.GeneroEnum;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(exclude = {"juegos", "primeraAparicion"})
@Table(name = "Personaje")
@Entity
public class Personaje {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)

    @Column(length = 36, updatable = false, nullable = false)
    private UUID id;

    @Column(nullable = false)
    private String nombre;

    private String imagen;

    @Column(nullable = false)
    private LocalDate nacimiento;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private GeneroEnum genero;

    @Column(nullable = false)
    private String grupoSanguineo;

    @ManyToMany(mappedBy = "personajes")
    private Set<Juego> juegos;

    @ManyToOne
    @JoinColumn(name = "primera_aparicion_id")
    private Juego primeraAparicion;

}
