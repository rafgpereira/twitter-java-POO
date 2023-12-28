package dados;

import java.util.ArrayList;
import java.util.List;

import negocio.PostagemPublica;
import negocio.Usuario;
/**
 * A classe Dados funciona como um sistema de banco de dados, armazenando os dados da aplicação em tempo de execução.
 * @author Rafael Gomes Pereira
 * @author João Pedro Silva Souza
 * @since 2023
 */
public class Dados {
	
	
	private List<Usuario> usuarios;
	
	/**
	 * Instancia a classe Dados, inicilizando a lista de usuarios e chamando o metodo que preenche os dados da aplicação.
	 */
	public Dados() {
		usuarios = new ArrayList<>();
		preencherDados();
	}
	
	/**
	 * @return Retorna a lista de todos os usuarios da aplicação.
	 */
	public List<Usuario> getUsuarios(){
		return usuarios;
	}
	
	/**
	 * Adiciona um usuario a lista de usuarios.
	 * @param usuario
	 */
	public void adicionarUsuario(Usuario usuario) {
		usuarios.add(usuario);
	}
	/**
	 * Retorna uma lista com a id e o nome dos demais usuarios da aplicação.
	 * @param usuario
	 * @return String
	 */
	public List<String> listarIdUsuariosBusca(Usuario usuario){
		List<String> idUsuarios = new ArrayList<>();
		for(Usuario u : usuarios) {
			if(u!=usuario) {
				idUsuarios.add("\n" + u.getIdDoUsuario() + " - " + u.getNome());
			}
		}
		return idUsuarios;
	}
	/**
	 * Retorna uma lista com a id e o nome dos demais usuarios que não sigo.
	 * @param usuario
	 * @return Array
	 */
	public List<String> listarIdUsuariosSeguir(Usuario usuario){
		List<String> idUsuarios = new ArrayList<>();
		for(Usuario u : usuarios) {
			if(!usuario.getSeguindo().contains(u)&& u!=usuario) {
				idUsuarios.add("\n" + u.getIdDoUsuario() + " - " + u.getNome());
			}
		}
		return idUsuarios;
	}
	/**
	 * Retorna o usuario buscado a partir de sua id.
	 * @param idDoUsuario
	 * @return Usuario
	 */
	public Usuario buscarUsuario(String idDoUsuario) {
		for(Usuario usuario : usuarios) {
			if(usuario.getIdDoUsuario().equals(idDoUsuario)) {
				return usuario;
			}
		}
		return null;
	}
	/**
	 * Retorna uma lista com todos os usuarios encontrados a partir de um termo de busca.
	 * @param nomeDeBusca
	 * @return List<Usuario>
	 */
	public List<Usuario> buscarUsuarios(String nomeDeBusca) {
		
		List<Usuario> usuariosBuscados = new ArrayList<>();

		for(Usuario usuario : usuarios) {
			if(usuario.getIdDoUsuario().toLowerCase().contains(nomeDeBusca.toLowerCase())) {
				usuariosBuscados.add(usuario);
			}
		}
		return (usuariosBuscados.size()>0 ? usuariosBuscados : null);
	}
	/**
	 * Retorna uma lista de todas as postagens publicas encontradas a partir de um termo de busca.
	 * @param textoDeBusca
	 * @return List<PostagemPublica>
	 */
	public List<PostagemPublica> buscarPostagens(String textoDeBusca){

		List<PostagemPublica> postagensBuscadas = new ArrayList<>();
		
		for(Usuario usuario : usuarios) {
			for(PostagemPublica postagem : usuario.getPostagensPublicas()) {
				if(postagem.getTexto().toLowerCase().contains(textoDeBusca.toLowerCase())) {
					postagensBuscadas.add(postagem);
				}
			}
		}
		return (postagensBuscadas.size()>0 ? postagensBuscadas : null);
	}
	/**
	 * Preenche com dados pré definidos a aplicação.
	 */
	public void preencherDados() {
		Usuario usuario1 = new Usuario("@rafaelpereira","Rafael Gomes Pereira","Gosto muito de pao de queijo uaai");
		Usuario usuario2 = new Usuario("@fabiosantos","Fabio Santos Araujo","forro e bingo aos fds");
		Usuario usuario3 = new Usuario("@joaolucas","Joao Lucas Siqueira","defeito de fabrica");
		Usuario usuario4 = new Usuario("@joaopedro","Joao Pedro Silva","doce como mel");
		Usuario usuario5 = new Usuario("@carloseduardo","Carlos Eduardo Sousa","fofo por fora, perigoso por dentro");
		Usuario usuario6 = new Usuario("@danielferreira","Daniel Ferreira","fora da lei");
		Usuario usuario7 = new Usuario("@arturcamargos","Artur de Camargos","meu stress diario");
		Usuario usuario8 = new Usuario("@milenafernandes","Milena Fernandes","mulheres com bolsonaro");
		Usuario usuario9 = new Usuario("@ingridalves","Ingrid Alves","cuidado que eu mordo");
		Usuario usuario10 = new Usuario("@joaoaugusto","Joao Augusto","little rico");
		Usuario usuario11 = new Usuario("@brunoserra","Bruno Serra","viciado em viver");
		
		usuarios.add(usuario1);usuarios.add(usuario2);usuarios.add(usuario3);usuarios.add(usuario4);usuarios.add(usuario5);usuarios.add(usuario6);usuarios.add(usuario7);usuarios.add(usuario8);usuarios.add(usuario9);usuarios.add(usuario10);usuarios.add(usuario11);
		
		usuario1.seguirUsuario(usuario2);usuario1.seguirUsuario(usuario4);usuario1.seguirUsuario(usuario6);
		usuario2.seguirUsuario(usuario3);usuario2.seguirUsuario(usuario5);
		usuario3.seguirUsuario(usuario1);usuario3.seguirUsuario(usuario2);usuario3.seguirUsuario(usuario11);usuario3.seguirUsuario(usuario8);usuario3.seguirUsuario(usuario9);
		usuario4.seguirUsuario(usuario8);usuario4.seguirUsuario(usuario9);usuario4.seguirUsuario(usuario3);
		usuario5.seguirUsuario(usuario4);usuario5.seguirUsuario(usuario1);usuario5.seguirUsuario(usuario11);usuario5.seguirUsuario(usuario10);
		usuario6.seguirUsuario(usuario8);usuario6.seguirUsuario(usuario3);
		usuario8.seguirUsuario(usuario3);usuario8.seguirUsuario(usuario2);usuario8.seguirUsuario(usuario6);
		usuario9.seguirUsuario(usuario1);usuario9.seguirUsuario(usuario4);usuario9.seguirUsuario(usuario8);
		usuario10.seguirUsuario(usuario11);usuario10.seguirUsuario(usuario1);usuario10.seguirUsuario(usuario4);usuario10.seguirUsuario(usuario3);
		usuario11.seguirUsuario(usuario1);usuario11.seguirUsuario(usuario2);usuario11.seguirUsuario(usuario3);usuario11.seguirUsuario(usuario4);usuario11.seguirUsuario(usuario5);usuario11.seguirUsuario(usuario6);
		
		usuario1.getPostagensPublicas().add(new PostagemPublica(usuario1.getIdDoUsuario(),"affs minha foto flopou :( ","10/09/2023 21:35"));
		usuario1.getPostagensPublicas().add(new PostagemPublica(usuario1.getIdDoUsuario(),"domingo a noite sem ela eh dificil","13/11/2023 01:22"));
		usuario1.getPostagensPublicas().add(new PostagemPublica(usuario1.getIdDoUsuario(),"saudades de patos... UAI","17/11/2023 15:40"));
		usuario2.getPostagensPublicas().add(new PostagemPublica(usuario2.getIdDoUsuario(),"Como wue us aisto daqui..?","15/10/1975 12:32"));
		usuario2.getPostagensPublicas().add(new PostagemPublica(usuario2.getIdDoUsuario(),"O pai ta bebo asdfaçsdjfhajds","25/08/2023 05:43"));
		usuario3.getPostagensPublicas().add(new PostagemPublica(usuario3.getIdDoUsuario(),"gagaga gaguejou pq? qu quem ta desnorteado eh eh vo voce","31/06/2023 22:24"));
		usuario3.getPostagensPublicas().add(new PostagemPublica(usuario3.getIdDoUsuario(),"NINGUEM TIRA O TITULO DO FOGAO ESSE ANO","16/11/2023 23:59"));
		usuario3.getPostagensPublicas().add(new PostagemPublica(usuario3.getIdDoUsuario(),"tiraram...  :( ","17/11/2023 00:00"));
		usuario4.getPostagensPublicas().add(new PostagemPublica(usuario4.getIdDoUsuario(),"aff quero um lovezin sincero","13/11/2023 02:41"));
		usuario4.getPostagensPublicas().add(new PostagemPublica(usuario4.getIdDoUsuario(),"afs odeio gastar roupa boa em role paia","15/11/2023 20:11"));
		usuario4.getPostagensPublicas().add(new PostagemPublica(usuario4.getIdDoUsuario(),"Alguem sabe algum tutorial atualizado de como dormir rapido e fácil? N aguento mais dormir dps das 3 todo dia","16/11/2023 03:28"));
		usuario4.getPostagensPublicas().add(new PostagemPublica(usuario4.getIdDoUsuario(),"Tem musica q parece q me transforma num bicho, hj eu to AQUECENDO com meu peso recorde po","16/11/2023 19:21"));
		usuario4.getPostagensPublicas().add(new PostagemPublica(usuario4.getIdDoUsuario(),"Po n tem jeito, trança é massa e tal…mas meu blackzin eh absurdo parsa","17/11/2023 10:58"));
		usuario6.getPostagensPublicas().add(new PostagemPublica(usuario6.getIdDoUsuario(),"apostei minha casa no sao paulo e perdi af","11/10/2023 23:06"));
		usuario7.getPostagensPublicas().add(new PostagemPublica(usuario7.getIdDoUsuario(),"alguem me segue pffff  :( ","15/10/2023 01:16"));
		usuario8.getPostagensPublicas().add(new PostagemPublica(usuario8.getIdDoUsuario(),"Nao me pegaro ;) ","08/01/2023 17:22"));
		usuario8.getPostagensPublicas().add(new PostagemPublica(usuario8.getIdDoUsuario(),"Flamengo n eh time, eh selecao","10/11/2023 15:50"));
		usuario9.getPostagensPublicas().add(new PostagemPublica(usuario9.getIdDoUsuario(),"Gastei 15 mil hj e o limite ainda nao estourou afs","02/11/2023 20:02"));
		usuario9.getPostagensPublicas().add(new PostagemPublica(usuario9.getIdDoUsuario(),"ODEIO A TAYLOR SWIFT","12/11/2023 21:25"));
		usuario9.getPostagensPublicas().add(new PostagemPublica(usuario9.getIdDoUsuario(),"afs vo ter que ir pra disney de novo","14/11/2023 09:20"));
		usuario10.getPostagensPublicas().add(new PostagemPublica(usuario10.getIdDoUsuario(),"Pode vir Cálculo 3","06/07/2023 22:28"));
		usuario10.getPostagensPublicas().add(new PostagemPublica(usuario10.getIdDoUsuario(),"Pidagonhagaba","19/06/2023 04:36"));
		usuario10.getPostagensPublicas().add(new PostagemPublica(usuario10.getIdDoUsuario(),"Homem, 21 anos e não dirige????? Affffff","27/10/2023 17:50"));
		usuario11.getPostagensPublicas().add(new PostagemPublica(usuario11.getIdDoUsuario(),"A essa quarentena......","12/11/2020 13:08"));
		usuario11.getPostagensPublicas().add(new PostagemPublica(usuario11.getIdDoUsuario(),"Passeio de Jericoacoara ;) ;)","05/06/2021 14:11"));
		usuario11.getPostagensPublicas().add(new PostagemPublica(usuario11.getIdDoUsuario(),"Tentando ser forte kkkk","21/06/2022 07:19"));
		usuario11.getPostagensPublicas().add(new PostagemPublica(usuario11.getIdDoUsuario(),"Turbulência 2022","18/10/2022 10:15"));
		usuario11.getPostagensPublicas().add(new PostagemPublica(usuario11.getIdDoUsuario(),"Dia de Beach park","03/12/2022 14:11"));
		usuario11.getPostagensPublicas().add(new PostagemPublica(usuario11.getIdDoUsuario(),"Feliz 2023 meu povo!!!","01/01/2023 00:21"));
		
	}


}
