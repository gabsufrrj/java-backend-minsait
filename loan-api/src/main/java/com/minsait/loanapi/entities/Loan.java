package com.minsait.loanapi.entities;

import java.math.BigDecimal;
import java.security.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;

import com.minsait.loanapi.types.Relacionamento;

@Entity
public class Loan {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Long cpfCliente;
	
	@NotNull
	@Range(min = 0)
	private BigDecimal valorInicial;
	
	@Range(min = 0)
	private BigDecimal valorFinal;
	
	private Relacionamento relacionamento;
	
	@NotBlank
	private String dataInicial;
	
	@NotBlank
	private String dataFinal;

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

	public void setValorFinal(int numEmprestimos) {
		this.valorFinal = this.relacionamento.calculaValorFinal(this.valorInicial, numEmprestimos);
	}

	public Relacionamento getRelacionamento() {
		return relacionamento;
	}

	public void setRelacionamento(Relacionamento relacionamento) {
		this.relacionamento = relacionamento;
	}

	public String getDataInicial() {
		return dataInicial;
	}

	public void setDataInicial(String dataInicial) {
		this.dataInicial = dataInicial;
	}

	public String getDataFinal() {
		return dataFinal;
	}

	public void setDataFinal(String dataFinal) {
		this.dataFinal = dataFinal;
	}	
}
