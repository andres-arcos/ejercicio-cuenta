package com.ejercicio.negocio.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.ejercicio.negocio.enumerator.TipoCuentaEnum;
import com.ejercicio.negocio.enumerator.TipoMovimientoEnum;

public class ReporteMovimientosDto {

	private String nombreCliente;
	private String numeroCuenta;
	private BigDecimal saldoCuenta;
	private BigDecimal valorMovimiento;
	private TipoCuentaEnum tipoCuenta;
	private TipoMovimientoEnum tipoMovimiento;
	private LocalDateTime fechaMovimiento;

	public ReporteMovimientosDto(String nombreCliente, String numeroCuenta, BigDecimal saldoCuenta,
			BigDecimal valorMovimiento, TipoCuentaEnum tipoCuenta, TipoMovimientoEnum tipoMovimiento,
			LocalDateTime fechaMovimiento) {
		super();
		this.nombreCliente = nombreCliente;
		this.numeroCuenta = numeroCuenta;
		this.saldoCuenta = saldoCuenta;
		this.valorMovimiento = valorMovimiento;
		this.tipoCuenta = tipoCuenta;
		this.tipoMovimiento = tipoMovimiento;
		this.fechaMovimiento = fechaMovimiento;
	}

	public String getNombreCliente() {
		return nombreCliente;
	}

	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}

	public String getNumeroCuenta() {
		return numeroCuenta;
	}

	public void setNumeroCuenta(String numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}

	public BigDecimal getSaldoCuenta() {
		return saldoCuenta;
	}

	public void setSaldoCuenta(BigDecimal saldoCuenta) {
		this.saldoCuenta = saldoCuenta;
	}

	public BigDecimal getValorMovimiento() {
		return valorMovimiento;
	}

	public void setValorMovimiento(BigDecimal valorMovimiento) {
		this.valorMovimiento = valorMovimiento;
	}

	public TipoCuentaEnum getTipoCuenta() {
		return tipoCuenta;
	}

	public void setTipoCuenta(TipoCuentaEnum tipoCuenta) {
		this.tipoCuenta = tipoCuenta;
	}

	public TipoMovimientoEnum getTipoMovimiento() {
		return tipoMovimiento;
	}

	public void setTipoMovimiento(TipoMovimientoEnum tipoMovimiento) {
		this.tipoMovimiento = tipoMovimiento;
	}

	public LocalDateTime getFechaMovimiento() {
		return fechaMovimiento;
	}

	public void setFechaMovimiento(LocalDateTime fechaMovimiento) {
		this.fechaMovimiento = fechaMovimiento;
	}

}
