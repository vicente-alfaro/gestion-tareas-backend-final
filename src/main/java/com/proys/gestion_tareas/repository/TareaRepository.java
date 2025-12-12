package com.proys.gestion_tareas.repository;

import com.proys.gestion_tareas.entity.EstadoTarea;
import com.proys.gestion_tareas.entity.Prioridad;
import com.proys.gestion_tareas.entity.Tarea;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TareaRepository extends JpaRepository<Tarea, Long> {

    @Query("""
           SELECT t
           FROM Tarea t
           WHERE (:estado IS NULL OR t.estado = :estado)
             AND (:prioridad IS NULL OR t.prioridad = :prioridad)
             AND (:proyectoId IS NULL OR t.proyecto.id = :proyectoId)
           """)
    Page<Tarea> buscarPorFiltros(@Param("estado") EstadoTarea estado,
                                 @Param("prioridad") Prioridad prioridad,
                                 @Param("proyectoId") Long proyectoId,
                                 Pageable pageable);

    @Query("""
           SELECT t.estado, COUNT(t)
           FROM Tarea t
           GROUP BY t.estado
           """)
    List<Object[]> contarTareasPorEstado();
}
