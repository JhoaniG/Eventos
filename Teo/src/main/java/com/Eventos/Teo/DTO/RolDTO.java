package com.Eventos.Teo.DTO;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RolDTO {

    private Long id;

    private String rol;
}
