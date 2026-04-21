package com.Eventos.Teo.servicios;

import com.Eventos.Teo.DTO.PartidoDTO;

import java.util.List;

public interface PartidoService {
    List<PartidoDTO> listarPartidos();
    PartidoDTO guardar(PartidoDTO partidoDTO);
    void borrar(Long id);
    PartidoDTO actualizar(long id, PartidoDTO partidoDTO);
}
