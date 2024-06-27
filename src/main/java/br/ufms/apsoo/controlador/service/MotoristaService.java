package br.ufms.apsoo.controlador.service;

import br.ufms.apsoo.controlador.model.Motorista;
import br.ufms.apsoo.controlador.repository.MotoristaRepository;
import javafx.collections.FXCollections;
import javafx.scene.control.ListView;

public class MotoristaService {

    public void loadMotoristaListView(ListView<Motorista> motoristaListView) {
        try (var motoristaRepository = new MotoristaRepository()) {
            motoristaListView.setItems(FXCollections.observableArrayList(motoristaRepository.getListMotorista()));
        }
    }

}
