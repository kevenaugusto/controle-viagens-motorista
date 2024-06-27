package br.ufms.apsoo.controlador.controller;

import br.ufms.apsoo.controlador.service.MotoristaService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class DriverFrameController implements Initializable {

    @FXML private TextField nomeCompletoTextField;
    @FXML private TextField cpfTextField;
    @FXML private TextField telefoneTextField;
    @FXML private DatePicker validadeCnhDatePicker;
    @FXML private Button removeButton;
    @FXML private VBox driverPanel;

    private final MotoristaService motoristaService;

    public DriverFrameController() {
        this.motoristaService = new MotoristaService();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        removeButton.setDisable(true);
    }

    @FXML
    private void handleSaveButtonAction() {
        try {
            motoristaService.saveMotorista(
                nomeCompletoTextField.getText(),
                cpfTextField.getText(),
                telefoneTextField.getText(),
                validadeCnhDatePicker.getValue()
            );

            // TODO: Make following messages parametrizable by a properties file
            Alert successAlert = new Alert(Alert.AlertType.INFORMATION, "O motorista foi salvo.");
            successAlert.setHeaderText("Sucesso!");
            successAlert.setTitle("Aviso");
            successAlert.showAndWait();

            closeDriverFrame();
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
    private void closeDriverFrame() {
        driverPanel.getScene().getWindow().hide();
    }

    @FXML
    private void handleRemoveButtonAction() {
        // TODO: Implement method to remove a driver
    }

}
