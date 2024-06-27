package br.ufms.apsoo.controlador.controller;

import br.ufms.apsoo.controlador.service.VeiculoService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class VehicleFrameController implements Initializable {

    @FXML private TextField marcaVeiculoTextField;
    @FXML private TextField modeloVeiculoTextField;
    @FXML private TextField placaVeiculoTextField;
    @FXML private TextField quilometragemAtualTextField;
    @FXML private Button removeButton;
    @FXML private VBox vehiclePanel;

    private final VeiculoService veiculoService;

    public VehicleFrameController() {
        this.veiculoService = new VeiculoService();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        removeButton.setDisable(true);
    }

    @FXML
    private void handleSaveButtonAction() {
        try {
            veiculoService.saveVehicle(
                marcaVeiculoTextField.getText(),
                modeloVeiculoTextField.getText(),
                placaVeiculoTextField.getText(),
                quilometragemAtualTextField.getText()
            );

            // TODO: Make following messages parametrizable by a properties file
            Alert successAlert = new Alert(Alert.AlertType.INFORMATION, "O veículo foi salvo.");
            successAlert.setHeaderText("Sucesso!");
            successAlert.setTitle("Aviso");
            successAlert.showAndWait();

            closeVehicleFrame();
        } catch (IllegalArgumentException e) {
            // TODO: Make following messages parametrizable by a properties file
            Alert warnAlert = new Alert(Alert.AlertType.WARNING, e.getMessage());
            warnAlert.setHeaderText("Cuidado!");
            warnAlert.setTitle("Ops...");
            warnAlert.showAndWait();
        } catch (Exception e) {
            e.printStackTrace(); // TODO: Change for SLF4J implementation
        }
    }

    @FXML
    private void closeVehicleFrame() {
        vehiclePanel.getScene().getWindow().hide();
    }

    @FXML
    private void handleRemoveButtonAction() {
        // TODO: Implement method to remove a vehicle
    }

}
