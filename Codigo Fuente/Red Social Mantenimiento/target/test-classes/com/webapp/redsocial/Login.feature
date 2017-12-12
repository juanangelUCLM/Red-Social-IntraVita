Feature: Test del login en el sistema

@Scenario1
Scenario Outline: Login suscessful de un usuario
	Given Un usuario y password para login
	When <email>  y <pwd> se quiere loguear
	Then se loguea correctamente
Examples:
	| email										|	pwd 								|
	|"asdf@gmail.com" 				| "1234" 							|
	|"fernycozar@hotmail.com" | "supersecreto"			|
	
	
@Scenario2
Scenario Outline: Login invalido de un usuario
	Given Un usuario y password para login
	When <email>  y <pwd> se quiere loguear
	Then no consigue loguearse
Examples:
	| email										|	pwd 								|
	|"meloinvento		" 				| "1asdfasdf4" 				|
	|"fasdfasdfasdfasdfasdfa" | "asdfasdfasdfasdf"	|