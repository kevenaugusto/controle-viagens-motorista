package br.ufms.apsoo.controlador.util;

import br.ufms.apsoo.controlador.ControladorApplication;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

// TODO: Refact all class to reduce duplicated code
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

    public static void startSearchDriverForm() {
        startSearchDriverForm(false);
    }

    public static void startSearchDriverForm(boolean showAndWait) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(ControladorApplication.class.getResource("driver-search-form.fxml"));
            Stage vehicleStage = new Stage();
            vehicleStage.setScene(new Scene(fxmlLoader.load()));
            vehicleStage.setTitle("Pesquisar Motorista"); // TODO: Get title from a properties file
            if (showAndWait) vehicleStage.showAndWait();
            else vehicleStage.show();
        } catch (Exception e) {
            // TODO: Make following messages parametrizable by a properties file
            Alert errorAlert = new Alert(Alert.AlertType.ERROR, "Houve um erro ao abrir a janela de pesquisa por motoristas.");
            errorAlert.setHeaderText("Erro!");
            errorAlert.setTitle("Erro interno");
            errorAlert.showAndWait();
            e.printStackTrace(); // TODO: Change for SLF4J implementation
        }
    }

    public static void startSearchVehicleForm() {
        startSearchVehicleForm(false);
    }

    public static void startSearchVehicleForm(boolean showAndWait) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(ControladorApplication.class.getResource("vehicle-search-form.fxml"));
            Stage vehicleStage = new Stage();
            vehicleStage.setScene(new Scene(fxmlLoader.load()));
            vehicleStage.setTitle("Veículo"); // TODO: Get title from a properties file
            if (showAndWait) vehicleStage.showAndWait();
            else vehicleStage.show();
        } catch (Exception e) {
            // TODO: Make following messages parametrizable by a properties file
            Alert errorAlert = new Alert(Alert.AlertType.ERROR, "Houve um erro ao abrir a janela de pesquisa de veículos.");
            errorAlert.setHeaderText("Erro!");
            errorAlert.setTitle("Erro interno");
            errorAlert.showAndWait();
            e.printStackTrace(); // TODO: Change for SLF4J implementation
        }
    }

    public static void startSearchTripForm() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(ControladorApplication.class.getResource("trip-search-form.fxml"));
            Stage vehicleStage = new Stage();
            vehicleStage.setScene(new Scene(fxmlLoader.load()));
            vehicleStage.setTitle("Viagem"); // TODO: Get title from a properties file
            vehicleStage.show();
        } catch (Exception e) {
            // TODO: Make following messages parametrizable by a properties file
            Alert errorAlert = new Alert(Alert.AlertType.ERROR, "Houve um erro ao abrir a janela de pesquisa de viagens.");
            errorAlert.setHeaderText("Erro!");
            errorAlert.setTitle("Erro interno");
            errorAlert.showAndWait();
            e.printStackTrace(); // TODO: Change for SLF4J implementation
        }
    }

    public static void startNewTripFrom() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(ControladorApplication.class.getResource("trip-create-form.fxml"));
            Stage tripStage = new Stage();
            tripStage.setScene(new Scene(fxmlLoader.load()));
            tripStage.setTitle("Viagem"); // TODO: Get title from a properties file
            tripStage.show();
        } catch (Exception e) {
            // TODO: Make following messages parametrizable by a properties file
            Alert errorAlert = new Alert(Alert.AlertType.ERROR, "Houve um erro ao abrir a janela de viagens.");
            errorAlert.setHeaderText("Erro!");
            errorAlert.setTitle("Erro interno");
            errorAlert.showAndWait();
            e.printStackTrace(); // TODO: Change for SLF4J implementation
        }
    }

    public static void startNewManagerForm() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(ControladorApplication.class.getResource("manager-create-form.fxml"));
            Stage managerStage = new Stage();
            managerStage.setScene(new Scene(fxmlLoader.load()));
            managerStage.setTitle("Gerente"); // TODO: Get title from a properties file
            managerStage.show();
        } catch (Exception e) {
            // TODO: Make following messages parametrizable by a properties file
            Alert errorAlert = new Alert(Alert.AlertType.ERROR, "Houve um erro ao abrir a janela de gerentes.");
            errorAlert.setHeaderText("Erro!");
            errorAlert.setTitle("Erro interno");
            errorAlert.showAndWait();
            e.printStackTrace(); // TODO: Change for SLF4J implementation
        }
    }

    public static void startNewUserForm() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(ControladorApplication.class.getResource("user-create-form.fxml"));
            Stage managerStage = new Stage();
            managerStage.setScene(new Scene(fxmlLoader.load()));
            managerStage.setTitle("Usuário"); // TODO: Get title from a properties file
            managerStage.show();
        } catch (Exception e) {
            // TODO: Make following messages parametrizable by a properties file
            Alert errorAlert = new Alert(Alert.AlertType.ERROR, "Houve um erro ao abrir a janela de usuários.");
            errorAlert.setHeaderText("Erro!");
            errorAlert.setTitle("Erro interno");
            errorAlert.showAndWait();
            e.printStackTrace(); // TODO: Change for SLF4J implementation
        }
    }

    public static void startMainFrame() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(ControladorApplication.class.getResource("main-frame.fxml"));
            Stage mainStage = new Stage();
            mainStage.setTitle("Controlador de Viagens");
            mainStage.setScene(new Scene(fxmlLoader.load()));
            mainStage.show();
        } catch (Exception e) {
            // TODO: Make following messages parametrizable by a properties file
            Alert errorAlert = new Alert(Alert.AlertType.ERROR, "Não foi possível iniciar a janela principal.");
            errorAlert.setHeaderText("Erro!");
            errorAlert.setTitle("Erro crítico");
            errorAlert.showAndWait();
            e.printStackTrace(); // TODO: Change for SLF4J implementation
        }
    }

}
