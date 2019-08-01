<%@ page import="java.util.*,java.text.*,AubergeInnServlet.*,AubergeInn.Tuple.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<title>IFT287 - Système de gestion de l'AubergeInn</title>
		<meta name="author" content="Maxime Paré et Simon Cesare-Zurek">
		<meta name="description" content="Page des réservations de l'AubergeInn.">
		
		<!-- Required meta tags -->
	    <meta charset="utf-8">
	    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	
	    <!-- Bootstrap CSS -->
	    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
	</head>
	<body>
	<%
	    List<TupleChambre> chambresDispo = AubergeInnHelper.getAubergeInnInterro(session).getGestionChambre()
	                .getChambresLibres();
        List<TupleChambre> chambres = AubergeInnHelper.getAubergeInnInterro(session).getGestionChambre()
	                .getAllChambres();
        List<TupleClient> clients = AubergeInnHelper.getAubergeInnInterro(session).getGestionClient()
	                .getAllClients();

%>
		<div class="container">
			<jsp:include page="/WEB-INF/menuBar.jsp" />
			<h1 class="text-center">Réservations d'une chambre</h1>
			<form action="Reservations" method="POST">
				<div class="row">
					<div class="form-group col-sm-3">
				    	<label for="idClient">Client</label>
				    	<select class="form-control" name="idClient">
							<option selected>Choisir...</option>
						<%
					        for (TupleClient client : clients)
				        	{

		%>
							<option value="<%=client.getIdClient()%>"><%=client.getIdClient()%> - <%=client.getPrenom()%> <%=client.getNom()%></option>
						<%
			        		} //fin clients
		%>
											 
						</select>
				    </div>
				    <div class="form-group col-sm-3">
				    	<label for="idChambre">Chambre</label>
				    	<select class="form-control" name="idChambre">
							<option selected>Choisir...</option>
						<%
					        for (TupleChambre chambre : chambres)
				        	{

		%>
							<option value="<%=chambre.getIdChambre()%>"><%=chambre.getIdChambre()%> - <%=chambre.getNom()%></option>
						<%
			        		} //fin chambres
		%>
											 
						</select>
				    </div>
				    <div class="form-group col-sm-2">
					    <label for="dateDebut">Date d'arrivé</label>
					    <input class="form-control" type="DATE" name="dateDebut" max="9999-12-31" value="<%= request.getAttribute("dateDebut") != null ? (String)request.getAttribute("dateDebut") : "" %>" required>
				    </div>
				    <div class="form-group col-sm-2">
				    	<label for="dateFin">Date du départ</label>
				    	<input class="form-control col-sm" type="DATE" name="dateFin" max="9999-12-31" value="<%= request.getAttribute("dateFin") != null ? (String)request.getAttribute("dateFin") : "" %>" required>
				    </div>
					 <input class="btn mt-4 mb-3  btn-primary col-sm-2" type="SUBMIT" name="reserver" value="Réserver">
			    </div>
			   
			     
				
			</form>
			<h1 class="text-center">Chambres libres aujourd'hui</h1>
			
				<%
						        if (!chambresDispo.isEmpty())
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
							        for (TupleChambre c : chambresDispo)
							        {
	%>
					<tr>
				      <td scope="row"><%=c.getIdChambre()%></td>
				      <td><%=c.getNom()%></td>
				      <td><%=c.getTypeLit()%></td>
				      <td><%=c.getPrix()%>$</td>
				    </tr>
				    <%
	     							
	         						} // end for chambresDispo
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
		<jsp:include page="/WEB-INF/footer.jsp" />
	</body>
</html>
