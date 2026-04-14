package com.Eventos.Teo.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Length;

@Entity
@Table(name = "usuarios")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class Usuarios {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_Usuario", nullable = false)
    private  Long idUsuario;
    @Column(name = "nombre", length = 30, nullable = false)
    private  String Nombre;
    @Column(name = "apellido", length = 15, nullable = false)
    private  String Apellido;
    @Column(name = "cedula",  length = 30, nullable = false)
    private  String Cedula;
    @Column(name = "edad", nullable = false)
    private Integer Edad;
    @Column(name = "foto")
    private  String Foto;
    @ManyToOne
    @JoinColumn(name = "id_Rol", nullable = false)
    private Rol rol;

}
