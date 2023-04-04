package com.minsait.loanapi.types;

import java.math.BigDecimal;

import com.minsait.loanapi.services.LoanService;


public enum Relacionamento {
	
	Bronze(1) {
		@Override
		public BigDecimal calculaValorFinal (BigDecimal valorInicial) {
			BigDecimal multiplicador = new BigDecimal("1.80");
			return valorInicial.multiply(multiplicador);
		}
	},
	
	Prata(2) {
		@Override
		public BigDecimal calculaValorFinal (BigDecimal valorInicial) {
			BigDecimal valueToCompare = new BigDecimal("5000");
			if (valorInicial.compareTo(valueToCompare) == 1) {
				BigDecimal multiplicador = new BigDecimal("1.40");
				return valorInicial.multiply(multiplicador);
			} 
			
			else {
				BigDecimal multiplicador = new BigDecimal("1.60");
				return valorInicial.multiply(multiplicador);

			}
		}
	};
	
//	Ouro(3) {
//		@Override
//		public BigDecimal calculaValorFinal (BigDecimal valorInicial) {
//			
//			
////			if () {
////				BigDecimal multiplicador = new BigDecimal("1.2");
////				return valorInicial.multiply(multiplicador);
////			} 
////			
////			else {
////				BigDecimal multiplicador = new BigDecimal("1.60");
////				return valorInicial.multiply(multiplicador);
////
////			}
//		}
//	};
	
	private Relacionamento(int codigo) {
		this.codigo = codigo;
	}

	private int codigo;
	
	
	public int getCodigo() {
		return this.codigo;
	}
	
	public abstract BigDecimal calculaValorFinal(BigDecimal valorInicial);
}
