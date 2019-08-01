<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    if(getServletContext().getAttribute("serveur") != null)
	{%>
        <jsp:forward page="/WEB-INF/menu.jsp" />
	<%}
%>
<!DOCTYPE html>
<html>
	<head>
		<title>IFT287 - Système de gestion de l'AubergeInn</title>
		<meta name="author" content="Maxime Paré et Simon Cesare-Zurek">
		<meta name="description" content="Page d'accueil du système de gestion de l'AubergeInn.">
		
		<!-- Required meta tags -->
	    <meta charset="utf-8">
	    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	
	    <!-- Bootstrap CSS -->
	    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
	</head>
	<body>
		<div class="container">
			<h1 class="text-center">Système de gestion de l'AubergeInn</h1>
			<div class="col-md-4 offset-md-4">
			<form action="Login" method="POST">
			    <div class="form-group">
				    <label for="userIdBD">Nom d'utilisateur de la base de donnée</label>
				    <input class="form-control" type="TEXT" name="userIdBD" value="<%= (request.getAttribute("userIdBD") != null ? (String)request.getAttribute("userIdBD") : "") %>" placeholder="ift287_XX">
			    </div>
			    <div class="form-group">
			    	<label for="motDePasseBD">Mot de passe</label>
			    	<input class="form-control" type="PASSWORD" name="motDePasseBD" value="<%= (request.getAttribute("motDePasseBD") != null ? (String)request.getAttribute("motDePasseBD") : "") %>">
			    </div>
			    <div class="form-group">
				    <label for="serveur">Serveur</label>
				    <select class="custom-select" name="serveur">
				    	<option value="local" <%= (request.getAttribute("serveur") != null ? (((String)request.getAttribute("serveur")).equals("local") ? "selected" : "") : "") %>>local
				    	<option value="dinf" <%= (request.getAttribute("serveur") != null ? (((String)request.getAttribute("serveur")).equals("dinf") ? "selected" : "") : "selected") %>>dinf
				    </select>
			    </div>
			    <div class="form-group">
			    	<label for="bd">Nom de la base de donnée</label>
			    	<input class="form-control" type="TEXT" name="bd" value="<%= (request.getAttribute("bd") != null ? (String)request.getAttribute("bd") : "") %>" placeholder="ift287_XXdb">
			    </div>
				<input class="btn btn-primary" type="SUBMIT" name="connecter" value="Se connecter">
			</form>
			</div>
		</div>
		<jsp:include page="/WEB-INF/footer.jsp" />
	</body>
</html>
