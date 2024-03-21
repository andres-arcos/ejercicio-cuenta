package com.ejercicio.negocio.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ejercicio.negocio.dto.ClienteDto;
import com.ejercicio.negocio.entity.Cuenta;
import com.ejercicio.negocio.exception.EjercicioException;
import com.ejercicio.negocio.repository.CuentaRepository;

@Service
public class CuentaServiceImpl implements CuentaService {

	private final CuentaRepository repository;
	private final RestTemplate restTemplate;
	@Value("${url.seguridad.api}")
	private String clienteServiceUrl;

	public CuentaServiceImpl(CuentaRepository repository, RestTemplateBuilder restTemplateBuilder) {
		this.repository = repository;
		this.restTemplate = restTemplateBuilder.build();
	}

	@Override
	public Cuenta guardarDatosCuenta(Cuenta cuenta) throws EjercicioException {
		ClienteDto cliente = restTemplate.getForObject(clienteServiceUrl + "/"+cuenta.getClienteId(), ClienteDto.class);
		if (cliente == null) {
			throw new EjercicioException("Cliente no encontrado");
		}
		cuenta.setSaldoActual(cuenta.getSaldoInicial());
		//TODO validaciones por número de cuenta
		return repository.save(cuenta);
	}

	@Override
	public Optional<Cuenta> obtenerCuentaPorId(Long id) {
		return repository.findById(id);
	}

	@Override
	public void eliminarCuenta(Cuenta cuenta) {
		repository.delete(cuenta);
	}

}
