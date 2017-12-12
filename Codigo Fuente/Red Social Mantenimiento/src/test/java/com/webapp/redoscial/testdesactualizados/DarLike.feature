Feature: Dar me gusta y quitar me gusta a publicaciones

@Scenario1
Scenario Outline: Me gusta correctamente una publicacion
	Given Usuario conectado quiere dar me gusta
	When No le gusta a <email>
	Then Le gusta publicacion
Examples:
	| email 										|
	|	"fernycozar@hotmail.com"	|
	| "asdf@gmail.com" 					|
	
	
@Scenario2
Scenario Outline: Me gusta erroneamente una publicacion
	Given Usuario conectado quiere dar me gusta
	When Le gusta a <email>
	Then No dar me gusta
Examples:	
	| email 										|
	|	"fernycozar@hotmail.com"	|
	| "asdf@gmail.com" 					|
	

@Scenario3
Scenario Outline: Dejar de gustar una publicacion
	Given Usuario conectado quiere quitar me gusta
	When Le gusta a <email>
	Then Dejar de gustar publicacion
Examples:	
	| email 										|
	|	"fernycozar@hotmail.com"	|
	| "asdf@gmail.com" 					|
	
	
@Scenario4
Scenario Outline: Dejar de gustar una publicacion erroneamente
	Given Usuario conectado quiere quitar me gusta
	When No le gusta a <email>
	Then No quitar me gusta
Examples:	
	| email 										|
	|	"fernycozar@hotmail.com"	|
	| "asdf@gmail.com" 					|