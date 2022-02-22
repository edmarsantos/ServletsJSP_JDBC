package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import connection.SingleConnectionBanco;
import model.ModelLogin;
import model.ModelTelefone;

public class DAOUsuarioRepository {

	private Connection connection;
	
		
	public DAOUsuarioRepository() {

		connection = SingleConnectionBanco.getConnection();
	
	}//esse construtor foi gerado com o crtl + espaço


	// void nao retornar nada e qdo coloca ModelLogin ele retorna par a ModelLogin
	public ModelLogin gravarUsuario(ModelLogin objeto, Long userLogado) throws Exception  {
		
		if(objeto.isNovo()) {
		
		String sql = "INSERT INTO public.model_login(login, senha, nome, email,usuario_id,perfil,sexo,cep ,  logradouro ,  bairro ,  localidade ,  uf,  numero,datanascimento,rendalMensal)VALUES (?, ?, ?, ?,?,?,?,?,?,?,?,?,?,?,?);";
				
		PreparedStatement preparedSql = connection.prepareStatement(sql);
				
				preparedSql.setString(1, objeto.getLogin());
				preparedSql.setString(2, objeto.getSenha());
				preparedSql.setString(3, objeto.getNome());
				preparedSql.setString(4, objeto.getEmail());
				preparedSql.setLong(5, userLogado);
				preparedSql.setString(6, objeto.getPerfil());
				preparedSql.setString(7, objeto.getSexo());
				preparedSql.setString(8, objeto.getCep());
				preparedSql.setString(9, objeto.getLogradouro());
				preparedSql.setString(10, objeto.getBairro());
				preparedSql.setString(11, objeto.getLocalidade());
				preparedSql.setString(12, objeto.getUf());
				preparedSql.setString(13, objeto.getNumero());
				preparedSql.setDate(14,objeto.getDataNascimento());
				preparedSql.setDouble(15,objeto.getRendamensal());
				
				preparedSql.execute();
				connection.commit();
				
				if(objeto.getFotouser() !=null && !objeto.getFotouser().isEmpty()) {
					
					sql = " update model_login set fotouser = ?, extensaofotouser=? where login =? ";
					
					preparedSql = connection.prepareStatement(sql);
					
					preparedSql.setString(1, objeto.getFotouser());
					preparedSql.setString(2, objeto.getExtensaofotouser());
					preparedSql.setString(3, objeto.getLogin());
					
					preparedSql.execute();
					connection.commit();
				
				}
				
				
				
		}else {
			String sql = "UPDATE public.model_login SET login=?, senha=?, nome=?, email=? , perfil=?, sexo =? ,cep=? ,  logradouro=? ,  bairro=? ,  localidade=? ,  uf=?,  numero=?, datanascimento=?, rendamensal=? WHERE id =" + objeto.getId() +" ;";

			PreparedStatement preparedSql = connection.prepareStatement(sql);
			
			preparedSql.setString(1, objeto.getLogin());
			preparedSql.setString(2, objeto.getSenha());
			preparedSql.setString(3, objeto.getNome());
			preparedSql.setString(4, objeto.getEmail());
			preparedSql.setString(5, objeto.getPerfil());
			preparedSql.setString(6, objeto.getSexo());
			
			preparedSql.setString(7, objeto.getCep());
			preparedSql.setString(8, objeto.getLogradouro());
			preparedSql.setString(9, objeto.getBairro());
			preparedSql.setString(10, objeto.getLocalidade());
			preparedSql.setString(11, objeto.getUf());
			preparedSql.setString(12, objeto.getNumero());
			preparedSql.setDate(13,objeto.getDataNascimento());
			preparedSql.setDouble(14,objeto.getRendamensal());
			
			preparedSql.executeUpdate();
			connection.commit();
			
			if (objeto.getFotouser() != null && !objeto.getFotouser().isEmpty()) {
				
				sql = " update model_login set fotouser = ?, extensaofotouser=? where id =? ";
				
				preparedSql = connection.prepareStatement(sql);
				
				preparedSql.setString(1, objeto.getFotouser());
				preparedSql.setString(2, objeto.getExtensaofotouser());
				preparedSql.setLong(3, objeto.getId());
				
				preparedSql.execute();
				connection.commit();
			
			}
			
			
		}
				
				
		 return this.consultarUsuario(objeto.getLogin(), userLogado);
		
	}
	
public List<ModelLogin> consultarUsuarioListPaginada(Long userLogado,Integer offset ) throws SQLException {
		
		List<ModelLogin> retorno = new ArrayList<ModelLogin>();
		String Sql = "select * from model_login where useradmin is false and usuario_id = "+ userLogado + " order by nome offset "+ offset +"  limit 5 ";
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
	
public int totalPagina(Long userLogado) throws Exception{
	
	String sql = "select count(1) as total from model_login where usuario_id =" + userLogado;
	PreparedStatement statement = connection.prepareStatement(sql);
	ResultSet resultado = statement.executeQuery();
	
	resultado.next();//next para abrir o resultSet
	
	Double cadastros = resultado.getDouble("total");
	
	Double porpagina = 5.0;
	
	Double pagina = cadastros / porpagina;
	
	Double resto =pagina % 2;
	
	if (resto > 0) {
		pagina++;
	}
	
	return pagina.intValue();
	
}



public List<ModelLogin> consultarUsuarioList(Long userLogado) throws SQLException {
		
		List<ModelLogin> retorno = new ArrayList<ModelLogin>();
		String Sql = "select * from model_login where useradmin is false and usuario_id = "+ userLogado + " limit 5 ";
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
	

public List<ModelLogin> consultarUsuarioListRel(Long userLogado) throws Exception {
	
	List<ModelLogin> retorno = new ArrayList<ModelLogin>();
	String Sql = "select * from model_login where useradmin is false and usuario_id = "+ userLogado + "  ";
	PreparedStatement preparedStatement = connection.prepareStatement(Sql);
	
	ResultSet resultado = preparedStatement.executeQuery();
	
	while (resultado.next()) {
		
		ModelLogin modelLogin = new ModelLogin();
		
		modelLogin.setEmail(resultado.getString("email"));
		modelLogin.setId(resultado.getLong("id"));
		modelLogin.setLogin(resultado.getString("login"));
		modelLogin.setNome(resultado.getString("nome"));
		modelLogin.setPerfil(resultado.getString("perfil"));
		modelLogin.setDataNascimento(resultado.getDate("datanascimento"));
		modelLogin.setSexo(resultado.getString("sexo"));
		
		
		  modelLogin.setTelefones(this.listFone(modelLogin.getId()));
		 
		retorno.add(modelLogin);
		
	}
	
	
	return retorno;
}

public List<ModelLogin> consultaUsuarioListRel(Long userLogado, String dataInicial, String dataFinal) throws Exception {
	
	List<ModelLogin> retorno = new ArrayList<ModelLogin>();
	
	SimpleDateFormat formato = new SimpleDateFormat("yyyy-mm-dd"); 
		
	String sql = "select * from model_login where useradmin is false and usuario_id = " + userLogado + 
			     " and datanascimento between '"+ 
			     Date.valueOf(new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("dd/mm/yyyy").parse(dataInicial)))
			     +"' and '"+
			     Date.valueOf(new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("dd/mm/yyyy").parse(dataFinal)))
			     +"'";
	PreparedStatement statement = connection.prepareStatement(sql);
	
	ResultSet resultado = statement.executeQuery();
	
	while (resultado.next()) { /*percorrer as linhas de resultado do SQL*/
		
		ModelLogin modelLogin = new ModelLogin();
		
		modelLogin.setEmail(resultado.getString("email"));
		modelLogin.setId(resultado.getLong("id"));
		modelLogin.setLogin(resultado.getString("login"));
		modelLogin.setNome(resultado.getString("nome"));
		//modelLogin.setSenha(resultado.getString("senha"));
		modelLogin.setPerfil(resultado.getString("perfil"));
		modelLogin.setSexo(resultado.getString("sexo"));
		modelLogin.setDataNascimento(resultado.getDate("datanascimento"));
		modelLogin.setTelefones(this.listFone(modelLogin.getId()));
		
		
		retorno.add(modelLogin);
	}
	
	
	return retorno;
}

	
public int consultarUsuarioListTotalPaginaPaginacao(String nome,Long userLogado) throws Exception {
	
	String Sql = "select count(1) as total from model_login where upper(nome) like upper(?) and useradmin is false AND USUARIO_ID = ?" + " limit 5 " ;
	PreparedStatement preparedStatement = connection.prepareStatement(Sql);
	preparedStatement.setString(1, "%"+nome +"%");
	preparedStatement.setLong(2, userLogado );
	
	ResultSet resultado = preparedStatement.executeQuery();
	
	resultado.next();
	
	Double cadastros = resultado.getDouble("total");
	
	Double porpagina = 5.0;
	
	Double pagina = cadastros / porpagina;
	
	Double resto = pagina % 2;
	
	if(resto > 0) {
		pagina ++;
	}	
	
	return pagina.intValue();

}


public List<ModelLogin> consultarUsuarioListOffset(String nome,Long userLogado,String offset) throws SQLException {
	
	List<ModelLogin> retorno = new ArrayList<ModelLogin>();
	
	String Sql = "select * from model_login where upper(nome) like upper(?) and useradmin is false AND USUARIO_ID = ? offset "+ offset +" limit 5 " ;
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



	public List<ModelLogin> consultarUsuarioList(String nome,Long userLogado) throws SQLException {
		
		List<ModelLogin> retorno = new ArrayList<ModelLogin>();
		String Sql = "select * from model_login where upper(nome) like upper(?) and useradmin is false AND USUARIO_ID = ?" + " limit 5 " ;
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
			modelLogin.setFotouser(resultado.getString("fotouser"));
	
			modelLogin.setCep(resultado.getString("cep"));
			modelLogin.setLogradouro(resultado.getString("logradouro"));
			modelLogin.setBairro(resultado.getString("bairro"));
			modelLogin.setLocalidade(resultado.getString("localidade"));
			modelLogin.setUf(resultado.getString("uf"));
			modelLogin.setNumero(resultado.getString("numero"));
			modelLogin.setDataNascimento(resultado.getDate("datanascimento"));
			modelLogin.setRendamensal(resultado.getDouble("rendamensal"));
			                                               
			
		}
		

		return modelLogin;
		
	}
	
public ModelLogin consultarUsuarioID(Long id) throws Exception {
	
	ModelLogin modelLogin = new ModelLogin();
	
	String sql = "select * from model_login where id = ? and useradmin is false";

	PreparedStatement statement = connection.prepareStatement(sql);
	statement.setLong(1, id);
	
	ResultSet resultado = statement.executeQuery();
	
		while(resultado.next()) {
	
		modelLogin.setId(resultado.getLong("id"));
		modelLogin.setEmail(resultado.getString("email"));
		modelLogin.setLogin(resultado.getString("login"));
		modelLogin.setSenha(resultado.getString("senha"));
		modelLogin.setNome(resultado.getString("nome"));
		modelLogin.setPerfil(resultado.getString("perfil"));
		modelLogin.setSexo(resultado.getString("sexo"));
		modelLogin.setFotouser(resultado.getString("fotouser"));
		modelLogin.setExtensaofotouser(resultado.getString("extensaofotouser"));
		
		modelLogin.setCep(resultado.getString("cep"));
		modelLogin.setLogradouro(resultado.getString("logradouro"));
		modelLogin.setBairro(resultado.getString("bairro"));
		modelLogin.setLocalidade(resultado.getString("localidade"));
		modelLogin.setUf(resultado.getString("uf"));
		modelLogin.setNumero(resultado.getString("numero"));
		modelLogin.setDataNascimento(resultado.getDate("datanascimento"));
		modelLogin.setRendamensal(resultado.getDouble("rendamensal"));
		
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
		modelLogin.setFotouser(resultado.getString("fotouser"));
		modelLogin.setExtensaofotouser(resultado.getString("extensaofotouser"));
		
		modelLogin.setCep(resultado.getString("cep"));
		modelLogin.setLogradouro(resultado.getString("logradouro"));
		modelLogin.setBairro(resultado.getString("bairro"));
		modelLogin.setLocalidade(resultado.getString("localidade"));
		modelLogin.setUf(resultado.getString("uf"));
		modelLogin.setNumero(resultado.getString("numero"));
		modelLogin.setDataNascimento(resultado.getDate("datanascimento"));
		modelLogin.setRendamensal(resultado.getDouble("rendamensal"));
		
		
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
			modelLogin.setFotouser(resultado.getString("fotouser"));
		
			modelLogin.setCep(resultado.getString("cep"));
			modelLogin.setLogradouro(resultado.getString("logradouro"));
			modelLogin.setBairro(resultado.getString("bairro"));
			modelLogin.setLocalidade(resultado.getString("localidade"));
			modelLogin.setUf(resultado.getString("uf"));
			modelLogin.setNumero(resultado.getString("numero"));
			modelLogin.setDataNascimento(resultado.getDate("datanascimento"));
			modelLogin.setRendamensal(resultado.getDouble("rendamensal"));
		
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
	    modelLogin.setFotouser(resultado.getString("fotouser"));
	    
		modelLogin.setCep(resultado.getString("cep"));
		modelLogin.setLogradouro(resultado.getString("logradouro"));
		modelLogin.setBairro(resultado.getString("bairro"));
		modelLogin.setLocalidade(resultado.getString("localidade"));
		modelLogin.setUf(resultado.getString("uf"));
		modelLogin.setNumero(resultado.getString("numero"));
		modelLogin.setDataNascimento(resultado.getDate("datanascimento"));
		modelLogin.setRendamensal(resultado.getDouble("rendamensal"));
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
	
	
public List<ModelTelefone> listFone(Long idUserPai) throws Exception {
		
		List<ModelTelefone> retorno = new ArrayList<ModelTelefone>();
		
		String sql = "select * from telefone where usuario_pai_id = ?";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		
		preparedStatement.setLong(1, idUserPai);
		
		ResultSet rs = preparedStatement.executeQuery();
		
		while (rs.next()) {
			
			ModelTelefone modelTelefone = new ModelTelefone();
			
			modelTelefone.setId(rs.getLong("id"));
			modelTelefone.setNumero(rs.getString("numero"));
			modelTelefone.setUsuario_cad_id(this.consultarUsuarioID(rs.getLong("usuario_cad_id")));
			modelTelefone.setUsuario_pai_id(this.consultarUsuarioID(rs.getLong("usuario_pai_id")));
			
			retorno.add(modelTelefone);
		}
		return retorno;
	}


}

	

