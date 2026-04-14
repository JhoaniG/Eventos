package com.Eventos.Teo.servicios;

import com.Eventos.Teo.DTO.UsuariosDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UsuarioServicio {
    List<UsuariosDTO>listartodos();
    UsuariosDTO guardar(UsuariosDTO usuariosDTO, MultipartFile archivo);
    UsuariosDTO actualizar(Long id, UsuariosDTO usuariosDTO, MultipartFile archivo);
    void eliminar(Long id);
}
