package application;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.shape.Polygon;

public class CE_E1 extends ControladorPrograma implements Initializable{
	
	@FXML
	private Button botonSuma;
	@FXML
	private Button botonResta;
	@FXML
	private Button botonMultiplicacion;
	@FXML
	private Button botonDivision;
	@FXML
	private Polygon poligonoVolver;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		poligonoVolver.setOnMouseClicked(event ->{
			irMenuPrincipal(event);
		});;
		
		poligonoVolver.setOnMouseEntered(event ->{
			poligonoVolver.setScaleX(1.1);
			poligonoVolver.setScaleY(1.1);
		});
		
		poligonoVolver.setOnMouseExited(event ->{
			poligonoVolver.setScaleX(1);
			poligonoVolver.setScaleY(1);
		});
		
		botonSuma.setOnMouseEntered(event ->{
			botonSuma.setScaleX(1.1);
			botonSuma.setScaleY(1.1);
		});
		
		botonSuma.setOnMouseExited(event ->{
			botonSuma.setScaleX(1);
			botonSuma.setScaleY(1);
		});
		
		botonSuma.setOnAction(event ->{
			irESuma(event);
		});
		
		botonResta.setOnMouseEntered(event ->{
			botonResta.setScaleX(1.1);
			botonResta.setScaleY(1.1);
		});
		
		botonResta.setOnMouseExited(event ->{
			botonResta.setScaleX(1);
			botonResta.setScaleY(1);
		});
		
		botonResta.setOnAction(event ->{
			irEResta(event);
		});
		
		botonMultiplicacion.setOnMouseEntered(event ->{
			botonMultiplicacion.setScaleX(1.1);
			botonMultiplicacion.setScaleY(1.1);
		});
		
		botonMultiplicacion.setOnMouseExited(event ->{
			botonMultiplicacion.setScaleX(1);
			botonMultiplicacion.setScaleY(1);
		});
		
		botonMultiplicacion.setOnAction(event ->{
			irEMultiplicacion(event);
		});
		
		botonDivision.setOnMouseEntered(event ->{
			botonDivision.setScaleX(1.1);
			botonDivision.setScaleY(1.1);
		});
		
		botonDivision.setOnMouseExited(event ->{
			botonDivision.setScaleX(1);
			botonDivision.setScaleY(1);
		});
		
		botonDivision.setOnAction(event ->{
			irEDivision(event);
		});
		
	}
	
	private void irESuma(Event event) {
		nombreSigEscena = "E2 - Suma.fxml";
		FXMLLoader loader = intercambiarEscena(event);
		CE_E2Suma cE_E2Suma = loader.getController();
		cE_E2Suma.setJugador(jugador);
	}
	
	private void irEResta(Event event) {
		nombreSigEscena = "E2 - Resta.fxml";
		FXMLLoader loader = intercambiarEscena(event);
		CE_E2Resta cE_E2Resta = loader.getController();
		cE_E2Resta.setJugador(jugador);
	}
	
	private void irEMultiplicacion(Event event) {
		nombreSigEscena = "E2 - Multiplicación.fxml";
		FXMLLoader loader = intercambiarEscena(event);
		CE_E2Multiplicacion cE_E2Multiplicacion = loader.getController();
		cE_E2Multiplicacion.setJugador(jugador);
	}
	
	private void irEDivision(Event event) {
		nombreSigEscena = "E2 - División.fxml";
		FXMLLoader loader = intercambiarEscena(event);
		CE_E2Division cE_E2Division = loader.getController();
		cE_E2Division.setJugador(jugador);
	}

}
