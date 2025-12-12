package com.proys.gestion_tareas.controller;

import com.proys.gestion_tareas.dto.ProyectoDTO;
import com.proys.gestion_tareas.service.ProyectoService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/proyectos")
@CrossOrigin(origins = "http://localhost:4200")
public class ProyectoController {

    private final ProyectoService proyectoService;

    public ProyectoController(ProyectoService proyectoService) {
        this.proyectoService = proyectoService;
    }

    @GetMapping
    public Page<ProyectoDTO> listar(Pageable pageable) {
        return proyectoService.listar(pageable);
    }

    @GetMapping("/{id}")
    public ProyectoDTO obtenerPorId(@PathVariable Long id) {
        return proyectoService.obtenerPorId(id);
    }

    @PostMapping
    public ProyectoDTO crear(@Valid @RequestBody ProyectoDTO dto) {
        return proyectoService.crear(dto);
    }

    @PutMapping("/{id}")
    public ProyectoDTO actualizar(@PathVariable Long id, @Valid @RequestBody ProyectoDTO dto) {
        return proyectoService.actualizar(id, dto);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        proyectoService.eliminar(id);
    }
}
