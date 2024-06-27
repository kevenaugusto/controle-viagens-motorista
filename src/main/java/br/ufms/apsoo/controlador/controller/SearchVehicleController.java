package br.ufms.apsoo.controlador.controller;

import br.ufms.apsoo.controlador.model.Veiculo;
import br.ufms.apsoo.controlador.service.SearchVehicleService;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class SearchVehicleController implements Initializable {

    private static final String MARCA = "Marca";
    private static final String MODELO = "Modelo";
    private static final String PLACA = "Placa";
    private static final String EMPTY = "";

    @FXML private TableView<Veiculo> vehicleTableView;
    @FXML private TableColumn<Veiculo, String> marcaTableColumn;
    @FXML private TableColumn<Veiculo, String> modeloTableColumn;
    @FXML private TableColumn<Veiculo, String> placaTableColumn;
    @FXML private ChoiceBox<String> vehicleChoiceBox;
    @FXML private TextField searchTextField;

    private final SearchVehicleService searchVehicleService;
    private final FilteredList<Veiculo> filteredList;

    public SearchVehicleController() {
        this.searchVehicleService = new SearchVehicleService();
        this.filteredList = new FilteredList<>(searchVehicleService.getVehicleList(), predicate -> true);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        vehicleTableView.setEditable(true);

        marcaTableColumn.setCellValueFactory(new PropertyValueFactory<>("marca"));
        modeloTableColumn.setCellValueFactory(new PropertyValueFactory<>("modelo"));
        placaTableColumn.setCellValueFactory(new PropertyValueFactory<>("placa"));

        vehicleTableView.setItems(searchVehicleService.getVehicleList());
        vehicleChoiceBox.getItems().addAll(MARCA, MODELO, PLACA);
        vehicleChoiceBox.setValue(MARCA);

        searchTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            switch (vehicleChoiceBox.getValue()) {
                case MARCA:
                    filteredList.setPredicate(predicate -> predicate.getMarca().toLowerCase().contains(newValue.toLowerCase().trim()));
                    break;
                case MODELO:
                    filteredList.setPredicate(predicate -> predicate.getModelo().toLowerCase().contains(newValue.toLowerCase().trim()));
                    break;
                default:
                    filteredList.setPredicate(predicate -> predicate.getPlaca().toLowerCase().contains(newValue.toLowerCase().trim()));
            }
            vehicleTableView.setItems(filteredList);
        });

        vehicleChoiceBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                searchTextField.setText(EMPTY);
            }
        });
    }

}
