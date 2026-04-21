package com.Eventos.Teo.DTO;

import com.Eventos.Teo.Model.Equipo;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PartidoDTO {
    private  Long idPartido;
    private LocalDate fecha;
    private String ciudad;
    private String estado;
    private Integer marcadorLocal;
    private Integer marcadorVisitante;
    private Long idEquipoVisitante;
    private Long idEquipoLocal;
}
