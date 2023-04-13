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
		String complemento = customer.getComplemento();
		if (complemento != null) {
			String trim = complemento.trim();
			if (trim == "") customer.setComplemento(null);
		}
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
		String complemento = customer.getComplemento();

		if (this.customerRepository.existsById(cpf)) {
			if (complemento != null) {
				String trim = complemento.trim();
				if (trim == "") customer.setComplemento(null);
			}
			customer.setCpf(cpf);
			return this.customerRepository.save(customer);
		}
		
		throw new NotFoundException("Cliente não encontrado em nossa base de dados");

	}
}
