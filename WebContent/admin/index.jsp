<% if(session.getAttribute("admin") != null) {%>
<jsp:include page="./header.jsp" />
<%@page import="dominio.Cliente"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>

<head>

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css" integrity="sha512-z3gLpd7yknf1YoNbCzqRKc4qyor8gaKU1qmn+CShxbuBusANI9QpRohGBreCFkKxLhei6S9CQXFEbbKuqLg0DA==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <style>
    
	@import url('https://fonts.googleapis.com/css2?family=DM+Serif+Display:ital@1&family=Dawning+of+a+New+Day&family=Kanit&family=Nunito:ital,wght@1,200&family=Quicksand&family=Roboto+Slab:wght@500&family=Shadows+Into+Light&display=swap');
    
    .titulo, .icono{
    	display: flex;
    	align-items:center;
    	justify-content:center;
    	padding-left:20px;
    }
    .titulo h1{
    		font-family: 'DM Serif Display', serif;           
            font-size: 100px;
    }
    
    </style>
</head>



<body>

	<%
	   String urlImagen = "https://cdn-icons-png.flaticon.com/512/3635/3635987.png";
	   ArrayList<Integer> meses = (ArrayList<Integer>)session.getAttribute("graficosAdminMeses");
	   ArrayList<Integer> cantPrestamos = (ArrayList<Integer>)session.getAttribute("graficosAdminCantPrestamos");
	%>
	
	<div class="titulo">
		<h1>BANCO FRGP</h1>
	</div>
    <div class="icono">
   		
    </div>
    <div>
	  <canvas id="myChart"></canvas>
	</div>
    <script src="https://cdn.jsdelivr.net/npm/chart.js@4.4.1/dist/chart.umd.min.js"></script>
    
    <script>
	  const ctx = document.getElementById('myChart');
	
	  new Chart(ctx, {
	    type: 'bar',
	    data: {
	      labels: [<%
                    for (int i = 0; i < meses.size(); i++) {
                        out.print(meses.get(i));
                        if (i < meses.size() - 1) {
                            out.print(", ");
                        }
                    }
                %>],
	      datasets: [{
	        label: '# of Votes',
	        data: [<%
                for (int i = 0; i < cantPrestamos.size(); i++) {
                    out.print(cantPrestamos.get(i));
                    if (i < cantPrestamos.size() - 1) {
                        out.print(", ");
                    }
                }
            %>],
	        borderWidth: 1
	      }]
	    },
	    options: {
	      scales: {
	        y: {
	          beginAtZero: true
	        }
	      }
	    }
	  });
	</script>
</body>


<jsp:include page="./footer.jsp" />
<% }else {
	response.sendRedirect("../index.jsp");
}%>