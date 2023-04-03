package com.minsait.loanapi.entities;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;

@Entity
public class Customer {
	
	@Id
	private Long cpf;
	
	@NotBlank
	private String nome;
	
	@NotNull
	@Range(min = 0)
	private Long telefone;
	
	@NotNull
	@Range(min = 0)
	private BigDecimal rendimentoMensal;
	
	@NotBlank
	private String rua;
	
	@NotNull
	@Range(min = 0)
	private int numero;
	
	@NotNull
	@Range(min = 0)
	private Long cep;
	
	public Long getCpf() {
		return cpf;
	}
	
	public void setCpf(Long cpf) {
		this.cpf = cpf;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public Long getTelefone() {
		return telefone;
	}
	
	public void setTelefone(Long telefone) {
		this.telefone = telefone;
	}
	
	public BigDecimal getRendimentoMensal() {
		return rendimentoMensal;
	}
	
	public void setRendimentoMensal(BigDecimal rendimentoMensal) {
		this.rendimentoMensal = rendimentoMensal;
	}


	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public Long getCep() {
		return cep;
	}

	public void setCep(Long cep) {
		this.cep = cep;
	}
	
	
}
