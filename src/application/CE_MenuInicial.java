package application;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Polygon;

public class CE_MenuInicial extends ControladorPrograma implements Initializable {
	
	//Creacion las variables de los elementos en la escena
	@FXML
	private TextField campoTexto;
	@FXML
	private Pane paneTexto;
	@FXML
	private Pane paneFondo;
	@FXML
	private Polygon poligonoBoton;
	@FXML
	private Label limiteCaracteres;
	
	//Metodos de accion del usuario
	@Override
	public void initialize(URL arg0, ResourceBundle arg1){
			
		paneTexto.setOnMouseClicked (event ->{
			campoTexto.setDisable(false);
			limiteCaracteres.setVisible(false);
		});
		
		paneFondo.setOnMouseClicked (event ->{
			if(!campoTexto.isDisable()) {
			campoTexto.setDisable(true);
			}
		});
		
		campoTexto.setOnKeyPressed(event ->{
			if(campoTexto.getText().length()>13) {
				campoTexto.setDisable(true);
				limiteCaracteres.setVisible(true);
			}
			if(event.getCode()==KeyCode.ENTER) {
				inicializarCambioEscena(event);
			}
		});

		poligonoBoton.setOnMouseClicked(event ->{
			inicializarCambioEscena(event);
		});
		
	}
	
	private void inicializarCambioEscena(Event event) {
		jugador.setNombre(campoTexto.getText());
		nombreSigEscena = "2 - Menu Principal.fxml";
		FXMLLoader loader = intercambiarEscena(event);
		CE_MenuPrincipal cE_MenuPrincipal = loader.getController();
		cE_MenuPrincipal.setJugador(jugador);
		cE_MenuPrincipal.setNombreYPuntuacion();
	}
	
}