package servlets;

import java.io.Serializable;

import dao.DAOUsuarioRepository;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import model.ModelLogin;


public class ServletGenericUtil extends HttpServlet implements Serializable {

	private static final long serialVersionUID = 1L;

    
	private DAOUsuarioRepository daoUsuarioRepository = new DAOUsuarioRepository();
	
	  //retorna o Id do usuario logado
    public long getUserLogado(HttpServletRequest request) throws Exception{
    	
    	HttpSession session = request.getSession();
      	String usuarioLogado = (String) session.getAttribute("usuario");
    
        return daoUsuarioRepository.consultarUsuarioLogado(usuarioLogado).getId();
    }
    
    
  //retorno o objeto inteiro do usuario logado 
public ModelLogin getUserLogadoObjt(HttpServletRequest request) throws Exception {
		
		HttpSession session = request.getSession();
		String usuarioLogado = (String) session.getAttribute("usuario");
		
		return daoUsuarioRepository.consultarUsuarioLogado(usuarioLogado);
		
	}
    
    
	/*
	 * public ModelLogin getUserLogadoObjt(HttpServletRequest request) throws
	 * Exception{
	 * 
	 * HttpSession session = request.getSession(); String usuarioLogado = (String)
	 * session.getAttribute("usuario");
	 * 
	 * return daoUsuarioRepository.consultarUsuarioLogado(usuarioLogado); }
	 */
	
}
