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
right:33%;

}

h1{
position:absolute;
top:30%;
left:33%;

}


.msg{
position:absolute;
top:10%;
left:33%;
    color: #664d03;
    background-color: #fff3cd;
    border-color: #ffecb5;

}
</style>


</head>
<body>

	<h1>Bem Vindo ao Curso de JSP</h1>

	
	<form action="<%= request.getContextPath() %>/ServletLogin" method="post"  class="row g-3 needs-validation" novalidate>
          <!--regra para validação de login-->
		 <input type="hidden" value= "<%= request.getParameter("url") %>" name="url">
		 
		 <div class="mb-3">
		 <label class="form-label">Login:</label>
		 <input class="form-control" type="text" id="login" name="login" required="required">
		  <div class="invalid-feedback">
      Informe um login!
          </div>
		 </div>
		 
		 <div class="mb-3">
		 <label class="form-label">Senha:</label>
		 <input class="form-control" type="password" id="senha" name="senha" required="required">
		  <div class="invalid-feedback">
      Informe uma Senha!
          </div>
		 
		 </div>
		 <button class="btn btn-primary" type="submit" value="Acessar">Logar</button> 
		 
		 
		 	</form>
		 	<h3 class="msg">${msg}</h3>
		 	
		 	<!-- Js do BootStrap -->
		 	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>

<script type="text/javascript">

(function () {
	  'use strict'

	  // validação de campos com bootstrap
	  var forms = document.querySelectorAll('.needs-validation')

	  // Loop over them and prevent submission
	  Array.prototype.slice.call(forms)
	    .forEach(function (form) {
	      form.addEventListener('submit', function (event) {
	        if (!form.checkValidity()) {
	          event.preventDefault()
	          event.stopPropagation()
	        }

	        form.classList.add('was-validated')
	      }, false)
	    })
	})()

</script>
</body>
</html>