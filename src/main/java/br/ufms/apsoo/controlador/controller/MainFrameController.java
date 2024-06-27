package br.ufms.apsoo.controlador.controller;

import br.ufms.apsoo.controlador.model.Motorista;
import br.ufms.apsoo.controlador.model.Veiculo;
import br.ufms.apsoo.controlador.model.Viagem;
import br.ufms.apsoo.controlador.service.MotoristaService;
import br.ufms.apsoo.controlador.service.VeiculoService;
import br.ufms.apsoo.controlador.service.ViagemService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class MainFrameController implements Initializable {

    @FXML
    private ListView<Motorista> listViewMotoristas;

    @FXML
    private ListView<Viagem> listViewViagens;

    @FXML
    private ListView<Veiculo> listViewVeiculos;

    @FXML
    private VBox mainPanel;

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
        motoristaService.loadMotoristaListView(listViewMotoristas);
        viagemService.loadViagemListView(listViewViagens);
        veiculoService.loadMVeiculoListView(listViewVeiculos);
    }

    @FXML
    private void handleCloseButtonAction(ActionEvent event) {
        mainPanel.getScene().getWindow().hide();
    }
}