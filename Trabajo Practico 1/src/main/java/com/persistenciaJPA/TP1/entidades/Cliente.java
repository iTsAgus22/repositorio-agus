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


public class Cliente extends BaseEntidad{

private String nombre;

private String apellido;

private String telefono;

private String email;


@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.EAGER)
@JoinColumn(name = "cliente_id")

@Builder.Default

private List<Domicilio> domicilios = new ArrayList<>();

@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
@JoinColumn(name = "id")

@Builder.Default

private List<Pedido> pedidos = new ArrayList<>();

    public void agregarDomicilio(Domicilio domi){

        domicilios.add(domi);

    }

    public void agregarPedido(Pedido pedido){

        pedidos.add(pedido);

    }
    public void mostrarDomicilios() {
        System.out.println("Domicilios de " + nombre + " " + apellido + ":");
        for (Domicilio domicilio : domicilios) {
            System.out.println("Calle: " + domicilio.getCalle() + ", Número: " + domicilio.getNumero());
        }

    }

    public void mostrarPedidos() {
        System.out.println("Pedidos de " + nombre + " " + apellido + ":");
        for (Pedido pedido : pedidos) {
            System.out.println("Estado: " + pedido.getEstado());
            System.out.println("Fecha: " + pedido.getFecha());
            System.out.println("Tipo de Envío: " + pedido.getTipoEnvio());
            System.out.println("Total: " + pedido.getTotal());
        }

    }

}
