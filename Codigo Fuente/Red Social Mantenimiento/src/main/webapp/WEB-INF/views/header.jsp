<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="col-md-12" style="position: fixed; z-index: 100;">
<div class="row">
		<nav class="navbar navbar-inverse" style="margin-bottom: -20px;">
 		 <div class="container-fluid">
		    <div class="navbar-header">
		      <a class="navbar-brand" href="#">Intravita</a>
		    </div>
		    <ul class="nav navbar-nav navbar-right">
		      <li>
		      	<a href="#"  class="dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" id="perfil-user" aria-expanded="true"><span class="glyphicon glyphicon-user"></span> ${user.nombre} <span class="caret"></span></a>
		      	<ul style="min-width:250px" class="dropdown-menu" aria-labelledby="perfil-user">
				    <li><label>Nombre: ${user.nombre}</label></li>
				    <li><label>Email: ${user.email}</label></li>
				    <li role="separator" class="divider"></li>
				    <li><a href="#" id="showPerfil"><i class="fa fa-pencil" aria-hidden="true"></i> Editar perfil</a></li>
				    <c:if test="${user.email!='admin@hotmail.com'}">
				    	<li><a href="borrarusuario" style="color:red;"><i class="fa fa-trash-o" aria-hidden="true"></i> Borrar cuenta</a></li>
				   </c:if>
				  </ul>
		      </li>
		      <li><a href="logout"><span class="glyphicon glyphicon-log-in"></span> Salir</a></li>
		    </ul>
  		</div>
</nav>
</div>
</div>

