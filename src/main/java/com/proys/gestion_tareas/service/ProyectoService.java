package com.proys.gestion_tareas.service;

import com.proys.gestion_tareas.dto.ProyectoDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProyectoService {

    Page<ProyectoDTO> listar(Pageable pageable);

    ProyectoDTO obtenerPorId(Long id);

    ProyectoDTO crear(ProyectoDTO dto);

    ProyectoDTO actualizar(Long id, ProyectoDTO dto);

    void eliminar(Long id);
}
