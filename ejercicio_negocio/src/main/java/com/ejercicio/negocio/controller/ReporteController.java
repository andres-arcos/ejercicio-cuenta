package com.ejercicio.negocio.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ejercicio.negocio.dto.ReporteMovimientosDto;
import com.ejercicio.negocio.service.MovimientoService;

@RestController
@RequestMapping(path = "api/v1/reportes")
public class ReporteController {
	
	private final MovimientoService service;

	public ReporteController(MovimientoService service) {
		this.service = service;
	}

	@GetMapping()
	public ResponseEntity<List<ReporteMovimientosDto>> obtenerReporte(
			@RequestParam("fechaInicio") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fechaInicio,
			@RequestParam("fechaFin") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fechaFin) {
		LocalDateTime fechaInicioLDT = fechaInicio.atStartOfDay();
		LocalDateTime fechaFinLDT = fechaFin.atTime(23, 59, 59);

		List<ReporteMovimientosDto> movimientos = service.obtenerReporteMovimientos(fechaInicioLDT, fechaFinLDT);
		return new ResponseEntity<List<ReporteMovimientosDto>>(movimientos, HttpStatus.OK);
	}
}
