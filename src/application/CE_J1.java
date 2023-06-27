package application;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class CE_J1 extends ControladorPrograma {

	//Creacion de las variables que estan en la escena
	@FXML
	private Label numeroEjercicio;
	@FXML
	private Label labelPredicado;
	
	private int numE;
	private boolean relacionO;
	
	public void setJuego(Event event) {
		numE = juego.getNumeroEjercicio();
		relacionO = juego.getRelacionOrden();
		
		if(relacionO) {
			labelPredicado.setText("Consigue un numero que sea el mas cercano y mayor a");
		}else {
			labelPredicado.setText("Consigue un numero que sea el mas cercano y menor a");
		}
		
		numeroEjercicio.setText(Integer.toString(numE));
	}
	
	public int getNumeroE() {
		return numE;
	}
	
	public boolean getRelacionO() {
		return relacionO;
	}

	
}
