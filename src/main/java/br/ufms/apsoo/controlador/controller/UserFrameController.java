package br.ufms.apsoo.controlador.controller;

import br.ufms.apsoo.controlador.model.Usuario;
import br.ufms.apsoo.controlador.service.UserService;
import br.ufms.apsoo.controlador.singleton.UserSingleton;
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
    private Usuario user;

    public UserFrameController() {
        this.userService = new UserService();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (UserSingleton.isUser()) {
            user = UserSingleton.getUser();
            userNameTextField.setText(user.getNomeCompleto());
            userCpfTextField.setText(user.getCpf());
            userPhoneTextField.setText(user.getTelefone());
            UserSingleton.clearUser();
        } else {
            removeButton.setDisable(true);
        }
    }

    @FXML
    void handleSaveButtonAction() {
        try {
            String successMessage;

            if (user == null || user.getId() == null) {
                userService.saveUser(
                    userNameTextField.getText(),
                    userCpfTextField.getText(),
                    userPhoneTextField.getText()
                );
                successMessage = "O usuário foi salvo.";
            } else {
                user.setNomeCompleto(userNameTextField.getText());
                user.setCpf(userCpfTextField.getText());
                user.setTelefone(userPhoneTextField.getText());
                userService.updateUser(user);
                successMessage = "O usuário foi atualizado.";
            }

            // TODO: Make following messages parametrizable by a properties file
            Alert successAlert = new Alert(Alert.AlertType.INFORMATION, successMessage);
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
        try {
            if (user != null && user.getId() != null) {
                userService.removeUser(user);

                // TODO: Make following messages parametrizable by a properties file
                Alert successAlert = new Alert(Alert.AlertType.INFORMATION, "O usuário foi removido.");
                successAlert.setHeaderText("Sucesso!");
                successAlert.setTitle("Aviso");
                successAlert.showAndWait();
            } else {
                // TODO: Make following messages parametrizable by a properties file
                Alert warnAlert = new Alert(Alert.AlertType.WARNING, "Não foi possível remover o usuário.");
                warnAlert.setHeaderText("Erro!");
                warnAlert.setTitle("Ops...");
                warnAlert.showAndWait();
            }

            closeUserFrame();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
