package com.proys.gestion_tareas.repository;

import com.proys.gestion_tareas.entity.Proyecto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProyectoRepository extends JpaRepository<Proyecto, Long> {
}
