package com.Eventos.Teo.RestController;

import com.Eventos.Teo.DTO.GolDTO;
import com.Eventos.Teo.servicios.GolService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/goles")
@CrossOrigin(origins = "http://localhost:3000")
public class GolRestController {

    private final GolService golService;

    public GolRestController(GolService golService) {
        this.golService = golService;
    }

    @GetMapping
    public ResponseEntity<List<GolDTO>> listar() {
        return ResponseEntity.ok(golService.listarGoles());
    }

    @PostMapping
    public ResponseEntity<?> registrarGol(@RequestBody GolDTO golDTO) {
        try {
            GolDTO nuevoGol = golService.registrarGol(golDTO);
            return new ResponseEntity<>(nuevoGol, HttpStatus.CREATED);
        } catch (Exception e) {
            // Si el jugador no existe o el equipo no pertenece al partido, caerá aquí
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarGol(@PathVariable Long id) {
        try {
            golService.eliminarGol(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}