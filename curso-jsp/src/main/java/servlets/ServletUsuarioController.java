package servlets;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.compress.utils.IOUtils;
import org.apache.tomcat.util.codec.binary.Base64;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.fasterxml.jackson.databind.ObjectMapper;

import beandto.BeanDtoGraficoSalarioUser;
import dao.DAOUsuarioRepository;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Part;
import model.ModelLogin;
import util.ReportUtil;

@MultipartConfig    //essa nota??o muda de acordo com a versao do apache
@WebServlet(urlPatterns ={"/ServletUsuarioController"})
public class ServletUsuarioController extends ServletGenericUtil {
	private static final long serialVersionUID = 1L;

	private DAOUsuarioRepository daoUsuarioRepository = new DAOUsuarioRepository();
	
    public ServletUsuarioController() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
		String acao = request.getParameter("acao");
		//regra delete normal sem ajax
		if(acao !=null && !acao.isEmpty() && acao.equalsIgnoreCase("deletar")) {
			String idUser = request.getParameter("id");
			daoUsuarioRepository.deletarUser(idUser);
			
			//carregar a list de usuarios
			List<ModelLogin> modelLogins = daoUsuarioRepository.consultarUsuarioList(super.getUserLogado(request));
			request.setAttribute("modelLogins", modelLogins);
						
			request.setAttribute("msg", "Excluido com sucesso!");
			request.setAttribute("totalPagina", daoUsuarioRepository.totalPagina(this.getUserLogado(request)));
			request.getRequestDispatcher("principal/usuario.jsp").forward(request, response);
			
			//regra delete com ajax
		}else if(acao !=null && !acao.isEmpty() && acao.equalsIgnoreCase("deletarajax")) {
				String idUser = request.getParameter("id");
				daoUsuarioRepository.deletarUser(idUser);
				//request.setAttribute("msg", "Excluido com sucesso!"); com a ajax nao pode usar o request
				response.getWriter().write("Excluido com sucesso!");
		}else if(acao !=null && !acao.isEmpty() && acao.equalsIgnoreCase("buscarUserAjax")){ 
				
			String  nomeBusca = request.getParameter("nomeBusca");
			
			List<ModelLogin> dadosJsonUser = daoUsuarioRepository.consultarUsuarioList(nomeBusca,super.getUserLogado(request));
			  
			  //ObjectMapper vem do repositorio maven Jackson databind instalado no arquivo	pom.xml 
			  ObjectMapper mapper =new ObjectMapper();
			  String json = mapper.writeValueAsString(dadosJsonUser); 
			  
			  //addheader serve para nao mistura as enviando a informa??o de forma separado
			  response.addHeader("totalPagina", "" + daoUsuarioRepository.consultarUsuarioListTotalPaginaPaginacao(nomeBusca, super.getUserLogado(request)));
			  response.getWriter().write(json);
			 	
		}		else if(acao !=null && !acao.isEmpty() && acao.equalsIgnoreCase("buscarUserAjaxPage")){ 
			
			String  nomeBusca = request.getParameter("nomeBusca");
			String  pagina = request.getParameter("pagina");
			
			List<ModelLogin> dadosJsonUser = daoUsuarioRepository.consultarUsuarioListOffset(nomeBusca,super.getUserLogado(request), pagina);
			  
			  //ObjectMapper vem do repositorio maven Jackson databind instalado no arquivo	pom.xml 
			  ObjectMapper mapper =new ObjectMapper();
			  String json = mapper.writeValueAsString(dadosJsonUser); 
			  
			  //addheader serve para nao mistura as enviando a informa??o de forma separado
			  response.addHeader("totalPagina", "" + daoUsuarioRepository.consultarUsuarioListTotalPaginaPaginacao(nomeBusca, super.getUserLogado(request)));
			  response.getWriter().write(json);
			 	
		}
		
		else if(acao !=null && !acao.isEmpty() && acao.equalsIgnoreCase("buscarEditar")){  
			  
			String id  = request.getParameter("id"); 
			
			ModelLogin modelLogin = daoUsuarioRepository.consultarUsuarioID(id, super.getUserLogado(request));
			
			//carregar a list de usuarios
			List<ModelLogin> modelLogins = daoUsuarioRepository.consultarUsuarioList(super.getUserLogado(request));
			request.setAttribute("modelLogins", modelLogins);
			
			request.setAttribute("msg", "Usu?rio em edi??o");
			request.setAttribute("modolLogin", modelLogin);
			request.setAttribute("totalPagina", daoUsuarioRepository.totalPagina(this.getUserLogado(request)));
			request.getRequestDispatcher("principal/usuario.jsp").forward(request, response);
			
			/*
			 * request.setAttribute("msg", "Usu?rio em Edi??o");
			 * request.setAttribute("modolLogin", modelLogin);
			 * request.setAttribute("totalPagina",
			 * daoUsuarioRepository.totalPagina(this.getUserLogado(request)));
			 * request.getRequestDispatcher("principal/usuario.jsp").forward(request,
			 * response);
			 */
			  
		}else if(acao !=null && !acao.isEmpty() && acao.equalsIgnoreCase("listarUser")){
			
			List<ModelLogin> modelLogins = daoUsuarioRepository.consultarUsuarioList(super.getUserLogado(request));
			
			request.setAttribute("msg", "Usu?rio Carregados");
			request.setAttribute("modelLogins", modelLogins);
			request.setAttribute("totalPagina", daoUsuarioRepository.totalPagina(this.getUserLogado(request)));
			request.getRequestDispatcher("principal/usuario.jsp").forward(request, response);
		
		}else if(acao !=null && !acao.isEmpty() && acao.equalsIgnoreCase("downloadFoto")){
			
	 String idUser = request.getParameter("id");
			 
			 ModelLogin modelLogin =  daoUsuarioRepository.consultarUsuarioID(idUser, super.getUserLogado(request));
			 if (modelLogin.getFotouser() != null && !modelLogin.getFotouser().isEmpty()) {
				 
				 response.setHeader("Content-Disposition", "attachment;filename=arquivo." + modelLogin.getExtensaofotouser()); //Content-Disposition srve para o navegador indentificar que e um dowload 
				 response.getOutputStream().write(new Base64().decodeBase64(modelLogin.getFotouser().split("\\,")[1]));//split foi utilizado para remover "data:image/exten??o;base64,..."  o split vai pegar a partir da ","
				 
			 }
		
			
		
		}else if(acao !=null && !acao.isEmpty() && acao.equalsIgnoreCase("paginar")){
		Integer offset = Integer.parseInt(request.getParameter("pagina"));
		
		List<ModelLogin> modelLogins = daoUsuarioRepository.consultarUsuarioListPaginada(this.getUserLogado(request), offset);
		request.setAttribute("modelLogins", modelLogins);
		request.setAttribute("totalPagina", daoUsuarioRepository.totalPagina(this.getUserLogado(request)));
			request.getRequestDispatcher("principal/usuario.jsp").forward(request, response);
	
		}else if(acao !=null && !acao.isEmpty() && acao.equalsIgnoreCase("ImprimiRelatorioUser")){
		
			String dataInicial = request.getParameter("dataInicial");
			String dataFinal = request.getParameter("dataFinal");			
			
	
			if(dataInicial == null || dataInicial.isEmpty()
					&& dataFinal == null || dataFinal.isEmpty()) {
				
				request.setAttribute("listaUser", daoUsuarioRepository.consultarUsuarioListRel(super.getUserLogado(request)));
				
			}else {
				request.setAttribute("listaUser", daoUsuarioRepository.
						consultaUsuarioListRel(super.getUserLogado(request),dataInicial,dataFinal));
			}
			
			
			request.setAttribute("dataInicial", dataInicial);
			request.setAttribute("dataFinal", dataFinal);
			request.getRequestDispatcher("principal/reluser.jsp").forward(request, response);
			
			
		}else if(acao !=null && !acao.isEmpty() && acao.equalsIgnoreCase("ImprimiRelatorioPDF")){
			

			String dataInicial = request.getParameter("dataInicial");
			String dataFinal = request.getParameter("dataFinal");			
			
	
			List<ModelLogin> modelLogins = null;
			
			
			if(dataInicial == null || dataInicial.isEmpty()
					&& dataFinal == null || dataFinal.isEmpty()) {
			
				modelLogins = daoUsuarioRepository.consultarUsuarioListRel(super.getUserLogado(request));
				
			}else {
			
				modelLogins = daoUsuarioRepository.
						consultaUsuarioListRel(super.getUserLogado(request),dataInicial,dataFinal);
			}
			
			HashMap<String, Object> params = new HashMap<String, Object>();
			params.put("PARAM_SUB_REPORT", request.getServletContext().getRealPath("relatorio")  + File.separator);
			
			byte [] relatorio = new ReportUtil().gerarRelatorioPDF(modelLogins, "rel-user-jsp", params ,request.getServletContext());
			
			response.setHeader("Content-Disposition", "attachment;filename=arquivo.pdf") ; //Content-Disposition srve para o navegador indentificar que e um dowload 
			 response.getOutputStream().write(relatorio);
			
		}else if(acao !=null && !acao.isEmpty() && acao.equalsIgnoreCase("graficoSalario")){
			
			String dataInicial = request.getParameter("dataInicial");
			String dataFinal = request.getParameter("dataFinal");			
			
	
			//List<ModelLogin> modelLogins = null;
			
			
			if(dataInicial == null || dataInicial.isEmpty()
					&& dataFinal == null || dataFinal.isEmpty()) {
			
				BeanDtoGraficoSalarioUser beanDtoGraficoSalarioUser =	daoUsuarioRepository.montarGraficoMediaSalario(super.getUserLogado(request));
				
				ObjectMapper mapper = new ObjectMapper();
				String json = mapper.writeValueAsString(beanDtoGraficoSalarioUser);
				
				response.getWriter().write(json);
				
			}else {
			
	BeanDtoGraficoSalarioUser beanDtoGraficoSalarioUser =	daoUsuarioRepository.
			montarGraficoMediaSalario(super.getUserLogado(request),dataInicial,dataFinal);
				
				ObjectMapper mapper = new ObjectMapper();
				String json = mapper.writeValueAsString(beanDtoGraficoSalarioUser);
				
				response.getWriter().write(json);
				
			}
			
			
		}else {
			
			 List<ModelLogin> modelLogins = daoUsuarioRepository.consultarUsuarioList(super.getUserLogado(request));
		     request.setAttribute("modelLogins", modelLogins);
		     request.setAttribute("totalPagina", daoUsuarioRepository.totalPagina(this.getUserLogado(request)));
			 request.getRequestDispatcher("principal/usuario.jsp").forward(request, response);
			
			
			/*
			 * //carregar a list de usuarios List<ModelLogin> modelLogins =
			 * daoUsuarioRepository.consultarUsuarioList(super.getUserLogado(request));
			 * request.setAttribute("modelLogins", modelLogins);
			 * request.setAttribute("totalPagina",
			 * daoUsuarioRepository.totalPagina(this.getUserLogado(request)));
			 * request.getRequestDispatcher("principal/usuario.jsp").forward(request,
			 * response);
			 */
			}
		
		
		} catch (Exception e) {
			
			e.printStackTrace();
			RequestDispatcher redirecionar = request.getRequestDispatcher("erro.jsp");
			request.setAttribute("msg",e.getMessage());
			redirecionar.forward(request,response);
			
		}
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		try {
			
			String msg ="Opera??o realizada com Sucesso!";
		
		String id = request.getParameter("id");
		String nome = request.getParameter("nome");
		String email = request.getParameter("email");
		String login = request.getParameter("login");
		String senha = request.getParameter("senha");
		String perfil = request.getParameter("perfil");
		String sexo = request.getParameter("sexo");
		
		String cep = request.getParameter("cep");
		String logradouro = request.getParameter("logradouro");
		String bairro = request.getParameter("bairro");
		String localidade = request.getParameter("localidade");
		String uf = request.getParameter("uf");
		String numero = request.getParameter("numero");
		String dataNascimento = request.getParameter("dataNascimento");
		String rendalMensal = request.getParameter("rendamensal").replace(".", "");
		
		rendalMensal = rendalMensal.replaceAll(",", ".");
		
		ModelLogin modelLogin = new ModelLogin();
		//id como if operador ternario
		modelLogin.setId(id !=null && !id.isEmpty() ? Long.parseLong(id) : null);
		modelLogin.setNome(nome);
		modelLogin.setEmail(email);
		modelLogin.setLogin(login);
		modelLogin.setSenha(senha);
		modelLogin.setPerfil(perfil);
		modelLogin.setSexo(sexo);
		
		modelLogin.setCep(cep);
		modelLogin.setLogradouro(logradouro);
		modelLogin.setBairro(bairro);
		modelLogin.setLocalidade(localidade);
		modelLogin.setUf(uf);
		modelLogin.setNumero(numero);
		modelLogin.setDataNascimento(Date.valueOf(new SimpleDateFormat("yyyy-mm-dd").format(new SimpleDateFormat("dd/mm/yyyy").parse(dataNascimento))));
		modelLogin.setRendamensal(Double.valueOf(rendalMensal));
		
if (request.getPart("fileFoto") != null) {
			
			Part part = request.getPart("fileFoto"); /*Pega foto da tela*/
			
			if (part.getSize() > 0) {
				byte[] foto = IOUtils.toByteArray(part.getInputStream()); /*Converte imagem para byte*/
				String imagemBase64 = "data:image/" + part.getContentType().split("\\/")[1] + ";base64," +  new Base64().encodeBase64String(foto);
				
				modelLogin.setFotouser(imagemBase64);
				modelLogin.setExtensaofotouser(part.getContentType().split("\\/")[1]);
			}
			
		}
		
		if(daoUsuarioRepository.validarLogin(modelLogin.getLogin()) && modelLogin.getId() == null) {
			msg = "J? existe usuario com esse login!";
		}else {
		
            if(modelLogin.isNovo()) {
            	msg = "Gravado com Sucesso!";
            }else {
            	msg = "Atualizado com Sucesso!";
            }
			
			modelLogin =  daoUsuarioRepository.gravarUsuario(modelLogin,super.getUserLogado(request));	
		}
		
		
		//carregar a list de usuarios
		List<ModelLogin> modelLogins = daoUsuarioRepository.consultarUsuarioList(super.getUserLogado(request));
		request.setAttribute("modelLogins", modelLogins);
		
		
		request.setAttribute("msg", msg);
		request.setAttribute("modolLogin", modelLogin);
		request.setAttribute("totalPagina", daoUsuarioRepository.totalPagina(this.getUserLogado(request)));
		request.getRequestDispatcher("principal/usuario.jsp").forward(request, response);
		
		}catch (Exception e) {
			
			e.printStackTrace();
			RequestDispatcher redirecionar = request.getRequestDispatcher("erro.jsp");
			request.setAttribute("msg",e.getMessage());
			redirecionar.forward(request,response);
			
		}
		
	}

}
