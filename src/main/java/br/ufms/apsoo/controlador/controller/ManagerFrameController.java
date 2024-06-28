package br.ufms.apsoo.controlador.controller;

import br.ufms.apsoo.controlador.model.Gerente;
import br.ufms.apsoo.controlador.service.ManagerService;
import br.ufms.apsoo.controlador.singleton.ManagerSingleton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;

public class ManagerFrameController implements Initializable {

    @FXML private VBox managerPanel;
    @FXML private TextField managerNameTextField;
    @FXML private TextField managerCpfTextField;
    @FXML private TextField managerTelefoneTextField;
    @FXML private DatePicker managerValidadeCnhDatePicker;
    @FXML private Button removeButton;

    private final ManagerService managerService;
    private Gerente manager;

    public ManagerFrameController() {
        this.managerService = new ManagerService();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (ManagerSingleton.isGerente()) {
            manager = ManagerSingleton.getGerente();
            managerNameTextField.setText(manager.getNomeCompleto());
            managerCpfTextField.setText(manager.getCpf());
            managerTelefoneTextField.setText(manager.getTelefone());
            managerValidadeCnhDatePicker.setValue(LocalDate.ofInstant(manager.getValidadeCnh().toInstant(), ZoneId.systemDefault()));
            ManagerSingleton.clearGerente();
        } else {
            removeButton.setDisable(true);
        }
    }

    @FXML
    private void handleSaveButtonAction() {
        try {
            String successMessage;

            if (manager == null || manager.getId() == null) {
                managerService.saveManager(
                    managerNameTextField.getText(),
                    managerCpfTextField.getText(),
                    managerTelefoneTextField.getText(),
                    managerValidadeCnhDatePicker.getValue()
                );
                successMessage = "O gerente foi salvo.";
            } else {
                manager.setNomeCompleto(managerNameTextField.getText());
                manager.setCpf(managerCpfTextField.getText());
                manager.setTelefone(managerTelefoneTextField.getText());
                Date convertedValidadeCnh = Date.from(Instant.from(managerValidadeCnhDatePicker.getValue().atStartOfDay(ZoneId.systemDefault())));
                manager.setValidadeCnh(convertedValidadeCnh);
                managerService.updateManager(manager);
                successMessage = "O gerente foi atualizado.";
            }

            // TODO: Make following messages parametrizable by a properties file
            Alert successAlert = new Alert(Alert.AlertType.INFORMATION, successMessage);
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
        try {
            if (manager != null && manager.getId() != null) {
                managerService.removeManager(manager);

                // TODO: Make following messages parametrizable by a properties file
                Alert successAlert = new Alert(Alert.AlertType.INFORMATION, "O gerente foi removido.");
                successAlert.setHeaderText("Sucesso!");
                successAlert.setTitle("Aviso");
                successAlert.showAndWait();
            } else {
                // TODO: Make following messages parametrizable by a properties file
                Alert warnAlert = new Alert(Alert.AlertType.WARNING, "Não foi possível remover o gerente.");
                warnAlert.setHeaderText("Erro!");
                warnAlert.setTitle("Ops...");
                warnAlert.showAndWait();
            }

            closeManagerCreateForm();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
