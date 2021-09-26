<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Curso JSP</title>
</head>
<body>

	<h1>Bem Vindo ao Curso de JSP</h1>

	
	<form action="ServletLogin" method="post"  class="login-form">
          <!--regra para validação de login-->
		 <input type="hidden" value= "<%= request.getParameter("url") %>" name="url">
		 
		 <table>
		 <tr>
		 <td>Login: </td>
		 <td><input type="text" id="login" name="login"></td>
		 </tr>
		 <tr>
		 <td>Senha:</td>
		 <td> <input type="password" id="senha" name="senha"> </td>
		 </tr>
		 <tr>
		 <td></td>
		 <td><button type="submit" value="Logar">Logar</button> </td>
		 </tr>
		 </table>
		 
		 	</form>
		 	<h4>${msg}</h4>
</body>
</html>