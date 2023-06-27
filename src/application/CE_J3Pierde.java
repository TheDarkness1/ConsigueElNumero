package application;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class CE_J3Pierde extends ControladorPrograma implements Initializable {

	@FXML
	private Label labelNR;
	@FXML
	private Label labelRO;
	@FXML
	private Label labelNO;
	@FXML
	private Label labelPuntuacion;
	@FXML
	private Label labelOC;
	@FXML
	private Button botonMenu;
	
	public void setLabels(double numO, boolean relacionO, int numE, String operacionCorrecta) {
		
		labelPuntuacion.setText("Puntuación: "+jugador.getPuntaje());
		
		String numOst = String.format("%.1f",numO);
		labelNR.setText(numOst);
		
		String numEst = Integer.toString(numE);
		labelNO.setText(numEst);
		
		String correccion;
		if(relacionO) {
			labelRO.setText("<");
			correccion = operacionCorrecta+">"+numE;
		}else {
			labelRO.setText(">");
			correccion = operacionCorrecta+"<"+numE;
		}
		labelOC.setText(correccion);
		
	}
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		botonMenu.setOnAction(event ->{
			irMenuPrincipal(event);
		});
		
		botonMenu.setOnMouseEntered(event ->{
			botonMenu.setScaleX(1.1);
			botonMenu.setScaleY(1.1);
		});;
		
		botonMenu.setOnMouseExited(event ->{
			botonMenu.setScaleX(1);
			botonMenu.setScaleY(1);
		});;
		
		
	}

}
