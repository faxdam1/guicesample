
package com.sample.guice;


public class DinamicoCreditCardProcessor implements CreditCardProcessor{
	
	public String toString(){
		return this.getClass().getName();
	}
	
	public DinamicoCreditCardProcessor() {
		System.out.println("DinamicoCreditCardProcessor Constructor");
	}

}
