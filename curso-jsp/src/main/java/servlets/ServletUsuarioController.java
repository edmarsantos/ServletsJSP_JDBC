package servlets;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import dao.DAOLoginRepository;
import dao.DAOUsuarioRepository;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ModelLogin;

@WebServlet("/ServletUsuarioController")
public class ServletUsuarioController extends HttpServlet {
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
			request.setAttribute("msg", "Excluido com sucesso!");
			
			request.getRequestDispatcher("principal/usuario.jsp").forward(request, response);
			
			//regra delete com ajax
		}else if(acao !=null && !acao.isEmpty() && acao.equalsIgnoreCase("deletarajax")) {
				String idUser = request.getParameter("id");
				daoUsuarioRepository.deletarUser(idUser);
				//request.setAttribute("msg", "Excluido com sucesso!"); com a ajax nao pode usar o request
				response.getWriter().write("Excluido com sucesso!");
		}else if(acao !=null && !acao.isEmpty() && acao.equalsIgnoreCase("buscarUserAjax")){ 
				
			String  nomeBusca = request.getParameter("nomeBusca");
			
			
			
			  List<ModelLogin> dadosJsonUser =
			  daoUsuarioRepository.consultarUsuarioList(nomeBusca);
			  
			  //ObjectMapper vem do repositorio maven Jackson databind instalado no arquivo	pom.xml 
			  ObjectMapper mapper =new ObjectMapper(); String json =
			  mapper.writeValueAsString(dadosJsonUser); response.getWriter().write(json);
			 	
			}else {
				request.getRequestDispatcher("principal/usuario.jsp").forward(request, response);
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
			
			String msg ="Operação realizada com Sucesso!";
		
		String id = request.getParameter("id");
		String nome = request.getParameter("nome");
		String email = request.getParameter("email");
		String login = request.getParameter("login");
		String senha = request.getParameter("senha");
		
		ModelLogin modelLogin = new ModelLogin();
		//id como if operador ternario
		modelLogin.setId(id !=null && !id.isEmpty() ? Long.parseLong(id) : null);
		modelLogin.setNome(nome);
		modelLogin.setEmail(email);
		modelLogin.setLogin(login);
		modelLogin.setSenha(senha);
		
		
		if(daoUsuarioRepository.validarLogin(modelLogin.getLogin()) && modelLogin.getId() == null) {
			msg = "Já existe usuario com esse login!";
		}else {
		
            if(modelLogin.isNovo()) {
            	msg = "Gravado com Sucesso!";
            }else {
            	msg = "Atualizado com Sucesso!";
            }
			
			modelLogin =  daoUsuarioRepository.gravarUsuario(modelLogin);	
		}
		
		
 
		
		request.setAttribute("msg", msg);
		request.setAttribute("modolLogin", modelLogin);
		request.getRequestDispatcher("principal/usuario.jsp").forward(request, response);
		
		}catch (Exception e) {
			
			e.printStackTrace();
			RequestDispatcher redirecionar = request.getRequestDispatcher("erro.jsp");
			request.setAttribute("msg",e.getMessage());
			redirecionar.forward(request,response);
			
		}
		
	}

}
