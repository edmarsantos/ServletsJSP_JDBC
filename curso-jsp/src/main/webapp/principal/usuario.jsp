<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">


<!-- include da Head -->
<jsp:include page="head.jsp"></jsp:include>

<body>
	<!-- Pre-loader include do tema escolhido-->
	<jsp:include page="theme-loader.jsp"></jsp:include>
	<!-- Pre-loader end -->


	<div id="pcoded" class="pcoded">
		<div class="pcoded-overlay-box"></div>
		<div class="pcoded-container navbar-wrapper">

			<!-- include da barra de navega��o -->
			<jsp:include page="navbar.jsp"></jsp:include>


			<div class="pcoded-main-container">
				<div class="pcoded-wrapper">

					<!-- include do menu -->
					<jsp:include page="navbarmainmenu.jsp"></jsp:include>

					<div class="pcoded-content">

						<!-- include Page-header start Cabe�alho da pagina -->
						<jsp:include page="page-header.jsp"></jsp:include>
						<!-- Page-header end  Cabe�alho da pagina -->

						<div class="pcoded-inner-content">
							<!-- Main-body start -->
							<div class="main-body">
								<div class="page-wrapper">
									<!-- Page-body start -->
									<div class="page-body">

										<h3>Cadastro Usuario</h3>

										<div class="row">
											<div class="col-sm-12">
												<!-- Basic Form Inputs card start -->
												<div class="card">

													<div class="card-block">
														<h4 class="sub-title">Cadastro de Usu�rio</h4>



											<form class="form-material"  action="<%= request.getContextPath()%>/ServletUsuarioController" method="post" id="formUser">
													
													<input type="hidden" name="acao" id="acao" value="">
													
													<div class="form-group form-default">
													<input type="text" name="id" id="id" class="form-control" readonly="readonly" value="${modolLogin.id }"> 
													<span class="form-bar"></span> <label class="float-label">Id</label>
															</div>
															<div class="form-group form-default form-static-label">
																<input type="text" name="nome" id="nome" 
																	class="form-control" required="required" value="${modolLogin.nome }">
																<span class="form-bar"></span> <label
																	class="float-label">Nome</label>
															</div>
															
															<div class="form-group form-default form-static-label">
																<input type="email" name="email" id="email" autocomplete="off" 
																	class="form-control" required="required" value="${modolLogin.email}"> <span
																	class="form-bar"></span> 
																	<label class="float-label">E-mail</label>
															</div>
															<div class="form-group form-default form-static-label">
																<input type="text" name="login" id="login" 
																	class="form-control" required="required" value="${modolLogin.login }">
																<span class="form-bar"></span> 
																<label class="float-label">Login</label>
															</div>
															
															
															
															<div class="form-group form-default form-static-label">
																<input type="password" name="senha" id="senha"  
																	class="form-control" required="required" autocomplete="off" value="${modolLogin.senha }"> <span
																	class="form-bar"></span> <label class="float-label">Senha</label>
															</div>
								
								<!-- colocando o type button ele nao envia o formulario -->							
	        <button type="button" class="btn btn-primary waves-effect waves-light"  onclick="limparform();">Novo</button>
			<button class="btn btn-success waves-effect waves-light">Salvar</button>
            <button  type="button" class="btn btn-info waves-effect waves-light" onclick="criarDeleteComAjax();">Excluir</button>
            											</form>
            											<span id="msg">${msg}</span>
													</div>
													<!-- Page-body end -->
												</div>
												<div id="styleSelector"></div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>


					<!-- include do javascript Jquery -->
					<jsp:include page="javascriptfile.jsp"></jsp:include>
					
<!-- 					xhr -> detalhes do erro,status -> do erro ,errorThorwn -> excessao do erro  -->
					<script type="text/javascript">
					
					
					function criarDeleteComAjax(){
						
						if(confirm('Deseja Realmente excluir os dados?')){
							
						var urlAction = document.getElementById("formUser").action;
						var idUser = document.getElementById("id").value;
						
						$.ajax({
						
							method:"get",
						    url:urlAction,
						    data: "id=" + idUser + "&acao=deletarajax",
						    success:function(response){
						    	
						    	limparform();
						    	document.getElementById('msg').textContent = response;
						    }
							
						}).fail(function(xhr,status,errorThorwn){
						alert('Erro ao deletar usuario por id:'+ xhr.responseText);
						});
						
					}
					}
					
					
					
					function criarDelete(){
						
						if(confirm('Deseja Realmente excluir os dados?')){
						
						document.getElementById("formUser").method = 'get';
						document.getElementById("acao").value = 'deletar';
						document.getElementById("formUser").submit();
					
						}
						}
					
					
					
					function limparform(){
						var elementos = document.getElementById("formUser").elements;
						for(p = 0;p < elementos.length; p++){
						elementos[p].value = '';
						}
					}
						
					
					
					</script>
</body>

</html>
