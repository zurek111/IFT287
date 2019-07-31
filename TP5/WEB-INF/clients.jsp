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
			<jsp:include page="/WEB-INF/menuBar.jsp" />
			<h1 class="text-center">Clients</h1>
			<form action="Clients" method="POST">
				<div class="row">
					<div class="form-group col-sm-2">
				    	<label for="idClient">IdClient</label>
				    	<input class="form-control" type="NUMBER" name="idClient" min="0" value="<%= request.getAttribute("idClient") != null ? (String)request.getAttribute("idClient") : "" %>" required>
				    </div>
				    <div class="form-group col-sm-3">
				    	<label for="prenom">Prénom</label>
				    	<input class="form-control" type="TEXT" name="prenom" value="<%= request.getAttribute("prenom") != null ? (String)request.getAttribute("prenom") : "" %>" required>
				    </div>
				    <div class="form-group col-sm-3">
				    	<label for="nom">Nom</label>
				    	<input class="form-control" type="TEXT" name="nom" value="<%= request.getAttribute("nom") != null ? (String)request.getAttribute("nom") : "" %>" required>
				    </div>
				    <div class="form-group col-sm-2">
				    	<label for="age">Âge</label>
				    	<input class="form-control" type="NUMBER" name="age" min="0" value="<%= request.getAttribute("age") != null ? (String)request.getAttribute("age") : "" %>" required>
				    </div>
				    <input class="btn mt-4 mb-3  btn-primary col-sm-2" type="SUBMIT" name="ajouter" value="Ajouter">
				    
			    </div>
			     
				
			</form>

			<%
						    List<TupleClient> clients = AubergeInnHelper.getAubergeInnInterro(session).getGestionClient()
						                .getAllClients();
						        if (!clients.isEmpty())
						        {
%>
			<table class="table">
				<thead>
				    <tr>
				      <th scope="col">IdClient</th>
				      <th scope="col">Prénom</th>
				      <th scope="col">Nom</th>
				      <th scope="col">Âge</th>
				      <th scope="col">Réservations</th>
				      <th scope="col">Supprimer</th>
				    </tr>
				</thead>
				<tbody>
					<form action="Clients" method="POST">
					<%
							        for (TupleClient c : clients)
						        	{
							            
	%>
						<tr>
					      <td scope="row"><%=c.getIdClient()%></td>
					      <td><%=c.getPrenom()%></td>
					      <td><%=c.getNom()%></td>
					      <td><%=c.getAge()%> ans</td>
					      <%
						    List<TupleReservation> reservations = AubergeInnHelper.getAubergeInnInterro(session).getGestionClient().getClient(c.getIdClient()).getReservations();
						        if (!reservations.isEmpty())
						        {
						  %>
					      <td><a href="/tp5/Clients?idClient=<%=c.getIdClient()%>" class="btn btn-primary">Afficher</a></td>
					      <%    
					      		} // end if
						        else
						        {
					      %>
					      <td>Aucune</td>
					       <%    
					      		} // end else
					      %>
					      <td><button class="btn btn-primary" type="SUBMIT" name="supprimer" value="<%=c.getIdClient()%>">Supprimer</button></td>
					    </tr>
				    <%
	     							
	         						} // end for clients
	%>
					</form>
				</tbody>
			</table>
	         		<%			} //end if
	         					else
	         					{
	 %>
	 				<h3 class="text-center text-primary">Aucun clients dans la base de données</h3>
	 			<%
	     							
     						} // end else
	         					
	 %>	
			<div class="row">
				<div class="col-sm-5">
				</div>
				<a href="/tp5/Accueil" class="btn btn-primary col-sm-2">Retour au menu</a>
			</div>		
		</div>
		<jsp:include page="/WEB-INF/footer.jsp" />
	</body>
</html>
