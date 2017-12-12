<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="resources/css/style.css"/>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/jquery-validation@1.17.0/dist/jquery.validate.min.js"></script>
	<script src="resources/js/index.js"></script>
	<script type="text/javascript" src="resources/js/password.js"></script>
	<%-- By JA captcha registro--%>
	<script src='https://www.google.com/recaptcha/api.js'></script>
	<title>Actualizar Contrase&ntilde;a - IntraVita</title>
</head>
<body>


	<div class="col-md-6 col-md-offset-3">
		<div class="panel panel-login">
			<div class="panel-heading">
				<div class="row">
					<div>
						<strong>Actualizar Contrase&ntilde;a</strong>
					</div>
				</div>
				<hr>
			</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-lg-12">
							<form action="renovarPwd" method="POST" autocomplete="off" role="form" style="display: block;">
								<div class="form-group">
									<input type="password" name="txtUsuarioClave" tabindex="1" class="form-control" autocomplete="off" placeholder="Contrase&ntilde;a">
								</div>
								<div class="form-group">
									<input type="password" name="txtUsuarioClave1" tabindex="2" class="form-control" autocomplete="off" placeholder="Repita la Contrase&ntilde;a">
								</div>
								<div class="form-group">
									<div class="row">
										<div class="col-sm-6 col-sm-offset-3">
											<button type="submit" tabindex="4" class="form-control btn btn-primary">
												<strong><span class="glyphicon glyphicon-refresh"></span>&nbsp;Actualizar</strong>
											</button>
										</div>
									</div>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
</body>
</html>