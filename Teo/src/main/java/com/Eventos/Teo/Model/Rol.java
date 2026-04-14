package com.Eventos.Teo.Model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.Id;


@Table(name = "Roles")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class Rol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_Rol")
    private Long id;
    @Column(name="Rol", length =  50, nullable = false)
    private String rol;

}
