package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.Event;
//import java.util.Timer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;


public class CE_MenuPrincipal extends ControladorPrograma implements Initializable{

	@FXML
	private Label labelPuntuacion;
	@FXML
	private Button botonJugar;
	@FXML
	private Button botonInstrucciones;
	@FXML
	private Button botonExplicaciones;
	
	public void setNombreYPuntuacion() {
		labelPuntuacion.setText(jugador.getNombre()+" tu puntuación es "+jugador.getPuntaje());
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		botonJugar.setScaleX(1.1);
		botonJugar.setScaleY(1.1);
		
		botonJugar.setOnKeyPressed(event ->{
			if(event.getCode()==KeyCode.DOWN) {
				if(!botonJugar.isHover()) {
					botonJugar.setScaleX(1);
					botonJugar.setScaleY(1);
				}
				botonInstrucciones.setScaleX(1.1);
				botonInstrucciones.setScaleY(1.1);
			}
			if(event.getCode()==KeyCode.TAB) {
				if(!botonJugar.isHover()) {
					botonJugar.setScaleX(1);
					botonJugar.setScaleY(1);
				}
				botonInstrucciones.setScaleX(1.1);
				botonInstrucciones.setScaleY(1.1);
			}
		});
		
		botonInstrucciones.setOnKeyPressed(event ->{
			if(event.getCode()==KeyCode.DOWN) {
				if(!botonInstrucciones.isHover()) {
					botonInstrucciones.setScaleX(1);
					botonInstrucciones.setScaleY(1);
				}
				botonExplicaciones.setScaleX(1.1);
				botonExplicaciones.setScaleY(1.1);
			}
			if(event.getCode()==KeyCode.TAB) {
				if(!botonInstrucciones.isHover()) {
					botonInstrucciones.setScaleX(1);
					botonInstrucciones.setScaleY(1);
				}
				botonExplicaciones.setScaleX(1.1);
				botonExplicaciones.setScaleY(1.1);
			}
			if(event.getCode()==KeyCode.UP) {
				if(!botonInstrucciones.isHover()) {
					botonInstrucciones.setScaleX(1);
					botonInstrucciones.setScaleY(1);
				}
				botonJugar.setScaleX(1.1);
				botonJugar.setScaleY(1.1);
			}
		});
		
		
		botonExplicaciones.setOnKeyPressed(event ->{
			if(event.getCode()==KeyCode.TAB) {
				if(!botonExplicaciones.isHover()) {
					botonExplicaciones.setScaleX(1);
					botonExplicaciones.setScaleY(1);
				}
				botonJugar.setScaleX(1.1);
				botonJugar.setScaleY(1.1);
			}
			if(event.getCode()==KeyCode.UP) {
				if(!botonExplicaciones.isHover()) {
					botonExplicaciones.setScaleX(1);
					botonExplicaciones.setScaleY(1);
				}
				botonInstrucciones.setScaleX(1.1);
				botonInstrucciones.setScaleY(1.1);
			}		
		});
		
		botonJugar.setOnAction(event ->{
			inicializarCambioEscenaJugar(event);
		});
		botonInstrucciones.setOnAction(event->{
			inicializarEscenaIntrucciones(event);
		});
		
		
		
		botonJugar.setOnMouseEntered(event ->{
			botonJugar.setScaleX(1.1);
			botonJugar.setScaleY(1.1);
		});;
		botonJugar.setOnMouseExited(event ->{
			if(!botonJugar.isFocused()) {
				botonJugar.setScaleX(1);
				botonJugar.setScaleY(1);
			}
		});
		
		botonInstrucciones.setOnMouseEntered(event ->{
			botonInstrucciones.setScaleX(1.1);
			botonInstrucciones.setScaleY(1.1);
		});;
		
		botonInstrucciones.setOnMouseExited(event ->{
			if(!botonInstrucciones.isFocused()) {
				botonInstrucciones.setScaleX(1);
				botonInstrucciones.setScaleY(1);
			}
		});
		
		botonExplicaciones.setOnAction(event ->{
			irMenuExplicaciones(event);
		});
		
		botonExplicaciones.setOnMouseEntered(event ->{
			botonExplicaciones.setScaleX(1.1);
			botonExplicaciones.setScaleY(1.1);
		});;
		botonExplicaciones.setOnMouseExited(event ->{
			if(!botonExplicaciones.isFocused()) {
				botonExplicaciones.setScaleX(1);
				botonExplicaciones.setScaleY(1);
			}
		});
		
		
	}
	
	
	private void inicializarCambioEscenaJugar(Event event) {
		nombreSigEscena = "J1 - Juego.fxml";
		nombreEscenaTransicion = "J2 - Juego.fxml";
		FXMLLoader[] loader = intercambiarEscenaTransicion(event, 1);
		CE_J1 cE_J1 = loader[0].getController();
		CE_J2 cE_J2 = loader[1].getController();
		cE_J1.setJuego(event);
		cE_J2.iniciarJuego(cE_J1.getNumeroE(),cE_J1.getRelacionO());
		cE_J2.setJugador(jugador);
	}
	
	private void inicializarEscenaIntrucciones(Event event) {
		nombreSigEscena = "I1 - Instrucciones.fxml";
		FXMLLoader loader = intercambiarEscena(event);
		CE_I1 cE_I1 = loader.getController();
		cE_I1.setJugador(jugador);
	}
	
}
