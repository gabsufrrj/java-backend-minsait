package com.minsait.loanapi.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.minsait.loanapi.entities.Loan;
import com.minsait.loanapi.exception.NotAuthorizedException;
import com.minsait.loanapi.exception.NotFoundException;
import com.minsait.loanapi.services.LoanService;

@RestController
@RequestMapping("api/v1/clientes/{cpf}/emprestimos")

public class LoanController {
	
	private LoanService loanService;
	
	
	public LoanController(LoanService loanService) {
		this.loanService = loanService;
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Loan createLoan(@Valid @RequestBody Loan loan, @PathVariable Long cpf) throws NotFoundException, NotAuthorizedException {
		try {
			return this.loanService.createLoan(loan, cpf);
		} catch (NotFoundException e) {
			System.out.println(e.getMessage());
			throw new NotFoundException("Cliente não encontrado em nossa base de dados.");
		}
	}
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<Loan> findAllCustomersLoans() {
		return this.loanService.findAllCustomersLoans();
	}
	
	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Optional<Loan> findLoanById(@PathVariable Long id) throws NotFoundException {
		try {
			return this.loanService.findLoanById(id);
		} catch (NotFoundException e) {
			System.out.println(e.getMessage());
			throw new NotFoundException("Id da transação não encontrado em nossa base de dados");
		}
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public boolean deleteLoan(@PathVariable Long id) throws NotFoundException {
		try {
			this.loanService.deleteLoan(id);
			return true;
		} catch (NotFoundException e) {
			System.out.println(e.getMessage());
			throw new NotFoundException("Id da transação não encontrado em nossa base de dados");
		}
	}
}
