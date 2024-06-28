package br.ufms.apsoo.controlador.controller;

import br.ufms.apsoo.controlador.model.Usuario;
import br.ufms.apsoo.controlador.service.SearchUserService;
import br.ufms.apsoo.controlador.singleton.UserSingleton;
import br.ufms.apsoo.controlador.util.SceneBuilder;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

import static br.ufms.apsoo.controlador.controller.SearchDriverController.*;
import static br.ufms.apsoo.controlador.controller.SearchVehicleController.EMPTY;

public class SearchUserController implements Initializable {

    @FXML private TableView<Usuario> userTableView;
    @FXML private TableColumn<Usuario, String> userNameTableColumn;
    @FXML private TableColumn<Usuario, String> userCpfTableColumn;
    @FXML private TableColumn<Usuario, String> userPhoneTableColumn;
    @FXML private ChoiceBox<String> userChoiceBox;
    @FXML private TextField userTextField;

    private final SearchUserService searchUserService;
    private final FilteredList<Usuario> filteredList;

    public SearchUserController() {
        this.searchUserService = new SearchUserService();
        this.filteredList = new FilteredList<>(searchUserService.getUserList(), predicate -> true);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        userTableView.setEditable(true);

        userNameTableColumn.setCellValueFactory(new PropertyValueFactory<>("nomeCompleto"));
        userCpfTableColumn.setCellValueFactory(new PropertyValueFactory<>("cpf"));
        userPhoneTableColumn.setCellValueFactory(new PropertyValueFactory<>("telefone"));

        userTableView.setItems(searchUserService.getUserList());
        userChoiceBox.getItems().addAll(NOME_COMPLETO, CPF, TELEFONE);
        userChoiceBox.setValue(NOME_COMPLETO);

        userTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            switch (userChoiceBox.getValue()) {
                case NOME_COMPLETO:
                    filteredList.setPredicate(predicate -> predicate.getNomeCompleto().toLowerCase().contains(newValue.toLowerCase().trim()));
                    break;
                case CPF:
                    filteredList.setPredicate(predicate -> predicate.getCpf().toLowerCase().contains(newValue.toLowerCase().trim()));
                    break;
                default:
                    filteredList.setPredicate(predicate -> predicate.getTelefone().toLowerCase().contains(newValue.toLowerCase().trim()));
            }
            userTableView.setItems(filteredList);
        });

        userChoiceBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                userTextField.setText(EMPTY);
            }
        });

        userTableView.setRowFactory(userTableView -> {
            TableRow<Usuario> tableRow = new TableRow<>();
            tableRow.setOnMouseClicked(mouseEvent -> {
                if (mouseEvent.getClickCount() == 2 && !tableRow.isEmpty()) {
                    UserSingleton.setUser(tableRow.getItem());
                    SceneBuilder.startNewUserForm();
                    userTableView.getScene().getWindow().hide();
                }
            });
            return tableRow;
        });
    }
}
