package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.shape.Polygon;

public class CE_E2Suma extends ControladorPrograma implements Initializable{

	@FXML
	private Polygon poligonoVolver;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		poligonoVolver.setOnMouseClicked(event ->{
			irMenuExplicaciones(event);
		});;
		
		poligonoVolver.setOnMouseEntered(event ->{
			poligonoVolver.setScaleX(1.1);
			poligonoVolver.setScaleY(1.1);
		});
		
		poligonoVolver.setOnMouseExited(event ->{
			poligonoVolver.setScaleX(1);
			poligonoVolver.setScaleY(1);
		});
		
	}
	
}
