package com.Eventos.Teo.ServiceImpl;


import com.Eventos.Teo.DTO.UsuariosDTO;
import com.Eventos.Teo.Model.Rol;
import com.Eventos.Teo.Model.Usuarios;
import com.Eventos.Teo.repositorios.UsuarioRepositorio;
import com.Eventos.Teo.servicios.UsuarioServicio;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioServicio {

    private final UsuarioRepositorio usuarioRepositorio;
    private final ModelMapper modelMapper;

    @Override
    public List<UsuariosDTO> listartodos() {
        return usuarioRepositorio.findAll().stream()
                .map(model-> modelMapper
                        .map(model, UsuariosDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public UsuariosDTO guardar(UsuariosDTO usuariosDTO, MultipartFile archivo) {
        Usuarios usuarios=modelMapper.map(usuariosDTO, Usuarios.class);
        if(archivo!=null && !archivo.isEmpty()){
            String nombreFoto = System.currentTimeMillis() + "_" + archivo.getOriginalFilename();
            // Lógica para guardar el archivo físicamente (ver nota abajo)
            usuarios.setFoto(nombreFoto);


        }
        if (usuariosDTO.getIdRol()!=null){
            Rol rol=new Rol();
            rol.setId(usuariosDTO.getIdRol());
            usuarios.setRol(rol);
        }
        Usuarios UsuarioGuardado=usuarioRepositorio.save(usuarios);


        return modelMapper.map(UsuarioGuardado, UsuariosDTO.class);
    }

    @Override
    public UsuariosDTO actualizar(Long id, UsuariosDTO usuariosDTO, MultipartFile archivo) {
        return usuarioRepositorio.findById(id).map(usuarioExistente -> {
            usuarioExistente.setNombre(usuariosDTO.getNombre());
            usuarioExistente.setApellido(usuariosDTO.getApellido());
            usuarioExistente.setCedula(usuariosDTO.getCedula());
            usuarioExistente.setEdad(usuariosDTO.getEdad());

            // Actualizar Rol
            if(usuariosDTO.getIdRol() != null) {
                Rol rol = new Rol();
                rol.setId(usuariosDTO.getIdRol());
                usuarioExistente.setRol(rol);
            }

            // Actualizar Foto si viene una nueva
            if (archivo != null && !archivo.isEmpty()) {
                String nombreFoto = System.currentTimeMillis() + "_" + archivo.getOriginalFilename();
                usuarioExistente.setFoto(nombreFoto);
            }

            Usuarios actualizado = usuarioRepositorio.save(usuarioExistente);
            return modelMapper.map(actualizado, UsuariosDTO.class);
        }).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }


    @Override
    public void eliminar(Long id) {
        if (usuarioRepositorio.existsById(id)){

            usuarioRepositorio.deleteById(id);
        }else{
            throw new RuntimeException("No se pudo eliminar el usuario");
        }

    }
}
