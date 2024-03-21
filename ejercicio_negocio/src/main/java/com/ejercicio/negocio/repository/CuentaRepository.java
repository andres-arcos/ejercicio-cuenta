package com.ejercicio.negocio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ejercicio.negocio.entity.Cuenta;

@Repository
public interface CuentaRepository extends JpaRepository<Cuenta, Long>{

	List<Cuenta> findByClienteId(Long clienteId);
}
