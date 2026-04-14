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
    private final UploadFileService uploadFileService;

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
        // --- AQUÍ USAMOS EL SERVICIO ---
        if (archivo != null && !archivo.isEmpty()) {
            String nombreFoto = uploadFileService.guardarArchivo(archivo);
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

            // --- ACTUALIZAR FOTO ---
            if (archivo != null && !archivo.isEmpty()) {
                // Opcional: Borrar la foto vieja para no gastar espacio
                if (usuarioExistente.getFoto() != null) {
                    uploadFileService.eliminarArchivo(usuarioExistente.getFoto());
                }
                String nuevoNombre = uploadFileService.guardarArchivo(archivo);
                usuarioExistente.setFoto(nuevoNombre);
            }

            Usuarios actualizado = usuarioRepositorio.save(usuarioExistente);
            return modelMapper.map(actualizado, UsuariosDTO.class);
        }).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }


    @Override
    public void eliminar(Long id) {
        Usuarios usuario = usuarioRepositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("No se pudo encontrar el usuario"));

        // 1. Borrar la foto física primero
        if (usuario.getFoto() != null) {
            uploadFileService.eliminarArchivo(usuario.getFoto());
        }

        // 2. Borrar el registro de la base de datos
        usuarioRepositorio.deleteById(id);
    }}
