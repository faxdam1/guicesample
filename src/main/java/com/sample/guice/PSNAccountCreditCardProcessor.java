package com.sample.guice;

public class PSNAccountCreditCardProcessor implements CreditCardProcessor {
	
	public String toString() {
		return this.getClass().getName();
	}
	
	public PSNAccountCreditCardProcessor() {
		System.out.println("PSNAccountCreditCardProcessor Constructor");
	}
}
