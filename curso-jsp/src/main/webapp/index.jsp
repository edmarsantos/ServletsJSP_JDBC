<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html  lang="en">
<head>
<meta charset="ISO-8859-1">

<!-- viewport serve para responsividade -->
 <meta name="viewport" content="width=device-width, initial-scale=1">
 
 <!-- Layout do CSS -->
 <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
 
<title>Curso JSP</title>

<style type="text/css">

/* css para ajustar tela position -> absuta top -> topo em 40%  margem esquerda em 33% margem direita em 33%*/
form{
position:absolute;
top:40%;
left:33%;
right:33;

}

h1{
position:absolute;
top:30%;
left:33%;

}


.msg{
position:absolute;
top:60%;
left:33%;
color:red;

}
</style>


</head>
<body>

	<h1>Bem Vindo ao Curso de JSP</h1>

	
	<form action="ServletLogin" method="post"  class="row g-3">
          <!--regra para validação de login-->
		 <input type="hidden" value= "<%= request.getParameter("url") %>" name="url">
		 
		 <div class="col-md-6">
		 <label class="form-label">Login:</label>
		 <input class="form-control" type="text" id="login" name="login">
		 </div>
		 
		 <div class="col-md-6">
		 <label class="form-label">Senha:</label>
		 <input class="form-control" type="password" id="senha" name="senha">
		 </div>
		 <button class="btn btn-primary" type="submit" value="Acessar">Logar</button> 
		 
		 
		 	</form>
		 	<h3 class="msg">${msg}</h3>
		 	
		 	<!-- Js do BootStrap -->
		 	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</body>
</html>