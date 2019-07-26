<%@ page import="java.util.*,java.text.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<title>Système de gestion d'auberge</title>
		
		<!-- Required meta tags -->
	    <meta charset="utf-8">
	    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	
		<!-- Bootstrap CSS -->
	    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
	</head>
	
	<body>
		<div class="container">
			<h1 class="text-center">Ajout d'une chambre</h1>
			<div class="col-md-4 offset-md-4">
			<form action="Accueil" method="POST">
			    <div class="form-group">
				    <label for="nom">Nom de la chambre</label>
				    <input class="form-control" type="TEXT" name="nom" value="<%= request.getAttribute("nom") != null ? (String)request.getAttribute("nom") : "" %>">
			    </div>
			    <div class="form-group">
				    <label for="type">Type de lit</label>
				    <input class="form-control" type="TEXT" name="type" value="<%= request.getAttribute("type") != null ? (String)request.getAttribute("type") : "" %>">
			    </div>
			    <div class="form-group">
			    	<label for="prix">Prix de base</label>
			    	<input class="form-control" type="NUMBER" name="prix" min="0" value="<%= request.getAttribute("prix") != null ? (String)request.getAttribute("prix") : "" %>">
			    </div>
			    <div class="form-group">
			    	<label for="id">Id</label>
			    	<input class="form-control" type="NUMBER" name="id" min="0" value="<%= request.getAttribute("id") != null ? (String)request.getAttribute("id") : "" %>">
			    </div>
			     
				<input class="btn btn-primary" type="SUBMIT" name="ajouter" value="Ajouter la chambre">
			</form>
			</div>
		</div>
		<br>
		<%-- inclusion d'une autre page pour l'affichage des messages d'erreur--%>
		<jsp:include page="/WEB-INF/messageErreur.jsp" />
		<br>
		
		<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
		<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
	</body>
	
</html>