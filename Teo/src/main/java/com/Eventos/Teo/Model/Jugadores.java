package com.Eventos.Teo.Model;

import jakarta.persistence.*;

import javax.naming.Name;

@Entity
@Table(name = "jugadores")
public class Jugadores {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_Jugador")
    private Long id;
    @Column(name = "posicion", length = 30)
    private String posicion;
    @Column(name = "numeroJugador")
    private Integer numeroJugador;
    @ManyToOne
    @JoinColumn(name = "id_Usuario", nullable = false)
    private Usuarios usuarios;


}
