<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<title>Recordar contraseña</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	<link href="resources/css/style.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="resources/js/jquery-3.2.1.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
	<div class="row">
		<div class="col-md-4 col-md-offset-4">
            <div class="panel panel-default">
              <div class="panel-body">
                <div class="text-center">
                  <h3><i class="fa fa-lock fa-4x"></i></h3>
                  <h2 class="text-center">Recordar contraseña</h2>
                  <p></p>
                  <div class="panel-body">
    				
	    				<c:if test="${!empty message}">
	    				<div class="alert alert-success alert-dismissable">
						  <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
						 <strong>${message}</strong>
						</div>
	   				 </c:if>
                    <form id="forgot-password" action="forgotpassword" role="form" autocomplete="off" class="form" method="post">
    
                      <div class="form-group">
                        <div class="input-group">
                          <span class="input-group-addon"><i class="glyphicon glyphicon-envelope color-blue"></i></span>
                          <input id="email" name="email" placeholder="Correo electrónico" class="form-control"  type="email">
                        </div>
                      </div>
                      <div class="form-group">
                        <button class="btn btn-lg btn-primary btn-block" type="submit">Recuperar contraseña</button>
                      </div>
                      <div class="form-group">
										<div class="row">
											<div class="col-lg-12">
												<div class="text-center">
													<a href="#" onclick="history.back();" class="forgot-password"><i class="glyphicon glyphicon-arrow-left color-blue"></i> Volver</a>
												</div>
											</div>
										</div>
									</div>
                    </form>
                  </div>
                </div>
              </div>
            </div>
          </div>
	</div>
</div>
</body>
</html>