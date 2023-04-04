package com.minsait.loanapi.services;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.minsait.loanapi.entities.Customer;
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
	
	
	public Loan createLoan(Loan loan, Long cpf) throws NotFoundException, NotAuthorizedException {
		if (this.customerRepository.existsById(cpf)) {
			loan.setCpfCliente(cpf);
			
			BigDecimal rendimentoMensal = this.customerRepository.findById(cpf).get().getRendimentoMensal();
			
			boolean validacaoDeRendimento = calculaValorTotalDeEmprestimos(rendimentoMensal, loan.getValorInicial());
			
			if (validacaoDeRendimento) {
				return this.loanRepository.save(loan);				
			}
			
			throw new NotAuthorizedException("Valor de Empréstimo não autorizado");
			
		}
		throw new NotFoundException("Cliente não encontrado na base de dados");
	}
	
	public List<Loan> findAllCustomersLoans() {
		return this.loanRepository.findAll();
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
}