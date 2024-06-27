package br.ufms.apsoo.controlador.controller;

import br.ufms.apsoo.controlador.model.Motorista;
import br.ufms.apsoo.controlador.model.Veiculo;
import br.ufms.apsoo.controlador.model.Viagem;
import br.ufms.apsoo.controlador.service.MotoristaService;
import br.ufms.apsoo.controlador.service.VeiculoService;
import br.ufms.apsoo.controlador.service.ViagemService;
import br.ufms.apsoo.controlador.util.SceneBuilder;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class MainFrameController implements Initializable {

    @FXML private ListView<Motorista> listViewMotoristas;
    @FXML private ListView<Viagem> listViewViagens;
    @FXML private ListView<Veiculo> listViewVeiculos;
    @FXML private VBox mainPanel;

    private final MotoristaService motoristaService;
    private final ViagemService viagemService;
    private final VeiculoService veiculoService;

    public MainFrameController() {
        this.motoristaService = new MotoristaService();
        this.viagemService = new ViagemService();
        this.veiculoService = new VeiculoService();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        reloadTrips();
        reloadDrivers();
        reloadVehicles();
    }

    @FXML
    private void reloadTrips() {
        viagemService.loadViagemListView(listViewViagens);
    }

    @FXML
    private void reloadDrivers() {
        motoristaService.loadMotoristaListView(listViewMotoristas);
    }

    @FXML
    private void reloadVehicles() {
        veiculoService.loadMVeiculoListView(listViewVeiculos);
    }

    @FXML
    private void closeMainFrame() {
        mainPanel.getScene().getWindow().hide();
    }

    @FXML
    private void handleNewTripButtonAction() {
        SceneBuilder.startNewTripFrom();
    }

    @FXML
    private void handleNewDriverButtonAction() {
        SceneBuilder.startDriverCreateForm();
    }

    @FXML
    private void handleNewManagerButtonAction() {
        SceneBuilder.startNewManagerForm();
    }

    @FXML
    private void handleNewUserButtonAction() {
        SceneBuilder.startNewUserForm();
    }

    @FXML
    private void handleNewVehicleButtonAction() {
        SceneBuilder.startVehicleCreateForm();
    }

    @FXML
    private void handleSearchDriverButtonAction() {
        SceneBuilder.startSearchDriverForm();
    }

    @FXML
    private void handleSearchVehicleButtonAction() {
        SceneBuilder.startSearchVehicleForm();
    }

    @FXML
    private void handleSearchTripButtonAction() {
        SceneBuilder.startSearchTripForm();
    }

}