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
import org.springframework.web.bind.annotation.RestController;

import com.ejercicio.negocio.entity.Cuenta;
import com.ejercicio.negocio.exception.EjercicioException;
import com.ejercicio.negocio.service.CuentaService;

@RestController
@RequestMapping(path = "api/v1/cuentas")
public class CuentaController {

	private final CuentaService service;

	public CuentaController(CuentaService service) {
		this.service = service;
	}

	@PostMapping
	public ResponseEntity<?> registrarCuenta(@RequestBody Cuenta cuenta)  {
		try {
			return new ResponseEntity<Cuenta>(this.service.guardarDatosCuenta(cuenta), HttpStatus.CREATED);
		} catch (EjercicioException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<Cuenta> obtenerCuenta(@PathVariable("id") Long id) {
		Optional<Cuenta> cuenta = service.obtenerCuentaPorId(id);
		if (cuenta.isPresent()) {
			return new ResponseEntity<Cuenta>(cuenta.get(), HttpStatus.OK);
		}
		return ResponseEntity.notFound().build();
	}

	@PutMapping("/{id}")
	public ResponseEntity<Cuenta> actualizarCuenta(@PathVariable("id") Long id, @RequestBody Cuenta cuenta)
			throws EjercicioException {
		try {
			return new ResponseEntity<Cuenta>(this.service.guardarDatosCuenta(cuenta), HttpStatus.CREATED);
		} catch (EjercicioException e) {
			throw new EjercicioException(e.getMessage());
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> eliminarCuenta(@PathVariable("id") Long id) {
		Optional<Cuenta> cuenta = service.obtenerCuentaPorId(id);
		if (cuenta.isPresent()) {
			service.eliminarCuenta(cuenta.get());
		}
		return ResponseEntity.notFound().build();
	}

}
