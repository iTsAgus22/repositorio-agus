package com.persistenciaJPA.TP1.repositorios;

import com.persistenciaJPA.TP1.entidades.Domicilio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DomicilioRepository extends JpaRepository<Domicilio, Long> {
}
