package it.polito.tdp.dizionariograph.model;

import java.util.ArrayList;
import java.util.List;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import it.polito.tdp.dizionariograph.db.WordDAO;

public class Model {

	private Graph<String,DefaultEdge> grafo;
	private WordDAO dao;
	private List<String> parole;
	
	public Model() {
		dao = new WordDAO();
	}
	public void createGraph(int numeroLettere) {

		grafo = new SimpleGraph<String,DefaultEdge>(DefaultEdge.class);
		parole = dao.getAllWordsFixedLength(numeroLettere);
		
		//popolare vertici grafo
		Graphs.addAllVertices(this.grafo, this.parole);
		
		//popolare archi
		for(String v : grafo.vertexSet()) {
			for(String target : TrovaArchi.getVicini(v, parole, numeroLettere))
				grafo.addEdge(v, target);
		}
		
		
	}

	public List<String> displayNeighbours(String parolaInserita) {

		ArrayList<String> vicini = new ArrayList<String>(TrovaArchi.getVicini(parolaInserita, parole, parolaInserita.length()));
		return vicini;
	}

	public Graph<String,DefaultEdge> getGrafo() {
		return grafo;
	}
	public String getGradoMassimo(int numeroLettere) {
		String massimo = new String();
		int grado = 0;
		for(String v : grafo.vertexSet()) {
			if(TrovaArchi.getVicini(v, parole, numeroLettere).size()>grado) {
				grado = TrovaArchi.getVicini(v, parole, numeroLettere).size();
				massimo = v;
			}
		}
		return massimo+";"+grado;
	}
	
}
