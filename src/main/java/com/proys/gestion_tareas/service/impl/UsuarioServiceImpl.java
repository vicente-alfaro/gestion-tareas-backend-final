package com.proys.gestion_tareas.service.impl;

import com.proys.gestion_tareas.dto.UsuarioDTO;
import com.proys.gestion_tareas.entity.Usuario;
import com.proys.gestion_tareas.repository.UsuarioRepository;
import com.proys.gestion_tareas.service.UsuarioService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public Page<UsuarioDTO> listar(Pageable pageable) {
        return usuarioRepository.findAll(pageable).map(this::mapToDTO);
    }

    @Override
    public UsuarioDTO obtenerPorId(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        return mapToDTO(usuario);
    }

    @Override
    public UsuarioDTO crear(UsuarioDTO dto) {
        Usuario u = mapToEntity(dto);
        Usuario guardado = usuarioRepository.save(u);
        return mapToDTO(guardado);
    }

    @Override
    public UsuarioDTO actualizar(Long id, UsuarioDTO dto) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        usuario.setNombre(dto.getNombre());
        usuario.setEmail(dto.getEmail());

        Usuario actualizado = usuarioRepository.save(usuario);
        return mapToDTO(actualizado);
    }

    @Override
    public void eliminar(Long id) {
        if (!usuarioRepository.existsById(id)) {
            throw new RuntimeException("Usuario no encontrado");
        }
        usuarioRepository.deleteById(id);
    }

    private UsuarioDTO mapToDTO(Usuario usuario) {
        UsuarioDTO dto = new UsuarioDTO();
        dto.setId(usuario.getId());
        dto.setNombre(usuario.getNombre());
        dto.setEmail(usuario.getEmail());
        return dto;
    }

    private Usuario mapToEntity(UsuarioDTO dto) {
        Usuario u = new Usuario();
        u.setId(dto.getId());
        u.setNombre(dto.getNombre());
        u.setEmail(dto.getEmail());
        return u;
    }
}
