package com.sample.guice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.google.inject.Guice;
import com.google.inject.Injector;

public class TestingGuice {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
	
		
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader (isr);
		String strComando="";
		Injector injector;
		BillingService billingService;
		
		
		while(!strComando.equals("salir")){
			System.out.println("\nIntroduzca un comando: ");
			strComando = br.readLine();
			if(!strComando.equals("salir")){
				injector = Guice.createInjector(new BillingModule(strComando.toLowerCase()));
				billingService = injector.getInstance(RealBillingService.class);
				System.out.println("RealBillingService : "+billingService);		
			}
				
		}
	  
	}

}
