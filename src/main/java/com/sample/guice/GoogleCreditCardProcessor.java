
package com.sample.guice;

public class GoogleCreditCardProcessor implements CreditCardProcessor {
	
	public String toString() {
		return this.getClass().getName();
	}
	
	public GoogleCreditCardProcessor() {
		System.out.println("GoogleCreditCardProcessor Constructor");
	}
}
