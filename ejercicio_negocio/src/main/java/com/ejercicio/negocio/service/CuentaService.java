package com.ejercicio.negocio.service;

import java.util.Optional;

import com.ejercicio.negocio.entity.Cuenta;
import com.ejercicio.negocio.exception.EjercicioException;

public interface CuentaService {

	Cuenta guardarDatosCuenta(Cuenta cuenta) throws EjercicioException;

	Optional<Cuenta> obtenerCuentaPorId(Long id);

	void eliminarCuenta(Cuenta cuenta);
}
