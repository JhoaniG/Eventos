package com.Eventos.Teo.ServiceImpl;

import com.Eventos.Teo.DTO.EquipoDTO;
import com.Eventos.Teo.Model.Equipo;
import com.Eventos.Teo.repositorios.EquipoRepositorio;
import com.Eventos.Teo.servicios.EquipoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EquipoServiceImpl implements EquipoService {


    @Autowired
    EquipoRepositorio equipoRepositorio;
    @Autowired
    ModelMapper   modelMapper;
    @Autowired
    UploadFileService uploadFileService;

    @Override
    public List<EquipoDTO> listarEquipos() {
        return equipoRepositorio.findAll()
                .stream()
                .map(model->modelMapper.map(model, EquipoDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public EquipoDTO guardarEquipo(EquipoDTO equipoDTO, MultipartFile escudo) {
        Equipo equipo=modelMapper.map(equipoDTO, Equipo.class);

        if (escudo!=null && !escudo.isEmpty()){
            String nombreFoto=uploadFileService.guardarArchivo(escudo);
            equipo.setEscudo(nombreFoto);
        }
        Equipo nuevoEquipo=equipoRepositorio.save(equipo);


        return modelMapper.map(nuevoEquipo, EquipoDTO.class);
    }

    @Override
    public void eliminarEquipo(Long id) {
        Equipo equipo=equipoRepositorio.findById(id).orElseThrow(()
                ->new RuntimeException("No se encontro el Equipo"));

        if (equipo.getEscudo()!=null){

            uploadFileService.eliminarArchivo(equipo.getEscudo());

        }
        equipoRepositorio.deleteById(id);
    }

    @Override
    public EquipoDTO actualizar(Long id, EquipoDTO equipoDTO, MultipartFile escudo) {

        return  equipoRepositorio.findById(id).map(equipo -> {
            equipo.setNombreEquipo(equipoDTO.getNombreEquipo());
            equipo.setCiudad(equipoDTO.getCiudad());
            if (escudo!=null && !escudo.isEmpty()){
                if (equipo.getEscudo()!=null){
                    uploadFileService.eliminarArchivo(equipo.getEscudo());

                }

                String nuevoNombre=uploadFileService.guardarArchivo(escudo);
                equipo.setEscudo(nuevoNombre);
            }

            Equipo equipoNuevo=equipoRepositorio.save(equipo);



            return modelMapper.map(equipoNuevo, EquipoDTO.class);



        }).orElseThrow(()->new RuntimeException("No se encontro el equipo"));



    }
}
