package br.ufms.apsoo.controlador.controller;

import br.ufms.apsoo.controlador.service.ManagerService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class ManagerFrameController implements Initializable {

    @FXML private VBox managerPanel;
    @FXML private TextField managerNameTextField;
    @FXML private TextField managerCpfTextField;
    @FXML private TextField managerTelefoneTextField;
    @FXML private DatePicker managerValidadeCnhDatePicker;
    @FXML private Button removeButton;

    private final ManagerService managerService;

    public ManagerFrameController() {
        this.managerService = new ManagerService();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        removeButton.setDisable(true);
    }

    @FXML
    private void handleSaveButtonAction() {
        try {
            managerService.saveManager(
                managerNameTextField.getText(),
                managerCpfTextField.getText(),
                managerTelefoneTextField.getText(),
                managerValidadeCnhDatePicker.getValue()
            );

            // TODO: Make following messages parametrizable by a properties file
            Alert successAlert = new Alert(Alert.AlertType.INFORMATION, "O gerente foi salvo.");
            successAlert.setHeaderText("Sucesso!");
            successAlert.setTitle("Aviso");
            successAlert.showAndWait();

            closeManagerCreateForm();
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
    private void closeManagerCreateForm() {
        managerPanel.getScene().getWindow().hide();
    }

    @FXML
    private void handleRemoveButtonAction() {
        // TODO: Implement method to remove a manager
    }

}
