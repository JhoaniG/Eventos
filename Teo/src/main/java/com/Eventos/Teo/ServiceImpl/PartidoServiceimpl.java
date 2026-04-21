package com.Eventos.Teo.ServiceImpl;

import com.Eventos.Teo.DTO.PartidoDTO;
import com.Eventos.Teo.Model.Equipo;
import com.Eventos.Teo.Model.Partido;
import com.Eventos.Teo.repositorios.EquipoRepositorio;
import com.Eventos.Teo.repositorios.PartidoRepositorio;
import com.Eventos.Teo.servicios.PartidoService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PartidoServiceimpl
        implements PartidoService
{
    private final PartidoRepositorio partidoRepositorio;
    private  final ModelMapper modelMapper;
    private final EquipoRepositorio equipoRepositorio;

    public PartidoServiceimpl(PartidoRepositorio partidoRepositorio, ModelMapper modelMapper, EquipoRepositorio equipoRepositorio) {
        this.partidoRepositorio = partidoRepositorio;
        this.modelMapper = modelMapper;
        this.equipoRepositorio = equipoRepositorio;
    }

    @Override
    public List<PartidoDTO> listarPartidos() {
        return partidoRepositorio.findAll()
                .stream()
                .map(model->modelMapper
                        .map(model, PartidoDTO.class))
                .collect(Collectors
                        .toList());
    }

    @Override
    public PartidoDTO guardar(PartidoDTO partidoDTO) {

        Partido partido=modelMapper.map(partidoDTO, Partido.class);
        if (partidoDTO.getIdEquipoVisitante()!=null) {
            Equipo equipo= equipoRepositorio.findById(partidoDTO
                            .getIdEquipoVisitante())
                    .orElseThrow(()->new RuntimeException("El equipo especificado no existe"));

            partido.setEquipoVisitante(equipo);
        }

        if (partidoDTO.getIdEquipoLocal()!=null) {
            Equipo equipoDos= equipoRepositorio
                    .findById(partidoDTO
                            .getIdEquipoLocal())
                    .orElseThrow(()->new RuntimeException("El equipo especificado no existe"));
            if (partidoDTO.getIdEquipoLocal().equals(partidoDTO.getIdEquipoVisitante())) {
                throw new RuntimeException("Un equipo no puede jugar contra sí mismo");
            }


            partido.setEquipoLocal(equipoDos);
        }



         Partido partidoGuardado=partidoRepositorio.save(partido);





        return modelMapper.map(partidoGuardado, PartidoDTO.class);
    }

    @Override
    public void borrar(Long id) {

        if (partidoRepositorio.findById(id).isPresent()) {

            partidoRepositorio.deleteById(id);
        }else  {
            throw new RuntimeException("El partido especificado no existe");
        }

    }

    @Override
    public PartidoDTO actualizar(long id, PartidoDTO partidoDTO) {
        return partidoRepositorio.findById(id).map(partido -> {
            partido.setCiudad(partidoDTO.getCiudad());
            partido.setEstado(partidoDTO.getEstado());
            partido.setMarcadorVisitante(partidoDTO.getMarcadorVisitante());
            partido.setMarcadorLocal(partidoDTO.getMarcadorLocal());
            partido.setFecha(partidoDTO.getFecha());
            if (partidoDTO.getIdEquipoVisitante()!=null) {
                Equipo equipo1=equipoRepositorio
                        .findById(partidoDTO.getIdEquipoVisitante())
                        .orElseThrow(()->new RuntimeException("Equipo no existe"));

                partido.setEquipoVisitante(equipo1);

            }
            if (partidoDTO.getIdEquipoLocal()!=null) {

                Equipo equipo2=equipoRepositorio
                        .findById(partidoDTO.getIdEquipoLocal())
                        .orElseThrow( ()->new RuntimeException("Equipo no existe"));
               partido.setEquipoLocal(equipo2);
            }

          Partido partidoguardado=partidoRepositorio.save(partido);

            return modelMapper.map(partidoguardado, PartidoDTO.class);
        }).orElseThrow(()->new RuntimeException("Partido no encontrado"));
    }
}
