package com.minsait.loanapi.entities;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Range;

@Entity
public class Customer {
	
	@Id
	@Pattern(regexp="^[0-9]{11}$", message="O campo CPF precisa ter 11 dígitos.")
	private String cpf;
	
	@OneToMany(mappedBy="cpfCliente")
	private List<Loan> loansList;
	
	@NotBlank
	@Pattern(regexp="^[a-zA-Z0-9\\s]{3,}",message="O campo Nome Completo precisa ter 3 ou mais dígitos.")
	private String nome;
	
	@NotBlank
	@Pattern(regexp = "^[0-9]{10,11}", message="O campo Telefone precisa ter 10 ou 11 dígitos.")
	private String telefone;
	
	@NotNull
	@Range(min = 0)
	private BigDecimal rendimentoMensal;
	
	@NotBlank
	@Pattern(regexp="^[a-zA-Z0-9\\s]{4,}",message="O campo Rua precisa ter 4 ou mais dígitos.")
	private String rua;
	
	@NotBlank
	private String numero;
	
	@NotBlank
	@Pattern(regexp="^[0-9]{8}$", message="O campo CEP deve ter exatamente 8 dígitos.")
	private String cep;
	
	private String complemento;
	
	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getCpf() {
		return cpf;
	}
	
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getTelefone() {
		return telefone;
	}
	
	public void setTelefone(String telefone) {
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

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}
	
	
}
