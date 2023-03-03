package com.ti2cc;

import java.util.*;

public class Principal {
	
	public static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		DAO dao = new DAO();
		
		dao.connect();
		
		int option = 0;
		String nome;
		String dataLancamento;
		String genero;
		int id;
		
		System.out.println("Bem vindo ao CRUD de Games! O que deseja fazer?");
		System.out.println("\t1.Listar\n\t2.Inserir\n\t3.Excluir\n\t4.Atualizar\n\t5.Sair");
		
		option = Integer.parseInt(sc.nextLine());
		Game game;
		
		switch(option) {
		case 2:
			System.out.println("Digite o nome do jogo a ser inserido:");
			id = Integer.parseInt(sc.nextLine());
			System.out.println("Digite o nome do jogo a ser inserido:");
			nome = sc.nextLine();
			System.out.println("Digite a data de lançamento do jogo a ser inserido:");
			dataLancamento = sc.nextLine();
			System.out.println("Digite o genero do jogo a ser inserido:");
			genero = sc.nextLine();
			
			game = new Game(id, nome, dataLancamento, genero);
			if(dao.inserirGame(game) == true) {
				System.out.println("Inserção com sucesso -> " + game.toString());
			}
			break;
		case 1:
			System.out.println("==== Mostrar games === ");
			Game[] games = dao.getGames();
			for(int i = 0; i < games.length; i++) {
				System.out.println(games[i].toString());
			}
			break;
		case 3:
			System.out.println("Digite o id do jogo a ser excluido:");
			id = Integer.parseInt(sc.nextLine());
			dao.excluirGame(id);
			break;
		case 4:
			System.out.println("Digite o id do jogo a ser atualizado:");
			id = Integer.parseInt(sc.nextLine());
			System.out.println("Digite o nome do jogo a ser atualizado:");
			nome = sc.nextLine();
			System.out.println("Digite a data de lançamento do jogo a ser atualizado:");
			dataLancamento = sc.nextLine();
			System.out.println("Digite o genero do jogo a ser atualizado:");
			genero = sc.nextLine();
			game = new Game(id, nome, dataLancamento, genero);
			dao.atualizarGame(game);
			break;
		case 5:
			break;
		}
		
		dao.close();
	}

}
