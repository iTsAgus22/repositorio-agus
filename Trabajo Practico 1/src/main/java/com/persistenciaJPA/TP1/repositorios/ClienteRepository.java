package com.persistenciaJPA.TP1.repositorios;

import com.persistenciaJPA.TP1.entidades.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
