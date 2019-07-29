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
			<h1 class="text-center">Chambres</h1>
			<form action="Chambres" method="POST">
				<div class="row">
					<div class="form-group col-sm-2">
				    	<label for="idChambre">IdChambre</label>
				    	<input class="form-control" type="NUMBER" name="idChambre" min="0" value="<%= request.getAttribute("idChambre") != null ? (String)request.getAttribute("idChambre") : "" %>" required>
				    </div>
				    <div class="form-group col-sm-3">
				    	<label for="nom">Nom</label>
				    	<input class="form-control" type="TEXT" name="nom" min="0" value="<%= request.getAttribute("nom") != null ? (String)request.getAttribute("nom") : "" %>" required>
				    </div>
				    <div class="form-group col-sm-3">
				    	<label for="typeLit">Type de lit</label>
				    	<input class="form-control" type="TEXT" name="typeLit" min="0" value="<%= request.getAttribute("typeLit") != null ? (String)request.getAttribute("typeLit") : "" %>" required>
				    </div>
				    <div class="form-group col-sm-2">
				    	<label for="prix">Prix</label>
				    	<input class="form-control" type="NUMBER" name="prix" value="<%= request.getAttribute("prix") != null ? (String)request.getAttribute("prix") : "" %>" required>
				    </div>
				    <input class="btn mt-4 mb-3  btn-primary col-sm-2" type="SUBMIT" name="ajouterChambre" value="Ajouter">
				    
			    </div>
			     
				
			</form>

			<%
						    List<TupleChambre> chambres = AubergeInnHelper.getAubergeInnInterro(session).getGestionChambre()
						                .getAllChambres();
						        if (!chambres.isEmpty())
						        {
%>
			<table class="table">
				<thead>
				    <tr>
				      <th scope="col">IdChambre</th>
				      <th scope="col">Nom</th>
				      <th scope="col">Type de lit</th>
				      <th scope="col">Prix</th>
				      <th scope="col">Commodité</th>
				      <th scope="col">Supprimer</th>
				    </tr>
				</thead>
				<tbody>
					<form action="Chambres" method="POST">
					<%
							        for (TupleChambre c : chambres)
						        	{
	%>
						<tr>
					      <td scope="row"><%=c.getIdChambre()%></td>
					      <td><%=c.getNom()%></td>
					      <td><%=c.getTypeLit()%></td>
					      <td><%=c.getPrix()%> ans</td>
					      <td>?</td>
					      <td><button class="btn btn-primary" type="SUBMIT" name="supprimerChambre" value="<%=c.getIdChambre()%>">Supprimer</button></td>
					    </tr>
				    <%
	     							
	         						} // end for chambres
	%>
					</form>
				</tbody>
			</table>
	         		<%			} //end if
	         					else
	         					{
	 %>
	 				<h3 class="text-center text-primary">Aucune chambres dans la base de données</h3>
	 			<%
	     							
     						} // end else
	         					
	 %>	
			<div class="row">
				<a href="/tp5/Accueil" class="btn btn-primary col-sm-2 offset-sm-5">Retour au menu</a>
			</div>		
		</div>
		<jsp:include page="/WEB-INF/footer.jsp" />
	</body>
</html>
