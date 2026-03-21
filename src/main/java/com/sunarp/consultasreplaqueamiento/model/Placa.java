package com.sunarp.consultasreplaqueamiento.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "TA_REEMPL", schema = "EXTRANET")
public class Placa {
    @Id
    @Column(name = "NO_PLAC")
    private String numero;
}
