Feature: Test del registro en el sistema

@Scenario1
Scenario Outline: Registro suscessful de un usuario
	Given Un nombre, email, dos pwd para registrar
	When <nombre> <email>  y <pwd> <pwd2> se quiere registrar
	Then se registra correctamente
Examples:
	|nombre							| email														|	pwd 		| pwd2	|
	|"soynuevo"					|"soynuevo@gmail.com" 						| "1234" 	| "1234"|
	|"yotambiensoynuevo"|"yotambiensoynuevo@hotmail.com" 	| "4321"	| "4321"|
	
	
@Scenario2
Scenario Outline: Registro invalido de un usuario
	Given Un nombre, email, dos pwd para registrar
	When <nombre> <email>  y <pwd> <pwd2> se quiere registrar
	Then no consigue registrarse
Examples:
	|nombre							| email										|	pwd 		| pwd2	|
	|"noloconsigo"			|"noloconsigo@gmail.com" 	| "1234" 	| "4321"|
	|"suplantoid"				|"soynuevo@gmail.com" 	 	| "1234"	| "1234"|