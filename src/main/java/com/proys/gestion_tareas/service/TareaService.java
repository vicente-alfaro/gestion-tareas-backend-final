package com.proys.gestion_tareas.service;

import com.proys.gestion_tareas.dto.TareaDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TareaService {

    Page<TareaDTO> listar(String estado,
                          String prioridad,
                          Long proyectoId,
                          Pageable pageable);

    TareaDTO obtenerPorId(Long id);

    TareaDTO crear(TareaDTO dto);

    TareaDTO actualizar(Long id, TareaDTO dto);

    void eliminar(Long id);
}
