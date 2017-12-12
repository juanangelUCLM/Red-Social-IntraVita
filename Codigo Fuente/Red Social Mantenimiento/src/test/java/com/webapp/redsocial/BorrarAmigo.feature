Feature: Eliminar amistades


@Scenario1
Scenario Outline: Eliminar amistad valido
	Given Usuario conectado para eliminar un amigo
	When <emisor> borra a  <receptor>
	Then Borrar de amigos
	
	Examples:
	|emisor|receptor|
	|"emisor@hotmail.com"|"receptor@hotmail.com"|
	|"emisor2@hotmail.com"|"receptor2@hotmail.com"|
	
@Scenario2
Scenario Outline: Eliminar amistad invalido
	Given Usuario conectado para eliminar un amigo
	When <emisor> intenta borrar a <receptor> pero no son amigos
	Then Mensaje de error al borrado
	
	Examples:
	|emisor|receptor|
	|"emisor@hotmail.com"|"receptor@hotmail.com"|
	|"emisor2@hotmail.com"|"receptor2@hotmail.com"|