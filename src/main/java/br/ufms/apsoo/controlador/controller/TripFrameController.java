package br.ufms.apsoo.controlador.controller;

import br.ufms.apsoo.controlador.model.Viagem;
import br.ufms.apsoo.controlador.service.ViagemService;
import br.ufms.apsoo.controlador.singleton.MotoristaSingleton;
import br.ufms.apsoo.controlador.singleton.VeiculoSingleton;
import br.ufms.apsoo.controlador.singleton.ViagemSingleton;
import br.ufms.apsoo.controlador.util.SceneBuilder;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.Date;
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
    private Viagem viagem;

    public TripFrameController() {
        this.viagemService = new ViagemService();
        this.viagem = new Viagem();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (ViagemSingleton.isViagem()) {
            viagem = ViagemSingleton.getViagem();
            motoristaTextField.setText(viagem.getMotoristaDesignado().getNomeCompleto());
            veiculoTextField.setText(viagem.getVeiculoDesignado().getModelo() + "/" + viagem.getVeiculoDesignado().getPlaca());
            destinoViagemTextField.setText(viagem.getDestino());
            Date horaInicial = viagem.getHoraInicial();
            if (horaInicial != null) {
                horarioInicioTextField.setText(horaInicial.toString());
                startTripButton.setDisable(true);
            }
            Date horaFinal = viagem.getHoraFinal();
            if (horaFinal != null) {
                horarioTerminoTextField.setText(horaFinal.toString());
                endTripButton.setDisable(true);
            }
            ViagemSingleton.clearViagem();
        } else {
            removeButton.setDisable(true);
            startTripButton.setDisable(true);
            endTripButton.setDisable(true);
        }
    }

    @FXML
    private void handleSelectDriverButtonAction() {
        MotoristaSingleton.setFromTripForm(true);
        SceneBuilder.startSearchDriverForm(true);
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
        SceneBuilder.startSearchVehicleForm(true);
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
            String successMessage;

            if (viagem == null || viagem.getId() == null) {
                viagem.setDestino(destinoViagemTextField.getText());
                viagemService.saveTrip(viagem);
                successMessage = "A viagem foi salva.";
            } else {
                viagem.setDestino(destinoViagemTextField.getText());
                viagemService.updateTrip(viagem);
                successMessage = "A viagem foi atualizada.";
            }

            // TODO: Make following messages parametrizable by a properties file
            Alert successAlert = new Alert(Alert.AlertType.INFORMATION, successMessage);
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
        try {
            if (viagem != null && viagem.getId() != null) {
                viagemService.removeTrip(viagem);

                // TODO: Make following messages parametrizable by a properties file
                Alert successAlert = new Alert(Alert.AlertType.INFORMATION, "A viagem foi removida.");
                successAlert.setHeaderText("Sucesso!");
                successAlert.setTitle("Aviso");
                successAlert.showAndWait();
            } else {
                // TODO: Make following messages parametrizable by a properties file
                Alert warnAlert = new Alert(Alert.AlertType.WARNING, "Não foi possível remover a viagem.");
                warnAlert.setHeaderText("Erro!");
                warnAlert.setTitle("Ops...");
                warnAlert.showAndWait();
            }

            closeTripFrame();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleStartTripButtonAction() {
        try {
            if (viagem != null && viagem.getId() != null && viagem.getHoraInicial() == null) {
                viagem.setHoraInicial(new Date());
                viagemService.startTrip(viagem);

                // TODO: Make following messages parametrizable by a properties file
                Alert successAlert = new Alert(Alert.AlertType.INFORMATION, "A viagem foi iniciada.");
                successAlert.setHeaderText("Sucesso!");
                successAlert.setTitle("Aviso");
                successAlert.showAndWait();
            } else {
                // TODO: Make following messages parametrizable by a properties file
                Alert warnAlert = new Alert(Alert.AlertType.WARNING, "Não foi possível iniciar a viagem.");
                warnAlert.setHeaderText("Erro!");
                warnAlert.setTitle("Ops...");
                warnAlert.showAndWait();
            }

            closeTripFrame();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleEndTripButtonAction() {
        try {
            if (viagem != null && viagem.getId() != null && viagem.getHoraInicial() != null && viagem.getHoraFinal() == null) {
                viagem.setHoraFinal(new Date());
                viagemService.finishTrip(viagem);

                // TODO: Make following messages parametrizable by a properties file
                Alert successAlert = new Alert(Alert.AlertType.INFORMATION, "A viagem foi encerrada.");
                successAlert.setHeaderText("Sucesso!");
                successAlert.setTitle("Aviso");
                successAlert.showAndWait();
            } else {
                // TODO: Make following messages parametrizable by a properties file
                Alert warnAlert = new Alert(Alert.AlertType.WARNING, "Não foi possível encerrar a viagem.");
                warnAlert.setHeaderText("Erro!");
                warnAlert.setTitle("Ops...");
                warnAlert.showAndWait();
            }

            closeTripFrame();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
