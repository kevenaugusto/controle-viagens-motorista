package br.ufms.apsoo.controlador.controller;

import br.ufms.apsoo.controlador.model.Motorista;
import br.ufms.apsoo.controlador.service.SearchDriverService;
import br.ufms.apsoo.controlador.singleton.MotoristaSingleton;
import br.ufms.apsoo.controlador.util.SceneBuilder;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

import static br.ufms.apsoo.controlador.controller.SearchVehicleController.EMPTY;

public class SearchDriverController implements Initializable {

    private static final String NOME_COMPLETO = "Nome Completo";
    private static final String CPF = "CPF";
    private static final String TELEFONE = "Telefone";

    @FXML private TableView<Motorista> driverTableView;
    @FXML private TableColumn<Motorista, String> nomeTableColumn;
    @FXML private TableColumn<Motorista, String> cpfTableColumn;
    @FXML private TableColumn<Motorista, String> telefoneTableColumn;
    @FXML private ChoiceBox<String> driverChoiceBox;
    @FXML private TextField driverTextField;

    private final SearchDriverService searchDriverService;
    private final FilteredList<Motorista> filteredList;

    public SearchDriverController() {
        this.searchDriverService = new SearchDriverService();
        this.filteredList = new FilteredList<>(searchDriverService.getDriverList(), predicate -> true);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        driverTableView.setEditable(true);

        nomeTableColumn.setCellValueFactory(new PropertyValueFactory<>("nomeCompleto"));
        cpfTableColumn.setCellValueFactory(new PropertyValueFactory<>("cpf"));
        telefoneTableColumn.setCellValueFactory(new PropertyValueFactory<>("telefone"));

        driverTableView.setItems(searchDriverService.getDriverList());
        driverChoiceBox.getItems().addAll(NOME_COMPLETO, CPF, TELEFONE);
        driverChoiceBox.setValue(NOME_COMPLETO);

        driverTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            switch (driverChoiceBox.getValue()) {
                case NOME_COMPLETO:
                    filteredList.setPredicate(predicate -> predicate.getNomeCompleto().toLowerCase().contains(newValue.toLowerCase().trim()));
                    break;
                case CPF:
                    filteredList.setPredicate(predicate -> predicate.getCpf().toLowerCase().contains(newValue.toLowerCase().trim()));
                    break;
                default:
                    filteredList.setPredicate(predicate -> predicate.getTelefone().toLowerCase().contains(newValue.toLowerCase().trim()));
            }
            driverTableView.setItems(filteredList);
        });

        driverChoiceBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                driverTextField.setText(EMPTY);
            }
        });

        driverTableView.setRowFactory(motoristaTableView -> {
            TableRow<Motorista> tableRow = new TableRow<>();
            tableRow.setOnMouseClicked(mouseEvent -> {
                if (mouseEvent.getClickCount() == 2 && !tableRow.isEmpty()) {
                    MotoristaSingleton.setMotorista(tableRow.getItem());
                    SceneBuilder.startDriverCreateForm();
                    driverTableView.getScene().getWindow().hide();
                }
            });
            return tableRow;
        });
    }

}
