package com.Eventos.Teo.servicios;

import com.Eventos.Teo.DTO.UsuariosDTO;

import java.util.List;

public interface UsuarioServicio {
    List<UsuariosDTO>listartodos();
    UsuariosDTO guardar(UsuariosDTO usuariosDTO);
    void eliminar(Long id);
}
