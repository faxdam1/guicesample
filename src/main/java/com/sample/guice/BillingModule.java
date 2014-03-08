package com.sample.guice;

import java.util.logging.Logger;

import com.google.inject.AbstractModule;
import com.google.inject.Provider;
import com.google.inject.name.Names;

public class BillingModule extends AbstractModule {
	
	private String strType;
	public BillingModule(String strType){
		this.strType=strType;		
	}
	
	@Override
	protected void configure() {
		bind(TransactionLog.class).to(DatabaseTransactionLog.class);
		
		if(strType.equals("paypal")){
			bind(CreditCardProcessor.class).to(PaypalCreditCardProcessor.class);
		}
		else
		if(strType.equals("google")){
			bind(CreditCardProcessor.class).to(GoogleCreditCardProcessor.class);
		}
		else{
			
			bind(CreditCardProcessor.class).toProvider(CreditCardProcessorProvider.class);		
		}
		
	}
	
	
}