package com.Eventos.Teo.ServiceImpl;

import com.Eventos.Teo.DTO.GolDTO;
import com.Eventos.Teo.Model.Equipo;

import com.Eventos.Teo.Model.Goles;
import com.Eventos.Teo.Model.Jugadores;
import com.Eventos.Teo.Model.Partido;
import com.Eventos.Teo.repositorios.EquipoRepositorio;
import com.Eventos.Teo.repositorios.GolRepositorio;
import com.Eventos.Teo.repositorios.JugadorRepositorio;
import com.Eventos.Teo.repositorios.PartidoRepositorio;
import com.Eventos.Teo.servicios.GolService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GolServiceImpl implements GolService {

    private final GolRepositorio golRepositorio;
    private final PartidoRepositorio partidoRepositorio;
    private final JugadorRepositorio jugadorRepositorio;
    private final EquipoRepositorio equipoRepositorio;
    private final ModelMapper modelMapper;

    public GolServiceImpl(GolRepositorio golRepositorio, PartidoRepositorio partidoRepositorio,
                          JugadorRepositorio jugadorRepositorio, EquipoRepositorio equipoRepositorio,
                          ModelMapper modelMapper) {
        this.golRepositorio = golRepositorio;
        this.partidoRepositorio = partidoRepositorio;
        this.jugadorRepositorio = jugadorRepositorio;
        this.equipoRepositorio = equipoRepositorio;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<GolDTO> listarGoles() {
        return golRepositorio.findAll()
                .stream()
                .map(gol -> modelMapper.map(gol, GolDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public GolDTO registrarGol(GolDTO golDTO) {
        // 1. Buscamos todas las entidades involucradas
        Partido partido = partidoRepositorio.findById(golDTO.getIdPartido())
                .orElseThrow(() -> new RuntimeException("El partido no existe"));

        Jugadores jugador = jugadorRepositorio.findById(golDTO.getIdJugador())
                .orElseThrow(() -> new RuntimeException("El jugador no existe"));

        Equipo equipo = equipoRepositorio.findById(golDTO.getIdEquipo())
                .orElseThrow(() -> new RuntimeException("El equipo no existe"));

        // 2. Validamos que el equipo que anota realmente esté jugando este partido
        if (!equipo.getIdEquipo().equals(partido.getEquipoLocal().getIdEquipo()) &&
                !equipo.getIdEquipo().equals(partido.getEquipoVisitante().getIdEquipo())) {
            throw new RuntimeException("El equipo seleccionado no participa en este partido");
        }

        // 3. Creamos y guardamos el Gol
        Goles gol = new Goles();
        gol.setMinuto(golDTO.getMinuto());
        gol.setTipoGol(golDTO.getTipoGol());
        gol.setPartido(partido);
        gol.setJugador(jugador);
        gol.setEquipo(equipo);

        Goles golGuardado = golRepositorio.save(gol);

        // 4. LÓGICA DE SUMAR AL MARCADOR
        // Nos aseguramos de que no sean nulos (por si el partido recién se creó vacío)
        int golesLocal = partido.getMarcadorLocal() != null ? partido.getMarcadorLocal() : 0;
        int golesVisitante = partido.getMarcadorVisitante() != null ? partido.getMarcadorVisitante() : 0;

        if (equipo.getIdEquipo().equals(partido.getEquipoLocal().getIdEquipo())) {
            partido.setMarcadorLocal(golesLocal + 1);
        } else {
            partido.setMarcadorVisitante(golesVisitante + 1);
        }

        // Guardamos el partido actualizado con el nuevo marcador
        partidoRepositorio.save(partido);

        // 5. Retornamos el DTO al Frontend
        return modelMapper.map(golGuardado, GolDTO.class);
    }

    @Override
    public void eliminarGol(Long idGol) {
        if (!golRepositorio.existsById(idGol)) {
            throw new RuntimeException("El gol no existe");
        }

        // NOTA: Si eliminas un gol, en un sistema avanzado también deberías
        // buscar el partido y restarle -1 al marcador para mantener la consistencia.
        golRepositorio.deleteById(idGol);
    }
}