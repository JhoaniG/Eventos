package com.Eventos.Teo.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.naming.Name;

@Entity
@Table(name = "jugadores")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Jugadores {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_Jugador", unique = true, nullable = false)
    private Long id;
    @Column(name = "posicion", length = 30)
    private String posicion;
    @Column(name = "numeroJugador")
    private Integer numeroJugador;
    @Column(name = "categoria", length = 40)
    private String categoria;
    @Column(name = "estatura", nullable = false)
    private String estatura;
    @Column(name = "peso", nullable = false)
    private Double peso;
    @Column(name = "puntosAnotados", nullable = true)
    private Integer puntosAnotados;
    @Column(name = "goles", nullable = true)
    private String goles;
    @ManyToOne
    @JoinColumn(name = "id_Usuario", nullable = false, unique = true)
    private Usuarios usuarios;
    @ManyToOne
    @JoinColumn(name = "id_Equipo", nullable = false, unique = true)
    private Equipo equipo;
}
