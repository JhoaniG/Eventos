package com.Eventos.Teo.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "equipos")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Equipo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_Equipo", unique = true, nullable = false)
    private  Long idEquipo;
    @Column(name = "nombreEquipo", unique = true, nullable = false)
    private  String nombreEquipo;
    @Column(name = "escudo")
    private  String escudo;
    @Column(name = "ciudad", nullable = true)
    private  String ciudad;




}
