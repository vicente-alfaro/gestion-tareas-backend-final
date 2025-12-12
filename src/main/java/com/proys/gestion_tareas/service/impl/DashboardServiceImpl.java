package com.proys.gestion_tareas.service.impl;

import com.proys.gestion_tareas.dto.TareasPorEstadoDTO;
import com.proys.gestion_tareas.entity.EstadoTarea;
import com.proys.gestion_tareas.repository.TareaRepository;
import com.proys.gestion_tareas.service.DashboardService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DashboardServiceImpl implements DashboardService {

    private final TareaRepository tareaRepository;

    public DashboardServiceImpl(TareaRepository tareaRepository) {
        this.tareaRepository = tareaRepository;
    }

    @Override
    public List<TareasPorEstadoDTO> obtenerTareasPorEstado() {
        List<Object[]> resultados = tareaRepository.contarTareasPorEstado();
        return resultados.stream()
                .map(obj -> new TareasPorEstadoDTO(
                        ((EstadoTarea) obj[0]).name(),
                        (Long) obj[1]
                ))
                .toList();
    }
}
