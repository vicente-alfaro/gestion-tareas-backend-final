CREATE DATABASE IF NOT EXISTS gestion_tareas
  CHARACTER SET utf8mb4
  COLLATE utf8mb4_unicode_ci;

USE gestion_tareas;

CREATE TABLE proyectos (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    descripcion VARCHAR(500),
    fecha_inicio DATE,
    fecha_fin DATE,
    estado ENUM('PLANIFICADO','EN_PROGRESO','COMPLETADO','PAUSADO') NOT NULL
);

CREATE TABLE usuarios (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    email VARCHAR(150) NOT NULL UNIQUE
);

CREATE TABLE tareas (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(150) NOT NULL,
    descripcion VARCHAR(1000),
    estado ENUM('PENDIENTE','EN_PROGRESO','COMPLETADA','BLOQUEADA') NOT NULL,
    prioridad ENUM('BAJA','MEDIA','ALTA') NOT NULL,
    fecha_creacion DATE NOT NULL DEFAULT CURRENT_DATE,
    fecha_vencimiento DATE,
    proyecto_id BIGINT NOT NULL,
    usuario_id BIGINT,

    CONSTRAINT fk_tarea_proyecto
      FOREIGN KEY (proyecto_id) REFERENCES proyectos(id),

    CONSTRAINT fk_tarea_usuario
      FOREIGN KEY (usuario_id) REFERENCES usuarios(id)
);
