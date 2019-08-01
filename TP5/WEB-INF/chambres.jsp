<%@ page import="java.util.*,java.text.*,AubergeInnServlet.*,AubergeInn.Tuple.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<title>IFT287 - Système de gestion de l'AubergeInn</title>
		<meta name="author" content="Maxime Paré et Simon Cesare-Zurek">
		<meta name="description" content="Page des chambres du système de gestion de l'AubergeInn.">
		
		<!-- Required meta tags -->
	    <meta charset="utf-8">
	    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	
	    <!-- Bootstrap CSS -->
	    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
	</head>
	<body>
		<div class="container-fluid">
			<jsp:include page="/WEB-INF/menuBar.jsp" />
			<div class="row">
			<div class="col-sm-6">
				<h1 class="text-center">Chambres</h1>
				<form action="Chambres" method="POST">
					<div class="row">
						<div class="form-group col-sm-6 offset-sm-3">
					    	<label for="idChambre">IdChambre</label>
					    	<input class="form-control" type="NUMBER" name="idChambre" min="0" value="<%= request.getAttribute("idChambre") != null ? (String)request.getAttribute("idChambre") : "" %>" required>
					    </div>
					</div>
					<div class="row">
					    <div class="form-group col-sm-6 offset-sm-3">
					    	<label for="nom">Nom</label>
					    	<input class="form-control" type="TEXT" name="nom" value="<%= request.getAttribute("nom") != null ? (String)request.getAttribute("nom") : "" %>" required>
					    </div>
				    </div>
					<div class="row">
					    <div class="form-group col-sm-4 offset-sm-3">
					    	<label for="typeLit">Type de lit</label>
					    	<input class="form-control" type="TEXT" name="typeLit" value="<%= request.getAttribute("typeLit") != null ? (String)request.getAttribute("typeLit") : "" %>" required>
					    </div>

					    <div class="form-group col-sm-2">
					    	<label for="prix">Prix</label>
					    	<input class="form-control" type="NUMBER" name="prix" min="0" value="<%= request.getAttribute("prix") != null ? (String)request.getAttribute("prix") : "" %>" required>
					    </div>
					</div>
					<div class="row">
					    <input class="btn btn-primary mt-2 mb-2 col-sm-4 offset-sm-4" type="SUBMIT" name="ajouterChambre" value="Ajouter">
					</div>
				    
				     
					
				</form>
	
				<%
								List<TupleCommodite> commodites = AubergeInnHelper.getAubergeInnInterro(session).getGestionCommodite()
							                .getAllCommodites();
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
							        		List<TupleCommodite> commoditesChambre = AubergeInnHelper.getAubergeInnInterro(session).getGestionCommodite()
							                .getCommoditesOffertes(c.getIdChambre());
							                List<TupleCommodite> commoditesManquante = new ArrayList<TupleCommodite>(commodites);
		%>
							<tr>
						      <td scope="row"><%=c.getIdChambre()%></td>
						      <td><%=c.getNom()%></td>
						      <td><%=c.getTypeLit()%></td>
						      <td><%=c.getPrix()%>$</td>
						      <td> 
						      	<table class="table-sm">
<%
								        for (TupleCommodite commodite : commoditesChambre)
							        	{
							        		commoditesManquante.remove(commodite);
		%>
								
									<tr>
									  	<td><%=commodite.getDescription()%></td>
									  	<td>
									  		<button type="SUBMIT" name="enleverCommodite" value="<%=c.getIdChambre()%>/<%=commodite.getIdCommodite()%>"> <img src="<%=request.getContextPath()%>/Images/trash.png" height="25" width="25" alt="Enlever la commodité"></button></td>
								  	</tr>
							  	
							  	<%
								        } //fin commodite
		%>
									<tr>
									  	<td>
											<select name="idCommodite<%=c.getIdChambre()%>">
												<option selected>...</option>
											<%
										        for (TupleCommodite commodite : commoditesManquante)
									        	{

		%>
												<option value="<%=commodite.getIdCommodite()%>"><%=commodite.getDescription()%></option>
											<%
								        		} //fin commoditeManquante
		%>
											 
											</select>
									  	</td>
									  	<td>
									  		<button type="SUBMIT" name="inclureCommodite" value="<%=c.getIdChambre()%>"> <img src="<%=request.getContextPath()%>/Images/plus.png" alt="Inclure la commodité"></button></td>
								  	</tr>
								</table>
							  </td>
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
	 		</div>
	 		<div class="col-sm-4 offset-sm-1">
				<h1 class="text-center">Commodités</h1>
				<form action="Chambres" method="POST">
					<div class="row">
						<div class="form-group col-sm-6 offset-sm-3">
					    	<label for="idCommodite">IdCommodité</label>
					    	<input class="form-control" type="NUMBER" name="idCommodite" min="0" value="<%= request.getAttribute("IdCommodite") != null ? (String)request.getAttribute("IdCommodite") : "" %>" required>
					    </div>
					</div>
					<div class="row">
					    <div class="form-group col-sm-6 offset-sm-3">
					    	<label for="description">Description</label>
					    	<input class="form-control" type="TEXT" name="description" value="<%= request.getAttribute("description") != null ? (String)request.getAttribute("description") : "" %>" required>
					    </div>
				    </div>
					<div class="row">
					    <div class="form-group col-sm-6 offset-sm-3">
					    	<label for="prix">Prix</label>
					    	<input class="form-control" type="NUMBER" name="prix" min="0" value="<%= request.getAttribute("prix") != null ? (String)request.getAttribute("prix") : "" %>" required>
					    </div>
					</div>
					
					<div class="row">
					    <input class="btn mt-2 mb-2 btn-primary col-sm-4 offset-sm-4" type="SUBMIT" name="ajouterCommodite" value="Ajouter">
					</div>
				    
				     
					
				</form>
	
				<%
							    
							        if (!commodites.isEmpty())
							        {
	%>
				<table class="table">
					<thead>
					    <tr>
					      <th scope="col">IdCommodité</th>
					      <th scope="col">Description</th>
					      <th scope="col">Prix</th>
					    </tr>
					</thead>
					<tbody>
						<form action="Chambres" method="POST">
						<%
								        for (TupleCommodite c : commodites)
							        	{
		%>
							<tr>
						      <td scope="row"><%=c.getIdCommodite()%></td>
						      <td><%=c.getDescription()%></td>
						      <td><%=c.getPrix()%>$</td>
						    </tr>
					    <%
		     							
		         						} // end for commodites
		%>
						</form>
					</tbody>
				</table>
		         		<%			} //end if
		         					else
		         					{
		 %>
		 				<h3 class="text-center text-primary">Aucune commodités dans la base de données</h3>
		 			<%
		     							
	     						} // end else
		         					
		 %>	
		 		</div>
		 	</div>
			<div class="row">
				<a href="/tp5/Accueil" class="btn btn-primary col-sm-2 offset-sm-5">Retour au menu</a>
			</div>		
		</div>
		<jsp:include page="/WEB-INF/footer.jsp" />
	</body>
</html>
