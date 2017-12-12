Feature: Rechazar solicitudes de amistad de otro usuario


@Scenario1
Scenario Outline: Rechazar solicitud valido
	Given Usuario conectado para rechazar
	When <emisor> es rechazado por <receptor>
	Then Rechazar peticion de amistad
		
	Examples:
	|emisor|receptor|
	|"emisor@hotmail.com"|"receptor@hotmail.com"|
	|"emisor2@hotmail.com"|"receptor2@hotmail.com"|
	
@Scenario2
Scenario Outline: Rechazar solicitud invalido
	Given Usuario conectado para rechazar
	When <emisor> es rechazado por <receptor> pero no le habia enviado solicitud
	Then Mensaje de error al rechazo de la peticion
	
	Examples:
	|emisor|receptor|
	|"emisor@hotmail.com"|"receptor@hotmail.com"|
	|"emisor2@hotmail.com"|"receptor2@hotmail.com"|
	