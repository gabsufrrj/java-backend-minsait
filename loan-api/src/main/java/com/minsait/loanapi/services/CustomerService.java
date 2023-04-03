package com.minsait.loanapi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.minsait.loanapi.entities.Customer;
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
		return this.customerRepository.save(customer);
	}



	public List<Customer> findAllCustomer() {
		return this.customerRepository.findAll();
	}
	
	public Optional<Customer> findCustomerByCpf(Long cpf) {
		return this.customerRepository.findById(cpf);
	}



	public void deleteCustomer(Long cpf) {
		this.customerRepository.deleteById(cpf);
	}
}
