package com.Eventos.Teo.DTO;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsuariosDTO {

    private  Long idUsuario;

    private  String nombre;

    private  String apellido;

    private  String cedula;

    private Integer edad;
}
