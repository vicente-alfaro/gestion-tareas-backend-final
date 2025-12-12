package com.proys.gestion_tareas.service;

import com.proys.gestion_tareas.dto.TareasPorEstadoDTO;

import java.util.List;

public interface DashboardService {

    List<TareasPorEstadoDTO> obtenerTareasPorEstado();
}
