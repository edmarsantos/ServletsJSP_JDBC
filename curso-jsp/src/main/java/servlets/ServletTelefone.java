package servlets;

import java.io.IOException;
import java.util.List;

import dao.DAOUsuarioRepository;
import dao.DaoTelefoneRepository;
import javax.servlet.annotation.WebServlet;

import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.ModelLogin;
import model.ModelTelefone;

//caso venha com erro ctrl + shift + o ele ira corrigir possiveis erros de imports
@WebServlet("/ServletTelefone")
public class ServletTelefone extends ServletGenericUtil {

	private static final long serialVersionUID = 1L;

	// instanciando para chamar mais abaixo no Metodo get e Post
	private DAOUsuarioRepository daoUsuarioRepository = new DAOUsuarioRepository();
	private DaoTelefoneRepository daoTelefoneRepository = new DaoTelefoneRepository();

	public ServletTelefone() {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {

			String acao = request.getParameter("acao");

			if (acao != null && !acao.isEmpty() && acao.equals("excluir")) {
				String idfone = request.getParameter("id");
				daoTelefoneRepository.deletarFone(Long.parseLong(idfone));

				String userpai = request.getParameter("userpai");
				// modellogin para pegar o usuario pai
				ModelLogin modelLogin = daoUsuarioRepository.consultarUsuarioID(Long.parseLong(userpai));

				List<ModelTelefone> modelTelefones = daoTelefoneRepository.listFone(modelLogin.getId());
				request.setAttribute("modelTelefones", modelTelefones);

				request.setAttribute("msg", "Telefone Excluido!!");
				request.setAttribute("modelLogin", modelLogin);
				request.getRequestDispatcher("principal/telefone.jsp").forward(request, response);

				return;

			}

			String iduser = request.getParameter("iduser");

			if (iduser != null && !iduser.isEmpty()) {

				ModelLogin modelLogin = daoUsuarioRepository.consultarUsuarioID(Long.parseLong(iduser));

				List<ModelTelefone> modelTelefones = daoTelefoneRepository.listFone(modelLogin.getId());
				request.setAttribute("modelTelefones", modelTelefones);

				request.setAttribute("modelLogin", modelLogin);
				request.getRequestDispatcher("principal/telefone.jsp").forward(request, response);

			} else {
				List<ModelLogin> modelLogins = daoUsuarioRepository.consultarUsuarioList(super.getUserLogado(request));
				request.setAttribute("modelLogins", modelLogins);
				request.setAttribute("totalPagina", daoUsuarioRepository.totalPagina(this.getUserLogado(request)));
				request.getRequestDispatcher("principal/usuario.jsp").forward(request, response);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {

			String usuario_pai_id = request.getParameter("id");
			String numero = request.getParameter("numero");

			if (!daoTelefoneRepository.existeFone(numero, Long.valueOf(usuario_pai_id))) {

				ModelTelefone modelTelefone = new ModelTelefone();

				modelTelefone.setNumero(numero);
				modelTelefone
						.setUsuario_pai_id(daoUsuarioRepository.consultarUsuarioID(Long.parseLong(usuario_pai_id)));
				modelTelefone.setUsuario_cad_id(super.getUserLogadoObjt(request));

				daoTelefoneRepository.gravaTelefone(modelTelefone);

				request.setAttribute("msg", "Telefone salvo com Sucesso");

			} else {
				request.setAttribute("msg", "Telefone já existe!");
			} // if de validação de duplicidade

			List<ModelTelefone> modelTelefones = daoTelefoneRepository.listFone(Long.parseLong(usuario_pai_id));
			ModelLogin modelLogin = daoUsuarioRepository.consultarUsuarioID(Long.parseLong(usuario_pai_id));
			request.setAttribute("modelLogin", modelLogin);
  	        request.setAttribute("modelTelefones", modelTelefones);
	        request.getRequestDispatcher("principal/telefone.jsp").forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
