<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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

				<div class="row mt-5">
					<a href="/tp5/Reservations" class="btn btn-primary col-sm-2 offset-sm-5">Réserver une chambre</a>
				</div>
				<div class="row mt-3">
					<a href="/tp5/Chambres" class="btn btn-primary col-sm-2 offset-sm-5">Gérer les chambres</a>
				</div>
				<div class="row mt-3">
					<a href="/tp5/Clients" class="btn btn-primary col-sm-2 offset-sm-5">Gérer les clients</a>
				</div>
				<div class="row mt-3">
					<a href="/tp5/Logout" class="btn btn-primary col-sm-2 offset-sm-5">Quitter</a>
				</div>

		</div>
		<jsp:include page="/WEB-INF/footer.jsp" />
	</body>
</html>
