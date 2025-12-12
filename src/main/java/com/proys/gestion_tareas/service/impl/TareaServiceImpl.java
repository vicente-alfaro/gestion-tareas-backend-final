package com.proys.gestion_tareas.service.impl;

import com.proys.gestion_tareas.dto.TareaDTO;
import com.proys.gestion_tareas.entity.*;
import com.proys.gestion_tareas.repository.ProyectoRepository;
import com.proys.gestion_tareas.repository.TareaRepository;
import com.proys.gestion_tareas.repository.UsuarioRepository;
import com.proys.gestion_tareas.service.TareaService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class TareaServiceImpl implements TareaService {

    private final TareaRepository tareaRepository;
    private final ProyectoRepository proyectoRepository;
    private final UsuarioRepository usuarioRepository;

    public TareaServiceImpl(TareaRepository tareaRepository,
                            ProyectoRepository proyectoRepository,
                            UsuarioRepository usuarioRepository) {
        this.tareaRepository = tareaRepository;
        this.proyectoRepository = proyectoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public Page<TareaDTO> listar(String estadoStr, String prioridadStr, Long proyectoId, Pageable pageable) {
        EstadoTarea estado = null;
        Prioridad prioridad = null;

        if (estadoStr != null && !estadoStr.isBlank()) {
            estado = EstadoTarea.valueOf(estadoStr);
        }
        if (prioridadStr != null && !prioridadStr.isBlank()) {
            prioridad = Prioridad.valueOf(prioridadStr);
        }

        return tareaRepository.buscarPorFiltros(estado, prioridad, proyectoId, pageable)
                .map(this::mapToDTO);
    }

    @Override
    public TareaDTO obtenerPorId(Long id) {
        Tarea tarea = tareaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tarea no encontrada"));
        return mapToDTO(tarea);
    }

    @Override
    public TareaDTO crear(TareaDTO dto) {
        Tarea tarea = mapToEntity(dto);
        Tarea guardada = tareaRepository.save(tarea);
        return mapToDTO(guardada);
    }

    @Override
    public TareaDTO actualizar(Long id, TareaDTO dto) {
        Tarea tarea = tareaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tarea no encontrada"));

        tarea.setTitulo(dto.getTitulo());
        tarea.setDescripcion(dto.getDescripcion());

        if (dto.getEstado() != null) {
            tarea.setEstado(EstadoTarea.valueOf(dto.getEstado()));
        }
        if (dto.getPrioridad() != null) {
            tarea.setPrioridad(Prioridad.valueOf(dto.getPrioridad()));
        }

        tarea.setFechaVencimiento(dto.getFechaVencimiento());

        if (dto.getProyectoId() != null) {
            Proyecto proyecto = proyectoRepository.findById(dto.getProyectoId())
                    .orElseThrow(() -> new RuntimeException("Proyecto no encontrado"));
            tarea.setProyecto(proyecto);
        }

        if (dto.getUsuarioId() != null) {
            Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
            tarea.setUsuarioAsignado(usuario);
        } else {
            tarea.setUsuarioAsignado(null);
        }

        Tarea actualizada = tareaRepository.save(tarea);
        return mapToDTO(actualizada);
    }

    @Override
    public void eliminar(Long id) {
        if (!tareaRepository.existsById(id)) {
            throw new RuntimeException("Tarea no encontrada");
        }
        tareaRepository.deleteById(id);
    }

    private TareaDTO mapToDTO(Tarea tarea) {
        TareaDTO dto = new TareaDTO();
        dto.setId(tarea.getId());
        dto.setTitulo(tarea.getTitulo());
        dto.setDescripcion(tarea.getDescripcion());
        dto.setEstado(tarea.getEstado().name());
        dto.setPrioridad(tarea.getPrioridad().name());
        dto.setFechaCreacion(tarea.getFechaCreacion());
        dto.setFechaVencimiento(tarea.getFechaVencimiento());
        dto.setProyectoId(tarea.getProyecto().getId());
        dto.setProyectoNombre(tarea.getProyecto().getNombre());
        if (tarea.getUsuarioAsignado() != null) {
            dto.setUsuarioId(tarea.getUsuarioAsignado().getId());
            dto.setUsuarioNombre(tarea.getUsuarioAsignado().getNombre());
        }
        return dto;
    }

    private Tarea mapToEntity(TareaDTO dto) {
        Tarea tarea = new Tarea();
        tarea.setId(dto.getId());
        tarea.setTitulo(dto.getTitulo());
        tarea.setDescripcion(dto.getDescripcion());

        if (dto.getEstado() != null) {
            tarea.setEstado(EstadoTarea.valueOf(dto.getEstado()));
        }
        if (dto.getPrioridad() != null) {
            tarea.setPrioridad(Prioridad.valueOf(dto.getPrioridad()));
        }

        LocalDate fechaCreacion = dto.getFechaCreacion() != null ? dto.getFechaCreacion() : LocalDate.now();
        tarea.setFechaCreacion(fechaCreacion);
        tarea.setFechaVencimiento(dto.getFechaVencimiento());

        Proyecto proyecto = proyectoRepository.findById(dto.getProyectoId())
                .orElseThrow(() -> new RuntimeException("Proyecto no encontrado"));
        tarea.setProyecto(proyecto);

        if (dto.getUsuarioId() != null) {
            Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
            tarea.setUsuarioAsignado(usuario);
        }

        return tarea;
    }
}
