@featureTest
Feature: Uso de cucumber en el que se va a probar el funcionamiento 
de una red social.

@Scenario1
Scenario: Login correcto de un usuario
	Given Un usuario y password 
	When usuario correcto y password correcta
	Then se loguea
	

@Scenario2
Scenario: Login incorrecto de usuario
	Given Un usuario y password
	When Usuario incorrecto y una password incorrecta
	Then no se loguea
	
@Scenario3
Scenario: Registro correcto de un usuario
	Given Un nombre, un email, una pwduno y una pwddos
	When pwduno es igual a pwddos
	Then se registra
	
@Scenario4
Scenario: Registro incorrecto de un usuario
	Given Un nombre, un email, una pwduno y una pwddos
	When pwduno es distinto a pwddos
	Then no se registra
	
@Scenario5
Scenario: Registro incorrecto de un usuario
	Given Un nombre, un email, una pwduno y una pwddos
	When email ya esta en la base de datos
	Then no se registra
	
@Scenario6
Scenario: Borrado de cuenta de usuario
	Given Un idUsuario
	When datos correctos
	Then se borra
	
@Scenario7
Scenario: Modificacion de cuenta de usuario
	Given Un Usuario
	When datos usuario correctos
	Then se modifica usuario
	
@Scenario8
Scenario: Hacer una publicacion
	Given Usuario en wall
	When escribes publicacion
	Then publicas
	
@Scenario9
Scenario: Borrar una publicacion
	Given Un idpublicacion
	When datos publicacion correctos
	Then se borra publicacion
	
@Scenario10
Scenario: Modificar una publicacion
	Given Una publicacion
	When publicacion correcta
	Then se modifica publicacion
	
@Scenario11
Scenario: Hacer un mensaje privado
	Given Un mensaje privado
	When mensaje privado correcto
	Then se manda mensaje privado
	
@Scenario12
Scenario Outline: Login suscessful de un usuario
	Given Un usuario y password
	When <email>  y <pwd> se quiere loguear
	Then se loguea correctamente
Examples:
	| email										|	pwd 								|
	|"asdf@gmail.com" 				| "1234" 							|
	|"fernycozar@hotmail.com" | "supersecreto"	|
	