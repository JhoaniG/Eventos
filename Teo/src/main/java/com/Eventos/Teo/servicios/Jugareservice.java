package com.Eventos.Teo.servicios;

import com.Eventos.Teo.DTO.JugadoresDTO;

import java.util.List;

public interface Jugareservice {
    List<JugadoresDTO> listarJugadores();
    JugadoresDTO guardarJugadores(JugadoresDTO jugadoresDTO);
    JugadoresDTO actualizarJugadores(Long id, JugadoresDTO jugadoresDTO);
    JugadoresDTO eliminarJugadores(Long id);
}
