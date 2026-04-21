package com.Eventos.Teo.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GolDTO {

    private Long idGol;
    private Integer minuto;
    private String tipoGol;

    private Long idPartido;
    private Long idJugador;
    private Long idEquipo;
}