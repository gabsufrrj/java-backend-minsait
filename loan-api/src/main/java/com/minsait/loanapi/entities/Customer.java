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
	
//	private Endereco endereco;
	
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

//	public Endereco getEndereco() {
//		return endereco;
//	}
//
//	public void setEndereco(Endereco endereco) {
//		this.endereco = endereco;
//	}
	
	
}
