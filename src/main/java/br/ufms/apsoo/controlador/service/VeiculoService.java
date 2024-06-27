package br.ufms.apsoo.controlador.service;

import br.ufms.apsoo.controlador.model.Veiculo;
import br.ufms.apsoo.controlador.repository.VeiculoRepository;
import javafx.collections.FXCollections;
import javafx.scene.control.ListView;

public class VeiculoService {

    public void loadMVeiculoListView(ListView<Veiculo> veiculoListView) {
        try (var veiculoRepository = new VeiculoRepository()) {
            veiculoListView.setItems(FXCollections.observableArrayList(veiculoRepository.getListVeiculo()));
        }
    }

}
