package com.Eventos.Teo.DTO;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EquipoDTO {
    private  Long idEquipo;
    private  String nombreEquipo;
    private  String escudo;
    private  String ciudad;

}
