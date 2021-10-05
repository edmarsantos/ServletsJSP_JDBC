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

			<!-- include da barra de navegação -->
			<jsp:include page="navbar.jsp"></jsp:include>


			<div class="pcoded-main-container">
				<div class="pcoded-wrapper">

					<!-- include do menu -->
					<jsp:include page="navbarmainmenu.jsp"></jsp:include>

					<div class="pcoded-content">

						<!-- include Page-header start Cabeçalho da pagina -->
						<jsp:include page="page-header.jsp"></jsp:include>
						<!-- Page-header end  Cabeçalho da pagina -->

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
														<h4 class="sub-title">Cadastro de Usuário</h4>



											<form class="form-material"  action="<%= request.getContextPath()%>/ServletUsuarioController" method="post">
													<div class="form-group form-default">
													<input type="text" name="id" id="id" class="form-control" readonly="readonly"> 
													<span class="form-bar"></span> <label class="float-label">Id</label>
															</div>
															<div class="form-group form-default">
																<input type="text" name="nome" id="nome"
																	class="form-control" required="required" value="Nome">
																<span class="form-bar"></span> <label
																	class="float-label">Nome</label>
															</div>
															
															<div class="form-group form-default">
																<input type="email" name="email" id="email" autocomplete="off"
																	class="form-control" required="required"> <span
																	class="form-bar"></span> <label class="float-label">E-mail</label>
															</div>
															<div class="form-group form-default">
																<input type="text" name="login" id="login"
																	class="form-control" required="required" value="Nome">
																<span class="form-bar"></span> <label
																	class="float-label">Login</label>
															</div>
															
															
															
															<div class="form-group form-default">
																<input type="password" name="senha" id="senha" autocomplete="off"
																	class="form-control" required="required"> <span
																	class="form-bar"></span> <label class="float-label">Senha</label>
															</div>
															
	        <button class="btn btn-primary waves-effect waves-light">Primary Button</button>
			<button class="btn btn-success waves-effect waves-light">Success Button</button>
            <button class="btn btn-info waves-effect waves-light">Info Button</button>
            <button class="btn btn-warning waves-effect waves-light">Warning Button</button>
            <button class="btn btn-danger waves-effect waves-light">Danger Button</button>
            <button class="btn btn-inverse waves-effect waves-light">Inverse Button</button>
            <button class="btn btn-disabled disabled waves-effect waves-light">Disabled Button</button>
														</form>
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
</body>

</html>
