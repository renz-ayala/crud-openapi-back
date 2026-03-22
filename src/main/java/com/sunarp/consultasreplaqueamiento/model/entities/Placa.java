package com.sunarp.consultasreplaqueamiento.model.entities;

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
    @Column(name = "ID_PLACA")
    private Long id;

    @Column(name = "NUMERO_PLACA", nullable = false, unique = true, length = 10)
    private String numeroPlaca;

    @Column(name = "MARCA", length = 50)
    private String marca;

    @Column(name = "MODELO", length = 50)
    private String modelo;

    @Column(name = "COLOR", length = 30)
    private String color;

    @Column(name = "ANIO_FABRICACION")
    private Integer anioFabricacion;

    @Column(name = "PROPIETARIO_DNI", length = 15)
    private String propietarioDni;

    @Column(name = "ESTADO", length = 20)
    private String estado = "ACTIVO";

    @Column(name = "FECHA_REGISTRO", updatable = false)
    private LocalDateTime fechaRegistro;

    @Column(name = "OBSERVACIONES", length = 500)
    private String observaciones;

    @PrePersist
    protected void onCreate() {
        if (this.fechaRegistro == null) {
            this.fechaRegistro = LocalDateTime.now();
        }
    }
}
