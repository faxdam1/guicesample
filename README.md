[MANUAL DE INSTALACION]
=======================

NOTA: Es un proyecto maven para java aplication, desarrollado en eclipse Kepler.
1. Clone la aplicaci�n a su repositorio local, y abra el proyecto en eclipse.
2. Corra la clase principal TestingGuice como una Java Application.
3. Ingrese alguno de los siguientes comandos en la consola seguidos de la tecla enter:

Paypal: para inyectar el CreditCardProcessor tipo PaypalCreditCardProcessor al BillingService.
Google: para inyectar el CreditCardProcessor tipo GoogleCreditCardProcessor al BillingService.
PSN: para inyectar el CreditCardProcessor tipo PSNAccountCreditCardProcessor al BillingService.
Dinamico: o cualquier otra opci�n para inyectar el CreditCardProcessor dinamico que desee en el BillingService.
Salir: para detener la ejecuci�n.

4. Para probar la carga din�mica, sin salirse de la ejecuci�n del TestingGuice introduzca en la ruta 
�C:/claseDinamica/com/sample/guice/� la clase �.class� que desea inyectar din�micamente como CreditCardProcessor 
en tiempo de ejecuci�n.

Tenga en cuenta que si se ingresa un comando diferente a los anteriores se inyectara por defecto la clase din�mica, 
pero si la clase din�mica no cumple con el contrato necesario para integrarse o no se puede encontrar se inyectara 
por defecto PaypalCreditCardProcessor.

[CONTRATO DE INTEGRACION CLASE DINAMICA]
=========================================
	1. La clase din�mica debe pertenecer a un paquete llamado  �com.sample.guice;�.
	2. La clase din�mica debe implementar una interface llamada �CreditCardProcessor�.
	3. Debe poseer un constructor p�blico que puede imprimir un texto de prueba.
	4. Debe poseer un m�todo publico llamado toString() que retorne un String con la informaci�n getClass().getName().

NOTA: En el proyecto puede encontrar un ejemplo de la clase din�mica inyectada llamada DinamicoCreditCardProcessor la cual 
puede ser eliminada si se considera necesario.

Si desea cambiar la ruta de posici�n de la clase din�mica se pude modificar el m�todo cargarClase() de la clase 
CreditCardProcessorProvider, para los files dirRoot y dirClass. Tenga en cuenta que se debe respetar la estructura 
�com/sample/guice/� del directorio.

[MANUAL T�CNICO]
================
En general para realizar la inyecci�n de dependencias se utiliz� GUICE 3.0.

Para el ejemplo se tiene un la implementaci�n de un servicio de cobro (RealBillingService) en donde se especifica la inyecci�n 
de dependencias en su constructor por medio de la anotaci�n @Inject, con esto decimos que cuando se cree una instancia de 
RealBillingService se busque la disponibilidad de instancias para inyectar las dependencias de CreditCardProcessor y TransactionLog.

Para la dependencia de TransactionLog solo se tiene una implementaci�n llamada DatabaseTransactionLog.

Para la dependencia de CreditCardProcessor se tienen cuatro implementaciones llamadas:
PaypalCreditCardProcessor
GoogleCreditCardProcessor
PSNAccountCreditCardProcessor
DinamicoCreditCardProcessor

Se tiene la clase BillingModule que extiende de AbstractModule de Guice y se encarga de definir las reglas de binding para realizar 
la inyecci�n de la instancia de la clase concreta cuando se solicite por medio del @Inject. Aqu� se defini� una sola regla cuando se 
requiera una instancia de TransactionLog que retornar� la implementaci�n DatabaseTransactionLog, pero para CreditCardProcessor se 
determina la regla de binding por medio del tipo recibido en el constructor del m�dulo de forma que:
Si tipo = paypal entonces retornar� la implementaci�n PaypalCreditCardProcessor
Si tipo = google entonces retornar� la implementaci�n GoogleCreditCardProcessor
Si tipo = psn entonces retornar� la implementaci�n PSNAccountCreditCardProcessor
Si tipo tiene otro valor entonces retornar� la implementaci�n retornada por el CreditCardProcessorProvider

El CreditCardProcessorProvider implementa la carga de la clase que es la implementaci�n concreta de CreditCardProcessor de forma que 
se pueda realizar din�micamente en tiempo de ejecuci�n y cuando se requiera inyectar la dependencia y en caso de que no se pueda obtener 
una instancia de la clase cargada din�micamente se retorna una instancia de PaypalCreditCardProcessor.


El c�digo cliente que contiene el c�digo para ejecutar la prueba se encuentra en la clase TestingGuice en donde la entrada digitada por 
el usuario determina la regla de negocio para la regla de binding a usar en la inyecci�n de la implementaci�n para el CreditCardProcessor.
