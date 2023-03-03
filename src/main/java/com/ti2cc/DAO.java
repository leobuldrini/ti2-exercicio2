package com.ti2cc;

import java.sql.*;

public class DAO {
	
	private Connection conexao;
	
	public DAO() {
		conexao = null;
	}
	
	public boolean connect () {
		String driverName = "org.postgresql.Driver";                    
		String serverName = "localhost";
		String mydatabase = "teste";
		int porta = 5432;
		String url = "jdbc:postgresql://" + serverName + ":" + porta +"/" + mydatabase;
		String username = "ti2cc";
		String password = "ti@cc";
		boolean status = false;
		
		try {
			Class.forName(driverName);
			conexao = DriverManager.getConnection(url, username, password);
			status = (conexao == null);
			System.out.println("Conexão efetuada com o postgres!");
		} catch (ClassNotFoundException e) { 
			System.err.println("Conexão NÃO efetuada com o postgres -- Driver não encontrado -- " + e.getMessage());
		} catch (SQLException e) {
			System.err.println("Conexão NÃO efetuada com o postgres -- " + e.getMessage());
		}

		return status;
	}
	
	public boolean close() {
		boolean status = false;
		
		try {
			conexao.close();
			status = true;
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return status;
	}
	
	public boolean inserirGame(Game game) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			st.executeUpdate("INSERT INTO games (id, nome, dataLancamento, genero) "
					       + "VALUES ("+game.getId()+ ", '" + game.getNome() + "', '"  
					       + game.getDataLancamento() + "', '" + game.getGenero() + "');");
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
	
	public boolean atualizarGame(Game game) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			String sql = "UPDATE games SET nome = '" + game.getNome() + "', dataLancamento = '"  
				       + game.getDataLancamento() + "', genero = '" + game.getGenero() + "'"
					   + " WHERE id = " + game.getId();
			st.executeUpdate(sql);
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
	
	public boolean excluirGame(int id) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			st.executeUpdate("DELETE FROM games WHERE id = " + id);
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
	
	public Game[] getGames() {
		Game[] games = null;
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = st.executeQuery("SELECT * FROM games");		
	         if(rs.next()){
	             rs.last();
	             games = new Game[rs.getRow()];
	             rs.beforeFirst();

	             for(int i = 0; rs.next(); i++) {
	                games[i] = new Game(rs.getInt("id"), rs.getString("nome"), 
	                		                  rs.getString("dataLancamento"), rs.getString("genero"));
	             }
	          }
	          st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return games;
	}
	

	
	public int getLastId() {
		int id = -1;
		Game game;
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = st.executeQuery("SELECT * FROM usuario ORDER BY id DESC LIMIT 1");
			if(rs.next()){
	             rs.last();
	             game = new Game();
	             game.setId(rs.getInt("id"));
	             id = game.getId();
	          }
	          st.close();
		}catch(Exception e) {
			System.err.println(e.getMessage());
		}
		
		return id;
	}

}
