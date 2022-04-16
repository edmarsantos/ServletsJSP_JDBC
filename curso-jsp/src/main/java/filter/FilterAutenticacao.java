package filter;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import connection.SingleConnectionBanco;
import dao.DaoVersionadorBanco;
import javax.servlet.annotation.WebFilter;


//filter qqer tela vai passar por essa classe filter ela a porta de entrada do projeto ou mapeamento ja existe um classe pronta chamada de Filter

@WebFilter(urlPatterns = {"/principal/*"})//tudo que vier da pasta principal vai ser interceptado
public class FilterAutenticacao implements Filter {

	
	private static Connection connection;
    
	public FilterAutenticacao() {
    
    }

	//Destroy encerrar os processos quando o servidor e parado
    public void destroy() {
    	
    	try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
    	
    }

    
    //intercepta as requisições e a resposta do sistema todas as execuções passa por aqui exemplo:autenticação, commit e rolback,redirecionamento de pagina
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) 
			throws IOException, ServletException {
		
		try {
		
		//pegar os request enviados para trabalha-los
		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession();
		
		//capturar o usuario logado		
		String usuarioLogado = (String) session.getAttribute("usuario");
		
		String urlParaAutenticar = req.getServletPath();//url sendo acessada
		
		//valida se o usuario as logado
		if(usuarioLogado == null  &&
				!urlParaAutenticar.equalsIgnoreCase("/principal/ServletLogin")) {
			//!urlParaAutenticar.equalsIgnoreCase("ServletLogin") regra caso tente acessar algum link sem esta logado
			//redireciona para a tela de login
			RequestDispatcher redireciona = request.getRequestDispatcher("/index.jsp?url="+ urlParaAutenticar);
			request.setAttribute("msg", "Por Favor Realize o login");
			//comando de redirecionamento
			redireciona.forward(request, response);
			return; //para a execução e redireciona paara o login
		}else {
			   //se estiver logado realiza todo o procedimento
		       chain.doFilter(request, response);
			   }
		
		connection.commit();		
		
		}catch (Exception e) {
		
		e.printStackTrace();
		
		RequestDispatcher redirecionar = request.getRequestDispatcher("erro.jsp");
		request.setAttribute("msg",e.getMessage());
		redirecionar.forward(request,response);
		
		try {
			connection.rollback();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			
			RequestDispatcher redirecionar2 = request.getRequestDispatcher("erro.jsp");
			request.setAttribute("msg",e1.getMessage());
			redirecionar2.forward(request,response);
		}
		
		}	
		
		}


	
	//inicia os processos ou recursos quando sobe o servidor
	public void init(FilterConfig fConfig) throws ServletException {
	
	connection = SingleConnectionBanco.getConnection();
	
	DaoVersionadorBanco daoVersionadorBanco = new DaoVersionadorBanco();
	
	String caminhoPastaSql = fConfig.getServletContext().getRealPath("versionadobancosql") + File.separator;
	
	File[] fileSql = new File(caminhoPastaSql).listFiles();
	
	try {
		
		for(File file : fileSql) {
			
			boolean arquivoJaRodado = daoVersionadorBanco.arquivoSqlRodado(file.getName());
		
			if(!arquivoJaRodado) {
				
				FileInputStream entradaArquivo = new FileInputStream(file);
				
				Scanner lerArquivo = new Scanner(entradaArquivo,"UTF-8");
				
                StringBuilder sql = new StringBuilder();
                
                while(lerArquivo.hasNext()) {
                	
                	sql.append(lerArquivo.nextLine());
                    sql.append("\n");
                }
				
                connection.prepareStatement(sql.toString()).execute();
                daoVersionadorBanco.gravarArquivoSqlRodado(file.getName());
                connection.commit();
                lerArquivo.close();
			}
						
		}
		
	}catch (Exception e){
		try {
			connection.rollback();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		e.printStackTrace();
	}
	
	
	
	}

}
