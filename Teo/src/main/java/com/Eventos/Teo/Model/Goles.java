package com.Eventos.Teo.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "goles")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Goles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_gol", nullable = false)
    private Long idGol;
    @Column(name = "minuto", nullable = false)
    private Integer minuto;
    @Column(name = "tipoGol")
    private String tipoGol;


    //Llaver FOrneas
    @ManyToOne
    @JoinColumn(name = "id_partido", nullable = false)
    private Partido partido;

    @ManyToOne
    @JoinColumn(name = "id_jugador", nullable = false)
    private Jugadores jugador;

    // Importante saber a qué equipo suma (útil para detectar autogoles)
    @ManyToOne
    @JoinColumn(name = "id_equipo", nullable = false)
    private Equipo equipo;





}
