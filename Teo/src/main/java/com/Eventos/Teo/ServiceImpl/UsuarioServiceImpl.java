package com.Eventos.Teo.ServiceImpl;


import com.Eventos.Teo.DTO.UsuariosDTO;
import com.Eventos.Teo.Model.Usuarios;
import com.Eventos.Teo.repositorios.UsuarioRepositorio;
import com.Eventos.Teo.servicios.UsuarioServicio;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service

public class UsuarioServiceImpl implements UsuarioServicio {



    @Autowired
    UsuarioRepositorio usuarioRepositorio;
    @Autowired
    ModelMapper modelMapper;

    @Override
    public List<UsuariosDTO> listartodos() {
        return usuarioRepositorio.findAll().stream()
                .map(model-> modelMapper
                        .map(model, UsuariosDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public UsuariosDTO guardar(UsuariosDTO usuariosDTO) {
        Usuarios usuarios=modelMapper.map(usuariosDTO, Usuarios.class);
        Usuarios guardar=usuarioRepositorio.save(usuarios);


        return modelMapper.map(guardar, UsuariosDTO.class);
    }

    @Override
    public void eliminar(Long id) {
        if (usuarioRepositorio.existsById(id)){

            usuarioRepositorio.deleteById(id);
        }else{
            throw new RuntimeException("No se puedo");
        }

    }
}
