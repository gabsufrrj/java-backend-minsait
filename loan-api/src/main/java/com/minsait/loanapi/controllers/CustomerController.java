package com.minsait.loanapi.controllers;

import javax.validation.Valid;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.minsait.loanapi.entities.Customer;
import com.minsait.loanapi.exception.NotFoundException;
import com.minsait.loanapi.services.CustomerService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/clientes")
public class CustomerController {
	
	private CustomerService customerService;
	
		
	public CustomerController(CustomerService customerService) {
		this.customerService = customerService;
	}


	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Customer createClient(@Valid @RequestBody Customer customer) {
		return this.customerService.createCustomer(customer);
	}
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<Customer> findAllCustomers() {
		return this.customerService.findAllCustomer();
	}
	
	@GetMapping("/{cpf}")
	@ResponseStatus(HttpStatus.OK)
	public Optional<Customer> findCustomerByCpf(@PathVariable Long cpf) throws NotFoundException {
		try {
			return this.customerService.findCustomerByCpf(cpf);
		} catch (NotFoundException e) {
			System.out.println(e.getMessage());
			throw new NotFoundException("Cliente não encontrado em nossa base de dados.");
		}
	}
	
	@DeleteMapping("/{cpf}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public boolean deleteCustomer(@PathVariable Long cpf) throws NotFoundException {
		try {
			this.customerService.deleteCustomer(cpf);
			return true;
		} catch (NotFoundException e) {
			System.out.println(e.getMessage());
			throw new NotFoundException("Cliente não encontrado em nossa base de dados.");
		}
	}
	
	@PutMapping("/{cpf}")
	@ResponseStatus(HttpStatus.OK)
	public Customer updateCustomer(@PathVariable Long cpf, @Valid @RequestBody Customer customer) throws NotFoundException {
		try {
			return this.customerService.updateCustomer(cpf, customer);	
			
		} catch (NotFoundException e) {
			System.out.println(e.getMessage());
			throw new NotFoundException("Cliente não encontrado em nossa base de dados.");
		}
	}
}
