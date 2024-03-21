package com.ejercicio.negocio.controller;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ejercicio.negocio.entity.Movimiento;
import com.ejercicio.negocio.exception.EjercicioException;
import com.ejercicio.negocio.service.MovimientoService;

@RestController
@RequestMapping(path = "api/v1/movimientos")
public class MovimientoController {

	private final MovimientoService service;

	public MovimientoController(MovimientoService service) {
		this.service = service;
	}

	@PostMapping
	public ResponseEntity<?> registrarMovimiento(@RequestBody Movimiento movimiento, @RequestParam Long cuentaId) {
		try {
			return new ResponseEntity<Movimiento>(this.service.guardarDatosMovimiento(movimiento, cuentaId),
					HttpStatus.CREATED);

		} catch (EjercicioException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<Movimiento> obtenerMovimiento(@PathVariable("id") Long id) {
		Optional<Movimiento> movimiento = service.obtenerMovimientoPorId(id);
		if (movimiento.isPresent()) {
			return new ResponseEntity<Movimiento>(movimiento.get(), HttpStatus.OK);
		}
		return ResponseEntity.notFound().build();
	}

	@PutMapping("/{id}")
	public ResponseEntity<Movimiento> actualizarMovimiento(@PathVariable("id") Long id,
			@RequestBody Movimiento movimiento) {
		return new ResponseEntity<Movimiento>(this.service.actualizarMovimiento(movimiento), HttpStatus.CREATED);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> eliminarMovimiento(@PathVariable("id") Long id) {
		Optional<Movimiento> movimiento = service.obtenerMovimientoPorId(id);
		if (movimiento.isPresent()) {
			service.eliminarMovimiento(movimiento.get());
		}
		return ResponseEntity.notFound().build();
	}

}
