package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;

public class CE_J2 extends ControladorPrograma implements Initializable{
	
	//Creacion de las variables que estan en la escena
	@FXML
	private Label labelNum1;
	@FXML
	private Label labelOperacion;
	@FXML
	private Label labelNum2;
	@FXML
	private Label labelNum3;
	@FXML
	private Label labelNumE;
	@FXML
	private Label labelOrden;
	@FXML
	private ChoiceBox<String> seleccionOperacion;
	@FXML
	private Button botonCS;
	@FXML
	private Button botonMP;
	
	private String[] operaciones = {"+","-","x","÷"};
	private int numE;
	private boolean relacionO;
	private int operacion;
	private int[] numerosCondicion; 
	
	public void iniciarJuego(int numE, boolean relacionO) {
		
		this.numE = numE;
		this.relacionO = relacionO;
		operacion = juego.getOperacion();
		numerosCondicion = juego.getNumerosCondicion(numE, operacion, relacionO);
		
		labelNum1.setText("("+Integer.toString(numerosCondicion[0]));
		labelNum2.setText(Integer.toString(numerosCondicion[1])+")");
		labelNum3.setText(Integer.toString(numerosCondicion[2]));
		labelNumE.setText(Integer.toString(numE));
		
		
		if(relacionO) {
			labelOrden.setText(">");
		}else {
			labelOrden.setText("<");
		}
		
		if(operacion == 1) {
			labelOperacion.setText("+");
		}
		if(operacion == 2) {
			labelOperacion.setText("-");
		}
		if(operacion == 3) {
			labelOperacion.setText("x");
		}
		if(operacion == 4) {
			labelOperacion.setText("÷");
		}	
	}
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		seleccionOperacion.getItems().addAll(operaciones);
		
		seleccionOperacion.setOnMouseEntered(event ->{
			seleccionOperacion.setScaleX(1.1);
			seleccionOperacion.setScaleY(1.1);
		});
		seleccionOperacion.setOnMouseExited(event ->{
			seleccionOperacion.setScaleX(1);
			seleccionOperacion.setScaleY(1);
		});
		
		botonCS.setOnMouseEntered(event ->{
			botonCS.setScaleX(1.1);
			botonCS.setScaleY(1.1);
		});
		
		botonCS.setOnMouseExited(event ->{
			botonCS.setScaleX(1);
			botonCS.setScaleY(1);
		});
		
		botonCS.setOnAction(event ->{
			confirmarRespuesta(event,operacion,numerosCondicion,numE,relacionO);
			
		});
		
		botonMP.setOnMouseEntered(event ->{
			botonMP.setScaleX(1.1);
			botonMP.setScaleY(1.1);
		});
		
		botonMP.setOnMouseExited(event ->{
			botonMP.setScaleX(1);
			botonMP.setScaleY(1);
		});
		
		botonMP.setOnAction(event ->{
			irMenuPrincipal(event);
		});
		
	}
	
	private void confirmarRespuesta(Event event, int operacion, int[] numerosCondicion, int numE, boolean relacionO ) {
		String opSeleccionada = seleccionOperacion.getValue();
		Object[] condiciones = juego.verificarRespuesta(operacion,numerosCondicion,numE,relacionO,opSeleccionada);
		
		double num1 = numerosCondicion[0];
		double num2 = numerosCondicion[1];
		double num3 = numerosCondicion[2];
		double numeroObtenido = 0;
		
		if(operacion==1) {
			
			if(opSeleccionada == "+") {
				numeroObtenido = (num1+num2)+num3;
			}
			
			if(opSeleccionada == "-") {
				numeroObtenido = (num1+num2)-num3;
			}
			
			if(opSeleccionada == "x") {
				numeroObtenido = (num1+num2)*num3;
			}
			
			if(opSeleccionada == "÷") {
				numeroObtenido = (num1+num2)/num3;
			}
			
		}
		
		if(operacion==2) {
			
			if(opSeleccionada == "+") {
				numeroObtenido = (num1-num2)+num3;
			}
			
			if(opSeleccionada == "-") {
				numeroObtenido = (num1-num2)-num3;
			}
			
			if(opSeleccionada == "x") {
				numeroObtenido = (num1-num2)*num3;
			}
			
			if(opSeleccionada == "÷") {
				numeroObtenido = (num1-num2)/num3;
			}
			
		}
		
		if(operacion==3) {
			
			if(opSeleccionada == "+") {
				numeroObtenido = (num1*num2)+num3;
			}
			
			if(opSeleccionada == "-") {
				numeroObtenido = (num1*num2)-num3;
			}
			
			if(opSeleccionada == "x") {
				numeroObtenido = (num1*num2)*num3;
			}
			
			if(opSeleccionada == "÷") {
				numeroObtenido = (num1*num2)/num3;
			}
			
		}
		
		if(operacion==4) {
			
			if(opSeleccionada == "+") {
				numeroObtenido = (num1/num2)+num3;
			}
			
			if(opSeleccionada == "-") {
				numeroObtenido = (num1/num2)-num3;
			}
			
			if(opSeleccionada == "x") {
				numeroObtenido = (num1/num2)*num3;
			}
			
			if(opSeleccionada == "÷") {
				numeroObtenido = (num1/num2)/num3;
			}
			
		}
		
		String opCorrecta = (String) condiciones[3]+"="+String.format("%.1f",condiciones[2]);
		
		if((boolean) condiciones[0]) {
			if((boolean) condiciones[1]) {
				nombreSigEscena = "J3 - JuegoGana.fxml";
				FXMLLoader loader = intercambiarEscena(event);
				CE_J3Gana cE_J3Gana = loader.getController();
				cE_J3Gana.setJugador(jugador);
				cE_J3Gana.setLabels(numeroObtenido,relacionO,numE);
			}else {
				nombreSigEscena = "J3 - JuegoCasiGana.fxml";
				FXMLLoader loader = intercambiarEscena(event);
				CE_J3CasiGana cE_J3CasiGana = loader.getController();
				cE_J3CasiGana.setJugador(jugador);
				cE_J3CasiGana.setLabels(numeroObtenido,relacionO,numE,opCorrecta);
			}
		}else {
			nombreSigEscena = "J3 - JuegoPierde.fxml";
			FXMLLoader loader = intercambiarEscena(event);
			CE_J3Pierde cE_J3Pierde = loader.getController();
			cE_J3Pierde.setJugador(jugador);
			cE_J3Pierde.setLabels(numeroObtenido,relacionO,numE,opCorrecta);
		}
		
	}
	
}
