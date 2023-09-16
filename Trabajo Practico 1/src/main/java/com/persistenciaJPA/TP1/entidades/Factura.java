package com.persistenciaJPA.TP1.entidades;

import com.persistenciaJPA.TP1.enums.FormaPago;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Factura extends BaseEntidad{

    private int numero;

    private Date fecha;

    private double descuento;

    @Enumerated(EnumType.STRING)
    private FormaPago formaPago;

    private int total;



}
