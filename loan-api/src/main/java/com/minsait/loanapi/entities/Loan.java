package com.minsait.loanapi.entities;

import java.math.BigDecimal;
import java.security.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;

@Entity
public class Loan {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Long cpfCliente;
	
	@NotNull
	@Range(min = 0)
	private BigDecimal valorInicial;
	
	@NotNull
	@Range(min = 0)
	private BigDecimal valorFinal;
	
	@NotBlank
	private String relacionamento;
	
	@NotNull
	@Range(min = 0)
	private Long dataInicial;
	
	@NotNull
	@Range(min = 0)
	private Long dataFinal;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCpfCliente() {
		return cpfCliente;
	}

	public void setCpfCliente(Long cpfCliente) {
		this.cpfCliente = cpfCliente;
	}

	public BigDecimal getValorInicial() {
		return valorInicial;
	}

	public void setValorInicial(BigDecimal valorInicial) {
		this.valorInicial = valorInicial;
	}

	public BigDecimal getValorFinal() {
		return valorFinal;
	}

	public void setValorFinal(BigDecimal valorFinal) {
		this.valorFinal = valorFinal;
	}

	public String getRelacionamento() {
		return relacionamento;
	}

	public void setRelacionamento(String relacionamento) {
		this.relacionamento = relacionamento;
	}

	public Long getDataInicial() {
		return dataInicial;
	}

	public void setDataInicial(Long dataInicial) {
		this.dataInicial = dataInicial;
	}

	public Long getDataFinal() {
		return dataFinal;
	}

	public void setDataFinal(Long dataFinal) {
		this.dataFinal = dataFinal;
	}

	
	
}
