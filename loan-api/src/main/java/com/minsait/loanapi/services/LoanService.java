package com.minsait.loanapi.services;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.minsait.loanapi.entities.Loan;
import com.minsait.loanapi.exception.NotAuthorizedException;
import com.minsait.loanapi.exception.NotFoundException;
import com.minsait.loanapi.repository.CustomerRepository;
import com.minsait.loanapi.repository.LoanRepository;


@Service
public class LoanService {
	
	private LoanRepository loanRepository;
	private CustomerRepository customerRepository;
	
	@Autowired
	public LoanService(LoanRepository loanRepository, CustomerRepository customerRepository) {
		this.loanRepository = loanRepository;
		this.customerRepository = customerRepository;
	}
	
	public boolean calculaValorTotalDeEmprestimos(BigDecimal rendimentoMensal, BigDecimal valorInicial) {
		BigDecimal TEN = new BigDecimal("10");
		BigDecimal valorTotal = rendimentoMensal.multiply(TEN);
		
		int comparaValores = valorInicial.compareTo(valorTotal);
		
		
		if (comparaValores == 1) {
			return false;
		}
		
		return true;
	}
	
	public BigDecimal calculaSaldoCliente(String cpf) {
		List<Loan> loans = findAllCustomersLoans(cpf);
		
		List<BigDecimal> valores = 	loans.stream()
				.map(loan -> loan.getValorFinal())
				.collect(Collectors.toList());
				
		BigDecimal ZERO = new BigDecimal("0");
		BigDecimal soma = valores.stream().reduce(ZERO, (acc, curr) -> acc.add(curr));
		
//		System.out.println(soma);
		return soma;
	}
	
	public boolean validacaoFinal(BigDecimal rendimentoMensal, BigDecimal valorInicial, BigDecimal soma) {
		BigDecimal TEN = new BigDecimal("10");
		BigDecimal valorTotal = rendimentoMensal.multiply(TEN);
		
//		System.out.println("CALCULO " + valorTotal.subtract(soma));
		
		int compare = valorTotal.subtract(soma).compareTo(valorInicial);
		
		if (compare == 1 || compare == 0) return true;
		
		return false;
		
	}
	
	
	public Loan createLoan(Loan loan, String cpf) throws NotFoundException, NotAuthorizedException {
		if (this.customerRepository.existsById(cpf)) {
			loan.setCpfCliente(cpf);
			
			
			
			BigDecimal rendimentoMensal = this.customerRepository.findById(cpf).get().getRendimentoMensal();
			
			boolean validacaoDeRendimento = calculaValorTotalDeEmprestimos(rendimentoMensal, loan.getValorInicial());
			
			if (validacaoDeRendimento) {
				int numEmprestimos = calculaNumeroDeEmprestimos(cpf);
//				System.out.println("EMPRESTIMOOOOOOOS " + numEmprestimos);
				BigDecimal saldo = calculaSaldoCliente(cpf);
				loan.setValorFinal(numEmprestimos);
				
				boolean validacaoFinal = validacaoFinal(rendimentoMensal, loan.getValorInicial(), saldo);
				
				if (validacaoFinal) return this.loanRepository.save(loan);
				
				throw new NotAuthorizedException("Valor de Empréstimo não autorizado");
						
			}
			
			
			throw new NotAuthorizedException("Valor de Empréstimo não autorizado");
			
		}
		
		throw new NotFoundException("Cliente não encontrado na base de dados");
	}
	
	public List<Loan> findAllCustomersLoans(String cpf) {
		List<Loan> loanList = this.loanRepository.findAll();
		return loanList.stream()
			.filter(el -> cpf.equals(el.getCpfCliente()))
				.collect(Collectors.toList());
	}

	public void deleteLoan(Long id) throws NotFoundException {
		if (this.loanRepository.existsById(id)) {
			this.loanRepository.deleteById(id);
		} else {
			throw new NotFoundException("Id da transação não encontrado em nossa base de dados");

		}
	}

	public Optional<Loan> findLoanById(Long id) throws NotFoundException {

		if (this.loanRepository.existsById(id)) {
			return this.loanRepository.findById(id);
		}

		throw new NotFoundException("Cliente não encontrado em nossa base de dados");

	}
	
	public int calculaNumeroDeEmprestimos(String cpf) {
		return findAllCustomersLoans(cpf).size();
	}
}
