package com.Eventos.Teo.DTO;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsuariosDTO {

    private  Long idUsuario;

    private  String Nombre;

    private  String Apellido;

    private  String Cedula;

    private Integer Edad;
}
