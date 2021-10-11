package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connection.SingleConnectionBanco;
import model.ModelLogin;

public class DAOUsuarioRepository {

	private Connection connection;
	
	public DAOUsuarioRepository() {

		connection = SingleConnectionBanco.getConnection();
	
	}//esse construtor foi gerado com o crtl + espaço


	// void nao retornar nada e qdo coloca ModelLogin ele retorna par a ModelLogin
	public ModelLogin gravarUsuario(ModelLogin objeto) throws Exception  {
		
		if(objeto.isNovo()) {
		
		String sql = "INSERT INTO public.model_login(login, senha, nome, email)VALUES (?, ?, ?, ?);";
				
		PreparedStatement preparedSql = connection.prepareStatement(sql);
				
				preparedSql.setString(1, objeto.getLogin());
				preparedSql.setString(2, objeto.getSenha());
				preparedSql.setString(3, objeto.getNome());
				preparedSql.setString(4, objeto.getEmail());
				
				preparedSql.execute();
				connection.commit();
		}else {
			String sql = "UPDATE public.model_login SET login=?, senha=?, nome=?, email=?  WHERE id =" + objeto.getId() +" ;";

			PreparedStatement preparedSql = connection.prepareStatement(sql);
			
			preparedSql.setString(1, objeto.getLogin());
			preparedSql.setString(2, objeto.getSenha());
			preparedSql.setString(3, objeto.getNome());
			preparedSql.setString(4, objeto.getEmail());
			
			preparedSql.executeUpdate();
			connection.commit();
		}
				
				
		 return this.consultarUsuario(objeto.getLogin());
		
	}
	
	public ModelLogin consultarUsuario(String login) throws Exception {
		
		ModelLogin modelLogin = new ModelLogin();
		
		
		String sql = "select * from model_login where upper(login) = upper('"+ login +"')";
		PreparedStatement statement = connection.prepareStatement(sql);
		
		ResultSet resultado = statement.executeQuery();
		
		
		while(resultado.next()) {
		
			modelLogin.setId(resultado.getLong("id"));
			modelLogin.setEmail(resultado.getString("email"));
			modelLogin.setNome(resultado.getString("nome"));
			modelLogin.setLogin(resultado.getString("login"));
			modelLogin.setSenha(resultado.getString("senha"));
		
		}
		
		return modelLogin;
		
	}
	
	public boolean validarLogin(String login) throws Exception{
		
		String sql = "select count(1) > 0 as existe from model_login where upper(login) = upper('"+ login + "')";
		
		PreparedStatement statement = connection.prepareStatement(sql);
		
		ResultSet resultado = statement.executeQuery();
		
		resultado.next(); //next server para entrar no resultado
		
		return resultado.getBoolean("existe");
		
		
	}
	
	public void deletarUser(String idUser) throws SQLException {
		String sql = "delete from model_login where id= ?;" ;
	
		PreparedStatement preparedSql = connection.prepareStatement(sql);
				preparedSql.setLong(1, Long.parseLong(idUser));
		preparedSql.executeUpdate();
		connection.commit();
	
	}
	
	
}
