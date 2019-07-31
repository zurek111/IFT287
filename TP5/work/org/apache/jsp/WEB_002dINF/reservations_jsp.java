/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/9.0.22
 * Generated at: 2019-07-31 06:17:51 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.*;
import java.text.*;
import AubergeInnServlet.*;
import AubergeInn.Tuple.*;

public final class reservations_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.HashSet<>();
    _jspx_imports_packages.add("AubergeInn.Tuple");
    _jspx_imports_packages.add("javax.servlet");
    _jspx_imports_packages.add("java.util");
    _jspx_imports_packages.add("java.text");
    _jspx_imports_packages.add("javax.servlet.http");
    _jspx_imports_packages.add("AubergeInnServlet");
    _jspx_imports_packages.add("javax.servlet.jsp");
    _jspx_imports_classes = null;
  }

  private volatile javax.el.ExpressionFactory _el_expressionfactory;
  private volatile org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public java.util.Set<java.lang.String> getPackageImports() {
    return _jspx_imports_packages;
  }

  public java.util.Set<java.lang.String> getClassImports() {
    return _jspx_imports_classes;
  }

  public javax.el.ExpressionFactory _jsp_getExpressionFactory() {
    if (_el_expressionfactory == null) {
      synchronized (this) {
        if (_el_expressionfactory == null) {
          _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
        }
      }
    }
    return _el_expressionfactory;
  }

  public org.apache.tomcat.InstanceManager _jsp_getInstanceManager() {
    if (_jsp_instancemanager == null) {
      synchronized (this) {
        if (_jsp_instancemanager == null) {
          _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
        }
      }
    }
    return _jsp_instancemanager;
  }

  public void _jspInit() {
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
      throws java.io.IOException, javax.servlet.ServletException {

    if (!javax.servlet.DispatcherType.ERROR.equals(request.getDispatcherType())) {
      final java.lang.String _jspx_method = request.getMethod();
      if ("OPTIONS".equals(_jspx_method)) {
        response.setHeader("Allow","GET, HEAD, POST, OPTIONS");
        return;
      }
      if (!"GET".equals(_jspx_method) && !"POST".equals(_jspx_method) && !"HEAD".equals(_jspx_method)) {
        response.setHeader("Allow","GET, HEAD, POST, OPTIONS");
        response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "JSPs only permit GET, POST or HEAD. Jasper also permits OPTIONS");
        return;
      }
    }

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html>\r\n");
      out.write("\t<head>\r\n");
      out.write("\t\t<title>IFT287 - Système de gestion de l'AubergeInn</title>\r\n");
      out.write("\t\t<meta name=\"author\" content=\"Maxime Paré et Simon Cesare-Zurek\">\r\n");
      out.write("\t\t<meta name=\"description\" content=\"Page d'accueil du système de gestion de l'AubergeInn.\">\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t<!-- Required meta tags -->\r\n");
      out.write("\t    <meta charset=\"utf-8\">\r\n");
      out.write("\t    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1, shrink-to-fit=no\">\r\n");
      out.write("\t\r\n");
      out.write("\t    <!-- Bootstrap CSS -->\r\n");
      out.write("\t    <link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css\" integrity=\"sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO\" crossorigin=\"anonymous\">\r\n");
      out.write("\t</head>\r\n");
      out.write("\t<body>\r\n");
      out.write("\t");

	    List<TupleChambre> chambresDispo = AubergeInnHelper.getAubergeInnInterro(session).getGestionChambre()
	                .getChambresLibres();
        List<TupleChambre> chambres = AubergeInnHelper.getAubergeInnInterro(session).getGestionChambre()
	                .getAllChambres();
        List<TupleClient> clients = AubergeInnHelper.getAubergeInnInterro(session).getGestionClient()
	                .getAllClients();


      out.write("\r\n");
      out.write("\t\t<div class=\"container\">\r\n");
      out.write("\t\t\t");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "/WEB-INF/menuBar.jsp", out, false);
      out.write("\r\n");
      out.write("\t\t\t<h1 class=\"text-center\">Réservations d'une chambre</h1>\r\n");
      out.write("\t\t\t<form action=\"Reservations\" method=\"POST\">\r\n");
      out.write("\t\t\t\t<div class=\"row\">\r\n");
      out.write("\t\t\t\t\t<div class=\"form-group col-sm-3\">\r\n");
      out.write("\t\t\t\t    \t<label for=\"idClient\">Client</label>\r\n");
      out.write("\t\t\t\t    \t<select class=\"form-control\" name=\"idClient\">\r\n");
      out.write("\t\t\t\t\t\t\t<option selected>Choisir...</option>\r\n");
      out.write("\t\t\t\t\t\t");

					        for (TupleClient client : clients)
				        	{

		
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t<option value=\"");
      out.print(client.getIdClient());
      out.write('"');
      out.write('>');
      out.print(client.getIdClient());
      out.write(' ');
      out.write('-');
      out.write(' ');
      out.print(client.getPrenom());
      out.write(' ');
      out.print(client.getNom());
      out.write("</option>\r\n");
      out.write("\t\t\t\t\t\t");

			        		} //fin clients
		
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t \r\n");
      out.write("\t\t\t\t\t\t</select>\r\n");
      out.write("\t\t\t\t    </div>\r\n");
      out.write("\t\t\t\t    <div class=\"form-group col-sm-3\">\r\n");
      out.write("\t\t\t\t    \t<label for=\"idChambre\">Chambre</label>\r\n");
      out.write("\t\t\t\t    \t<select class=\"form-control\" name=\"idChambre\">\r\n");
      out.write("\t\t\t\t\t\t\t<option selected>Choisir...</option>\r\n");
      out.write("\t\t\t\t\t\t");

					        for (TupleChambre chambre : chambres)
				        	{

		
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t<option value=\"");
      out.print(chambre.getIdChambre());
      out.write('"');
      out.write('>');
      out.print(chambre.getIdChambre());
      out.write(' ');
      out.write('-');
      out.write(' ');
      out.print(chambre.getNom());
      out.write("</option>\r\n");
      out.write("\t\t\t\t\t\t");

			        		} //fin chambres
		
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t \r\n");
      out.write("\t\t\t\t\t\t</select>\r\n");
      out.write("\t\t\t\t    </div>\r\n");
      out.write("\t\t\t\t    <div class=\"form-group col-sm-3\">\r\n");
      out.write("\t\t\t\t\t    <label for=\"dateDebut\">Date d'arrivé</label>\r\n");
      out.write("\t\t\t\t\t    <input class=\"form-control\" type=\"DATE\" name=\"dateDebut\" value=\"");
      out.print( request.getAttribute("dateDebut") != null ? (String)request.getAttribute("dateDebut") : "" );
      out.write("\" required>\r\n");
      out.write("\t\t\t\t    </div>\r\n");
      out.write("\t\t\t\t    <div class=\"form-group col-sm-3\">\r\n");
      out.write("\t\t\t\t    \t<label for=\"dateFin\">Date du départ</label>\r\n");
      out.write("\t\t\t\t    \t<input class=\"form-control col-sm\" type=\"DATE\" name=\"dateFin\" value=\"");
      out.print( request.getAttribute("dateFin") != null ? (String)request.getAttribute("dateFin") : "" );
      out.write("\" required>\r\n");
      out.write("\t\t\t\t    </div>\r\n");
      out.write("\r\n");
      out.write("\t\t\t    </div>\r\n");
      out.write("\t\t\t    \r\n");
      out.write("\t\t\t    <div style=\"text-align:center;\">\r\n");
      out.write("\t\t\t\t<input class=\"btn btn-primary\" type=\"SUBMIT\" name=\"reserver\" value=\"Réserver\">\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t     \r\n");
      out.write("\t\t\t\t\r\n");
      out.write("\t\t\t</form>\r\n");
      out.write("\t\t\t<h1 class=\"text-center\">Chambres libres aujourd'hui</h1>\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\t\t");

						        if (!chambresDispo.isEmpty())
						        {

      out.write("\r\n");
      out.write("\t\t\t<table class=\"table\">\r\n");
      out.write("\t\t\t\t<thead>\r\n");
      out.write("\t\t\t\t    <tr>\r\n");
      out.write("\t\t\t\t      <th scope=\"col\">IdChambre</th>\r\n");
      out.write("\t\t\t\t      <th scope=\"col\">Nom</th>\r\n");
      out.write("\t\t\t\t      <th scope=\"col\">Type de lit</th>\r\n");
      out.write("\t\t\t\t      <th scope=\"col\">Prix total</th>\r\n");
      out.write("\t\t\t\t    </tr>\r\n");
      out.write("\t\t\t\t</thead>\r\n");
      out.write("\t\t\t\t<tbody>\r\n");
      out.write("\t\t\t\t\t");

							        for (TupleChambre c : chambresDispo)
							        {
	
      out.write("\r\n");
      out.write("\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t      <td scope=\"row\">");
      out.print(c.getIdChambre());
      out.write("</td>\r\n");
      out.write("\t\t\t\t      <td>");
      out.print(c.getNom());
      out.write("</td>\r\n");
      out.write("\t\t\t\t      <td>");
      out.print(c.getTypeLit());
      out.write("</td>\r\n");
      out.write("\t\t\t\t      <td>");
      out.print(c.getPrix());
      out.write("$</td>\r\n");
      out.write("\t\t\t\t    </tr>\r\n");
      out.write("\t\t\t\t    ");

	     							
	         						} // end for chambresDispo
	
      out.write("\r\n");
      out.write("\t\t\t\t</tbody>\r\n");
      out.write("\t\t\t</table>\r\n");
      out.write("\t         \t\t");
			} //end if
	         					else
	         					{
	 
      out.write("\r\n");
      out.write("\t \t\t\t\t<h3 class=\"text-center text-primary\">Aucune chambre libre</h3>\r\n");
      out.write("\t \t\t\t");

	     							
     						} // end else
	         					
	 
      out.write("\t\r\n");
      out.write("\t\t\t<div class=\"row\">\r\n");
      out.write("\t\t\t\t<div class=\"col-sm-5\">\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t<a href=\"/tp5/Accueil\" class=\"btn btn-primary col-sm-2\">Retour au menu</a>\r\n");
      out.write("\t\t\t</div>\t\t\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t\t");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "/WEB-INF/footer.jsp", out, false);
      out.write("\r\n");
      out.write("\t</body>\r\n");
      out.write("</html>\r\n");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
