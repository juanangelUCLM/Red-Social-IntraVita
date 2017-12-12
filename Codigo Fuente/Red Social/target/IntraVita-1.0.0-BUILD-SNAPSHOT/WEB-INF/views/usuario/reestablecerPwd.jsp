<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Reestablecer Contrase�a</title>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link REL=StyleSheet HREF="css/login.css" TYPE="text/css" MEDIA=screen>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

<!-- Latest compiled JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>


<style>
body {
	background-color: #2CCBE7;
	width: 100%;
	heigth: 100%;
}


.boton {
  padding: 15px 35px;
  font-size: 18px;
  text-align: center;
  cursor: pointer;
  outline: none;
  color: #fff;
  background-color: #166EE7;
  border: none;
  border-radius: 10px;
  box-shadow: 0 3px #999;
}

.boton:hover {background-color: #21DD27}

.b:active {
  background-color: #21DD27;
  box-shadow: 0 5px #666;
  transform: translateY(4px);
}

span {
    padding: 20px;
    color: #DF0101;
    margin-left: 12px;
	font-weight: bold;
	font-size: 15px;
}
spam {
    padding: 20px;
    color: #3629D3;
    margin-left: 12px;
	font-weight: bold;
	font-size: 20px;
}
</style>


</head>
<body>

	<div class="row bg-primary">
		<div class="col-md-5 col-md-offset-1">
			<img src="http://i65.tinypic.com/2dvizyh.png" style="width:30%;">
		</div>
		
		<div class="col-md-2 col-md-offset-4">
				<button type="button" class="btn btn-primary btn-md" data-toggle="modal" data-target="#miModal" style="border-width: 10px;"><strong>?</strong></button>
		</div>
	</div>
	
	<div class="modal fade" id="miModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	      <div class="modal-dialog" role="document">
	        <div class="modal-content">
	          <div class="modal-header">
	            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
	              <span aria-hidden="true">&times;</span>
	            </button>
	            <h4 class="modal-title" id="myModalLabel">Ayuda</h4>
	          </div>
	          <div class="modal-body">
	          	Introduzca la contrase�a temporal recibida en su direccion de correo electronico, y posteriormente
	          	una nueva contrase�a personal.
	          </div>
	        </div>
	      </div>
	  </div>
				
	<form action="reestablecerPwd" method="POST">
	
		<div class="row">
		 	<div class="col-md-8 col-md-offset-2">
		 		<label for="usr" style="color:white; font-size:15px;">Introduzca su contrase�a temporal:</label>
				<input name="txtPwdTemporal" type="password" class="form-control" id="usr" placeholder="pwdC">
				<br>
			</div>  
		</div>
		
		<div class="row">
		 	<div class="col-md-8 col-md-offset-2">
		 		<label for="usr" style="color:white; font-size:15px;">Introduzca una nueva contrase�a:</label>
				<input name="txtPwdNueva1" type="password" class="form-control" id="usr" placeholder="pwd1">
				<br>
			</div>  
		</div>
		
		<div class="row">
			<div class="col-md-8 col-md-offset-2">
				<label for="usr" style="color:white; font-size:15px;">Vuelva a introducir su nueva contrase�a</label>
				<input name="txtPwdNueva2" type="password" class="form-control" id="usr" placeholder="pwd2">
				<br>
			</div>
		</div>	
		
		<div class="row">
			<div class="col-md-2 col-md-offset-8">
				
				<button class="boton btn-info btn-block" type="submit" value="cambiar" >Cambiar contrase�a</button>
	
			</div>	  
		</div>
	<br>
	</form>
	
	<div class="row">
		<div class="col-md-12">
			<span><em>${alerta}</em></span>
		</div>
	</div>
	
	<div class="col-md-12" style="position: fixed; bottom: 0;">
		<div class="panel-footer">
			� Copyright 2017 IntraVita. Todos los derechos reservados.
		</div>
	</div>	
		
</body>
</html>