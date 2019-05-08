package it.polito.tdp.dizionariograph.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;

public class WordDAO {

	/*
	 * Ritorna tutte le parole di una data lunghezza
	 */
	public List<String> getAllWordsFixedLength(int length) {

		String sql = "SELECT nome FROM parola WHERE LENGTH(nome) = ?;";
		List<String> parole = new ArrayList<String>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, length);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				parole.add(res.getString("nome"));
				
			}
			conn.close();
			return parole;

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Error Connection Database");
		}
	}
	
	public List<String> getAllWorsConnected(String parola, Graph<String,DefaultEdge> grafo){
		
		String sql = "SELECT nome " + 
				"FROM parola " + 
				"WHERE LENGTH(nome)= ? AND nome LIKE ? ";
		List<String> parole = new ArrayList<String>();

		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, parola.length());
			
			for(int i=0;i<parola.length();i++) {
				String x = new String();
				x = parola.replace(parola.charAt(i), '_');
				st.setString(2, x);
				ResultSet res = st.executeQuery();

				while (res.next()) {
					if(!res.getString("nome").equals(parola)) {
						parole.add(res.getString("nome"));
						if(!grafo.containsEdge(parola, res.getString("nome"))) {
							grafo.addEdge(parola, res.getString("nome"));
						}
					}
				}
			}
			conn.close();
			return parole;

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Error Connection Database");
		}
		
	}

}
