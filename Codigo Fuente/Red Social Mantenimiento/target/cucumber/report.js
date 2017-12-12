$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri('com\webapp\redsocial\BorrarAmigo.feature');
formatter.feature({
  "line": 1,
  "name": "Eliminar amistades",
  "description": "",
  "id": "eliminar-amistades",
  "keyword": "Feature"
});
formatter.scenario({
  "line": 12,
  "name": "Eliminar amistad valido",
  "description": "",
  "id": "eliminar-amistades;eliminar-amistad-valido;;2",
  "type": "scenario",
  "keyword": "Scenario Outline",
  "tags": [
    {
      "line": 4,
      "name": "@Scenario1"
    }
  ]
});
formatter.step({
  "line": 6,
  "name": "Usuario conectado para eliminar un amigo",
  "keyword": "Given "
});
formatter.step({
  "line": 7,
  "name": "\"emisor@hotmail.com\" borra a  \"receptor@hotmail.com\"",
  "matchedColumns": [
    0,
    1
  ],
  "keyword": "When "
});
formatter.step({
  "line": 8,
  "name": "Borrar de amigos",
  "keyword": "Then "
});
formatter.match({
  "location": "BorrarAmigoTest.Usuario_conectado_para_eliminar_un_amigo()"
});
formatter.result({
  "duration": 126983500,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "emisor@hotmail.com",
      "offset": 1
    },
    {
      "val": "receptor@hotmail.com",
      "offset": 31
    }
  ],
  "location": "BorrarAmigoTest.borra_a(String,String)"
});
formatter.result({
  "duration": 2472199400,
  "status": "passed"
});
formatter.match({
  "location": "BorrarAmigoTest.Borrar_de_amigos()"
});
formatter.result({
  "duration": 2058394700,
  "status": "passed"
});
formatter.scenario({
  "line": 13,
  "name": "Eliminar amistad valido",
  "description": "",
  "id": "eliminar-amistades;eliminar-amistad-valido;;3",
  "type": "scenario",
  "keyword": "Scenario Outline",
  "tags": [
    {
      "line": 4,
      "name": "@Scenario1"
    }
  ]
});
formatter.step({
  "line": 6,
  "name": "Usuario conectado para eliminar un amigo",
  "keyword": "Given "
});
formatter.step({
  "line": 7,
  "name": "\"emisor2@hotmail.com\" borra a  \"receptor2@hotmail.com\"",
  "matchedColumns": [
    0,
    1
  ],
  "keyword": "When "
});
formatter.step({
  "line": 8,
  "name": "Borrar de amigos",
  "keyword": "Then "
});
formatter.match({
  "location": "BorrarAmigoTest.Usuario_conectado_para_eliminar_un_amigo()"
});
formatter.result({
  "duration": 16800,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "emisor2@hotmail.com",
      "offset": 1
    },
    {
      "val": "receptor2@hotmail.com",
      "offset": 32
    }
  ],
  "location": "BorrarAmigoTest.borra_a(String,String)"
});
formatter.result({
  "duration": 2403134200,
  "status": "passed"
});
formatter.match({
  "location": "BorrarAmigoTest.Borrar_de_amigos()"
});
formatter.result({
  "duration": 2391560300,
  "status": "passed"
});
formatter.scenario({
  "line": 23,
  "name": "Eliminar amistad invalido",
  "description": "",
  "id": "eliminar-amistades;eliminar-amistad-invalido;;2",
  "type": "scenario",
  "keyword": "Scenario Outline",
  "tags": [
    {
      "line": 15,
      "name": "@Scenario2"
    }
  ]
});
formatter.step({
  "line": 17,
  "name": "Usuario conectado para eliminar un amigo",
  "keyword": "Given "
});
formatter.step({
  "line": 18,
  "name": "\"emisor@hotmail.com\" intenta borrar a \"receptor@hotmail.com\" pero no son amigos",
  "matchedColumns": [
    0,
    1
  ],
  "keyword": "When "
});
formatter.step({
  "line": 19,
  "name": "Mensaje de error al borrado",
  "keyword": "Then "
});
formatter.match({
  "location": "BorrarAmigoTest.Usuario_conectado_para_eliminar_un_amigo()"
});
formatter.result({
  "duration": 15800,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "emisor@hotmail.com",
      "offset": 1
    },
    {
      "val": "receptor@hotmail.com",
      "offset": 39
    }
  ],
  "location": "BorrarAmigoTest.intenta_borrar_a_pero_no_son_amigos(String,String)"
});
formatter.result({
  "duration": 654656600,
  "status": "passed"
});
formatter.match({
  "location": "BorrarAmigoTest.Mensaje_de_error_al_borrado()"
});
formatter.result({
  "duration": 1395865800,
  "status": "passed"
});
formatter.scenario({
  "line": 24,
  "name": "Eliminar amistad invalido",
  "description": "",
  "id": "eliminar-amistades;eliminar-amistad-invalido;;3",
  "type": "scenario",
  "keyword": "Scenario Outline",
  "tags": [
    {
      "line": 15,
      "name": "@Scenario2"
    }
  ]
});
formatter.step({
  "line": 17,
  "name": "Usuario conectado para eliminar un amigo",
  "keyword": "Given "
});
formatter.step({
  "line": 18,
  "name": "\"emisor2@hotmail.com\" intenta borrar a \"receptor2@hotmail.com\" pero no son amigos",
  "matchedColumns": [
    0,
    1
  ],
  "keyword": "When "
});
formatter.step({
  "line": 19,
  "name": "Mensaje de error al borrado",
  "keyword": "Then "
});
formatter.match({
  "location": "BorrarAmigoTest.Usuario_conectado_para_eliminar_un_amigo()"
});
formatter.result({
  "duration": 24900,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "emisor2@hotmail.com",
      "offset": 1
    },
    {
      "val": "receptor2@hotmail.com",
      "offset": 40
    }
  ],
  "location": "BorrarAmigoTest.intenta_borrar_a_pero_no_son_amigos(String,String)"
});
formatter.result({
  "duration": 533053200,
  "status": "passed"
});
formatter.match({
  "location": "BorrarAmigoTest.Mensaje_de_error_al_borrado()"
});
formatter.result({
  "duration": 1460570200,
  "status": "passed"
});
formatter.uri('com\webapp\redsocial\Login.feature');
formatter.feature({
  "line": 1,
  "name": "Test del login en el sistema",
  "description": "",
  "id": "test-del-login-en-el-sistema",
  "keyword": "Feature"
});
formatter.scenario({
  "line": 10,
  "name": "Login suscessful de un usuario",
  "description": "",
  "id": "test-del-login-en-el-sistema;login-suscessful-de-un-usuario;;2",
  "type": "scenario",
  "keyword": "Scenario Outline",
  "tags": [
    {
      "line": 3,
      "name": "@Scenario1"
    }
  ]
});
formatter.step({
  "line": 5,
  "name": "Un usuario y password para login",
  "keyword": "Given "
});
formatter.step({
  "line": 6,
  "name": "\"asdf@gmail.com\"  y \"1234\" se quiere loguear",
  "matchedColumns": [
    0,
    1
  ],
  "keyword": "When "
});
formatter.step({
  "line": 7,
  "name": "se loguea correctamente",
  "keyword": "Then "
});
formatter.match({
  "location": "LoginTest.Un_usuario_y_password_para_login()"
});
formatter.result({
  "duration": 16033000,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "asdf@gmail.com",
      "offset": 1
    },
    {
      "val": "1234",
      "offset": 21
    }
  ],
  "location": "LoginTest.y_se_quiere_loguear(String,String)"
});
formatter.result({
  "duration": 135272800,
  "status": "passed"
});
formatter.match({
  "location": "LoginTest.se_loguea_correctamente()"
});
formatter.result({
  "duration": 35600,
  "status": "passed"
});
formatter.scenario({
  "line": 11,
  "name": "Login suscessful de un usuario",
  "description": "",
  "id": "test-del-login-en-el-sistema;login-suscessful-de-un-usuario;;3",
  "type": "scenario",
  "keyword": "Scenario Outline",
  "tags": [
    {
      "line": 3,
      "name": "@Scenario1"
    }
  ]
});
formatter.step({
  "line": 5,
  "name": "Un usuario y password para login",
  "keyword": "Given "
});
formatter.step({
  "line": 6,
  "name": "\"fernycozar@hotmail.com\"  y \"supersecreto\" se quiere loguear",
  "matchedColumns": [
    0,
    1
  ],
  "keyword": "When "
});
formatter.step({
  "line": 7,
  "name": "se loguea correctamente",
  "keyword": "Then "
});
formatter.match({
  "location": "LoginTest.Un_usuario_y_password_para_login()"
});
formatter.result({
  "duration": 276500,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "fernycozar@hotmail.com",
      "offset": 1
    },
    {
      "val": "supersecreto",
      "offset": 29
    }
  ],
  "location": "LoginTest.y_se_quiere_loguear(String,String)"
});
formatter.result({
  "duration": 184785300,
  "status": "passed"
});
formatter.match({
  "location": "LoginTest.se_loguea_correctamente()"
});
formatter.result({
  "duration": 24600,
  "status": "passed"
});
formatter.scenario({
  "line": 21,
  "name": "Login invalido de un usuario",
  "description": "",
  "id": "test-del-login-en-el-sistema;login-invalido-de-un-usuario;;2",
  "type": "scenario",
  "keyword": "Scenario Outline",
  "tags": [
    {
      "line": 14,
      "name": "@Scenario2"
    }
  ]
});
formatter.step({
  "line": 16,
  "name": "Un usuario y password para login",
  "keyword": "Given "
});
formatter.step({
  "line": 17,
  "name": "\"meloinvento\t\t\"  y \"1asdfasdf4\" se quiere loguear",
  "matchedColumns": [
    0,
    1
  ],
  "keyword": "When "
});
formatter.step({
  "line": 18,
  "name": "no consigue loguearse",
  "keyword": "Then "
});
formatter.match({
  "location": "LoginTest.Un_usuario_y_password_para_login()"
});
formatter.result({
  "duration": 38000,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "meloinvento\t\t",
      "offset": 1
    },
    {
      "val": "1asdfasdf4",
      "offset": 20
    }
  ],
  "location": "LoginTest.y_se_quiere_loguear(String,String)"
});
formatter.result({
  "duration": 159968100,
  "status": "passed"
});
formatter.match({
  "location": "LoginTest.no_consigue_loguearse()"
});
formatter.result({
  "duration": 33300,
  "status": "passed"
});
formatter.scenario({
  "line": 22,
  "name": "Login invalido de un usuario",
  "description": "",
  "id": "test-del-login-en-el-sistema;login-invalido-de-un-usuario;;3",
  "type": "scenario",
  "keyword": "Scenario Outline",
  "tags": [
    {
      "line": 14,
      "name": "@Scenario2"
    }
  ]
});
formatter.step({
  "line": 16,
  "name": "Un usuario y password para login",
  "keyword": "Given "
});
formatter.step({
  "line": 17,
  "name": "\"fasdfasdfasdfasdfasdfa\"  y \"asdfasdfasdfasdf\" se quiere loguear",
  "matchedColumns": [
    0,
    1
  ],
  "keyword": "When "
});
formatter.step({
  "line": 18,
  "name": "no consigue loguearse",
  "keyword": "Then "
});
formatter.match({
  "location": "LoginTest.Un_usuario_y_password_para_login()"
});
formatter.result({
  "duration": 23600,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "fasdfasdfasdfasdfasdfa",
      "offset": 1
    },
    {
      "val": "asdfasdfasdfasdf",
      "offset": 29
    }
  ],
  "location": "LoginTest.y_se_quiere_loguear(String,String)"
});
formatter.result({
  "duration": 131505400,
  "status": "passed"
});
formatter.match({
  "location": "LoginTest.no_consigue_loguearse()"
});
formatter.result({
  "duration": 19800,
  "status": "passed"
});
formatter.uri('com\webapp\redsocial\SolicitudesAceptar.feature');
formatter.feature({
  "line": 1,
  "name": "Aceptar solicitudes de amistad de otro usuario",
  "description": "",
  "id": "aceptar-solicitudes-de-amistad-de-otro-usuario",
  "keyword": "Feature"
});
formatter.scenario({
  "line": 12,
  "name": "Aceptar solicitud valido",
  "description": "",
  "id": "aceptar-solicitudes-de-amistad-de-otro-usuario;aceptar-solicitud-valido;;2",
  "type": "scenario",
  "keyword": "Scenario Outline",
  "tags": [
    {
      "line": 4,
      "name": "@Scenario1"
    }
  ]
});
formatter.step({
  "line": 6,
  "name": "Usuario conectado para aceptar",
  "keyword": "Given "
});
formatter.step({
  "line": 7,
  "name": "\"emisor@hotmail.com\" es aceptado por \"receptor@hotmail.com\"",
  "matchedColumns": [
    0,
    1
  ],
  "keyword": "When "
});
formatter.step({
  "line": 8,
  "name": "Aceptar como amigo",
  "keyword": "Then "
});
formatter.match({
  "location": "SolicitudesAceptarTest.Usuario_conectado_para_aceptar()"
});
formatter.result({
  "duration": 14577100,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "emisor@hotmail.com",
      "offset": 1
    },
    {
      "val": "receptor@hotmail.com",
      "offset": 38
    }
  ],
  "location": "SolicitudesAceptarTest.es_aceptado_por(String,String)"
});
formatter.result({
  "duration": 1564592800,
  "status": "passed"
});
formatter.match({
  "location": "SolicitudesAceptarTest.Aceptar_como_amigo()"
});
formatter.result({
  "duration": 2967870400,
  "status": "passed"
});
formatter.scenario({
  "line": 13,
  "name": "Aceptar solicitud valido",
  "description": "",
  "id": "aceptar-solicitudes-de-amistad-de-otro-usuario;aceptar-solicitud-valido;;3",
  "type": "scenario",
  "keyword": "Scenario Outline",
  "tags": [
    {
      "line": 4,
      "name": "@Scenario1"
    }
  ]
});
formatter.step({
  "line": 6,
  "name": "Usuario conectado para aceptar",
  "keyword": "Given "
});
formatter.step({
  "line": 7,
  "name": "\"emisor2@hotmail.com\" es aceptado por \"receptor2@hotmail.com\"",
  "matchedColumns": [
    0,
    1
  ],
  "keyword": "When "
});
formatter.step({
  "line": 8,
  "name": "Aceptar como amigo",
  "keyword": "Then "
});
formatter.match({
  "location": "SolicitudesAceptarTest.Usuario_conectado_para_aceptar()"
});
formatter.result({
  "duration": 16700,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "emisor2@hotmail.com",
      "offset": 1
    },
    {
      "val": "receptor2@hotmail.com",
      "offset": 39
    }
  ],
  "location": "SolicitudesAceptarTest.es_aceptado_por(String,String)"
});
formatter.result({
  "duration": 1945660600,
  "status": "passed"
});
formatter.match({
  "location": "SolicitudesAceptarTest.Aceptar_como_amigo()"
});
formatter.result({
  "duration": 2991962100,
  "status": "passed"
});
formatter.scenario({
  "line": 23,
  "name": "Aceptar solicitud invalido",
  "description": "",
  "id": "aceptar-solicitudes-de-amistad-de-otro-usuario;aceptar-solicitud-invalido;;2",
  "type": "scenario",
  "keyword": "Scenario Outline",
  "tags": [
    {
      "line": 15,
      "name": "@Scenario2"
    }
  ]
});
formatter.step({
  "line": 17,
  "name": "Usuario conectado para aceptar",
  "keyword": "Given "
});
formatter.step({
  "line": 18,
  "name": "\"emisor@hotmail.com\" es aceptado por \"receptor@hotmail.com\" pero da error por no envio de solicitud",
  "matchedColumns": [
    0,
    1
  ],
  "keyword": "When "
});
formatter.step({
  "line": 19,
  "name": "Mensaje de error a la aceptacion",
  "keyword": "Then "
});
formatter.match({
  "location": "SolicitudesAceptarTest.Usuario_conectado_para_aceptar()"
});
formatter.result({
  "duration": 16500,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "emisor@hotmail.com",
      "offset": 1
    },
    {
      "val": "receptor@hotmail.com",
      "offset": 38
    }
  ],
  "location": "SolicitudesAceptarTest.es_aceptado_por_pero_da_error_por_no_envio_de_solicitud(String,String)"
});
formatter.result({
  "duration": 691560600,
  "status": "passed"
});
formatter.match({
  "location": "SolicitudesAceptarTest.Mensaje_de_error_a_la_aceptacion()"
});
formatter.result({
  "duration": 1820640800,
  "status": "passed"
});
formatter.scenario({
  "line": 24,
  "name": "Aceptar solicitud invalido",
  "description": "",
  "id": "aceptar-solicitudes-de-amistad-de-otro-usuario;aceptar-solicitud-invalido;;3",
  "type": "scenario",
  "keyword": "Scenario Outline",
  "tags": [
    {
      "line": 15,
      "name": "@Scenario2"
    }
  ]
});
formatter.step({
  "line": 17,
  "name": "Usuario conectado para aceptar",
  "keyword": "Given "
});
formatter.step({
  "line": 18,
  "name": "\"emisor2@hotmail.com\" es aceptado por \"receptor2@hotmail.com\" pero da error por no envio de solicitud",
  "matchedColumns": [
    0,
    1
  ],
  "keyword": "When "
});
formatter.step({
  "line": 19,
  "name": "Mensaje de error a la aceptacion",
  "keyword": "Then "
});
formatter.match({
  "location": "SolicitudesAceptarTest.Usuario_conectado_para_aceptar()"
});
formatter.result({
  "duration": 18700,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "emisor2@hotmail.com",
      "offset": 1
    },
    {
      "val": "receptor2@hotmail.com",
      "offset": 39
    }
  ],
  "location": "SolicitudesAceptarTest.es_aceptado_por_pero_da_error_por_no_envio_de_solicitud(String,String)"
});
formatter.result({
  "duration": 658762600,
  "status": "passed"
});
formatter.match({
  "location": "SolicitudesAceptarTest.Mensaje_de_error_a_la_aceptacion()"
});
formatter.result({
  "duration": 1843146000,
  "status": "passed"
});
formatter.uri('com\webapp\redsocial\SolicitudesEnvio.feature');
formatter.feature({
  "line": 1,
  "name": "Enviar solicitudes de amistad a otro usuario",
  "description": "",
  "id": "enviar-solicitudes-de-amistad-a-otro-usuario",
  "keyword": "Feature"
});
formatter.scenario({
  "line": 12,
  "name": "Envio de solicitud de amistad valido",
  "description": "",
  "id": "enviar-solicitudes-de-amistad-a-otro-usuario;envio-de-solicitud-de-amistad-valido;;2",
  "type": "scenario",
  "keyword": "Scenario Outline",
  "tags": [
    {
      "line": 4,
      "name": "@Scenario1"
    }
  ]
});
formatter.step({
  "line": 6,
  "name": "Usuario conectado para enviar solicitud",
  "keyword": "Given "
});
formatter.step({
  "line": 7,
  "name": "\"emisor@hotmail.com\" quiere enviar solicitud a \"receptor@hotmail.com\"",
  "matchedColumns": [
    0,
    1
  ],
  "keyword": "When "
});
formatter.step({
  "line": 8,
  "name": "Envio solicitud al segundo",
  "keyword": "Then "
});
formatter.match({
  "location": "SolicitudesEnvioTest.Usuario_conectado_para_enviar_solicitud()"
});
formatter.result({
  "duration": 12090100,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "emisor@hotmail.com",
      "offset": 1
    },
    {
      "val": "receptor@hotmail.com",
      "offset": 48
    }
  ],
  "location": "SolicitudesEnvioTest.quiere_enviar_solicitud_a(String,String)"
});
formatter.result({
  "duration": 1059563800,
  "status": "passed"
});
formatter.match({
  "location": "SolicitudesEnvioTest.Envio_solicitud_al_segundo()"
});
formatter.result({
  "duration": 3400916300,
  "status": "passed"
});
formatter.scenario({
  "line": 13,
  "name": "Envio de solicitud de amistad valido",
  "description": "",
  "id": "enviar-solicitudes-de-amistad-a-otro-usuario;envio-de-solicitud-de-amistad-valido;;3",
  "type": "scenario",
  "keyword": "Scenario Outline",
  "tags": [
    {
      "line": 4,
      "name": "@Scenario1"
    }
  ]
});
formatter.step({
  "line": 6,
  "name": "Usuario conectado para enviar solicitud",
  "keyword": "Given "
});
formatter.step({
  "line": 7,
  "name": "\"emisor2@hotmail.com\" quiere enviar solicitud a \"receptor2@hotmail.com\"",
  "matchedColumns": [
    0,
    1
  ],
  "keyword": "When "
});
formatter.step({
  "line": 8,
  "name": "Envio solicitud al segundo",
  "keyword": "Then "
});
formatter.match({
  "location": "SolicitudesEnvioTest.Usuario_conectado_para_enviar_solicitud()"
});
formatter.result({
  "duration": 20100,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "emisor2@hotmail.com",
      "offset": 1
    },
    {
      "val": "receptor2@hotmail.com",
      "offset": 49
    }
  ],
  "location": "SolicitudesEnvioTest.quiere_enviar_solicitud_a(String,String)"
});
formatter.result({
  "duration": 1815129300,
  "status": "passed"
});
formatter.match({
  "location": "SolicitudesEnvioTest.Envio_solicitud_al_segundo()"
});
formatter.result({
  "duration": 4661049800,
  "status": "passed"
});
formatter.scenario({
  "line": 23,
  "name": "Solicitud invalida porque son amigos",
  "description": "",
  "id": "enviar-solicitudes-de-amistad-a-otro-usuario;solicitud-invalida-porque-son-amigos;;2",
  "type": "scenario",
  "keyword": "Scenario Outline",
  "tags": [
    {
      "line": 15,
      "name": "@Scenario2"
    }
  ]
});
formatter.step({
  "line": 17,
  "name": "Usuario conectado para enviar solicitud",
  "keyword": "Given "
});
formatter.step({
  "line": 18,
  "name": "\"emisor@hotmail.com\" quiere enviar solicitud a \"receptor@hotmail.com\" pero son amigos",
  "matchedColumns": [
    0,
    1
  ],
  "keyword": "When "
});
formatter.step({
  "line": 19,
  "name": "No envio de solicitud",
  "keyword": "Then "
});
formatter.match({
  "location": "SolicitudesEnvioTest.Usuario_conectado_para_enviar_solicitud()"
});
formatter.result({
  "duration": 21400,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "emisor@hotmail.com",
      "offset": 1
    },
    {
      "val": "receptor@hotmail.com",
      "offset": 48
    }
  ],
  "location": "SolicitudesEnvioTest.quiere_enviar_solicitud_a_pero_son_amigos(String,String)"
});
formatter.result({
  "duration": 2566173900,
  "status": "passed"
});
formatter.match({
  "location": "SolicitudesEnvioTest.No_envio_de_solicitud()"
});
formatter.result({
  "duration": 2973090400,
  "status": "passed"
});
formatter.scenario({
  "line": 24,
  "name": "Solicitud invalida porque son amigos",
  "description": "",
  "id": "enviar-solicitudes-de-amistad-a-otro-usuario;solicitud-invalida-porque-son-amigos;;3",
  "type": "scenario",
  "keyword": "Scenario Outline",
  "tags": [
    {
      "line": 15,
      "name": "@Scenario2"
    }
  ]
});
formatter.step({
  "line": 17,
  "name": "Usuario conectado para enviar solicitud",
  "keyword": "Given "
});
formatter.step({
  "line": 18,
  "name": "\"emisor2@hotmail.com\" quiere enviar solicitud a \"receptor2@hotmail.com\" pero son amigos",
  "matchedColumns": [
    0,
    1
  ],
  "keyword": "When "
});
formatter.step({
  "line": 19,
  "name": "No envio de solicitud",
  "keyword": "Then "
});
formatter.match({
  "location": "SolicitudesEnvioTest.Usuario_conectado_para_enviar_solicitud()"
});
formatter.result({
  "duration": 19700,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "emisor2@hotmail.com",
      "offset": 1
    },
    {
      "val": "receptor2@hotmail.com",
      "offset": 49
    }
  ],
  "location": "SolicitudesEnvioTest.quiere_enviar_solicitud_a_pero_son_amigos(String,String)"
});
formatter.result({
  "duration": 2803763300,
  "status": "passed"
});
formatter.match({
  "location": "SolicitudesEnvioTest.No_envio_de_solicitud()"
});
formatter.result({
  "duration": 2463137400,
  "status": "passed"
});
formatter.scenario({
  "line": 34,
  "name": "Solicitud invalida porque tienen solicitudes pendientes",
  "description": "",
  "id": "enviar-solicitudes-de-amistad-a-otro-usuario;solicitud-invalida-porque-tienen-solicitudes-pendientes;;2",
  "type": "scenario",
  "keyword": "Scenario Outline",
  "tags": [
    {
      "line": 26,
      "name": "@Scenario3"
    }
  ]
});
formatter.step({
  "line": 28,
  "name": "Usuario conectado para enviar solicitud",
  "keyword": "Given "
});
formatter.step({
  "line": 29,
  "name": "\"emisor@hotmail.com\" quiere enviar solicitud a \"receptor@hotmail.com\" pero ya tiene solicitudes suyas",
  "matchedColumns": [
    0,
    1
  ],
  "keyword": "When "
});
formatter.step({
  "line": 30,
  "name": "Solicitud ya enviada",
  "keyword": "Then "
});
formatter.match({
  "location": "SolicitudesEnvioTest.Usuario_conectado_para_enviar_solicitud()"
});
formatter.result({
  "duration": 22400,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "emisor@hotmail.com",
      "offset": 1
    },
    {
      "val": "receptor@hotmail.com",
      "offset": 48
    }
  ],
  "location": "SolicitudesEnvioTest.quiere_enviar_solicitud_a_pero_ya_tiene_solicitudes_suyas(String,String)"
});
formatter.result({
  "duration": 1057499700,
  "status": "passed"
});
formatter.match({
  "location": "SolicitudesEnvioTest.Solicitud_ya_enviada()"
});
formatter.result({
  "duration": 2118939300,
  "status": "passed"
});
formatter.scenario({
  "line": 35,
  "name": "Solicitud invalida porque tienen solicitudes pendientes",
  "description": "",
  "id": "enviar-solicitudes-de-amistad-a-otro-usuario;solicitud-invalida-porque-tienen-solicitudes-pendientes;;3",
  "type": "scenario",
  "keyword": "Scenario Outline",
  "tags": [
    {
      "line": 26,
      "name": "@Scenario3"
    }
  ]
});
formatter.step({
  "line": 28,
  "name": "Usuario conectado para enviar solicitud",
  "keyword": "Given "
});
formatter.step({
  "line": 29,
  "name": "\"emisor2@hotmail.com\" quiere enviar solicitud a \"receptor2@hotmail.com\" pero ya tiene solicitudes suyas",
  "matchedColumns": [
    0,
    1
  ],
  "keyword": "When "
});
formatter.step({
  "line": 30,
  "name": "Solicitud ya enviada",
  "keyword": "Then "
});
formatter.match({
  "location": "SolicitudesEnvioTest.Usuario_conectado_para_enviar_solicitud()"
});
formatter.result({
  "duration": 20100,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "emisor2@hotmail.com",
      "offset": 1
    },
    {
      "val": "receptor2@hotmail.com",
      "offset": 49
    }
  ],
  "location": "SolicitudesEnvioTest.quiere_enviar_solicitud_a_pero_ya_tiene_solicitudes_suyas(String,String)"
});
formatter.result({
  "duration": 1069620500,
  "status": "passed"
});
formatter.match({
  "location": "SolicitudesEnvioTest.Solicitud_ya_enviada()"
});
formatter.result({
  "duration": 1930411400,
  "status": "passed"
});
formatter.uri('com\webapp\redsocial\SolicitudesRechazo.feature');
formatter.feature({
  "line": 1,
  "name": "Rechazar solicitudes de amistad de otro usuario",
  "description": "",
  "id": "rechazar-solicitudes-de-amistad-de-otro-usuario",
  "keyword": "Feature"
});
formatter.scenario({
  "line": 12,
  "name": "Rechazar solicitud valido",
  "description": "",
  "id": "rechazar-solicitudes-de-amistad-de-otro-usuario;rechazar-solicitud-valido;;2",
  "type": "scenario",
  "keyword": "Scenario Outline",
  "tags": [
    {
      "line": 4,
      "name": "@Scenario1"
    }
  ]
});
formatter.step({
  "line": 6,
  "name": "Usuario conectado para rechazar",
  "keyword": "Given "
});
formatter.step({
  "line": 7,
  "name": "\"emisor@hotmail.com\" es rechazado por \"receptor@hotmail.com\"",
  "matchedColumns": [
    0,
    1
  ],
  "keyword": "When "
});
formatter.step({
  "line": 8,
  "name": "Rechazar peticion de amistad",
  "keyword": "Then "
});
formatter.match({
  "location": "SolicitudesRechazoTest.Usuario_conectado_para_rechazar()"
});
formatter.result({
  "duration": 6000200,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "emisor@hotmail.com",
      "offset": 1
    },
    {
      "val": "receptor@hotmail.com",
      "offset": 39
    }
  ],
  "location": "SolicitudesRechazoTest.es_rechazado_por(String,String)"
});
formatter.result({
  "duration": 1647930400,
  "status": "passed"
});
formatter.match({
  "location": "SolicitudesRechazoTest.Rechazar_peticion_de_amistad()"
});
formatter.result({
  "duration": 2064385200,
  "status": "passed"
});
formatter.scenario({
  "line": 13,
  "name": "Rechazar solicitud valido",
  "description": "",
  "id": "rechazar-solicitudes-de-amistad-de-otro-usuario;rechazar-solicitud-valido;;3",
  "type": "scenario",
  "keyword": "Scenario Outline",
  "tags": [
    {
      "line": 4,
      "name": "@Scenario1"
    }
  ]
});
formatter.step({
  "line": 6,
  "name": "Usuario conectado para rechazar",
  "keyword": "Given "
});
formatter.step({
  "line": 7,
  "name": "\"emisor2@hotmail.com\" es rechazado por \"receptor2@hotmail.com\"",
  "matchedColumns": [
    0,
    1
  ],
  "keyword": "When "
});
formatter.step({
  "line": 8,
  "name": "Rechazar peticion de amistad",
  "keyword": "Then "
});
formatter.match({
  "location": "SolicitudesRechazoTest.Usuario_conectado_para_rechazar()"
});
formatter.result({
  "duration": 40000,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "emisor2@hotmail.com",
      "offset": 1
    },
    {
      "val": "receptor2@hotmail.com",
      "offset": 40
    }
  ],
  "location": "SolicitudesRechazoTest.es_rechazado_por(String,String)"
});
formatter.result({
  "duration": 1753590500,
  "status": "passed"
});
formatter.match({
  "location": "SolicitudesRechazoTest.Rechazar_peticion_de_amistad()"
});
formatter.result({
  "duration": 2219104800,
  "status": "passed"
});
formatter.scenario({
  "line": 23,
  "name": "Rechazar solicitud invalido",
  "description": "",
  "id": "rechazar-solicitudes-de-amistad-de-otro-usuario;rechazar-solicitud-invalido;;2",
  "type": "scenario",
  "keyword": "Scenario Outline",
  "tags": [
    {
      "line": 15,
      "name": "@Scenario2"
    }
  ]
});
formatter.step({
  "line": 17,
  "name": "Usuario conectado para rechazar",
  "keyword": "Given "
});
formatter.step({
  "line": 18,
  "name": "\"emisor@hotmail.com\" es rechazado por \"receptor@hotmail.com\" pero no le habia enviado solicitud",
  "matchedColumns": [
    0,
    1
  ],
  "keyword": "When "
});
formatter.step({
  "line": 19,
  "name": "Mensaje de error al rechazo de la peticion",
  "keyword": "Then "
});
formatter.match({
  "location": "SolicitudesRechazoTest.Usuario_conectado_para_rechazar()"
});
formatter.result({
  "duration": 22300,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "emisor@hotmail.com",
      "offset": 1
    },
    {
      "val": "receptor@hotmail.com",
      "offset": 39
    }
  ],
  "location": "SolicitudesRechazoTest.es_rechazado_por_pero_no_le_habia_enviado_solicitud(String,String)"
});
formatter.result({
  "duration": 551246000,
  "status": "passed"
});
formatter.match({
  "location": "SolicitudesRechazoTest.Mensaje_de_error_al_rechazo_de_la_peticion()"
});
formatter.result({
  "duration": 1495023600,
  "status": "passed"
});
formatter.scenario({
  "line": 24,
  "name": "Rechazar solicitud invalido",
  "description": "",
  "id": "rechazar-solicitudes-de-amistad-de-otro-usuario;rechazar-solicitud-invalido;;3",
  "type": "scenario",
  "keyword": "Scenario Outline",
  "tags": [
    {
      "line": 15,
      "name": "@Scenario2"
    }
  ]
});
formatter.step({
  "line": 17,
  "name": "Usuario conectado para rechazar",
  "keyword": "Given "
});
formatter.step({
  "line": 18,
  "name": "\"emisor2@hotmail.com\" es rechazado por \"receptor2@hotmail.com\" pero no le habia enviado solicitud",
  "matchedColumns": [
    0,
    1
  ],
  "keyword": "When "
});
formatter.step({
  "line": 19,
  "name": "Mensaje de error al rechazo de la peticion",
  "keyword": "Then "
});
formatter.match({
  "location": "SolicitudesRechazoTest.Usuario_conectado_para_rechazar()"
});
formatter.result({
  "duration": 35600,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "emisor2@hotmail.com",
      "offset": 1
    },
    {
      "val": "receptor2@hotmail.com",
      "offset": 40
    }
  ],
  "location": "SolicitudesRechazoTest.es_rechazado_por_pero_no_le_habia_enviado_solicitud(String,String)"
});
formatter.result({
  "duration": 559028200,
  "status": "passed"
});
formatter.match({
  "location": "SolicitudesRechazoTest.Mensaje_de_error_al_rechazo_de_la_peticion()"
});
formatter.result({
  "duration": 1411103900,
  "status": "passed"
});
});