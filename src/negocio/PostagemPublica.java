package negocio;
/**
 * A classe PostagemPublica herda da classe Postagem.
 * @author Rafael Gomes Pereira
 * @author João Pedro Silva Souza
 * @since 2023
 */
public class PostagemPublica extends Postagem {
	
	/**
	 * Instancia a partir da superclasse Postagem uma nova Postagem Publica.
	 * @param idDoUsuario
	 * @param texto
	 * @param dataHora
	 */
	public PostagemPublica(String idDoUsuario, String texto, String dataHora) {
		super(idDoUsuario, texto, dataHora);
	}
	
	/**
	 * Remove a postagem da lista de postagens publicas do usuario que a criou.
	 * @param post
	 * @param usuario
	 */
	public void deletarPostagemPublica(Postagem post, Usuario usuario) {
		usuario.getPostagensPublicas().remove(post);
	}
}