package com.Eventos.Teo.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "partido")
@Setter
@Getter
public class Partido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_Partido", nullable = false, unique = true)
    private  Long idPartido;
    @Column(name = "fecha", nullable = false)
    private LocalDate fecha;
    @Column(name = "ciudad", nullable = false)
    private String ciudad;
    @Column(name = "estado", nullable = false)
    private String estado;
    @Column(name = "marcadorLocal", nullable = false)
    private String marcadorLocal;
    @Column(name = "marcadorVisitante", nullable = false)
    private String marcadorVisitante;
    @ManyToOne
    @JoinColumn(name = "id_Equipo_Visitante", nullable = false, unique = true)
    private Equipo equipoVisitante;
    @ManyToOne
    @JoinColumn(name = "id_Equipo_Local", nullable = false, unique = true)
    private Equipo equipoLocal;
}
