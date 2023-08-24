package app;

import java.util.List;
import java.util.Scanner;
import dao.DAO;
import dao.UsuarioDAO;
import model.Usuario;

public class Aplicacao {
	
	public static void main(String[] args) throws Exception {
		
		Scanner scanner = new Scanner(System.in);
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		
		int escolha = 0;
		do {
			System.out.println("\n\n==== Menu ====");
			System.out.println("1. Listar usuários");
			System.out.println("2. Inserir usuário");
			System.out.println("3. Excluir usuário");
			System.out.println("4. Atualizar senha");
			System.out.println("5. Sair");
			System.out.print("Escolha uma opção: ");
			
			escolha = scanner.nextInt();
			
			switch (escolha) {
				case 1:
					System.out.println("\n\n==== Mostrar usuários === ");
					List<Usuario> usuarios = usuarioDAO.getOrderByCodigo();
					for (Usuario u: usuarios) {
						System.out.println(u.toString());
					}
					break;
					
				case 2:
					System.out.println("\n\n==== Inserir usuário === ");
				    System.out.print("Digite o código do usuário: ");
				    int codigo = scanner.nextInt();
				    
				    scanner.nextLine();
				    
				    System.out.print("Digite o login do usuário: ");
				    String login = scanner.nextLine();
				    
				    System.out.print("Digite a senha do usuário: ");
				    String senha = scanner.nextLine();
				    
				    System.out.print("Digite o sexo do usuário (M/F): ");
				    char sexo = scanner.next().charAt(0);
				    
				    Usuario novoUsuario = new Usuario(codigo, login, senha, sexo);
				    if(usuarioDAO.insert(novoUsuario)) {
				        System.out.println("Inserção com sucesso -> " + novoUsuario.toString());
				    } else {
				        System.out.println("Erro ao inserir usuário.");
				    }
				    break;
					
				case 3:
					System.out.print("Digite o código do usuário a ser excluído: ");
					int codigoExclusao = scanner.nextInt();
					usuarioDAO.delete(codigoExclusao);
					System.out.println("Usuário excluído com sucesso.");
					break;
					
				case 4:
				    System.out.println("\n\n==== Atualizar senha === ");
				    System.out.print("Digite o código do usuário para atualizar a senha: ");
				    int codigoAtualizacao = scanner.nextInt();
				    
				    scanner.nextLine();
				    
				    Usuario usuarioParaAtualizar = usuarioDAO.get(codigoAtualizacao);
				    
				    if (usuarioParaAtualizar != null) {
				        System.out.print("Digite a nova senha do usuário: ");
				        String novaSenha = scanner.nextLine();
				        
				        usuarioParaAtualizar.setSenha(DAO.toMD5(novaSenha));
				        
				        if (usuarioDAO.update(usuarioParaAtualizar)) {
				            System.out.println("Senha atualizada com sucesso para o usuário de código " + codigoAtualizacao);
				        } else {
				            System.out.println("Erro ao atualizar a senha.");
				        }
				    } else {
				        System.out.println("Usuário não encontrado.");
				    }
				    break;

					
				case 5:
					System.out.println("Saindo...");
					break;
					
				default:
					System.out.println("Opção inválida. Escolha novamente.");
					break;
			}
			
		} while (escolha != 5);
		
		scanner.close();
	}
}
