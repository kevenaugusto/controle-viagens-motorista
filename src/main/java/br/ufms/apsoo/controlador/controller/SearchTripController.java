package br.ufms.apsoo.controlador.controller;

import br.ufms.apsoo.controlador.model.Viagem;
import br.ufms.apsoo.controlador.service.SearchTripService;
import br.ufms.apsoo.controlador.singleton.ViagemSingleton;
import br.ufms.apsoo.controlador.util.SceneBuilder;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

import static br.ufms.apsoo.controlador.controller.SearchVehicleController.EMPTY;

public class SearchTripController implements Initializable {

    private static final String MOTORISTA = "Motorista";
    private static final String VEICULO = "Veículo";
    private static final String DESTINO = "Destino";

    @FXML private TableView<Viagem> searchTripTableView;
    @FXML private TableColumn<Viagem, String> motoristaTableColumn;
    @FXML private TableColumn<Viagem, String> veiculoTableColumn;
    @FXML private TableColumn<Viagem, String> destinoTableColumn;
    @FXML private ChoiceBox<String> searchTripChoiceBox;
    @FXML private TextField searchTripTextField;

    private final SearchTripService searchTripService;
    private final FilteredList<Viagem> filteredList;

    public SearchTripController() {
        this.searchTripService = new SearchTripService();
        this.filteredList = new FilteredList<>(searchTripService.getTripList(), predicate -> true);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        searchTripTableView.setEditable(true);

        motoristaTableColumn.setCellValueFactory(new PropertyValueFactory<>("motoristaDesignado"));
        veiculoTableColumn.setCellValueFactory(new PropertyValueFactory<>("veiculoDesignado"));
        destinoTableColumn.setCellValueFactory(new PropertyValueFactory<>("destino"));

        searchTripTableView.setItems(searchTripService.getTripList());
        searchTripChoiceBox.getItems().addAll(MOTORISTA, VEICULO, DESTINO);
        searchTripChoiceBox.setValue(MOTORISTA);

        searchTripTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            switch (searchTripChoiceBox.getValue()) {
                case MOTORISTA:
                    filteredList.setPredicate(predicate -> predicate.getMotoristaDesignado().toString().toLowerCase().contains(newValue.toLowerCase().trim()));
                    break;
                case VEICULO:
                    filteredList.setPredicate(predicate -> predicate.getVeiculoDesignado().toString().toLowerCase().contains(newValue.toLowerCase().trim()));
                    break;
                default:
                    filteredList.setPredicate(predicate -> predicate.getDestino().toLowerCase().contains(newValue.toLowerCase().trim()));
            }
            searchTripTableView.setItems(filteredList);
        });

        searchTripChoiceBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                searchTripTextField.setText(EMPTY);
            }
        });

        searchTripTableView.setRowFactory(viagemTableView -> {
            TableRow<Viagem> tableRow = new TableRow<>();
            tableRow.setOnMouseClicked(mouseEvent -> {
                if (mouseEvent.getClickCount() == 2 && !tableRow.isEmpty()) {
                    ViagemSingleton.setViagem(tableRow.getItem());
                    SceneBuilder.startNewTripFrom();
                    searchTripTableView.getScene().getWindow().hide();
                }
            });
            return tableRow;
        });
    }
}
