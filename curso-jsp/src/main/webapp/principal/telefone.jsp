<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>	
	
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


										<div class="row">
											<div class="col-sm-12">
												<div class="card">
													<div class="card-block">
														<h4 class="sub-title">Cad. Telefone</h4>
														
														<form class="form-material" action="<%= request.getContextPath()%>/ServletTelefone" 	method="post" id="formFone">
														
														<div class="form-group form-default form-static-label">
																<input type="text" name="id" id="id"
																	class="form-control" readonly="readonly"
																	value="${modelLogin.id}"> <span
																	class="form-bar"></span> <label class="float-label">Id User:</label>
															</div>
															
															<div class="form-group form-default form-static-label">
																<input readonly="readonly" type="text" name="nome" id="nome"
																	class="form-control" required="required"
																	value="${modelLogin.nome }"> <span
																	class="form-bar"></span> <label class="float-label">Nome</label>
															</div>
															
															<div class="form-group form-default form-static-label">
																<input  type="text" name="numero" id="numero" class="form-control" required="required"> 
																<span class="form-bar"></span> 
																<label class="float-label">Telefone</label>
															</div>
														
														<button class="btn btn-success waves-effect waves-light">Salvar</button>
															
														
														</form>
														

													</div>
												</div>
											</div>
											<!-- Page-body end -->
										</div>
										<span id="msg">${msg}</span>
										
										<div style="height: 300px; overflow: scroll;">
															<table class="table" id="tabelaresultadosView">
																<thead>
																	<tr>
																		<th scope="col">ID</th>
																		<th scope="col">Nome</th>
																		<th scope="col">Ver</th>
																	</tr>
																</thead>
																<tbody>
																	<c:forEach items='${modelTelefones}' var='f'>
																		<tr>
																			<td><c:out value="${f.id}"></c:out></td>
																			<td><c:out value="${f.numero}"></c:out></td>
																			<td><a class="btn btn-success"
																				href="<%= request.getContextPath() %>/ServletTelefone?acao=excluir&id=${f.id}&userpai=${modelLogin.id}">Excluir</a></td>
																		</tr>
																	</c:forEach>
																</tbody>
															</table>

														</div>
										
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>


			<!-- include do javascript Jquery -->
			<jsp:include page="javascriptfile.jsp"></jsp:include>
			
			<script type="text/javascript">
				
				
				$("#numero").keypress(function (event){
					return /\d/.test(String.fromCharCode(eventKeyCode));
				});
				
				</script>
</body>

</html>
