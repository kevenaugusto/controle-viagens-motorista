package br.ufms.apsoo.controlador.service;

import br.ufms.apsoo.controlador.model.Viagem;
import br.ufms.apsoo.controlador.repository.ViagemRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class SearchTripService {

    public ObservableList<Viagem> getTripList() {
        List<Viagem> tripList;
        try (var viagemRepository = new ViagemRepository()) {
            tripList = viagemRepository.getListViagem();
        }
        return FXCollections.observableArrayList(tripList);
    }

}
