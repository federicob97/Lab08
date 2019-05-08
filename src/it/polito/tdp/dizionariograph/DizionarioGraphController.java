package it.polito.tdp.dizionariograph;

import java.net.URL;
import java.util.*;
import java.util.ResourceBundle;

import it.polito.tdp.dizionariograph.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class DizionarioGraphController {
	
	private Model model;
	
	public void setModel(Model model) {
		this.model=model;
	}

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtLunghezza;

    @FXML
    private TextField txtParola;

    @FXML
    private TextArea txtResult;

    @FXML
    void doCercaVicini(ActionEvent event) {
    	String parola = txtParola.getText();
    	List<String> vicini = new ArrayList<String>(model.displayNeighbours(parola));
    	for(String s : vicini)
    		txtResult.appendText(s+"\n");
    	
    }

    @FXML
    void doGeneraGrafo(ActionEvent event) {
    	int lunghezza = Integer.parseInt(txtLunghezza.getText());
    	model.createGraph(lunghezza);
    	txtResult.appendText(model.getGrafo().toString());
    }

    @FXML
    void doGradoMax(ActionEvent event) {
    	int lunghezza = Integer.parseInt(txtLunghezza.getText());
    	String res[] = model.getGradoMassimo(lunghezza).split(";");
    	txtResult.appendText("Il vertice di grado massimo ha grado "+res[1]+" ed è "+res[0]);
    }

    @FXML
    void doReset(ActionEvent event) {
    	txtResult.clear();
    	txtParola.clear();
    	txtLunghezza.clear();
    }

    @FXML
    void initialize() {
        assert txtLunghezza != null : "fx:id=\"txtLunghezza\" was not injected: check your FXML file 'DizionarioGraph.fxml'.";
        assert txtParola != null : "fx:id=\"txtParola\" was not injected: check your FXML file 'DizionarioGraph.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'DizionarioGraph.fxml'.";

    }
}
