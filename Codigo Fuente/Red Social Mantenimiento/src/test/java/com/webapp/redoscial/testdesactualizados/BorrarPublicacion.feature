Feature: Test de borrado de publicaciones

@Scenario1
Scenario Outline: Borrar una publicacion correctamente
	Given Usuario en el muro quiere borrar una publicacion
	When <nombre> <email> es autor de <mensaje>
	Then borrado de publicacion correcto
	
Examples:
	|nombre							| email											|	mensaje 											|
	|"Fernando"					|"fernycozar@hotmail.com" 	| "Test de borrar publicacion"	|
	|"Mariap"						|"asdf@gmail.com" 					| "Test de borrar publicacion"					|
	
@Scenario2
Scenario Outline: Fallo al borrar una publicacion
	Given Usuario en el muro quiere borrar una publicacion
	When <nombre> <email> no es autor del <mensaje>
	Then borrado de publicacion incorrecto
	
Examples:
	|nombre							| email											|	mensaje 											|
	|"nosoyautor"				|"nolosoy@hotmail.com" 			| "Test de borrar publicacion"	|
	|"nosoyautor"				|"nolosoy@hotmail.com" 			| "Test de borrar publicacion"	|
