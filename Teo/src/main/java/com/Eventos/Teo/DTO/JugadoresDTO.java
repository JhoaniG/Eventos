package com.Eventos.Teo.DTO;

import com.Eventos.Teo.Model.Usuarios;
import jakarta.persistence.*;
import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class JugadoresDTO {

    private Long id;

    private String posicion;

    private Integer numeroJugador;

    private String categoria;
    private String estatura;

    private Double peso;

    private Integer puntosAnotados;

    private Long idUsuario;

}
