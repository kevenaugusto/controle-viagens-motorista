package br.ufms.apsoo.controlador.service;

import br.ufms.apsoo.controlador.model.Viagem;
import br.ufms.apsoo.controlador.repository.ViagemRepository;
import javafx.collections.FXCollections;
import javafx.scene.control.ListView;

import java.util.Date;
import java.util.Optional;

import static java.util.function.Predicate.not;

public class ViagemService {

    public void loadViagemListView(ListView<Viagem> viagemListView) {
        try (var viagemRepository = new ViagemRepository()) {
            viagemListView.setItems(FXCollections.observableArrayList(viagemRepository.getListViagem()));
        }
    }

    public void saveTrip(Viagem tripToBeSaved) throws IllegalArgumentException {
        var optionalDestinoViagem = Optional.ofNullable(tripToBeSaved.getDestino()).filter(not(String::isEmpty));

        try (var viagemRepository = new ViagemRepository()) {
            optionalDestinoViagem.orElseThrow(() -> new IllegalArgumentException("O destino da viagem não pode ser vazio."));
            viagemRepository.saveViagem(tripToBeSaved);
        }
    }

    public void updateTrip(Viagem tripToBeUpdated) {
        var optionalDestinoViagem = Optional.ofNullable(tripToBeUpdated.getDestino()).filter(not(String::isEmpty));

        try (var viagemRepository = new ViagemRepository()) {
            optionalDestinoViagem.orElseThrow(() -> new IllegalArgumentException("O destino da viagem não pode ser vazio."));
            viagemRepository.updateTrip(tripToBeUpdated);
        }
    }

    public void removeTrip(Viagem tripToBeRemoved) {
        try (var viagemRepository = new ViagemRepository()) {
            viagemRepository.removeTrip(tripToBeRemoved);
        }
    }

}
