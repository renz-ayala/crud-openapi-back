package com.sunarp.consultasreplaqueamiento.repository.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "PLACA", schema = "EXTRANET")
public class Placa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_placa")
    private Long id;

    @Column(name = "numero_placa", nullable = false, unique = true, length = 10)
    private String numeroPlaca;

    @Column(name = "marca", length = 50)
    private String marca;

    @Column(name = "modelo", length = 50)
    private String modelo;

    @Column(name = "color", length = 30)
    private String color;

    @Column(name = "anio_fabricacion")
    private Integer anioFabricacion;

    @Column(name = "propietario_dni", length = 15)
    private String propietarioDni;

    @Column(name = "estado", length = 20)
    private String estado = "ACTIVO";

    @Column(name = "fecha_registro", updatable = false)
    private LocalDateTime fechaRegistro;

    @Column(name = "observaciones", length = 500)
    private String observaciones;

    @PrePersist
    protected void onCreate() {
        if (this.fechaRegistro == null) {
            this.fechaRegistro = LocalDateTime.now();
        }
    }
}
