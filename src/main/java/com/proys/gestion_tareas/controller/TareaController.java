package com.proys.gestion_tareas.controller;

import com.proys.gestion_tareas.dto.TareaDTO;
import com.proys.gestion_tareas.service.TareaService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tareas")
@CrossOrigin(origins = "http://localhost:4200")
public class TareaController {

    private final TareaService tareaService;

    public TareaController(TareaService tareaService) {
        this.tareaService = tareaService;
    }

    @GetMapping
    public Page<TareaDTO> listar(
            @RequestParam(required = false) String estado,
            @RequestParam(required = false) String prioridad,
            @RequestParam(required = false) Long proyectoId,
            Pageable pageable
    ) {
        return tareaService.listar(estado, prioridad, proyectoId, pageable);
    }

    @GetMapping("/{id}")
    public TareaDTO obtenerPorId(@PathVariable Long id) {
        return tareaService.obtenerPorId(id);
    }

    @PostMapping
    public TareaDTO crear(@Valid @RequestBody TareaDTO dto) {
        return tareaService.crear(dto);
    }

    @PutMapping("/{id}")
    public TareaDTO actualizar(@PathVariable Long id, @Valid @RequestBody TareaDTO dto) {
        return tareaService.actualizar(id, dto);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        tareaService.eliminar(id);
    }
}
