package br.ufms.apsoo.controlador.controller;

import br.ufms.apsoo.controlador.model.Veiculo;
import br.ufms.apsoo.controlador.service.VeiculoService;
import br.ufms.apsoo.controlador.singleton.VeiculoSingleton;
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
    private Veiculo vehicle;

    public VehicleFrameController() {
        this.veiculoService = new VeiculoService();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (VeiculoSingleton.isVeiculo()) {
            vehicle = VeiculoSingleton.getVeiculo();
            marcaVeiculoTextField.setText(vehicle.getMarca());
            modeloVeiculoTextField.setText(vehicle.getModelo());
            placaVeiculoTextField.setText(vehicle.getPlaca());
            quilometragemAtualTextField.setText(String.valueOf(vehicle.getQuilometragem()));
            quilometragemAtualTextField.setDisable(true);
            VeiculoSingleton.clearVeiculo();
        } else {
            removeButton.setDisable(true);
        }
    }

    @FXML
    private void handleSaveButtonAction() {
        try {
            String successMessage;

            if (vehicle == null || vehicle.getId() == null) {
                veiculoService.saveVehicle(
                    marcaVeiculoTextField.getText(),
                    modeloVeiculoTextField.getText(),
                    placaVeiculoTextField.getText(),
                    quilometragemAtualTextField.getText()
                );
                successMessage = "O veículo foi saldo.";
            } else {
                vehicle.setMarca(marcaVeiculoTextField.getText());
                vehicle.setModelo(modeloVeiculoTextField.getText());
                vehicle.setPlaca(placaVeiculoTextField.getText());
                vehicle.setQuilometragem(Integer.parseInt(quilometragemAtualTextField.getText()));
                veiculoService.updateVehicle(vehicle);
                successMessage = "O veículo foi atualizado.";
            }

            // TODO: Make following messages parametrizable by a properties file
            Alert successAlert = new Alert(Alert.AlertType.INFORMATION, successMessage);
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
        try {
            if (vehicle != null && vehicle.getId() != null) {
                veiculoService.removeVehicle(vehicle);

                // TODO: Make following messages parametrizable by a properties file
                Alert successAlert = new Alert(Alert.AlertType.INFORMATION, "O veículo foi removido.");
                successAlert.setHeaderText("Sucesso!");
                successAlert.setTitle("Aviso");
                successAlert.showAndWait();
            } else {
                // TODO: Make following messages parametrizable by a properties file
                Alert warnAlert = new Alert(Alert.AlertType.WARNING, "Não foi possível remover o veículo.");
                warnAlert.setHeaderText("Erro!");
                warnAlert.setTitle("Ops...");
                warnAlert.showAndWait();
            }

            closeVehicleFrame();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
