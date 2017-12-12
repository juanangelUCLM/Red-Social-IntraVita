Feature: Test de envio de mensaje privado

@Scenario1
Scenario Outline: Envio correcto de un mensaje privado
	Given Un usuario quiere enviar un mensaje privado
	When <emisornombre> <emisoremail> son correctos y <receptornombre> <receptoremail> existe
	Then <mensaje> enviado
	
Examples:
	|emisornombre				| emisoremail								|	receptornombre 			| receptoremail						| mensaje 					|
	|"Fernando"					|"fernycozar@hotmail.com"		| "Mariap"						|"asdf@gmail.com" 				|"Test de mensaje"	|
	|"Mariap"						|"asdf@gmail.com" 					| "Fernando"					|"fernycozar@hotmail.com"	|"Test de mensaje2"	|

	
	
@Scenario2
Scenario Outline: Envio incorrecto de un mensaje privado
	Given Un usuario quiere enviar un mensaje privado
	When  <emisornombre> <emisoremail> son correctos pero <receptornombre> <receptoremail> no existe
	Then <mensaje> no enviado
Examples:
	|emisornombre				| emisoremail								|	receptornombre 			| receptoremail							| mensaje 					|
	|"Fernando"					|"fernycozar@hotmail.com"		| "jghjhhj"						|"ghdfghdfgh@gmail.com" 		|"Test de mensaje"	|
	|"Mariap"						|"asdf@gmail.com" 					| "fgjgfhgfhj"				|"dfhgdfhgdfhg@hotmail.com"	|"Test de mensaje2"	|
