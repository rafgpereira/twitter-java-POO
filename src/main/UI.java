package main;

import java.io.IOException;
import java.util.Scanner;

import negocio.Usuario;

public class UI {
	
	public static void limparConsole() {
        try {
            // Verifica o sistema operacional
            String os = System.getProperty("os.name").toLowerCase();
            
            ProcessBuilder processBuilder;
            if (os.contains("win")) {
                // Para o Windows
                processBuilder = new ProcessBuilder("cmd", "/c", "cls");
            } else {
                // Para sistemas baseados em Unix/Linux/Mac
                processBuilder = new ProcessBuilder("clear");
            }
            // Inicia o processo
            Process process = processBuilder.inheritIO().start();
            
            process.waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
	}
	
	public static Usuario paginaCadastro(Scanner sc) {
		System.out.print("Nome e sobrenome: ");
		String nomeUsuario = sc.nextLine();
		while(nomeUsuario.isEmpty()) {
			System.out.print("O nome precisa ter ao menos um caractere: ");
			nomeUsuario=sc.nextLine();
		}
		System.out.print("\nID do usuario: @");
		String idDoUsuario = "@";
		String idScan = sc.nextLine();

		while(idScan.isEmpty()) {
			System.out.print("O ID precisa ter ao menos um caractere: @");
			idScan =sc.nextLine();
		}
		String primeiraParte = idScan.trim().split("\\s+")[0];
		idDoUsuario+=primeiraParte;
		System.out.print("\nBiografia: ");
		String biografiaUsuario = sc.nextLine();
		while(biografiaUsuario.isEmpty()) {
			System.out.print("A biografia precisa ter ao menos um caractere: ");
			biografiaUsuario=sc.nextLine();
		}
		Usuario meuUsuario = new Usuario(idDoUsuario, nomeUsuario, biografiaUsuario);
		return meuUsuario;
		
	}
	public static void imprimirMenuInicial() {
		System.out.println("\n\n----------------------------MENU INICIAL----------------------------\n");
		System.out.println("0 - Meu perfil");
		System.out.println("1 - Buscar usuarios (filtra entre todos os usuarios)");
		System.out.println("2 - Buscar postagens publicas (filtra entre todas as postagens publicas)");
		System.out.println("3 - Minha timeline (lista postagens publicas de quem sigo)");
		System.out.println("4 - Seguir usuario");
		System.out.println("5 - Parar de seguir usuario");
		System.out.println("6 - Visualizar perfil de um usuario");
		System.out.print("\nInsira uma opcao do menu (numero) para navegar: ");
	}
	public static void imprimirMenuPerfil() {
		System.out.println("\n\n--------MENU MEU PERFIL--------\n");
		System.out.println("0 - Editar meu perfil");
		System.out.println("1 - Listar minhas postagens publicas");
		System.out.println("2 - Listar minhas postagens diretas");
		System.out.println("3 - Listar quem sigo");
		System.out.println("4 - Listar meus seguidores");
		System.out.println("5 - Excluir meu perfil");
		System.out.println("9 - Voltar ao MENU INICIAL");
		System.out.print("\nInsira uma opcao do menu (numero) para navegar: ");
	}
	public static void imprimirMenuPostagensPublicas() {
		System.out.println("\n\n------MENU POSTAGENS PUBLICAS------\n");
		System.out.println("0 - Criar nova postagem publica");
		System.out.println("1 - Editar postagem publica");
		System.out.println("2 - Excluir postagem publica existente");
		System.out.println("9 - Voltar ao MENU MEU PERFIL");
		System.out.print("\nInsira uma opcao do menu (numero) para navegar: ");
	}
	public static void imprimirMenuPostagemDireta() {
		System.out.println("\n\n------MENU POSTAGENS DIRETAS------\n");
		System.out.println("0 - Criar nova postagem direta");
		System.out.println("1 - Editar postagem direta");
		System.out.println("2 - Excluir postagem direta");
		System.out.println("9 - Voltar ao MENU MEU PERFIL");
		System.out.print("\nInsira uma opcao do menu (numero) para navegar: ");
	}
	public static void imprimirMenuSeguindo() {
		System.out.println("\n\n--------MENU SEGUINDO--------\n");
		System.out.println("0 - Seguir usuario");
		System.out.println("1 - Parar de seguir usuario");
		System.out.println("9 - Voltar ao MENU MEU PERFIL");
		System.out.print("\nInsira uma opcao do menu (numero) para navegar: ");
	}
	public static void imprimirMenuUsuarioQueSigo() {
		System.out.println("\n\n--------MENU USUARIO--------\n");
		System.out.println("0 - Parar de seguir esse usuario");
		System.out.println("1 - Listar seguidores desse usuario");
		System.out.println("2 - Listar quem esse usuario segue");
		System.out.println("9 - Voltar ao MENU INICIAL ");
		System.out.print("\nInsira uma opcao do menu (numero) para navegar: ");
	}
	public static void imprimirMenuUsuarioNaoSigo() {
		System.out.println("\n\n--------MENU USUARIO--------\n");
		System.out.println("0 - Seguir esse usuario");
		System.out.println("1 - Listar seguidores desse usuario");
		System.out.println("2 - Listar quem esse usuario segue");
		System.out.println("9 - Voltar ao MENU INICIAL");
		System.out.print("\nInsira uma opcao do menu (numero) para navegar: ");
	}
	

}
