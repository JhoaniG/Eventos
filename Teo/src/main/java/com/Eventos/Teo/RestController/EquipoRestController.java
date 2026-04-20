package com.Eventos.Teo.RestController;


import com.Eventos.Teo.DTO.EquipoDTO;
import com.Eventos.Teo.servicios.EquipoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/equipos")
@CrossOrigin(origins = "http://localhost:3000")
public class EquipoRestController {

    private  final EquipoService equipoService;


    public EquipoRestController(EquipoService equipoService) {
        this.equipoService = equipoService;
    }

//GET: http://localhost:8080/api/equipos
@GetMapping
    public ResponseEntity<List<EquipoDTO>> listarEquipos() {
        return ResponseEntity.ok(equipoService.listarEquipos());
}
//POST: http://localhost:8080/api/equipos
@PostMapping
    public  ResponseEntity<EquipoDTO>guardar(
        @ModelAttribute EquipoDTO equipoDTO,
        @RequestParam(value = "escudo", required = false)MultipartFile escudo){

        return new ResponseEntity<>(equipoService.guardarEquipo(equipoDTO, escudo), HttpStatus.CREATED);
}
//PUT: editar ayzen
@PutMapping("/{id}")
    public  ResponseEntity<EquipoDTO>actualizar(
            @PathVariable Long id,
            @ModelAttribute EquipoDTO equipoDTO,
            @RequestParam(value = "escudo", required = false) MultipartFile escudo){
        return new ResponseEntity<>
                (equipoService
                        .actualizar
                                (id, equipoDTO, escudo),   HttpStatus.OK);
}
//DELETE: editar ayzen
@DeleteMapping("/{id}")
    public  ResponseEntity<Void>eliminar(
            @PathVariable Long id
){
        equipoService.eliminarEquipo(id);

        return  new ResponseEntity<>(HttpStatus.NO_CONTENT);
}
}
