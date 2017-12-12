Feature: Test del borrado de cuenta del sistema

@Scenario1
Scenario Outline: Borrado de cuenta de usuario
	Given Un usuario quiere borrar su cuenta
	When <nombre> <email> <pwd> para borrar correctos
	Then se borra al usuario
	
Examples:
	|nombre							| email														|	pwd 		|
	|"meborraran"				|"meborraran@gmail.com" 					| "1234" 	|
	|"meborraran2"			|"meborraran@hotmail.com" 				| "4321"	|
	
	
@Scenario2
Scenario Outline: Borrado incorrecto de una cuenta de usuario
	Given Un usuario quiere borrar su cuenta
	When <nombre> <email> <pwd> para borrarincorrectos
	Then no se borra al usuario
Examples:
	|nombre							| email													|	pwd 		|
	|"nomeborraran"			|"nomeborraran@gmail.com" 			| "1234" 	|
	|"nomeborraran2"		|"nomeborraran@hotmail.com" 		| "4321"	|
