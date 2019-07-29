<%@ page import="java.util.*,java.text.*,AubergeInnServlet.*,AubergeInn.Tuple.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
			<div class="row">
				<a href="/tp5/Accueil" class="btn btn-primary col-sm-1">Menu</a>
			</div>
			<h1 class="text-center">Réservations d'une chambre</h1>
			<form action="Reservations" method="POST">
				<div class="row">
					<div class="form-group col-sm-3">
				    	<label for="idClient">IdClient</label>
				    	<input class="form-control" type="NUMBER" name="idClient" min="0" value="<%= request.getAttribute("idClient") != null ? (String)request.getAttribute("idClient") : "" %>" required>
				    </div>
				    <div class="form-group col-sm-3">
				    	<label for="idChambre">IdChambre</label>
				    	<input class="form-control" type="NUMBER" name="idChambre" min="0" value="<%= request.getAttribute("idChambre") != null ? (String)request.getAttribute("idChambre") : "" %>" required>
				    </div>
				    <div class="form-group col-sm-2">
					    <label for="dateDebut">Date d'arrivé</label>
					    <input class="form-control" type="DATE" name="dateDebut" value="<%= request.getAttribute("dateDebut") != null ? (String)request.getAttribute("dateDebut") : "" %>" required>
				    </div>
				    <div class="form-group col-sm-2">
				    	<label for="dateFin">Date du départ</label>
				    	<input class="form-control col-sm" type="DATE" name="dateFin" value="<%= request.getAttribute("dateFin") != null ? (String)request.getAttribute("dateFin") : "" %>" required>
				    </div>
				    <input class="btn mt-4 mb-3  btn-primary col-sm-2" type="SUBMIT" name="reserver" value="Réserver">
				    
			    </div>
			     
				
			</form>
			<h1 class="text-center">Chambres libres aujourd'hui</h1>
			
				<%
						    List<TupleChambre> chambres = AubergeInnHelper.getAubergeInnInterro(session).getGestionChambre()
						                .getChambresLibres();
						        if (!chambres.isEmpty())
						        {
%>
			<table class="table">
				<thead>
				    <tr>
				      <th scope="col">IdChambre</th>
				      <th scope="col">Nom</th>
				      <th scope="col">Type de lit</th>
				      <th scope="col">Prix total</th>
				    </tr>
				</thead>
				<tbody>
					<%
							        for (TupleChambre c : chambres)
							        {
	%>
					<tr>
				      <td scope="row"><%=c.getIdChambre()%></td>
				      <td><%=c.getNom()%></td>
				      <td><%=c.getTypeLit()%></td>
				      <td><%=c.getPrix()%>$</td>
				    </tr>
				    <%
	     							
	         						} // end for chambres
	%>
				</tbody>
			</table>
	         		<%			} //end if
	         					else
	         					{
	 %>
	 				<h3 class="text-center text-primary">Aucune chambre libre</h3>
	 			<%
	     							
     						} // end else
	         					
	 %>	
			<div class="row">
				<div class="col-sm-5">
				</div>
				<a href="/tp5/Accueil" class="btn btn-primary col-sm-2">Retour au menu</a>
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