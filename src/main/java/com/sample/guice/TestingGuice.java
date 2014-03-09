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
	
		InputStreamReader inputStreamReader;
		BufferedReader bufferedReader;
		String strComando;
		boolean salir;
		Injector injector;
		BillingService billingService;
		
		inputStreamReader = new InputStreamReader(System.in);
		bufferedReader = new BufferedReader(inputStreamReader);
		salir = false;
		
		do {
			System.out.println("\nIntroduzca un comando: ");
			strComando = bufferedReader.readLine();
			strComando = strComando.toLowerCase();
			salir = strComando.equals("salir");
			
			if(salir) {
				System.out.println("Ejemplo finalizado.");		
			} else {
				injector = Guice.createInjector(new BillingModule(strComando));
				
				billingService = injector.getInstance(RealBillingService.class);
				
				System.out.println("RealBillingService : " + billingService);
			}
		} while(!salir);
	}
}
