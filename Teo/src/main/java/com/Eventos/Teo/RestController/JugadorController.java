package com.Eventos.Teo.RestController;

import com.Eventos.Teo.DTO.JugadoresDTO;
import com.Eventos.Teo.servicios.Jugareservice;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jugadores")
@CrossOrigin(origins = "http://localhost:3000") // Permite la conexión con React
@RequiredArgsConstructor
public class JugadorController {

    private final Jugareservice jugareservice;

    // 1. LISTAR TODOS: GET http://localhost:8080/api/jugadores
    @GetMapping
    public ResponseEntity<List<JugadoresDTO>> listar() {
        return ResponseEntity.ok(jugareservice.listarJugadores());
    }

    // 2. GUARDAR: POST http://localhost:8080/api/jugadores
    @PostMapping
    public ResponseEntity<?> guardar(@RequestBody JugadoresDTO jugadoresDTO) {
        try {
            JugadoresDTO nuevoJugador = jugareservice.guardarJugadores(jugadoresDTO);
            return new ResponseEntity<>(nuevoJugador, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            // Si el rol no es "JUGADOR" o el usuario no existe, enviamos el error 400
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // 3. ACTUALIZAR: PUT http://localhost:8080/api/jugadores/1
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @RequestBody JugadoresDTO jugadoresDTO) {
        try {
            return ResponseEntity.ok(jugareservice.actualizarJugadores(id, jugadoresDTO));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    // 4. ELIMINAR: DELETE http://localhost:8080/api/jugadores/1
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        try {
            jugareservice.eliminarJugadores(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}