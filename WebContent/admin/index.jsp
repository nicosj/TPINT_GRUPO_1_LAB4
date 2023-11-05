<% if(session.getAttribute("admin") != null) {%>
<jsp:include page="./header.jsp" />
<%@page import="dominio.Cliente"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>

<header>
    <div class="container">
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">Sistema de bancos LAdri</h1>
            </div>
        </div>
    </div>
<%--    genera contenido para banco ladri--%>
    <section>
        <p>
            Lorem ipsum dolor sit amet, consectetur adipisicing elit. Quos
        </p>
    </section>
</header>


<jsp:include page="./footer.jsp" />
<% }else {
	response.sendRedirect("../index.jsp");
}%>