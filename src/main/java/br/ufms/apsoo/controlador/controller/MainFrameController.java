package br.ufms.apsoo.controlador.controller;

import br.ufms.apsoo.controlador.ControladorApplication;
import br.ufms.apsoo.controlador.model.Motorista;
import br.ufms.apsoo.controlador.model.Veiculo;
import br.ufms.apsoo.controlador.model.Viagem;
import br.ufms.apsoo.controlador.service.MotoristaService;
import br.ufms.apsoo.controlador.service.VeiculoService;
import br.ufms.apsoo.controlador.service.ViagemService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class MainFrameController implements Initializable {

    @FXML
    private ListView<Motorista> listViewMotoristas;

    @FXML
    private ListView<Viagem> listViewViagens;

    @FXML
    private ListView<Veiculo> listViewVeiculos;

    @FXML
    private VBox mainPanel;

    private final MotoristaService motoristaService;
    private final ViagemService viagemService;
    private final VeiculoService veiculoService;

    public MainFrameController() {
        this.motoristaService = new MotoristaService();
        this.viagemService = new ViagemService();
        this.veiculoService = new VeiculoService();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        motoristaService.loadMotoristaListView(listViewMotoristas);
        viagemService.loadViagemListView(listViewViagens);
        veiculoService.loadMVeiculoListView(listViewVeiculos);
    }

    @FXML
    private void handleCloseButtonAction(ActionEvent event) {
        mainPanel.getScene().getWindow().hide();
    }

    @FXML
    private void handleNewTripButtonAction() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(ControladorApplication.class.getResource("trip-create-form.xml"));
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

    @FXML
    private void handleNewDriverButtonAction() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(ControladorApplication.class.getResource("driver-create-form.fxml"));
            Stage tripStage = new Stage();
            tripStage.setScene(new Scene(fxmlLoader.load()));
            tripStage.setTitle("Motorista"); // TODO: Get title from a properties file
            tripStage.show();
        } catch (Exception e) {
            // TODO: Make following messages parametrizable by a properties file
            Alert errorAlert = new Alert(Alert.AlertType.ERROR, "Houve um erro ao abrir a janela de motoristas.");
            errorAlert.setHeaderText("Erro!");
            errorAlert.setTitle("Erro interno");
            errorAlert.showAndWait();
            e.printStackTrace(); // TODO: Change for SLF4J implementation
        }
    }

    @FXML
    private void handleNewManagerButtonAction() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(ControladorApplication.class.getResource("manager-create-form.fxml"));
            Stage tripStage = new Stage();
            tripStage.setScene(new Scene(fxmlLoader.load()));
            tripStage.setTitle("Gerente"); // TODO: Get title from a properties file
            tripStage.show();
        } catch (Exception e) {
            // TODO: Make following messages parametrizable by a properties file
            Alert errorAlert = new Alert(Alert.AlertType.ERROR, "Houve um erro ao abrir a janela de gerentes.");
            errorAlert.setHeaderText("Erro!");
            errorAlert.setTitle("Erro interno");
            errorAlert.showAndWait();
            e.printStackTrace(); // TODO: Change for SLF4J implementation
        }
    }

    @FXML
    private void handleNewVehicleButtonAction() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(ControladorApplication.class.getResource("vehicle-create-form.fxml"));
            Stage tripStage = new Stage();
            tripStage.setScene(new Scene(fxmlLoader.load()));
            tripStage.setTitle("Veículo"); // TODO: Get title from a properties file
            tripStage.show();
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