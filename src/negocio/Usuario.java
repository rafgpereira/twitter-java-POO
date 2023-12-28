package negocio;

import java.util.ArrayList;
import java.util.List;

import dados.Dados;

/**
 * Classe Usuario determina os atributos e define o comportamento de um usuario da aplicação.
 * @author Rafael Gomes Pereira
 * @author João Pedro Silva Sousa
 * @since 2023
 * @version 1.1
 * 
 */
public class Usuario {
	
	private String idDoUsuario;
	private String nome;
	private String biografia;
	private List<Usuario> seguidores;
	private List<Usuario> seguindo;
	private List<PostagemPublica> postagensPublicas;
	private List<PostagemDireta> postagensDiretas;
	
	/**
	 * Instancia um novo usuario com seus atributos e inicializa os atributos do tipo List.
	 * @param idDoUsuario
	 * @param nome
	 * @param biografia
	 */
	public Usuario(String idDoUsuario, String nome, String biografia) {
		this.idDoUsuario=idDoUsuario;
		this.nome=nome;
		this.biografia=biografia;
		seguidores = new ArrayList<>();
		seguindo = new ArrayList<>();
		postagensPublicas = new ArrayList<>();
		postagensDiretas = new ArrayList<>();
	}
	
	/**
	 * Retorna o nome do usuario.
	 * @return String
	 */
	public String getNome() {
		return nome;
	}
	/**
	 * Retorna a biografia do usuario.
	 * @return String
	 */
	public String getBiografia() {
		return biografia;
	}
	/**
	 * Retorna a id do usuario.
	 * @return String
	 */
	public String getIdDoUsuario() {
		return idDoUsuario;
	}
	/**
	 * Retorna a lista de quem o usuario segue.
	 * @return List<Usuario>
	 */
	public List<Usuario> getSeguindo(){
		return seguindo;
	}
	/**
	 * Retorna a lista de quem segue o usuario.
	 * @return List<Usuario>
	 */
	public List<Usuario> getSeguidores(){
		return seguidores;
	}
	/**
	 * Retorna a lista de postagens públicas do usuario.
	 * @return List<PostagemPublica>
	 */
	public List<PostagemPublica> getPostagensPublicas() {
		return postagensPublicas;
	}
	/**
	 * Retorna a lista de postagens diretas do usuario.
	 * @return List<PostagemDireta>
	 */
	public List<PostagemDireta> getPostagensDiretas() {
		return postagensDiretas;
	}
	
	/**
	 * Altera o nome e a biografia do usuario.
	 * @param nome
	 * @param biografia
	 */
	public void editarUsuario(String nome, String biografia ) {
		this.nome=nome;
		this.biografia=biografia;
	}
	/**
	 * Remove o usuario da base de dados da aplicação.
	 * @param dados
	 */
	public void deletarUsuario(Dados dados) {
		dados.getUsuarios().remove(this);
	}
	/**
	 * Adiciona o usuario na lista de seguidores e na lista de seguindo um do outro.
	 * @param usuario
	 */
	public void seguirUsuario(Usuario usuario) {
		seguindo.add(usuario);
		usuario.getSeguidores().add(this);
	}
	/**
	 * Remove o usuario da lista de seguidores e da lista de seguindo u do outro.
	 * @param usuario
	 */
	public void pararDeSeguirUsuario(Usuario usuario) {
		seguindo.remove(usuario);
		usuario.getSeguidores().remove(this);
	}
	/**
	 * Retorna a lista de postagens publicas dos usuarios que o usuario segue.
	 * @return List<PostagemPublica>
	 */
	public List<PostagemPublica> listarPostagensSeguindo() {
		List<PostagemPublica> postagensSeguindo = new ArrayList<>();
		for(Usuario seguindo : seguindo) {
			for(PostagemPublica postagem : seguindo.getPostagensPublicas()) {
				postagensSeguindo.add(postagem);
			}
		}
		return postagensSeguindo;
	}
	/**
	 * Adiciona a postagem direta na lista de postagens diretas do usuario remetente e destinatario.
	 * @param mensagem
	 * @param dados
	 */
	public void enviarPostagemDireta(PostagemDireta mensagem, Dados dados) {
		Usuario destinatario = dados.buscarUsuario(mensagem.getIdDoDestinatario());
		destinatario.getPostagensDiretas().add(mensagem);
		this.getPostagensDiretas().add(mensagem);	
	}
	/**
	 * Retorna a lista de postagens diretas enviadas pelo usuario.
	 * @return List<PostagemDireta>
	 */
	public List<PostagemDireta> buscarPostagensDiretasEnviadas(){
		List<PostagemDireta> postagensDiretasEnviadas = new ArrayList<>();
		for(PostagemDireta p : postagensDiretas) {
			if(p.getIdDoUsuario().equals(this.idDoUsuario)) {
				postagensDiretasEnviadas.add(p);
			}
		}
		if(postagensDiretasEnviadas.size()>0) {
			return postagensDiretasEnviadas;
		}else {
			return null;
		}
	}
	@Override
	/**
	 * Sobrescreve o metodo toString() da classe Object. Constrói uma String com o valor de alguns atributos do usuario.
	 * @return String
	 */
	public String toString() {
		return "\n\n"
				+ getIdDoUsuario()
				+ "\n"
				+ getNome()
				+ "\n"
				+ getBiografia()
				+ "\nSeguindo: "
				+ getSeguindo().size()
				+ "\nSeguidores: "
				+ getSeguidores().size()
				+"\n"
				+ getPostagensPublicas().size()
				+ (getPostagensPublicas().size()==1?" postagem publica":" postagens publicas");
				
	}

}
