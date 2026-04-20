package com.Eventos.Teo.servicios;

import com.Eventos.Teo.DTO.EquipoDTO;
import com.Eventos.Teo.repositorios.EquipoRepositorio;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface EquipoService {
    List<EquipoDTO>listarEquipos();
    EquipoDTO guardarEquipo(EquipoDTO equipoDTO, MultipartFile escudo);
    void eliminarEquipo(Long id);
    EquipoDTO actualizar(Long id, EquipoDTO equipoDTO, MultipartFile escudo);
}
