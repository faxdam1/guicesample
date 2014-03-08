MANUAL DE INSTALACION

NOTA: Es un proyecto maven para java aplication, desarrollado en eclipse Kepler.
1.	Clone la aplicación a su repositorio local, y abra el proyecto en eclipse.
2.	Corra la clase principal TestingGuice.
3.	Ingrese los comandos que desee:

Google: para inyectar el CreditCardProcessor  tipo GoogleCreditCardProcessor al BillingService.
Paypal: para inyectar el CreditCardProcessor  tipo PaypalCreditCardProcessor al BillingService.
Salir: para detener la ejecución.

Dinamico: o cualquier otra opción para inyectar el CreditCardProcessor  dinamico que desee en el BillingService.

4.	Introduzca  en la ruta “C:/claseDinamica/com/simple/guice/” la clase “.class”   que desea inyectar dinámicamente como 
CreditCardProcessor  en tiempo de ejecución.

Tenga en cuenta que si se ingresa un comando diferente a los anteriores se inyectara por defecto la clase dinámica, 
pero si la clase dinámica no cumple con el contrato necesario para integrarse o no se puede encontrar se inyectara por defecto 
PaypalCreditCardProcessor.

CONTRATO DE INTEGRACION CLASE DINAMICA:
	1. La clase dinámica debe pertenecer a un paquete llamado  “package com.sample.guice;”.
	2. La clase dinámica debe implementar una interface llamada “CreditCardProcessor”.
	3. Debe poseer un constructor público que puede imprimir un texto de prueba.
	4. Debe poseer un método publico llamado toString() que retorne un String con la información getClass().getName().


NOTA: En el proyecto puede encontrar un ejemplo de la clase dinámica inyectada llamada DinamicoCreditCardProcessor la cual 
puede ser eliminada si se considera necesario.

Si desea cambiar la ruta de posición de la clase dinámica se pude modificar el método cargarClase() de la clase 
CreditCardProcessorProvider, para los files dirRoot y dirClass. Tenga en cuenta que se debe respetar la estructura 
“com/simple/guice/” del directorio.
