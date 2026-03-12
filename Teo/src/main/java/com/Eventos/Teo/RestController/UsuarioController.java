package com.Eventos.Teo.RestController;

import com.Eventos.Teo.DTO.UsuariosDTO;
import com.Eventos.Teo.servicios.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "http://localhost:3000")
public class UsuarioController {

    private final UsuarioServicio usuarioServicio;

    @Autowired
    public UsuarioController(UsuarioServicio usuarioServicio) {
        this.usuarioServicio = usuarioServicio;
    }

    // GET: http://localhost:8080/api/usuarios
    @GetMapping
    public ResponseEntity<List<UsuariosDTO>> listar() {
        return ResponseEntity.ok(usuarioServicio.listartodos());
    }

    // POST: http://localhost:8080/api/usuarios
    @PostMapping
    public ResponseEntity<UsuariosDTO> guardar(@RequestBody UsuariosDTO usuariosDTO) {
        UsuariosDTO nuevoUsuario = usuarioServicio.guardar(usuariosDTO);
        return new ResponseEntity<>(nuevoUsuario, HttpStatus.CREATED);
    }

    // DELETE: http://localhost:8080/api/usuarios/1
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        usuarioServicio.eliminar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
