package com.Eventos.Teo.ServiceImpl;

import com.Eventos.Teo.DTO.RolDTO;
import com.Eventos.Teo.Model.Rol;
import com.Eventos.Teo.repositorios.RolRepositorio;
import com.Eventos.Teo.servicios.RolService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RolServiceImpl implements RolService {

    private  final RolRepositorio rolRepositorio;

    public RolServiceImpl(RolRepositorio rolRepositorio) {
        this.rolRepositorio = rolRepositorio;
    }
    @Autowired
    private ModelMapper modelMapper;


    @Override
    public List<RolDTO> obtenerTodos() {
        return rolRepositorio.findAll()
                .stream().map(model->modelMapper.
                        map(model, RolDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public RolDTO guardar(RolDTO rolDTO) {
        Rol rol= modelMapper.map(rolDTO, Rol.class);
        Rol rolaguardado=rolRepositorio.save(rol);



        return modelMapper.map(rolaguardado, RolDTO.class);
    }

    @Override
    public RolDTO actualizar(Long id, RolDTO rolDTO) {
        return rolRepositorio.findById(id).map(rol -> {
            rol.setRol(rolDTO.getRol());
            rol.setRol(rolDTO.getRol());
            Rol rolguardado=rolRepositorio.save(rol);


            return modelMapper.map(rolguardado, RolDTO.class);


        }).orElseThrow(()-> new RuntimeException("No se econtro"));
    }

    @Override
    public void eliminar(Long id) {
        if(rolRepositorio.findById(id).isPresent()){

            rolRepositorio.deleteById(id);
        }else {
            throw new RuntimeException("No se encontro el rol");
        }

    }
}
