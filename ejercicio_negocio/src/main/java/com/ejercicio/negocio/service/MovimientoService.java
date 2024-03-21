package com.ejercicio.negocio.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.ejercicio.negocio.dto.ReporteMovimientosDto;
import com.ejercicio.negocio.entity.Movimiento;
import com.ejercicio.negocio.exception.EjercicioException;

public interface MovimientoService {

	Movimiento guardarDatosMovimiento(Movimiento movimiento, Long cuentaId) throws EjercicioException;
	
	Movimiento actualizarMovimiento(Movimiento movimiento);

	Optional<Movimiento> obtenerMovimientoPorId(Long id);

	void eliminarMovimiento(Movimiento movimiento);
	
	List<ReporteMovimientosDto> obtenerReporteMovimientos(LocalDateTime inicio, LocalDateTime fin);
}
