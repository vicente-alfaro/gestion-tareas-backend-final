package com.proys.gestion_tareas.service.impl;

import com.proys.gestion_tareas.dto.ProyectoDTO;
import com.proys.gestion_tareas.entity.EstadoProyecto;
import com.proys.gestion_tareas.entity.Proyecto;
import com.proys.gestion_tareas.repository.ProyectoRepository;
import com.proys.gestion_tareas.service.ProyectoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProyectoServiceImpl implements ProyectoService {

    private final ProyectoRepository proyectoRepository;

    public ProyectoServiceImpl(ProyectoRepository proyectoRepository) {
        this.proyectoRepository = proyectoRepository;
    }

    @Override
    public Page<ProyectoDTO> listar(Pageable pageable) {
        return proyectoRepository.findAll(pageable).map(this::mapToDTO);
    }

    @Override
    public ProyectoDTO obtenerPorId(Long id) {
        Proyecto proyecto = proyectoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Proyecto no encontrado"));
        return mapToDTO(proyecto);
    }

    @Override
    public ProyectoDTO crear(ProyectoDTO dto) {
        Proyecto proyecto = mapToEntity(dto);
        Proyecto guardado = proyectoRepository.save(proyecto);
        return mapToDTO(guardado);
    }

    @Override
    public ProyectoDTO actualizar(Long id, ProyectoDTO dto) {
        Proyecto proyecto = proyectoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Proyecto no encontrado"));

        proyecto.setNombre(dto.getNombre());
        proyecto.setDescripcion(dto.getDescripcion());
        proyecto.setFechaInicio(dto.getFechaInicio());
        proyecto.setFechaFin(dto.getFechaFin());
        if (dto.getEstado() != null) {
            proyecto.setEstado(EstadoProyecto.valueOf(dto.getEstado()));
        }

        Proyecto actualizado = proyectoRepository.save(proyecto);
        return mapToDTO(actualizado);
    }

    @Override
    public void eliminar(Long id) {
        if (!proyectoRepository.existsById(id)) {
            throw new RuntimeException("Proyecto no encontrado");
        }
        proyectoRepository.deleteById(id);
    }

    private ProyectoDTO mapToDTO(Proyecto proyecto) {
        ProyectoDTO dto = new ProyectoDTO();
        dto.setId(proyecto.getId());
        dto.setNombre(proyecto.getNombre());
        dto.setDescripcion(proyecto.getDescripcion());
        dto.setFechaInicio(proyecto.getFechaInicio());
        dto.setFechaFin(proyecto.getFechaFin());
        dto.setEstado(proyecto.getEstado().name());
        return dto;
    }

    private Proyecto mapToEntity(ProyectoDTO dto) {
        Proyecto proyecto = new Proyecto();
        proyecto.setId(dto.getId());
        proyecto.setNombre(dto.getNombre());
        proyecto.setDescripcion(dto.getDescripcion());
        proyecto.setFechaInicio(dto.getFechaInicio());
        proyecto.setFechaFin(dto.getFechaFin());
        if (dto.getEstado() != null) {
            proyecto.setEstado(EstadoProyecto.valueOf(dto.getEstado()));
        }
        return proyecto;
    }
}
