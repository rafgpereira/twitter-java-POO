package negocio;
/**
 * A classe PostagemDireta herda da classe Postagem, e se difere pelo atributo destinatário.
 * @author Rafael Gomes Pereira
 * @author João Pedro Silva Souza
 * @since 2023
 */
public class PostagemDireta extends Postagem {
	
	private String idDoDestinatario;
	
	/**
	 * Instancia uma nova PostagemDireta a partir da superclasse Postagem.
	 * @param idDoUsuario
	 * @param idDoDestinatario
	 * @param texto
	 * @param dataHora
	 */
	public PostagemDireta(String idDoUsuario, String idDoDestinatario, String texto, String dataHora ) {
		super(idDoUsuario, texto, dataHora);
		this.idDoDestinatario = idDoDestinatario;
	}
	
	/**
	 * Retorna o id do destinatário.
	 * @return String
	 */
	public String getIdDoDestinatario() {
		return idDoDestinatario;
	}
	
	/**
	 * Remove a PostagemDireta da lista de postagens diretas do usuario que a criou.
	 * @param post
	 * @param usuario
	 */
	public void deletarPostagemDireta(Postagem post, Usuario usuario) {
		usuario.getPostagensDiretas().remove(post);
	}
	
	
	@Override
	/**
	 * Retorna uma string com os atributos da PostagemDireta.
	 * @return String
	 */
	public String toString() {
		return  "\n\nDe: "
				+ getIdDoUsuario()
				+"\nPara: " 
				+ idDoDestinatario 
				+ "\n"
				+ getTexto()
				+"\nEnviado em: "
				+ getDataHora(); 
	}
}
