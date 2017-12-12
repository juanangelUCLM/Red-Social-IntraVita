<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<div class="col-md-12">
	<div class="col-xs-12 col-sm-offset-3 col-sm-5" style="margin-bottom:10px;">
	<form action="publicar" method="post" enctype="multipart/form-data">
		<label>¿Qué estás pensando?</label>
		<div class="form-group">
			<textarea rows="8" cols="60" name="mensaje"></textarea>
			<input id="input-b5" name="fichero" type="file">
		</div>
		<button type="submit" class="btn btn-primary btn-outline" formaction="publicarAmigos">Solo amigos</button>	
		<button type="submit" class="btn btn-primary btn-outline">Publicar</button>	
	</form>
    </div>
</div>

<c:forEach items="${publicaciones}" var="publicacion">
	<div class="col-xs-12 col-sm-offset-2 col-sm-8">
            <div class="panel panel-default panel-google-plus">
                <c:if test="${publicacion.email==user.email || user.email=='admin@hotmail.com'}">
					 <div class="dropdown">
					 <a href="editarpublicacion?id=${publicacion.idPublicacion}"><i class="fa fa-pencil-square-o fa-lg" aria-hidden="true"></i></a>
                   	<button type="button" class="btn btn-link delete-publicacion" data-id="${publicacion.idPublicacion}"><i class="fa fa-trash-o fa-lg" aria-hidden="true"></i></button>
                </div>
	   			</c:if>
               
                <div class="panel-heading">
                    <h3>${publicacion.nombre}</h3>
                    <h5><span>Publicado el </span> - <span>${publicacion.fecha}</span> </h5>
                    
                </div>
                <div class="panel-body">
                    <p>${publicacion.mensaje}</p>
                    <img src="data:image/gif;base64,${publicacion.imagenCodificada}">
                    <div class="row">
                    	<div class="col-md-12">
                    	<c:forEach items="${respuestas[publicacion.idPublicacion]}" var="respuesta">
							<div class="row">
								<div class=" col-xs-12 col-sm-12 ">
								<div class="panel panel-default panel-google-plus">
									<div class="panel-heading">
										<h3 style="display:inline;">${respuesta.nombre} - </h3><span>${respuesta.fecha}</span>
									</div>
									<div class="panel-body">
										<p>${respuesta.mensaje}</p>
									</div>
								</div>
							</div>
							</div>
						</c:forEach>
                    </div>
                    </div>
                </div>
                <div class="col-md-2">
                	 <form action="darlike" method="post">
                    <button type="submit" ${checklikes[publicacion.idPublicacion]==1?"style='border-color:blue;border-width:2px;'":""} class="btn btn-default "><i class="fa fa-thumbs-o-up" aria-hidden="true"></i> (${likes[publicacion.idPublicacion]})</button>
                    <input type="hidden" name="like-publicacion" value="${publicacion.idPublicacion}">
                    </form>
                </div>
                <div class="panel-footer" style="margin-top: -10px;">
                    <div class="input-placeholder">Add a comment...</div>
                </div>   
                <div class="panel-google-plus-comment">
                    <div class="panel-google-plus-textarea">
                     <form action="comentar" method="post">
                        <textarea rows="4" name="respuesta" style="height: 100px;width: 500px;"></textarea><br>
                        <input type="hidden" name="respuesta-publicacion" value="${publicacion.idPublicacion}">
                        <button type="submit" class="btn btn-success">Comentar</button>
                        <button type="reset" class="btn btn-default">Cancelar</button>
                        </form>
                    </div>
                    <div class="clearfix"></div>
                </div>
            </div>
        </div>
</c:forEach>