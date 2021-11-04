package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connection.SingleConnectionBanco;
import model.ModelLogin;

public class DAOUsuarioRepository {

	private Connection connection;
	
	public DAOUsuarioRepository() {

		connection = SingleConnectionBanco.getConnection();
	
	}//esse construtor foi gerado com o crtl + espaço


	// void nao retornar nada e qdo coloca ModelLogin ele retorna par a ModelLogin
	public ModelLogin gravarUsuario(ModelLogin objeto, Long userLogado) throws Exception  {
		
		if(objeto.isNovo()) {
		
		String sql = "INSERT INTO public.model_login(login, senha, nome, email,usuario_id,perfil,sexo)VALUES (?, ?, ?, ?,?,?,?);";
				
		PreparedStatement preparedSql = connection.prepareStatement(sql);
				
				preparedSql.setString(1, objeto.getLogin());
				preparedSql.setString(2, objeto.getSenha());
				preparedSql.setString(3, objeto.getNome());
				preparedSql.setString(4, objeto.getEmail());
				preparedSql.setLong(5, userLogado);
				preparedSql.setString(6, objeto.getPerfil());
				preparedSql.setString(7, objeto.getSexo());
				
				preparedSql.execute();
				connection.commit();
		}else {
			String sql = "UPDATE public.model_login SET login=?, senha=?, nome=?, email=? , perfil=?, sexo =? WHERE id =" + objeto.getId() +" ;";

			PreparedStatement preparedSql = connection.prepareStatement(sql);
			
			preparedSql.setString(1, objeto.getLogin());
			preparedSql.setString(2, objeto.getSenha());
			preparedSql.setString(3, objeto.getNome());
			preparedSql.setString(4, objeto.getEmail());
			preparedSql.setString(5, objeto.getPerfil());
			preparedSql.setString(6, objeto.getSexo());
			
			preparedSql.executeUpdate();
			connection.commit();
		}
				
				
		 return this.consultarUsuario(objeto.getLogin(), userLogado);
		
	}
	
public List<ModelLogin> consultarUsuarioList(Long userLogado) throws SQLException {
		
		List<ModelLogin> retorno = new ArrayList<ModelLogin>();
		String Sql = "select * from model_login where useradmin is false and usuario_id = "+ userLogado ;
		PreparedStatement preparedStatement = connection.prepareStatement(Sql);
		
		ResultSet resultado = preparedStatement.executeQuery();
		
		while (resultado.next()) {
			
			ModelLogin modelLogin = new ModelLogin();
			
			modelLogin.setEmail(resultado.getString("email"));
			modelLogin.setId(resultado.getLong("id"));
			modelLogin.setLogin(resultado.getString("login"));
			modelLogin.setNome(resultado.getString("nome"));
			modelLogin.setPerfil(resultado.getString("perfil"));
			modelLogin.setSexo(resultado.getString("sexo"));
			
			retorno.add(modelLogin);
			
		}
		
		
		return retorno;
	}
	
	
	public List<ModelLogin> consultarUsuarioList(String nome,Long userLogado) throws SQLException {
		
		List<ModelLogin> retorno = new ArrayList<ModelLogin>();
		String Sql = "select * from model_login where upper(nome) like upper(?) and useradmin is false AND USUARIO_ID = ?" ;
		PreparedStatement preparedStatement = connection.prepareStatement(Sql);
		preparedStatement.setString(1, "%"+nome +"%");
		preparedStatement.setLong(2, userLogado );
		
		ResultSet resultado = preparedStatement.executeQuery();
		
		while (resultado.next()) {
			
			ModelLogin modelLogin = new ModelLogin();
			
			modelLogin.setEmail(resultado.getString("email"));
			modelLogin.setId(resultado.getLong("id"));
			modelLogin.setLogin(resultado.getString("login"));
			modelLogin.setNome(resultado.getString("nome"));
			modelLogin.setPerfil(resultado.getString("perfil"));
			modelLogin.setSexo(resultado.getString("sexo"));
			
			retorno.add(modelLogin);
			
		}
		
		
		return retorno;
	}
	
	
	
public ModelLogin consultarUsuarioLogado(String login) throws Exception {
		
		ModelLogin modelLogin = new ModelLogin();
		
		String sql = "select * from model_login where upper(login) = upper('"+ login +"')";
	
		PreparedStatement statement = connection.prepareStatement(sql);
		
		ResultSet resultado = statement.executeQuery();
				
		while(resultado.next()) {
		
			modelLogin.setId(resultado.getLong("id"));
			modelLogin.setEmail(resultado.getString("email"));
			modelLogin.setLogin(resultado.getString("login"));
			modelLogin.setSenha(resultado.getString("senha"));
			modelLogin.setNome(resultado.getString("nome"));
			modelLogin.setUseradmin(resultado.getBoolean("useradmin"));
			modelLogin.setPerfil(resultado.getString("perfil"));
			modelLogin.setSexo(resultado.getString("sexo"));
			
		}
		

		return modelLogin;
		
	}
	

public ModelLogin consultarUsuarioID(String id,Long userLogado) throws Exception {
	
	ModelLogin modelLogin = new ModelLogin();
	
	String sql = "select * from model_login where id = ? and useradmin is false and usuario_id =?";

	PreparedStatement statement = connection.prepareStatement(sql);
	statement.setLong(1, Long.parseLong(id));
	statement.setLong(2, userLogado);
	
	ResultSet resultado = statement.executeQuery();
	
		while(resultado.next()) {
	
		modelLogin.setId(resultado.getLong("id"));
		modelLogin.setEmail(resultado.getString("email"));
		modelLogin.setLogin(resultado.getString("login"));
		modelLogin.setSenha(resultado.getString("senha"));
		modelLogin.setNome(resultado.getString("nome"));
		modelLogin.setPerfil(resultado.getString("perfil"));
		modelLogin.setSexo(resultado.getString("sexo"));
	}
	
	return modelLogin;
	
}



public ModelLogin consultarUsuario(String login, Long userLogado) throws Exception {
		
		ModelLogin modelLogin = new ModelLogin();
		
		
		String sql = "select * from model_login where upper(login) = upper('"+ login +"') and useradmin is false  and usuario_id = " + userLogado;
		PreparedStatement statement = connection.prepareStatement(sql);
		
		ResultSet resultado = statement.executeQuery();
		
		
		while(resultado.next()) {
		
			modelLogin.setId(resultado.getLong("id"));
			modelLogin.setEmail(resultado.getString("email"));
			modelLogin.setNome(resultado.getString("nome"));
			modelLogin.setLogin(resultado.getString("login"));
			modelLogin.setSenha(resultado.getString("senha"));
			modelLogin.setPerfil(resultado.getString("perfil"));
			modelLogin.setSexo(resultado.getString("sexo"));
			
		}
		
		return modelLogin;
		
	}
	
	
public ModelLogin consultarUsuario(String login) throws Exception {
	
	ModelLogin modelLogin = new ModelLogin();
	
	
	String sql = "select * from model_login where upper(login) = upper('"+ login +"') and useradmin is false " ;
	PreparedStatement statement = connection.prepareStatement(sql);
	
	ResultSet resultado = statement.executeQuery();
	
	
	while(resultado.next()) {
	
		modelLogin.setId(resultado.getLong("id"));
		modelLogin.setEmail(resultado.getString("email"));
		modelLogin.setNome(resultado.getString("nome"));
		modelLogin.setLogin(resultado.getString("login"));
		modelLogin.setSenha(resultado.getString("senha"));
    	modelLogin.setUseradmin(resultado.getBoolean("useradmin"));
	    modelLogin.setPerfil(resultado.getString("perfil"));
	    modelLogin.setSexo(resultado.getString("sexo"));
	
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
		String sql = "delete from model_login where id= ? and useradmin is false;" ;
	
		PreparedStatement preparedSql = connection.prepareStatement(sql);
				preparedSql.setLong(1, Long.parseLong(idUser));
		preparedSql.executeUpdate();
		connection.commit();
	
	}
	
	
}
