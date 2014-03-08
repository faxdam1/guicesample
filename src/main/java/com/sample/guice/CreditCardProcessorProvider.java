package com.sample.guice;
import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;

import com.google.inject.Provider;

public class CreditCardProcessorProvider implements Provider<CreditCardProcessor>{
	
	public CreditCardProcessor get() {
		try{
			
	     
		 return (CreditCardProcessor)cargarClase();
		
		}catch(Exception ex){  }
		
	   return null;
	}
	
	
	public Object cargarClase(){
		try {
			File dirRoot = new File("C:/claseDinamica/");
		    URL url = dirRoot.toURL();  
		    URL[] urls = new URL[]{url};
		    ClassLoader cl = new URLClassLoader(urls);
		    
		    File dirClass = new File("C:/claseDinamica/com/sample/guice");
		    String[] lista=dirClass.list();
		
		    Class<?> cls = cl.loadClass("com.sample.guice."+lista[0].replace(".class", ""));
		    return cls.newInstance();
		} catch (Exception e) {}	
		
		return new PaypalCreditCardProcessor();
		
	}
	
	
}
