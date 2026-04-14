package com.Eventos.Teo.RestController;
import com.Eventos.Teo.DTO.JugadoresDTO;
import com.Eventos.Teo.Model.Jugadores;
import com.Eventos.Teo.Model.Usuarios;
import com.Eventos.Teo.repositorios.JugadorRepositorio;
import com.Eventos.Teo.repositorios.UsuarioRepositorio;
import com.Eventos.Teo.servicios.Jugareservice;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JugadorServiceImpl implements Jugareservice {

    private final JugadorRepositorio jugadorRepositorio;
    private final UsuarioRepositorio usuarioRepositorio;
    private final ModelMapper modelMapper;

    @Override
    public List<JugadoresDTO> listarJugadores() {
        return jugadorRepositorio.findAll().stream()
                .map(this::convertirADTO) // Usamos un método privado para mapear bien
                .collect(Collectors.toList());
    }

    @Override
    public JugadoresDTO guardarJugadores(JugadoresDTO jugadoresDTO) {
        // 1. Buscamos el usuario por el ID que viene en el DTO
        Usuarios usuario = usuarioRepositorio.findById(jugadoresDTO.getIdUsuario())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // 2. VALIDACIÓN: ¿Tiene el rol de JUGADOR?
        // Nota: Asegúrate que en tu base de datos el nombre del rol sea exactamente "JUGADOR"
        if (usuario.getRol() == null || !usuario.getRol().getRol().equalsIgnoreCase("JUGADOR")) {
            throw new RuntimeException("Solo los usuarios con rol 'JUGADOR' pueden ser registrados como deportistas.");
        }

        // 3. Mapeo manual para evitar errores de ModelMapper con el ID del usuario
        Jugadores jugador = modelMapper.map(jugadoresDTO, Jugadores.class);
        jugador.setUsuarios(usuario); // Asignamos la entidad completa

        Jugadores guardado = jugadorRepositorio.save(jugador);
        return convertirADTO(guardado);
    }

    @Override
    public JugadoresDTO actualizarJugadores(Long id, JugadoresDTO jugadoresDTO) {
        return jugadorRepositorio.findById(id).map(jugadorExistente -> {
            jugadorExistente.setPosicion(jugadoresDTO.getPosicion());
            jugadorExistente.setNumeroJugador(jugadoresDTO.getNumeroJugador());
            jugadorExistente.setCategoria(jugadoresDTO.getCategoria());
            jugadorExistente.setEstatura(jugadoresDTO.getEstatura());
            jugadorExistente.setPeso(jugadoresDTO.getPeso());
            jugadorExistente.setPuntosAnotados(jugadoresDTO.getPuntosAnotados());

            // Si cambias el usuario en la edición (opcional)
            if (jugadoresDTO.getIdUsuario() != null) {
                Usuarios usuario = usuarioRepositorio.findById(jugadoresDTO.getIdUsuario())
                        .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
                jugadorExistente.setUsuarios(usuario);
            }

            Jugadores actualizado = jugadorRepositorio.save(jugadorExistente);
            return convertirADTO(actualizado);
        }).orElseThrow(() -> new RuntimeException("No se encontró el jugador con ID: " + id));
    }

    @Override
    public JugadoresDTO eliminarJugadores(Long id) {
        Jugadores jugador = jugadorRepositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Jugador no encontrado"));

        JugadoresDTO dto = convertirADTO(jugador);
        jugadorRepositorio.deleteById(id);
        return dto;
    }

    // MÉTODO AUXILIAR: Para mapear de Entidad a DTO correctamente
    private JugadoresDTO convertirADTO(Jugadores jugador) {
        JugadoresDTO dto = modelMapper.map(jugador, JugadoresDTO.class);
        if (jugador.getUsuarios() != null) {
            dto.setIdUsuario(jugador.getUsuarios().getIdUsuario());
        }
        return dto;
    }
}
