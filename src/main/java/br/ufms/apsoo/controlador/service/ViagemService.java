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

    public void saveTrip(String destinoViagem) throws IllegalArgumentException {
        var optionalDestinoViagem = Optional.ofNullable(destinoViagem).filter(not(String::isEmpty));

        try (var viagemRepository = new ViagemRepository()) {
            viagemRepository.saveViagem(new Viagem(
                null, null, null,
                optionalDestinoViagem.orElseThrow(() -> new IllegalArgumentException("O destino da viagem não pode ser vazio.")),
                null, null, 0,
                new Date()
            ));
        }
    }

}
