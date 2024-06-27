package br.ufms.apsoo.controlador.util;

import br.ufms.apsoo.controlador.ControladorApplication;
import br.ufms.apsoo.controlador.controller.DriverFrameController;
import br.ufms.apsoo.controlador.model.Motorista;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

public class SceneBuilder {

    public static void startDriverCreateForm() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(ControladorApplication.class.getResource("driver-create-form.fxml"));
            Stage driverStage = new Stage();
            driverStage.setScene(new Scene(fxmlLoader.load()));
            driverStage.setTitle("Motorista"); // TODO: Get title from a properties file
            driverStage.show();
        } catch (Exception e) {
            // TODO: Make following messages parametrizable by a properties file
            Alert errorAlert = new Alert(Alert.AlertType.ERROR, "Houve um erro ao abrir a janela de motoristas.");
            errorAlert.setHeaderText("Erro!");
            errorAlert.setTitle("Erro interno");
            errorAlert.showAndWait();
            e.printStackTrace(); // TODO: Change for SLF4J implementation
        }
    }

    public static void startVehicleCreateForm() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(ControladorApplication.class.getResource("vehicle-create-form.fxml"));
            Stage vehicleStage = new Stage();
            vehicleStage.setScene(new Scene(fxmlLoader.load()));
            vehicleStage.setTitle("Veículo"); // TODO: Get title from a properties file
            vehicleStage.show();
        } catch (Exception e) {
            // TODO: Make following messages parametrizable by a properties file
            Alert errorAlert = new Alert(Alert.AlertType.ERROR, "Houve um erro ao abrir a janela de veículos.");
            errorAlert.setHeaderText("Erro!");
            errorAlert.setTitle("Erro interno");
            errorAlert.showAndWait();
            e.printStackTrace(); // TODO: Change for SLF4J implementation
        }
    }

}
