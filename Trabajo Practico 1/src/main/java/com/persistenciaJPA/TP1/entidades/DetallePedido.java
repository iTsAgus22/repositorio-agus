package com.persistenciaJPA.TP1.entidades;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class DetallePedido extends BaseEntidad{

    private int cantidad;

    private double subtotal;

    @ManyToOne
    @JoinColumn(
            name = "producto-id"
    )
    private Producto producto;

}
