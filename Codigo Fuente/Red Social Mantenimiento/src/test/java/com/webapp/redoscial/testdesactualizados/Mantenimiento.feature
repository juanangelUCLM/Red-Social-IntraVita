@featureTest
Feature: Uso de cucumber para implementar test nuevos no considerados antes
del mantenimiento.

@Scenario1
Scenario: Borrado incorrecto de una cuenta de usuario
	Given Un idUsuario para borrar
	When datos incorrectos para borrar
	Then no se borra
	
@Scenario2
Scenario: Modificacion incorrecta de cuenta de usuario
	Given Un idUsuario para modificar
	When datos incorrectos para modificar
	Then no se modifica usuario
	
@Scenario3
Scenario: Borrado incorrecto de una publicacion
	Given Un idPublicacion para borrar
	When datos de publicacion incorrectos
	Then no se borra la publicacion
	
@Scenario4
Scenario: Editado incorrecto de una publicacion
	Given Una publicacion para editar
	When Publicacion para editar es incorrecta
	Then no se modifica la publicacion