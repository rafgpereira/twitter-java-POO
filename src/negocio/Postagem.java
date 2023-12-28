package negocio;
/**
 * Classe Postagem é uma superclasse abstrata que determina o comportamento básico de uma postagem.
 * @author Rafael Gomes Pereira
 * @author João Pedro Silva Sousa
 * @since 2023
 * @version 1.1
 */
public abstract class Postagem {
	

	private String idDoUsuario;
	private String texto;
	private String dataHora;
	
	/**
	 * Instancia todos os atributos comuns da classe Postagem.
	 * @param idDoUsuario Identificação do usuario que fez a postagem.
	 * @param texto Conteúdo textual da Postagem.
	 * @param dataHora Data e hora em que a Postagem foi feita.
	 */
	public Postagem(String idDoUsuario, String texto, String dataHora) {
		this.idDoUsuario=idDoUsuario;
		this.texto=texto;
		this.dataHora=dataHora;
		
	}
	
	/**
	 * Retorna a id do usuario autor da postagem.
	 * @return String
	 */
	public String getIdDoUsuario() {
		return idDoUsuario;
	}
	/**
	 * Retorna o texto da postagem.
	 * @return String
	 */
	public String getTexto() {
		return texto;
	}
	/**
	 * Retorna a data e a hora da postagem.
	 * @return String
	 */
	public String getDataHora() {
		return dataHora;
	}
	
	/**
	 * Altera o texto da postagem.
	 * @param texto
	 */
	public void editarPostagem(String texto) {
		this.texto=texto;
	}

	/**
	 * Retorna uma String contendo os atributos da postagem.
	 * @return String
	 */
	public String toString() {
		return "\n"
				+ idDoUsuario
				+ "\n" 
				+ texto 
				+ "\nPostado em " 
				+ dataHora
				+"\n";
	}

	
}
