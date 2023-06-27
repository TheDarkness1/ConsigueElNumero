package application;

import javafx.application.Platform;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.util.Timer;
import java.util.TimerTask;

public class ControladorPrograma {
	
	protected String nombreSigEscena;
	protected String nombreEscenaTransicion;
	protected Jugador jugador = new Jugador();
	private Timer timer = new Timer();
	
	Juego juego = new Juego();
	
	
	private String css = this.getClass().getResource("application.css").toExternalForm();

	public void setNombreEscena(String nombreSigEscena) {
		this.nombreSigEscena = nombreSigEscena;
	}
	
	public void primeraEscena(Stage escenario) throws Exception {
		try {
			Parent raiz = FXMLLoader.load(getClass().getResource(nombreSigEscena));
			Scene escena = new Scene(raiz);
			escena.getStylesheets().add(css);
			escenario.setScene(escena);
			Image icon = new Image("Icono.png");
			escenario.getIcons().add(icon);
			escenario.setHeight(720);
			escenario.setWidth(1280);
			escenario.setMaxHeight(720);
			escenario.setMaxWidth(1280);
			escenario.setMinHeight(720);
			escenario.setMinWidth(1280);
			escenario.setTitle("Consigue El Número");
			escenario.show();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	

	protected FXMLLoader intercambiarEscena (Event event) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(nombreSigEscena));
			Parent raiz = loader.load();
			Stage escenario = (Stage)((Node)event.getSource()).getScene().getWindow();
			escenario.setHeight(720);
			escenario.setWidth(1280);
			escenario.setMaxHeight(720);
			escenario.setMaxWidth(1280);
			escenario.setMinHeight(720);
			escenario.setMinWidth(1280);
			Scene escena = new Scene(raiz);
			escena.getStylesheets().add(css);
			escenario.setScene(escena);
			return loader;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	protected FXMLLoader[] intercambiarEscenaTransicion (Event event, int tiempoTransicion) {
		try {
			
			tiempoTransicion = tiempoTransicion*1000;
			
			FXMLLoader[] loader = new FXMLLoader[2];
			
			FXMLLoader loader1 = new FXMLLoader(getClass().getResource(nombreSigEscena));
			FXMLLoader loader2 = new FXMLLoader(getClass().getResource(nombreEscenaTransicion));
			
			loader[0] = loader1;
			loader[1] = loader2;
			
			Parent raiz1 = loader1.load();
			Parent raiz2 = loader2.load();
			
			Stage escenario = (Stage)((Node)event.getSource()).getScene().getWindow();
			escenario.setHeight(720);
			escenario.setWidth(1280);
			escenario.setMaxHeight(720);
			escenario.setMaxWidth(1280);
			escenario.setMinHeight(720);
			escenario.setMinWidth(1280);
			
			Scene escena1 = new Scene(raiz1);
			Scene escena2 = new Scene(raiz2);
			
			escena1.getStylesheets().add(css);
			escena2.getStylesheets().add(css);
			
			escenario.setScene(escena1);
			
			TimerTask task = new TimerTask() {
				@Override
				public void run() {
					Platform.runLater(() -> {
						escenario.setScene(escena2);
				          });
				}
			};
			
			timer.schedule(task, tiempoTransicion);
			
			return loader;
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	protected void setJugador(Jugador jugador) {
		this.jugador = jugador;
	}
	
	protected void irMenuPrincipal(Event event) {
		nombreSigEscena = "2 - Menu Principal.fxml";
		FXMLLoader loader = intercambiarEscena(event);
		CE_MenuPrincipal cE_MenuPrincipal = loader.getController();
		cE_MenuPrincipal.setJugador(jugador);
		cE_MenuPrincipal.setNombreYPuntuacion();
	}
	
	protected void irMenuExplicaciones(Event event) {
		nombreSigEscena = "E1 - Menu Explicaciones.fxml";
		FXMLLoader loader = intercambiarEscena(event);
		CE_E1 cE_E1 = loader.getController();
		cE_E1.setJugador(jugador);
	}
	
}