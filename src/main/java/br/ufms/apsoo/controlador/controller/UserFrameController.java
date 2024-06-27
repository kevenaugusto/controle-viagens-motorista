package br.ufms.apsoo.controlador.controller;

import br.ufms.apsoo.controlador.service.UserService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class UserFrameController implements Initializable {

    @FXML private VBox userPanel;
    @FXML private TextField userCpfTextField;
    @FXML private TextField userNameTextField;
    @FXML private TextField userPhoneTextField;
    @FXML private Button removeButton;

    private final UserService userService;

    public UserFrameController() {
        this.userService = new UserService();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        removeButton.setDisable(true);
    }

    @FXML
    void handleSaveButtonAction() {
        try {
            userService.saveUser(
                userNameTextField.getText(),
                userCpfTextField.getText(),
                userPhoneTextField.getText()
            );

            // TODO: Make following messages parametrizable by a properties file
            Alert successAlert = new Alert(Alert.AlertType.INFORMATION, "O usuário foi salvo.");
            successAlert.setHeaderText("Sucesso!");
            successAlert.setTitle("Aviso");
            successAlert.showAndWait();

            closeUserFrame();
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
    void closeUserFrame() {
        userPanel.getScene().getWindow().hide();
    }

    @FXML
    void handleRemoveButtonAction() {
        // TODO: Implement method to remove a user
    }

}
