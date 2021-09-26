package connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class SingleConnectionBanco {
	
	private static String banco ="jdbc:postgresql://localhost:5433/curso-jsp?autoReconnect=true";
	private static String password = "admin";
	private static String user = "postgres";
	private static Connection connection = null;
	
	

	static{
		conectar();
	}
	
	public SingleConnectionBanco() { //qunado tiver um estancia vai conectar
		conectar();
	}		
	
	
	
	private static void conectar() {
		try {
			//nao se fecha conexao e sim trasaçao 
			if (connection == null) {
				Class.forName("org.postgresql.Driver");//drive de conexao 
				connection =DriverManager.getConnection(banco,  user, password);
				connection.setAutoCommit(false);  //para nao efetuar alteração no banco sem nosso comando
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new RuntimeException("Erro ao conectar com o banco de dados");
		}
	}
	
	public static Connection getConnection() {
		return connection;
	}
}



