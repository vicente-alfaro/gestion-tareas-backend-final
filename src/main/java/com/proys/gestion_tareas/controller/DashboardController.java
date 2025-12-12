package com.proys.gestion_tareas.controller;

import com.proys.gestion_tareas.dto.TareasPorEstadoDTO;
import com.proys.gestion_tareas.service.DashboardService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dashboard")
@CrossOrigin(origins = "http://localhost:4200")
public class DashboardController {

    private final DashboardService dashboardService;

    public DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @GetMapping("/tareas-por-estado")
    public List<TareasPorEstadoDTO> obtenerTareasPorEstado() {
        return dashboardService.obtenerTareasPorEstado();
    }
}
