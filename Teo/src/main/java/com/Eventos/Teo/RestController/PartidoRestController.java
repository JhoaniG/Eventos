package com.Eventos.Teo.RestController;

import com.Eventos.Teo.DTO.EquipoDTO;
import com.Eventos.Teo.DTO.PartidoDTO;
import com.Eventos.Teo.servicios.PartidoService;
import jakarta.servlet.http.Part;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/partidos")
@CrossOrigin(origins = "http://localhost:3000")
public class PartidoRestController {
    private final PartidoService partidoService;


    public PartidoRestController(PartidoService partidoService) {
        this.partidoService = partidoService;
    }


    @GetMapping
    public ResponseEntity<List<PartidoDTO>>listar(){
        return ResponseEntity.ok(partidoService.listarPartidos());
    }

    @PostMapping
    public ResponseEntity<?> createPartido(@RequestBody PartidoDTO partidoDTO) { // Cambiado a @RequestBody
        try {
            PartidoDTO nuevo = partidoService.guardar(partidoDTO);
            return new ResponseEntity<>(nuevo, HttpStatus.CREATED);
        } catch (Exception e) {
            // Devuelve el mensaje de error de tu Service (ej: "No puede jugar contra sí mismo")
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarPartido(@PathVariable Long id, @RequestBody PartidoDTO partidoDTO) {
        try {
            return ResponseEntity.ok(partidoService.actualizar(id, partidoDTO));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarPartido(@PathVariable Long id) {
        try {
            partidoService.borrar(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // Convención REST: 204
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }





}
