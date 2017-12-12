Feature: Test de modificacion de publicaciones

@Scenario1
Scenario Outline: Editar una publicacion correctamente
	Given Usuario en el muro quiere editar
	When Cuando coinciden <nombre> <email> y <nombre2> <email2>  para modificar <mensaje>
	Then sustituir la publicacion por <mensaje2>
	
Examples:
	|nombre							| email											|	mensaje 							| nombre2							| email2										|	mensaje2 							| 
	|"Fernando"					|"fernycozar@hotmail.com"		| "Test de publicacion"	| "Fernando"					|"fernycozar@hotmail.com"		| "Test editado"				|
	|"Mariap"						|"asdf@gmail.com" 					| "Test de publicacion"	| "Mariap"						|"asdf@gmail.com" 					| "Test editado"				|

@Scenario2
Scenario Outline: Editar una publicacion incorrectamente
	Given Usuario en el muro quiere editar
	When Cuando no coinciden <nombre> <email> y <nombre2> <email2>  para modificar <mensaje>
	Then no sustituir la publicacion por <mensaje2>
	
Examples:
	|nombre							| email											|	mensaje 							| nombre2							| email2										|	mensaje2 							| 
	|"Fernando"					|"fernycozar@hotmail.com"		| "Test de publicacion"	| "Fernando2"					|"fernycozar2@hotmail.com"	| "Test editado"				|
	|"Mariap"						|"asdf@gmail.com" 					| "Test de publicacion"	| "Mariap2"						|"asdf2@gmail.com" 					| "Test editado"				|

