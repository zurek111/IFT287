<%@ page import="java.util.*,java.text.*,AubergeInnServlet.*,AubergeInn.Tuple.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<title>IFT287 - Système de gestion de l'AubergeInn</title>
		<meta name="author" content="Maxime Paré et Simon Cesare-Zurek">
		<meta name="description" content="Page d'affichage des détails d'un client du système de gestion de l'AubergeInn.">
		
		<!-- Required meta tags -->
	    <meta charset="utf-8">
	    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	
	    <!-- Bootstrap CSS -->
	    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
	</head>
	<body>
	
		<div class="container">
			<jsp:include page="/WEB-INF/menuBar.jsp" />
			<h1 class="text-center">Détails du client</h1>
			<form action="ClientDetails" method="GET">
			<%
				int idClient = Integer.parseInt(request.getParameter("idClient"));
				TupleClient c = AubergeInnHelper.getAubergeInnInterro(session).getGestionClient().getClient(idClient);
			%>
				<div class="row">
				    <div class="form-group col-sm-3">
				    	<b><%=c.getPrenom()%></b>
				    </div>
				    <div class="form-group col-sm-3">
				    	<b><%=c.getNom()%></b>
				    </div>		    
			    </div>
			     
				
			</form>
			<h1 class="text-center">Réservations</h1>
			
				<%
						    List<TupleReservation> reservations = c.getReservations();
						        if (!reservations.isEmpty())
						        {
%>
			<table class="table">
				<thead>
				    <tr>
				   	  <th scope="col">Date de début</th>
				      <th scope="col">Date de fin</th>
				      <th scope="col">IdChambre</th>
				      <th scope="col">Nom</th>
				      <th scope="col">Type de lit</th>
				      <th scope="col">Prix total</th>
				    </tr>
				</thead>
				<tbody>
					<%
									
							        for (TupleReservation r : reservations)
							        {
							            TupleChambre chambre =  AubergeInnHelper.getAubergeInnInterro(session).getGestionChambre().getChambre(r.getIdChambre());
	%>
					<tr>
					  <td scope="row"><%=r.getDateDebut()%></td>
					  <td scope="row"><%=r.getDateFin()%></td>
					  <td scope="row"><%=chambre.getIdChambre()%></td>
				      <td><%=chambre.getNom()%></td>
				      <td><%=chambre.getTypeLit()%></td>
				      <td><%=r.getPrixTotal()%>$</td>
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
	 				<h3 class="text-center text-primary">Aucune réservation pour ce client</h3>
	 			<%
	     							
     						} // end else
	         					
	 %>	
			<div class="row">
				<div class="col-sm-5">
				</div>
				<a href="/tp5/Clients" class="btn btn-primary col-sm-2">Retour aux clients</a>
			</div>		
		</div>
		<jsp:include page="/WEB-INF/footer.jsp" />
	</body>
</html>
