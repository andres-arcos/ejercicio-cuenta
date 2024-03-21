package com.ejercicio.negocio.entity;

import java.math.BigDecimal;

import com.ejercicio.negocio.enumerator.EstadoCuentaEnum;
import com.ejercicio.negocio.enumerator.TipoCuentaEnum;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity()
@Table(name="cuenta",schema = "negocio")
public class Cuenta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String numeroCuenta;
	@Enumerated(EnumType.STRING)
	private TipoCuentaEnum tipoCuenta;
	private BigDecimal saldoInicial;
	private BigDecimal saldoActual;
	@Enumerated(EnumType.STRING)
	private EstadoCuentaEnum estado;
	private Long clienteId;
	
	public Cuenta() {
		super();
	}

	public Cuenta(Long id, String numeroCuenta, TipoCuentaEnum tipoCuenta, BigDecimal saldoInicial,
			EstadoCuentaEnum estado) {
		super();
		this.id = id;
		this.numeroCuenta = numeroCuenta;
		this.tipoCuenta = tipoCuenta;
		this.saldoInicial = saldoInicial;
		this.estado = estado;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumeroCuenta() {
		return numeroCuenta;
	}

	public void setNumeroCuenta(String numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}

	public TipoCuentaEnum getTipoCuenta() {
		return tipoCuenta;
	}

	public void setTipoCuenta(TipoCuentaEnum tipoCuenta) {
		this.tipoCuenta = tipoCuenta;
	}

	public BigDecimal getSaldoInicial() {
		return saldoInicial;
	}

	public void setSaldoInicial(BigDecimal saldoInicial) {
		this.saldoInicial = saldoInicial;
	}

	public EstadoCuentaEnum getEstado() {
		return estado;
	}

	public void setEstado(EstadoCuentaEnum estado) {
		this.estado = estado;
	}

	public Long getClienteId() {
		return clienteId;
	}

	public void setClienteId(Long clienteId) {
		this.clienteId = clienteId;
	}

	public BigDecimal getSaldoActual() {
		return saldoActual;
	}

	public void setSaldoActual(BigDecimal saldoActual) {
		this.saldoActual = saldoActual;
	}

}
