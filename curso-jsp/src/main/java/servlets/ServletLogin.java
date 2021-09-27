package servlets;

import java.io.IOException;

import dao.DAOLoginRepository;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ModelLogin;


//também sao controlers Servlets
//@WebServlet("/ServletLogin") /*Mapeamento de URL que vem da tela*/ anterior funiciona
@WebServlet(urlPatterns = {"/principal/ServletLogin","/ServletLogin"}) /*Mapeamento de URL que vem da tela*/

public class ServletLogin extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	private DAOLoginRepository daoLoginRepository = new DAOLoginRepository();
  
    public ServletLogin() {
    }


    /*Recebe os dados pela url em parametros*/
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	
	/*recebe os dados enviados por um formulario*/
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
			String login = request.getParameter("login");
			String senha = request.getParameter("senha");
			String url =  request.getParameter("url");
			
			if(login !=null && !login.isEmpty() && senha!=null && !senha.isEmpty() ) {
		

				ModelLogin modelLogin = new ModelLogin();
				modelLogin.setLogin(login);
				modelLogin.setSenha(senha);
								
				if(daoLoginRepository.ValidarAutenticacao(modelLogin) ) {
					
					request.getSession().setAttribute("usuario", modelLogin.getLogin());
					
					if(url == null || url.equals("null")) {
						
						url = "principal/principal.jsp";
						
					}
					
					
					RequestDispatcher redirecionar = request.getRequestDispatcher(url);
					redirecionar.forward(request,response);
					
				}else {
					RequestDispatcher redirecionar = request.getRequestDispatcher("/index.jsp");
					request.setAttribute("msg", "Informe o Login e Senha corretamente!");
					redirecionar.forward(request,response);
				}
				
		
			}else {
				RequestDispatcher redirecionar = request.getRequestDispatcher("index.jsp");
				request.setAttribute("msg", "Informe o Login e Senha corretamente!");
				redirecionar.forward(request,response);
			}
		
			
			
				
			
			
			
			/*
			if (daoLogin.validarLogin(login, senha)) {
				RequestDispatcher dispatcher = request.getRequestDispatcher("acessoliberado.jsp");
				dispatcher.forward(request, response);
			}else {
				RequestDispatcher dispatcher = request.getRequestDispatcher("acessonegado.jsp");
				dispatcher.forward(request, response);
			}}*/
			
			}catch (Exception e) {
				e.printStackTrace();
			}
		
		
		
	}

}