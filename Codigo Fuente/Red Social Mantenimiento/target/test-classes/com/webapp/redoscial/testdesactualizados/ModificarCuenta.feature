Feature: Test de modificacion de cuenta de un usuario

@Scenario1
Scenario Outline: Modificacion de cuenta de usuario
	Given Un Usuario quiere modificar su cuenta
	When <nombre> <email> <pwd> usuario correctos
	Then <nombre> <email> <pwd> modificado correctamente
	
Examples:
	|nombre							| email														|	pwd 		|
	|"meeditaron"				|"meeditaron@gmail.com" 					| "1234" 	|
	|"meeditaron2"			|"meeditaron@hotmail.com" 				| "4321"	|
	
	
@Scenario2
Scenario Outline: Modificacion incorrecta de una cuenta de usuario
	Given Un Usuario quiere modificar su cuenta
	When  <nombre> <email> <pwd> usuario incorrectos
	Then usuario no sufre cambios
Examples:
	|nombre							| email													|	pwd 		|
	|"nomeeditaron"			|"nomeeditaron@gmail.com" 			| "1234" 	|
	|"nomeeditaron2"		|"nomeeditaron@hotmail.com" 		| "4321"	|
