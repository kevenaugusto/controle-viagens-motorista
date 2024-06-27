package br.ufms.apsoo.controlador.controller;

import br.ufms.apsoo.controlador.model.Motorista;
import br.ufms.apsoo.controlador.model.Veiculo;
import br.ufms.apsoo.controlador.model.Viagem;
import br.ufms.apsoo.controlador.service.MotoristaService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ResourceBundle;

public class MainFrameController implements Initializable {

    @FXML
    private ListView<Motorista> listViewMotoristas;

    private final MotoristaService motoristaService;

    public MainFrameController() {
        this.motoristaService = new MotoristaService();
    }

    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {
        motoristaService.loadMotoristaListView(listViewMotoristas);
    }
}