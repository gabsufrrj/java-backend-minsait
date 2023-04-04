package com.minsait.loanapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.minsait.loanapi.entities.Loan;


public interface LoanRepository extends JpaRepository<Loan, Long>{

}
