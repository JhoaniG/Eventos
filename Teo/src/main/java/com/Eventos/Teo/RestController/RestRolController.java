package com.Eventos.Teo.RestController;

import com.Eventos.Teo.DTO.RolDTO;
import com.Eventos.Teo.servicios.RolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roles") // Es mejor usar el "/" inicial
@CrossOrigin(origins = "http://localhost:3000")
public class RestRolController {

    private final RolService rolService;

    @Autowired
    public RestRolController(RolService rolService) {
        this.rolService = rolService;
    }

    // GET
    @GetMapping
    public ResponseEntity<List<RolDTO>> listarRoles() {
        return ResponseEntity.ok(rolService.obtenerTodos());
    }

    // POST
    @PostMapping
    public ResponseEntity<RolDTO> guardarRol(@RequestBody RolDTO rolDTO) {
        RolDTO nuevoRol = rolService.guardar(rolDTO);
        return new ResponseEntity<>(nuevoRol, HttpStatus.CREATED);
    }

    // PUT
    @PutMapping("/{id}")
    public ResponseEntity<RolDTO> actualizarRol(@PathVariable Long id, @RequestBody RolDTO rolDTO) {
        return ResponseEntity.ok(rolService.actualizar(id, rolDTO));
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarRol(@PathVariable Long id) {
        rolService.eliminar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}