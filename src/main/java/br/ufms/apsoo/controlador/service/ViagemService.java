package br.ufms.apsoo.controlador.service;

import br.ufms.apsoo.controlador.model.Viagem;
import br.ufms.apsoo.controlador.repository.ViagemRepository;
import javafx.collections.FXCollections;
import javafx.scene.control.ListView;

public class ViagemService {

    public void loadViagemListView(ListView<Viagem> viagemListView) {
        try (var viagemRepository = new ViagemRepository()) {
            viagemListView.setItems(FXCollections.observableArrayList(viagemRepository.getListViagem()));
        }
    }

}
