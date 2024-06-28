package br.ufms.apsoo.controlador.controller;

import br.ufms.apsoo.controlador.model.Gerente;
import br.ufms.apsoo.controlador.service.SearchManagerService;
import br.ufms.apsoo.controlador.singleton.ManagerSingleton;
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

public class SearchManagerController implements Initializable {

    @FXML private TableView<Gerente> managerTableView;
    @FXML private TableColumn<Gerente, String> managerNameTableColumn;
    @FXML private TableColumn<Gerente, String> managerCpfTableColumn;
    @FXML private TableColumn<Gerente, String> managerPhoneTableColumn;
    @FXML private ChoiceBox<String> managerChoiceBox;
    @FXML private TextField managerTextField;

    private final SearchManagerService searchManagerService;
    private final FilteredList<Gerente> filteredList;

    public SearchManagerController() {
        this.searchManagerService = new SearchManagerService();
        this.filteredList = new FilteredList<>(searchManagerService.getManagerList(), predicate -> true);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        managerTableView.setEditable(true);

        managerNameTableColumn.setCellValueFactory(new PropertyValueFactory<>("nomeCompleto"));
        managerCpfTableColumn.setCellValueFactory(new PropertyValueFactory<>("cpf"));
        managerPhoneTableColumn.setCellValueFactory(new PropertyValueFactory<>("telefone"));

        managerTableView.setItems(searchManagerService.getManagerList());
        managerChoiceBox.getItems().addAll(NOME_COMPLETO, CPF, TELEFONE);
        managerChoiceBox.setValue(NOME_COMPLETO);

        managerTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            switch (managerChoiceBox.getValue()) {
                case NOME_COMPLETO:
                    filteredList.setPredicate(predicate -> predicate.getNomeCompleto().toLowerCase().contains(newValue.toLowerCase().trim()));
                    break;
                case CPF:
                    filteredList.setPredicate(predicate -> predicate.getCpf().toLowerCase().contains(newValue.toLowerCase().trim()));
                    break;
                default:
                    filteredList.setPredicate(predicate -> predicate.getTelefone().toLowerCase().contains(newValue.toLowerCase().trim()));
            }
            managerTableView.setItems(filteredList);
        });

        managerChoiceBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                managerTextField.setText(EMPTY);
            }
        });

        managerTableView.setRowFactory(managerTableView -> {
            TableRow<Gerente> tableRow = new TableRow<>();
            tableRow.setOnMouseClicked(mouseEvent -> {
                if (mouseEvent.getClickCount() == 2 && !tableRow.isEmpty()) {
                    ManagerSingleton.setGerente(tableRow.getItem());
                    SceneBuilder.startNewManagerForm();
                    managerTableView.getScene().getWindow().hide();
                }
            });
            return tableRow;
        });
    }
}
