Feature: Test de publicar en el sistema

@Scenario1
Scenario Outline: Hacer una publicacion correctamente
	Given Usuario en el muro quiere publicar
	When <nombre> <email> <mensaje> con privacidad publica
	Then publicacion publica correcta
	
Examples:
	|nombre							| email											|	mensaje 							|
	|"Fernando"					|"fernycozar@hotmail.com"		| "Test de publicacion"	|
	|"Mariap"						|"asdf@gmail.com" 					| "Test de publicacion"	|
