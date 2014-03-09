[MANUAL DE INSTALACION]
=======================

NOTA: Es un proyecto maven para java aplication, desarrollado en eclipse Kepler.

	1. Clone la aplicación a su repositorio local, y abra el proyecto en eclipse.
	2. Corra la clase principal TestingGuice como una Java Application.
	3. Ingrese alguno de los siguientes comandos en la consola seguidos de la tecla enter:

	- Paypal: para inyectar el CreditCardProcessor tipo PaypalCreditCardProcessor al BillingService.
	- Google: para inyectar el CreditCardProcessor tipo GoogleCreditCardProcessor al BillingService.
	- PSN: para inyectar el CreditCardProcessor tipo PSNAccountCreditCardProcessor al BillingService.
	- Dinamico: o cualquier otra opción para inyectar el CreditCardProcessor dinamico que desee en el BillingService.
	- Salir: para detener la ejecución.

	4. Para probar la carga dinámica, sin salirse de la ejecución del TestingGuice introduzca en la ruta “C:/claseDinamica/com/sample/guice/” la clase “.class” que desea inyectar dinámicamente como CreditCardProcessor en tiempo de ejecución.

Tenga en cuenta que si se ingresa un comando diferente a los anteriores se inyectara por defecto la clase dinámica,pero si la clase dinámica no cumple con el contrato necesario para integrarse o no se puede encontrar se inyectara por defecto PaypalCreditCardProcessor.

[CONTRATO DE INTEGRACION CLASE DINAMICA]
=========================================
	1. La clase dinámica debe pertenecer a un paquete llamado  “com.sample.guice;”.
	2. La clase dinámica debe implementar una interface llamada “CreditCardProcessor”.
	3. Debe poseer un constructor público que puede imprimir un texto de prueba.
	4. Debe poseer un método publico llamado toString() que retorne un String con la información getClass().getName().

NOTA: En el proyecto puede encontrar un ejemplo de la clase dinámica inyectada llamada DinamicoCreditCardProcessor la cual 
puede ser eliminada si se considera necesario.

Si desea cambiar la ruta de posición de la clase dinámica se pude modificar el método cargarClase() de la clase 
CreditCardProcessorProvider, para los files dirRoot y dirClass. Tenga en cuenta que se debe respetar la estructura 
“com/sample/guice/” del directorio.

[MANUAL TÉCNICO]
================
En general para realizar la inyección de dependencias se utilizó GUICE 3.0.

Para el ejemplo se tiene un la implementación de un servicio de cobro (RealBillingService) en donde se especifica la inyección 
de dependencias en su constructor por medio de la anotación @Inject, con esto decimos que cuando se cree una instancia de 
RealBillingService se busque la disponibilidad de instancias para inyectar las dependencias de CreditCardProcessor y TransactionLog.

Para la dependencia de TransactionLog solo se tiene una implementación llamada DatabaseTransactionLog.

Para la dependencia de CreditCardProcessor se tienen cuatro implementaciones llamadas:
PaypalCreditCardProcessor
GoogleCreditCardProcessor
PSNAccountCreditCardProcessor
DinamicoCreditCardProcessor

Se tiene la clase BillingModule que extiende de AbstractModule de Guice y se encarga de definir las reglas de binding para realizar 
la inyección de la instancia de la clase concreta cuando se solicite por medio del @Inject. Aquí se definió una sola regla cuando se 
requiera una instancia de TransactionLog que retornará la implementación DatabaseTransactionLog, pero para CreditCardProcessor se 
determina la regla de binding por medio del tipo recibido en el constructor del módulo de forma que:

	- Si tipo = paypal entonces retornará la implementación PaypalCreditCardProcessor
	- Si tipo = google entonces retornará la implementación GoogleCreditCardProcessor
	- Si tipo = psn entonces retornará la implementación PSNAccountCreditCardProcessor
	- Si tipo tiene otro valor entonces retornará la implementación retornada por el CreditCardProcessorProvider

El CreditCardProcessorProvider implementa la carga de la clase que es la implementación concreta de CreditCardProcessor de forma que 
se pueda realizar dinámicamente en tiempo de ejecución y cuando se requiera inyectar la dependencia y en caso de que no se pueda obtener 
una instancia de la clase cargada dinámicamente se retorna una instancia de PaypalCreditCardProcessor.


El código cliente que contiene el código para ejecutar la prueba se encuentra en la clase TestingGuice en donde la entrada digitada por 
el usuario determina la regla de negocio para la regla de binding a usar en la inyección de la implementación para el CreditCardProcessor.
