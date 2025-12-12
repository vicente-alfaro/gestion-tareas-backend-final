package com.proys.gestion_tareas.repository;

import com.proys.gestion_tareas.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
