package com.proys.gestion_tareas.dto;

public class TareasPorEstadoDTO {

    private String estado;
    private Long cantidad;

    public TareasPorEstadoDTO() {
    }

    public TareasPorEstadoDTO(String estado, Long cantidad) {
        this.estado = estado;
        this.cantidad = cantidad;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Long getCantidad() {
        return cantidad;
    }

    public void setCantidad(Long cantidad) {
        this.cantidad = cantidad;
    }
}
