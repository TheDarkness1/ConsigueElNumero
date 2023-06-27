package application;
	
import javafx.application.Application;
import javafx.stage.Stage;


public class Main extends Application {
	
	ControladorPrograma controlador = new ControladorPrograma();
	
	//Lanzamiento del programa
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage escenario) throws Exception {
		controlador.setNombreEscena("1 - Menu Inicial.fxml");
		controlador.primeraEscena(escenario);
	}

	
}
