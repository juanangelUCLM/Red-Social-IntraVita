Feature: Aceptar solicitudes de amistad de otro usuario


@Scenario1
Scenario Outline: Aceptar solicitud valido
	Given Usuario conectado para aceptar
	When <emisor> es aceptado por <receptor>
	Then Aceptar como amigo
	
	Examples:
	|emisor|receptor|
	|"emisor@hotmail.com"|"receptor@hotmail.com"|
	|"emisor2@hotmail.com"|"receptor2@hotmail.com"|
	
@Scenario2
Scenario Outline: Aceptar solicitud invalido
	Given Usuario conectado para aceptar
	When <emisor> es aceptado por <receptor> pero da error por no envio de solicitud
	Then Mensaje de error a la aceptacion
	
	Examples:
	|emisor|receptor|
	|"emisor@hotmail.com"|"receptor@hotmail.com"|
	|"emisor2@hotmail.com"|"receptor2@hotmail.com"|