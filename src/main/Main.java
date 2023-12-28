package main;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import dados.Dados;
import negocio.PostagemDireta;
import negocio.PostagemPublica;
import negocio.Usuario;

public class Main {

	public static void main (String args[]) {
		Scanner sc = new Scanner(System.in);
		SimpleDateFormat formatoDataHora = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		Dados dados = new Dados();
		
		UI.limparConsole();
		System.out.println("\n-----------Bem vindo ao Twitter!-----------\n");
		System.out.println("Para iniciar, vamos criar seu usuario:\n");
		
		Usuario meuUsuario = UI.paginaCadastro(sc);
		
		while(dados.buscarUsuario(meuUsuario.getIdDoUsuario())!= null) {
			System.out.println("Esse id de usuario ja esta sendo utilizado.");
			meuUsuario = UI.paginaCadastro(sc);
		}
	
		dados.adicionarUsuario(meuUsuario);
		UI.limparConsole();
		
		
		//Termino do pre carregamento de dados
		Date dataHoraAtual = new Date();
		String dataHoraAtualString = formatoDataHora.format(dataHoraAtual);
	
		dados.buscarUsuario("@rafaelpereira").enviarPostagemDireta(new PostagemDireta("@rafaelpereira",meuUsuario.getIdDoUsuario(),"Oi, " +meuUsuario.getNome() +". Seja bem vind@!",dataHoraAtualString), dados);
		dados.buscarUsuario("@joaopedro").enviarPostagemDireta(new PostagemDireta("@joaopedro",meuUsuario.getIdDoUsuario(),"Eai, " +meuUsuario.getNome() +". Esta gostando do nosso aplicativo??",dataHoraAtualString), dados);
		dados.buscarUsuario("@arturcamargos").enviarPostagemDireta(new PostagemDireta("@arturcamargos",meuUsuario.getIdDoUsuario(),"oi... me segue por favor :( ",dataHoraAtualString), dados);
		dados.buscarUsuario("@brunoserra").enviarPostagemDireta(new PostagemDireta("@brunoserra",meuUsuario.getIdDoUsuario(),"E ai? Curte um beach park??",dataHoraAtualString), dados);
		dados.buscarUsuario("@rafaelpereira").seguirUsuario(meuUsuario);
		dados.buscarUsuario("@joaopedro").seguirUsuario(meuUsuario);
		dados.buscarUsuario("@brunoserra").seguirUsuario(meuUsuario);
		dados.buscarUsuario("@ingridalves").seguirUsuario(meuUsuario);


		System.out.println("Seu perfil foi criado com sucesso:\n" + meuUsuario);
		System.out.println(("\n\nPressione enter para continuar."));
		sc.nextLine();
		UI.limparConsole();
		
		//Inicio da logica de menus da aplicacao
		while(dados.getUsuarios().contains(meuUsuario)) {
			System.out.println(meuUsuario.getNome()+" ("+meuUsuario.getIdDoUsuario()+")");
			UI.imprimirMenuInicial();
			int opcaoMenuInicial = sc.nextInt();
			sc.nextLine();
			UI.limparConsole();
			if(opcaoMenuInicial == 0 ) {
				int opcaoMenuPerfil=-1;
				while(opcaoMenuPerfil!=9) {
					System.out.println(meuUsuario);
					UI.imprimirMenuPerfil();
					opcaoMenuPerfil = sc.nextInt();
					sc.nextLine();
					UI.limparConsole();
					if(opcaoMenuPerfil==0) {
						System.out.print("Atualize seu nome: ");
						String novoNome = sc.nextLine();
						while(novoNome.isEmpty()) {
							System.out.print("O novo nome precisa ter ao menos um caractere: ");
							novoNome=sc.nextLine();
						}
						System.out.print("Atualize sua biografia: ");
						String novaBio = sc.nextLine();
						while(novaBio.isEmpty()) {
							System.out.print("A nova biografia precisa ter ao menos um caractere: ");
							novaBio=sc.nextLine();
						}
						meuUsuario.editarUsuario(novoNome, novaBio);
						System.out.println("\nSeu perfil foi atualizado com sucesso:\n" + meuUsuario);
						System.out.println("\nPressione enter para voltar ao menu anterior.");
						sc.nextLine();
						UI.limparConsole();
					}else if(opcaoMenuPerfil==1) {
						int opcaoMenuPostagemPublica=-1;
						while(opcaoMenuPostagemPublica!=9) {
							if(meuUsuario.getPostagensPublicas().size()==0) {
								System.out.println("\nVoce ainda nao tem postagens publicas");
							}else {
								System.out.println("Suas postagens publicas:\n\n" + meuUsuario.getPostagensPublicas());
							}
							UI.imprimirMenuPostagensPublicas();
							opcaoMenuPostagemPublica = sc.nextInt();
							sc.nextLine();
							UI.limparConsole();
							if(opcaoMenuPostagemPublica==0) {
								System.out.print("Digite o texto da postagem publica: ");
								String textoPostPublico = sc.nextLine();
								while(textoPostPublico.isEmpty()) {
									System.out.print("\nA postagem publica precisa ter pelo menos um caractere: ");
									textoPostPublico = sc.nextLine();
								}
								dataHoraAtual = new Date();
								dataHoraAtualString = formatoDataHora.format(dataHoraAtual);
								PostagemPublica postagemPublica = new PostagemPublica(meuUsuario.getIdDoUsuario(), textoPostPublico, dataHoraAtualString);
								meuUsuario.getPostagensPublicas().add(postagemPublica);
								System.out.println("\nSua postagem publica foi criada com sucesso:\n" + postagemPublica);
								System.out.println("\nPressione enter para voltar ao menu anterior.");
								sc.nextLine();
								UI.limparConsole();
							}else if(opcaoMenuPostagemPublica==1) {
								if(meuUsuario.getPostagensPublicas().size()==0) {
									System.out.println("\nVoce ainda nao tem postagens publicas.");
									System.out.println("\nPressione enter para voltar ao menu anterior.");
									sc.nextLine();
									UI.limparConsole();
								}else {
									System.out.println("Suas postagens publicas:\n\n " + meuUsuario.getPostagensPublicas());
									System.out.print("\n\nVoce tem " + meuUsuario.getPostagensPublicas().size() + " postagens publicas, qual delas deseja editar(numero/indice)? ");
									int indexPostPublico = sc.nextInt();
									sc.nextLine();
									while(indexPostPublico>meuUsuario.getPostagensPublicas().size()||indexPostPublico<1) {
										System.out.print("O indice inserido nao corresponde as suas postagens (1-"+meuUsuario.getPostagensPublicas().size()+"): ");
										indexPostPublico = sc.nextInt();
										sc.nextLine();
									}
									UI.limparConsole();
									System.out.print(meuUsuario.getPostagensPublicas().get(indexPostPublico-1));
									System.out.print("\nInsira o novo conteudo da postagem: ");
									String novoTextoPostagemPublica = sc.nextLine();
									meuUsuario.getPostagensPublicas().get(indexPostPublico-1).editarPostagem(novoTextoPostagemPublica);
									System.out.println("\nSua postagem publica foi editada com sucesso: ");
									System.out.print(meuUsuario.getPostagensPublicas().get(indexPostPublico-1));
									System.out.println("\nPressione enter para voltar ao menu anterior.");
									sc.nextLine();
									UI.limparConsole();
								}
							}else if(opcaoMenuPostagemPublica==2) {
								if(meuUsuario.getPostagensPublicas().size()==0) {
									System.out.println("\nVoce ainda nao tem postagens publicas.");
								}else {
									System.out.println("Suas postagens publicas:\n\n" + meuUsuario.getPostagensPublicas());
									System.out.print("Voce tem " + meuUsuario.getPostagensPublicas().size() + " postagens publicas, qual delas deseja excluir(indice)? ");
									int indexPostPublico = sc.nextInt();
									sc.nextLine();
									while(indexPostPublico>meuUsuario.getPostagensPublicas().size()||indexPostPublico<1) {
										System.out.print("O indice inserido nao corresponde as suas postagens (1-"+meuUsuario.getPostagensPublicas().size()+"): ");
										indexPostPublico = sc.nextInt();
										sc.nextLine();
									}
									UI.limparConsole();
									meuUsuario.getPostagensPublicas().get(indexPostPublico-1).deletarPostagemPublica(meuUsuario.getPostagensPublicas().get(indexPostPublico-1), meuUsuario);
									System.out.println("\nSua postagem " + indexPostPublico + " foi deletada com sucesso!");
									System.out.println("\nPressione enter para voltar ao menu anterior.");
									sc.nextLine();
									UI.limparConsole();
								}
							}else {
								continue;
							}
						}
						
					}else if(opcaoMenuPerfil == 2) {
						int opcaoMenuPostagemDireta=-1;
						while(opcaoMenuPostagemDireta!=9) {
							if(meuUsuario.getPostagensDiretas().size()==0) {
								System.out.println("\nVoce ainda nao tem postagens diretas.");
							}else {
								System.out.println("Suas postagens diretas:\n\n" + meuUsuario.getPostagensDiretas());
							}
							UI.imprimirMenuPostagemDireta();
							opcaoMenuPostagemDireta = sc.nextInt();
							sc.nextLine();
							UI.limparConsole();
							if(opcaoMenuPostagemDireta==0) {
								System.out.print("Digite o id do destinatario: ");
								String idDoDestinatario = sc.nextLine();
								while(dados.buscarUsuario(idDoDestinatario)== null) {
									System.out.print("\nEsse ID de destinatario nao existe, tente novamente: ");
									idDoDestinatario = sc.nextLine();
								}
								System.out.print("Digite o texto da postagem direta: ");
								String textoPostDireta = sc.nextLine();
								while(textoPostDireta.isEmpty()) {
									System.out.print("\nA postagem direta precisa ter pelo menos um caractere: ");
									textoPostDireta = sc.nextLine();
								}
								dataHoraAtual = new Date();
								dataHoraAtualString = formatoDataHora.format(dataHoraAtual);
								PostagemDireta postagemDireta = new PostagemDireta(meuUsuario.getIdDoUsuario(), idDoDestinatario, textoPostDireta, dataHoraAtualString);
								meuUsuario.getPostagensDiretas().add(postagemDireta);
								System.out.println("\nSua postagem direta foi criada com sucesso!\n" + postagemDireta);
								System.out.println("\nPressione enter para voltar ao menu anterior.");
								sc.nextLine();
								UI.limparConsole();
							}else if(opcaoMenuPostagemDireta==1) {
								if(meuUsuario.buscarPostagensDiretasEnviadas()==null) {
									System.out.println("\nApenas postagens diretas enviadas podem ser editadas.\n\nVoce ainda nao enviou postagens diretas.");
									System.out.println("\nPressione enter para voltar ao menu anterior.");
									sc.nextLine();
									UI.limparConsole();
								}else {
									System.out.print("Suas postagens diretas enviadas:\n\n" + meuUsuario.buscarPostagensDiretasEnviadas());
									System.out.print("\n\nVoce tem " + meuUsuario.buscarPostagensDiretasEnviadas().size() + " postagens diretas eviadas, qual delas deseja editar(numero/indice)? ");
									int indexPostDireto = sc.nextInt();
									sc.nextLine();
									while(indexPostDireto>meuUsuario.buscarPostagensDiretasEnviadas().size()||indexPostDireto<1) {
										System.out.print("O indice inserido nao corresponde as suas postagens diretas enviadas (1-"+meuUsuario.buscarPostagensDiretasEnviadas().size()+"): ");
										indexPostDireto = sc.nextInt();
										sc.nextLine();
									}
									UI.limparConsole();
									System.out.println(meuUsuario.buscarPostagensDiretasEnviadas().get(indexPostDireto-1));
									System.out.print("\nInsira o novo conteudo da postagem direta: ");
									String novoTextoPostagemDireta = sc.nextLine();
									while(novoTextoPostagemDireta.isEmpty()) {
										System.out.print("\nA postagem direta precisa ter pelo menos um caractere: ");
										novoTextoPostagemDireta = sc.nextLine();
									}
									meuUsuario.buscarPostagensDiretasEnviadas().get(indexPostDireto-1).editarPostagem(novoTextoPostagemDireta);
									System.out.println("\nSua postagem direta foi editada com sucesso: ");
									System.out.println(meuUsuario.buscarPostagensDiretasEnviadas().get(indexPostDireto-1));
		
									System.out.println("\nPressione enter para voltar ao menu anterior.");
									sc.nextLine();
									UI.limparConsole();
								}
								
							}else if(opcaoMenuPostagemDireta==2) {
								if(meuUsuario.buscarPostagensDiretasEnviadas()==null) {
									System.out.println("\nApenas postagens diretas enviadas podem ser excluidas.\n\nVoce ainda nao enviou postagens diretas.");
									System.out.println("\nPressione enter para voltar ao menu anterior.");
									sc.nextLine();
									UI.limparConsole();
								}else {
									System.out.println("Suas postagens diretas enviadas:\n\n" + meuUsuario.buscarPostagensDiretasEnviadas());
									System.out.print("\n\nVoce tem " + meuUsuario.buscarPostagensDiretasEnviadas().size() + " postagens diretas enviadas, qual delas deseja excluir(numero/indice)? ");
									int indexPostDireto = sc.nextInt();
									sc.nextLine();
									while(indexPostDireto>meuUsuario.buscarPostagensDiretasEnviadas().size()||indexPostDireto<1) {
										System.out.print("O indice inserido nao corresponde as suas postagens diretas enviadas (1-"+meuUsuario.buscarPostagensDiretasEnviadas().size()+"): ");
										indexPostDireto = sc.nextInt();
										sc.nextLine();
									}
									UI.limparConsole();
									meuUsuario.buscarPostagensDiretasEnviadas().get(indexPostDireto-1).deletarPostagemDireta(meuUsuario.buscarPostagensDiretasEnviadas().get(indexPostDireto-1), meuUsuario);
									System.out.println("\nSua postagem direta enviada " +indexPostDireto + " foi excluida com sucesso!");
									
									System.out.println("\nPressione enter para voltar ao menu anterior.");
									sc.nextLine();
									UI.limparConsole();
								}
							}else {
								continue;
							}
						}
					}else if(opcaoMenuPerfil==3) {
						int opcaoMenuSeguindo=-1;
						while(opcaoMenuSeguindo!=9) {
							if(meuUsuario.getSeguindo().size()==0) {
								System.out.println("\nVoce ainda nao segue nenhum usuario.");
							}else {
								System.out.println("Usuarios que voce segue:\n\n " + meuUsuario.getSeguindo()); 
							}
							UI.imprimirMenuSeguindo();
							opcaoMenuSeguindo = sc.nextInt();
							sc.nextLine();
							UI.limparConsole();
							if(opcaoMenuSeguindo==0) {
								System.out.println("Usuarios recomendados:\n\n" + dados.listarIdUsuariosSeguir(meuUsuario));
								System.out.print("\nID do usuario que deseja seguir: ");
								String idSeguir = sc.nextLine();
								if(dados.buscarUsuario(idSeguir)!= null) {
									meuUsuario.seguirUsuario(dados.buscarUsuario(idSeguir));
									System.out.println("\nSeguindo " + dados.buscarUsuario(idSeguir).getNome() + " (" + idSeguir + ").");
								}else {
									System.out.println("\nO ID digitado nao corresponde a um usuario existente.");
								}
								System.out.println("\nPressione enter para voltar ao menu anterior.");
								sc.nextLine();
								UI.limparConsole();
							}else if(opcaoMenuSeguindo == 1) {
								if(meuUsuario.getSeguindo().size()==0) {
									System.out.println("\nVoce ainda nao segue nenhum usuario.");
								}else {
									System.out.println("Usuarios que voce segue:\n\n " + meuUsuario.getSeguindo());
									System.out.print("\nID do usuario que deseja parar de seguir: ");
									String idSeguir = sc.nextLine();
									if(meuUsuario.getSeguindo().contains(dados.buscarUsuario(idSeguir))) {
										meuUsuario.pararDeSeguirUsuario(dados.buscarUsuario(idSeguir));
										System.out.println("\nVoce deixou de seguir " + dados.buscarUsuario(idSeguir).getNome() + " (" + idSeguir + ").");
									}else {
										System.out.println("\nVoce nao segue nenhum usuario com esse ID.");
									}
								}	
								System.out.println("\nPressione enter para voltar ao menu anterior.");
								sc.nextLine();
								UI.limparConsole();
							}else {
								continue;
							}
						}
					}else if(opcaoMenuPerfil==4) {
						if(meuUsuario.getSeguidores().size()==0) {
							System.out.println("\nVoce ainda nao tem seguidores.");
						}else {						
							System.out.println("Seus seguidores:\n\n " + meuUsuario.getSeguidores());
						}
						System.out.println("\nPressione enter para voltar ao menu anterior.");
						sc.nextLine();
						UI.limparConsole();				
					}else if(opcaoMenuPerfil==5) {
						System.out.println("+\n"+meuUsuario+"\n");
						System.out.print("\nA exclusao do perfil e irreversivel, deseja continuar?(s/n) ");
						char resposta = sc.next().charAt(0);
						while(resposta!='s'&&resposta!='n') {
							System.out.print("Deseja excluir o perfil?(s/n) ");
							resposta = sc.next().charAt(0);
						}
						if(resposta=='s') {
							meuUsuario.deletarUsuario(dados);
							break;
						}else{
							UI.limparConsole();
						}
					}else {
						continue;
					}
				}
			}else if(opcaoMenuInicial==1) {
				System.out.print("Digite um termo para buscar entre os ID's dos usuarios ou apenas @ para ver a lista de todos os usuarios: ");
				String idBusca = sc.nextLine();
				if(dados.buscarUsuarios(idBusca)==null) {
					System.out.println("\nNao existem usuarios correspondentes.");
				}else {
					System.out.println(dados.buscarUsuarios(idBusca));
				}
				System.out.println("\nPressione enter para voltar ao menu anterior.");
				sc.nextLine();
				UI.limparConsole();
			}else if(opcaoMenuInicial==2) {
				System.out.print("Digite um termo para buscar entre todas as postagens publicas: ");
				String postBusca = sc.nextLine();
				if(dados.buscarPostagens(postBusca)==null) {
					System.out.println("\nNao existem postagens publicas correspondentes");
				}else {
					System.out.println(dados.buscarPostagens(postBusca));
				}
				System.out.println("\nPressione enter para voltar ao menu anterior.");
				sc.nextLine();
				UI.limparConsole();
			}else if(opcaoMenuInicial==3) {
				if(meuUsuario.listarPostagensSeguindo().size()==0) {
					if(meuUsuario.getSeguindo().size()==0) {
						System.out.println("\nVoce ainda nao segue ninguem, siga outros usuarios para ver suas postagens publicas.");
					}else {
						System.out.println("\nOs usuarios que voce segue ainda nao fizeram postagens publicas.");
					}
				}else {
					System.out.println("Sua TIMELINE: "+meuUsuario.listarPostagensSeguindo());
				}
				System.out.println("\nPressione enter para voltar ao menu anterior.");
				sc.nextLine();
				UI.limparConsole();
			}else if(opcaoMenuInicial==4) {
				System.out.println("Usuarios recomendados:\n\n" + dados.listarIdUsuariosSeguir(meuUsuario));
				System.out.print("\nID do usuario que deseja seguir:");
				String idSeguir = sc.nextLine();
				if(dados.buscarUsuario(idSeguir)!= null) {
					meuUsuario.seguirUsuario(dados.buscarUsuario(idSeguir));
					System.out.println("\nSeguindo " + dados.buscarUsuario(idSeguir).getNome() + "(" + idSeguir + ").");
				}else {
					System.out.println("\nO ID digitado nao corresponde a um usuario existente.");
				}
				System.out.println("\nPressione enter para voltar ao menu anterior.");
				sc.nextLine();
				UI.limparConsole();
			}else if(opcaoMenuInicial==5) {
				if(meuUsuario.getSeguindo().size()==0) {
					System.out.println("\nVoce nao segue ninguem.");
				}else {
					System.out.println("Usuarios que voce segue:\n\n " + meuUsuario.getSeguindo());
					System.out.print("\nID do usuario que deseja parar de seguir: ");
					String idSeguir = sc.nextLine();
					if(meuUsuario.getSeguindo().contains(dados.buscarUsuario(idSeguir))) {
						meuUsuario.pararDeSeguirUsuario(dados.buscarUsuario(idSeguir));
						System.out.println("\nVoce deixou de seguir " + dados.buscarUsuario(idSeguir).getNome() + " (" + idSeguir + ").");
					}else {
						System.out.println("\nVoce nao segue nenhum usuario com esse ID.");
					}
				}	
				System.out.println("\nPressione enter para voltar ao menu anterior.");
				sc.nextLine();
				UI.limparConsole();
			}else if(opcaoMenuInicial==6) {
				System.out.println("Usuarios recomendados:\n\n"+dados.listarIdUsuariosBusca(meuUsuario));
				System.out.print("\nDigite o ID do usuario para ver seu perfil: ");
				String busca = sc.nextLine();
				if(dados.buscarUsuario(busca)==null) {
					System.out.println("\nO ID digitado nao corresponde a nenhum usuario existente.");
					System.out.println("\nPressione enter para voltar ao menu anterior.");
					sc.nextLine();
					UI.limparConsole();
				}else {
					int opcaoMenuUsuario=-1;
					while(opcaoMenuUsuario!=9) {
						UI.limparConsole();
						System.out.println(dados.buscarUsuario(busca));
						if(dados.buscarUsuario(busca).getPostagensPublicas().size()==0) {
							System.out.println("\n\n" + dados.buscarUsuario(busca).getIdDoUsuario()+ " ainda nao tem postagens publicas para serem exibidas.");
						}else {
							System.out.println("\n\nPosts publicos de " + dados.buscarUsuario(busca).getIdDoUsuario() +": \n");
							System.out.println(dados.buscarUsuario(busca).getPostagensPublicas());
						}
						
						if(dados.buscarUsuario(busca).getSeguidores().contains(meuUsuario)) {
							UI.imprimirMenuUsuarioQueSigo();
							opcaoMenuUsuario = sc.nextInt();
							sc.nextLine();
							UI.limparConsole();
							if(opcaoMenuUsuario==0) {
								meuUsuario.pararDeSeguirUsuario(dados.buscarUsuario(busca));
								System.out.println("\nVoce deixou de seguir " + dados.buscarUsuario(busca).getNome() + "(" + dados.buscarUsuario(busca).getIdDoUsuario() + ").");
								System.out.println("\nPressione enter para voltar ao menu anterior.");
								sc.nextLine();
							}else if(opcaoMenuUsuario==1) {
								if(dados.buscarUsuario(busca).getSeguidores().size()==0) {
									System.out.println("\n"+dados.buscarUsuario(busca).getIdDoUsuario()+" nao tem seguidores.");
	
								}else {
									System.out.println("Usuarios que seguem "+ dados.buscarUsuario(busca).getIdDoUsuario()+":\n\n"+dados.buscarUsuario(busca).getSeguidores());
								}
								System.out.println("\nPressione enter para voltar ao menu anterior.");
								sc.nextLine();
							}else if(opcaoMenuUsuario==2) {
								if(dados.buscarUsuario(busca).getSeguindo().size()==0) {
									System.out.println(dados.buscarUsuario(busca).getIdDoUsuario()+" nao segue ninguem.");
								}else {
									System.out.println("Usuarios que "+ dados.buscarUsuario(busca).getIdDoUsuario()+" segue:\n\n"+ dados.buscarUsuario(busca).getSeguindo());
								}
								System.out.println("\nPressione enter para voltar ao menu anterior.");
								sc.nextLine();
							}
							UI.limparConsole();
						}else {
							UI.imprimirMenuUsuarioNaoSigo();
							opcaoMenuUsuario = sc.nextInt();
							sc.nextLine();
							UI.limparConsole();
							if(opcaoMenuUsuario==0) {
								meuUsuario.seguirUsuario(dados.buscarUsuario(busca));
								System.out.println("\nSeguindo " + dados.buscarUsuario(busca).getNome() + "(" + dados.buscarUsuario(busca).getIdDoUsuario() + ").");
								System.out.println("\nPressione enter para voltar ao menu anterior.");
								sc.nextLine();
							}else if(opcaoMenuUsuario==1) {
								if(dados.buscarUsuario(busca).getSeguidores().size()==0) {
									System.out.println("\n"+dados.buscarUsuario(busca).getIdDoUsuario()+" nao tem seguidores.");
	
								}else {
									System.out.println("Usuarios que seguem "+ dados.buscarUsuario(busca).getIdDoUsuario()+":\n\n"+dados.buscarUsuario(busca).getSeguidores());
								}
								System.out.println("\nPressione enter para voltar ao menu anterior.");
								sc.nextLine();
							}else if(opcaoMenuUsuario==2) {
								if(dados.buscarUsuario(busca).getSeguindo().size()==0) {
									System.out.println(dados.buscarUsuario(busca).getIdDoUsuario()+" nao segue ninguem.");
								}else {
									System.out.println("Usuarios que "+ dados.buscarUsuario(busca).getIdDoUsuario()+" segue:\n\n"+ dados.buscarUsuario(busca).getSeguindo());
								}
								System.out.println("\nPressione enter para voltar ao menu anterior.");
								sc.nextLine();
							}
							
							UI.limparConsole();
						}	
					}
				}
			}else {
				continue;
			}
		}
		System.out.println("\nSeu perfil foi excluido com sucesso. Obrigado por usar nosso aplicativo!\n\n");
	}
}