package com.sample.guice;

import com.google.inject.AbstractModule;

public class BillingModule extends AbstractModule {
	
	private String tipo;
	
	public BillingModule(String tipo) {
		this.tipo = tipo;		
	}
	
	@Override
	protected void configure() {
		bind(TransactionLog.class).to(DatabaseTransactionLog.class);
		
		if(tipo.equals("paypal")) {
			bind(CreditCardProcessor.class).to(PaypalCreditCardProcessor.class);
		} else if(tipo.equals("google")) {
			bind(CreditCardProcessor.class).to(GoogleCreditCardProcessor.class);
		} else if(tipo.equals("psn")) {
			bind(CreditCardProcessor.class).to(PSNAccountCreditCardProcessor.class);
		} else {
			bind(CreditCardProcessor.class).toProvider(CreditCardProcessorProvider.class);
		}
	}
}