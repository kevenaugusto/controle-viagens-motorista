package br.ufms.apsoo.controlador;

import br.ufms.apsoo.controlador.util.SceneBuilder;
import javafx.application.Application;
import javafx.stage.Stage;

public class ControladorApplication extends Application {
    public static final String PERSISTENCE_UNIT_NAME = "br.ufms.apsoo.controlador";

    @Override
    public void start(Stage stage) {
        SceneBuilder.startMainFrame();
    }

    public static void main(String[] args) {
        launch();
    }
}