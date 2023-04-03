package com.minsait.loanapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.minsait.loanapi.entities.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long>{

}
