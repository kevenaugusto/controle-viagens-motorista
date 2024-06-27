package br.ufms.apsoo.controlador.controller;

import br.ufms.apsoo.controlador.model.Viagem;
import br.ufms.apsoo.controlador.service.ViagemService;
import br.ufms.apsoo.controlador.singleton.MotoristaSingleton;
import br.ufms.apsoo.controlador.singleton.VeiculoSingleton;
import br.ufms.apsoo.controlador.util.SceneBuilder;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class TripFrameController implements Initializable {

    @FXML private TextField motoristaTextField;
    @FXML private TextField veiculoTextField;
    @FXML private TextField destinoViagemTextField;
    @FXML private TextField horarioInicioTextField;
    @FXML private TextField horarioTerminoTextField;
    @FXML private Button selecionarMotoristaButton;
    @FXML private Button selecionarVeiculoButton;
    @FXML private Button removeButton;
    @FXML private Button startTripButton;
    @FXML private Button endTripButton;
    @FXML private VBox tripPanel;

    private final ViagemService viagemService;
    private final Viagem viagem;

    public TripFrameController() {
        this.viagemService = new ViagemService();
        this.viagem = new Viagem();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        removeButton.setDisable(true);
        startTripButton.setDisable(true);
        endTripButton.setDisable(true);
    }

    @FXML
    private void handleSelectDriverButtonAction() {
        MotoristaSingleton.setFromTripForm(true);
        SceneBuilder.startSearchDriverForm();
        if (MotoristaSingleton.isMotorista()) {
            viagem.setMotoristaDesignado(MotoristaSingleton.getMotorista());
            MotoristaSingleton.clearMotorista();
            MotoristaSingleton.setFromTripForm(false);
            motoristaTextField.setText(viagem.getMotoristaDesignado().getNomeCompleto());
        }
    }

    @FXML
    private void handleSelectVehicleButtonAction() {
        VeiculoSingleton.setFromTripForm(true);
        SceneBuilder.startSearchVehicleForm();
        if (VeiculoSingleton.isVeiculo()) {
            viagem.setVeiculoDesignado(VeiculoSingleton.getVeiculo());
            VeiculoSingleton.clearVeiculo();
            VeiculoSingleton.setFromTripForm(false);
            veiculoTextField.setText(viagem.getVeiculoDesignado().getModelo() + "/" + viagem.getVeiculoDesignado().getPlaca());
        }
    }

    @FXML
    private void handleSaveButtonAction() {
        try {
            viagemService.saveTrip(destinoViagemTextField.getText());

            // TODO: Make following messages parametrizable by a properties file
            Alert successAlert = new Alert(Alert.AlertType.INFORMATION, "A viagem foi salvo.");
            successAlert.setHeaderText("Sucesso!");
            successAlert.setTitle("Aviso");
            successAlert.showAndWait();

            closeTripFrame();
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
    private void closeTripFrame() {
        tripPanel.getScene().getWindow().hide();
    }

    @FXML
    private void handleRemoveButtonAction() {
        // TODO: Implement method to remove a driver
    }

    @FXML
    private void handleStartTripButtonAction() {
        // TODO: Implement method to start a trip
    }

    @FXML
    private void handleEndTripButtonAction() {
        // TODO: Implement method to end a trip
    }

}
