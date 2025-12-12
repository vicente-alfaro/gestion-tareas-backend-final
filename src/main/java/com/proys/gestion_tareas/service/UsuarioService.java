package com.proys.gestion_tareas.service;

import com.proys.gestion_tareas.dto.UsuarioDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UsuarioService {

    Page<UsuarioDTO> listar(Pageable pageable);

    UsuarioDTO obtenerPorId(Long id);

    UsuarioDTO crear(UsuarioDTO dto);

    UsuarioDTO actualizar(Long id, UsuarioDTO dto);

    void eliminar(Long id);
}
