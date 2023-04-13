package com.minsait.loanapi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.minsait.loanapi.entities.Customer;
import com.minsait.loanapi.exception.NotFoundException;
import com.minsait.loanapi.repository.CustomerRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

	private CustomerRepository customerRepository;

	@Autowired
	public CustomerService(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	public Customer createCustomer(Customer customer) {
		if (customer.getComplemento() == null) customer.setComplemento("");
		return this.customerRepository.save(customer);
	}

	public List<Customer> findAllCustomer() {
		return this.customerRepository.findAll();
	}

	public Optional<Customer> findCustomerByCpf(String cpf) throws NotFoundException {

		if (this.customerRepository.existsById(cpf)) {
			return this.customerRepository.findById(cpf);
		}

		throw new NotFoundException("Cliente não encontrado em nossa base de dados");

	}

	public void deleteCustomer(String cpf) throws NotFoundException {
		if (this.customerRepository.existsById(cpf)) {
			this.customerRepository.deleteById(cpf);
		} else {
			throw new NotFoundException("Cliente não encontrado em nossa base de dados");

		}
	}

	public Customer updateCustomer(String cpf, Customer customer) throws NotFoundException {
		if (this.customerRepository.existsById(cpf)) {
			customer.setCpf(cpf);
			if (customer.getComplemento() == null) customer.setComplemento("");
			return this.customerRepository.save(customer);
		}
		
		throw new NotFoundException("Cliente não encontrado em nossa base de dados");

	}
}
