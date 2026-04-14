package com.Eventos.Teo.servicios;

import com.Eventos.Teo.DTO.RolDTO;

import java.util.List;

public interface RolService {
    List<RolDTO> obtenerTodos();
    RolDTO guardar(RolDTO rolDTO);
    RolDTO actualizar(Long id, RolDTO rolDTO);
    void eliminar(Long id);
}
