package com.ejercicio.negocio.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ejercicio.negocio.dto.ClienteDto;
import com.ejercicio.negocio.dto.ReporteMovimientosDto;
import com.ejercicio.negocio.entity.Cuenta;
import com.ejercicio.negocio.entity.Movimiento;
import com.ejercicio.negocio.enumerator.TipoMovimientoEnum;
import com.ejercicio.negocio.exception.EjercicioException;
import com.ejercicio.negocio.repository.CuentaRepository;
import com.ejercicio.negocio.repository.MovimientoRepository;

@Service
public class MovimientoServiceImpl implements MovimientoService {

	private final MovimientoRepository repository;
	private final CuentaRepository cuentaRepository;
	private final RestTemplate restTemplate;
	@Value("${url.seguridad.api}")
	private String clienteServiceUrl;

	public MovimientoServiceImpl(MovimientoRepository repository, CuentaRepository cuentaRepository,
			RestTemplateBuilder restTemplateBuilder) {
		this.repository = repository;
		this.cuentaRepository = cuentaRepository;
		this.restTemplate = restTemplateBuilder.build();
	}

	@Override
	public Movimiento guardarDatosMovimiento(Movimiento movimiento, Long cuentaId) throws EjercicioException {
		Cuenta cuenta = cuentaRepository.findById(cuentaId)
				.orElseThrow(() -> new EjercicioException("Cuenta no existe"));
		if (movimiento.getValor().compareTo(cuenta.getSaldoActual()) > 0
				&& movimiento.getTipoMovimiento().equals(TipoMovimientoEnum.RETIRO)) {
			throw new EjercicioException("Saldo insuficiente");
		}
		cuenta.setSaldoActual(
				obtenerSaldoMovimiento(cuenta.getSaldoActual(), movimiento.getValor(), movimiento.getTipoMovimiento()));
		cuentaRepository.save(cuenta);
		movimiento.setSaldo(cuenta.getSaldoActual());
		movimiento.setCuenta(cuenta);
		return repository.save(movimiento);
	}

	@Override
	public Optional<Movimiento> obtenerMovimientoPorId(Long id) {
		return repository.findById(id);
	}

	@Override
	public void eliminarMovimiento(Movimiento movimiento) {
		repository.delete(movimiento);

	}

	@Override
	public Movimiento actualizarMovimiento(Movimiento movimiento) {
		return repository.save(movimiento);
	}

	private BigDecimal obtenerSaldoMovimiento(BigDecimal saldoActual, BigDecimal valor, TipoMovimientoEnum tipo) {
		if (tipo.equals(TipoMovimientoEnum.DEPOSITO)) {
			return saldoActual.add(valor);
		} else {
			return saldoActual.subtract(valor);
		}
	}

	@Override
	public List<ReporteMovimientosDto> obtenerReporteMovimientos(LocalDateTime inicio, LocalDateTime fin) {
		List<ReporteMovimientosDto> movientosReporte = new ArrayList<>();
		ParameterizedTypeReference<List<ClienteDto>> typeRef = new ParameterizedTypeReference<List<ClienteDto>>() {
		};
		ResponseEntity<List<ClienteDto>> responseEntity = restTemplate.exchange(clienteServiceUrl, HttpMethod.GET, null,
				typeRef);
		List<ClienteDto> clientes = responseEntity.getBody();
		for (ClienteDto dto : clientes) {
			List<Cuenta> cuentasPorCliente = cuentaRepository.findByClienteId(dto.getId());
			for (Cuenta cuentaDto : cuentasPorCliente) {
				List<Movimiento> movimientosPorCuenta = repository.findByCuenta_Id(cuentaDto.getId());
				List<Movimiento> movimientosFiltrados = movimientosPorCuenta.stream().filter(
						movimiento -> !movimiento.getFecha().isBefore(inicio) && !movimiento.getFecha().isAfter(fin))
						.collect(Collectors.toList());
				for (Movimiento movimientoDto : movimientosFiltrados) {
					movientosReporte.add(convertirDtoReporte(movimientoDto, dto));
				}

			}

		}
		return movientosReporte;
	}

	private ReporteMovimientosDto convertirDtoReporte(Movimiento movimiento, ClienteDto cliente) {
		return new ReporteMovimientosDto(cliente.getNombres(), movimiento.getCuenta().getNumeroCuenta(),
				movimiento.getCuenta().getSaldoActual(), movimiento.getValor(), movimiento.getCuenta().getTipoCuenta(),
				movimiento.getTipoMovimiento(), movimiento.getFecha());

	}

}
