Feature: Enviar solicitudes de amistad a otro usuario


@Scenario1
Scenario Outline: Envio de solicitud de amistad valido
	Given Usuario conectado para enviar solicitud
	When <emisor> quiere enviar solicitud a <receptor>
	Then Envio solicitud al segundo

Examples:
	|emisor|receptor|
	|"emisor@hotmail.com"|"receptor@hotmail.com"|
	|"emisor2@hotmail.com"|"receptor2@hotmail.com"|
	
@Scenario2
Scenario Outline: Solicitud invalida porque son amigos
	Given Usuario conectado para enviar solicitud
	When <emisor> quiere enviar solicitud a <receptor> pero son amigos
	Then No envio de solicitud
	
	Examples:
	|emisor|receptor|
	|"emisor@hotmail.com"|"receptor@hotmail.com"|
	|"emisor2@hotmail.com"|"receptor2@hotmail.com"|
	
@Scenario3
Scenario Outline: Solicitud invalida porque tienen solicitudes pendientes
	Given Usuario conectado para enviar solicitud
	When <emisor> quiere enviar solicitud a <receptor> pero ya tiene solicitudes suyas
	Then Solicitud ya enviada
	
	Examples:
	|emisor|receptor|
	|"emisor@hotmail.com"|"receptor@hotmail.com"|
	|"emisor2@hotmail.com"|"receptor2@hotmail.com"|