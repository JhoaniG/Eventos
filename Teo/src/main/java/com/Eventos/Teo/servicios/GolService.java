package com.Eventos.Teo.servicios;

import com.Eventos.Teo.DTO.GolDTO;
import java.util.List;

public interface GolService {
    List<GolDTO> listarGoles();
    GolDTO registrarGol(GolDTO golDTO);
    void eliminarGol(Long idGol);
}