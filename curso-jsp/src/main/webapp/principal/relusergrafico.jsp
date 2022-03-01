<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">

<!-- obs.toda tela lembrar de inserir o jstl -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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
												<!-- Basic Form Inputs card start -->
												<div class="card">

													<div class="card-block">
														<h4 class="sub-title">Relatório de Usuário</h4>

														<form class="form-material"
															action="<%=request.getContextPath()%>/ServletUsuarioController"
															method="get" id="formUser">

															<input type="hidden" name="acao"
																id="acaoRelatorioImprimirTipo"
																value="ImprimiRelatorioUser">

															<div class="form-row align-items-center">

																<div class="col-sm-3 my-1">
																	<label class="sr-only" for="datainicial">Data
																		Inicial</label> <input value="${dataInicial}" type="text"
																		class="form-control" id="dataInicial"
																		name="dataInicial">
																</div>

																<div class="col-sm-3 my-1">
																	<label class="sr-only" for="dataFinal">Data
																		Final</label> <input value="${dataFinal}" type="text"
																		class="form-control" id="dataFinal" name="dataFinal">
																</div>


																<div CLASS="col-auto my-1">
																	<button type="button" onclick="gerarGrafico();"
																		class="btn btn-primary">Gerar Gráfico</button>

																</div>


															</div>



														</form>

														<div style="height: 500px; overflow: scroll;">

															<div>
																<canvas id="myChart"></canvas>
															</div>

														</div>

													</div>
												</div>
											</div>
										</div>

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


	<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
		
	<script type="text/javascript">
	
		
		function gerarGrafico() {
			
			const myChart = new Chart(
				    document.getElementById('myChart'),
				    {
						  type: 'line',
						  data: {
							  labels: [
								  'January',
								  'February',
								  'March',
								  'April',
								  'May',
								  'June',
								],
							  datasets: [{
							    label: 'Gráfico de Média Salarial por Tipo',
							    backgroundColor: 'rgb(255, 99, 132)',
							    borderColor: 'rgb(255, 99, 132)',
							    data: [0, 10, 5, 2, 20, 30, 60],
							  }]
							},
						  options: {}
						}
				  );	
		}

		$(function() {

			$("#dataInicial")
					.datepicker(
							{
								dateFormat : 'dd/mm/yy',
								dayNames : [ 'Domingo', 'Segunda', 'Terça',
										'Quarta', 'Quinta', 'Sexta', 'Sábado' ],
								dayNamesMin : [ 'D', 'S', 'T', 'Q', 'Q', 'S',
										'S', 'D' ],
								dayNamesShort : [ 'Dom', 'Seg', 'Ter', 'Qua',
										'Qui', 'Sex', 'Sáb', 'Dom' ],
								monthNames : [ 'Janeiro', 'Fevereiro', 'Março',
										'Abril', 'Maio', 'Junho', 'Julho',
										'Agosto', 'Setembro', 'Outubro',
										'Novembro', 'Dezembro' ],
								monthNamesShort : [ 'Jan', 'Fev', 'Mar', 'Abr',
										'Mai', 'Jun', 'Jul', 'Ago', 'Set',
										'Out', 'Nov', 'Dez' ],
								nextText : 'Próximo',
								prevText : 'Anterior'
							});
		});

		$(function() {

			$("#dataFinal")
					.datepicker(
							{
								dateFormat : 'dd/mm/yy',
								dayNames : [ 'Domingo', 'Segunda', 'Terça',
										'Quarta', 'Quinta', 'Sexta', 'Sábado' ],
								dayNamesMin : [ 'D', 'S', 'T', 'Q', 'Q', 'S',
										'S', 'D' ],
								dayNamesShort : [ 'Dom', 'Seg', 'Ter', 'Qua',
										'Qui', 'Sex', 'Sáb', 'Dom' ],
								monthNames : [ 'Janeiro', 'Fevereiro', 'Março',
										'Abril', 'Maio', 'Junho', 'Julho',
										'Agosto', 'Setembro', 'Outubro',
										'Novembro', 'Dezembro' ],
								monthNamesShort : [ 'Jan', 'Fev', 'Mar', 'Abr',
										'Mai', 'Jun', 'Jul', 'Ago', 'Set',
										'Out', 'Nov', 'Dez' ],
								nextText : 'Próximo',
								prevText : 'Anterior'
							});
		});
	</script>


</body>

</html>
