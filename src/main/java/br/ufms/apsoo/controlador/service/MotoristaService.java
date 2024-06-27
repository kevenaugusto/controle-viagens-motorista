package br.ufms.apsoo.controlador.service;

import br.ufms.apsoo.controlador.model.Motorista;
import br.ufms.apsoo.controlador.repository.MotoristaRepository;
import javafx.collections.FXCollections;
import javafx.scene.control.ListView;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

import static java.util.function.Predicate.not;

public class MotoristaService {

    public void loadMotoristaListView(ListView<Motorista> motoristaListView) {
        try (var motoristaRepository = new MotoristaRepository()) {
            motoristaListView.setItems(FXCollections.observableArrayList(motoristaRepository.getListMotorista()));
        }
    }

    public void saveMotorista(String nomeCompleto, String cpf, String telefone, LocalDate validadeCnh) throws IllegalArgumentException {
        var optionalNomeCompleto = Optional.ofNullable(nomeCompleto).filter(not(String::isEmpty));
        var optionalCpf = Optional.ofNullable(cpf).filter(not(String::isEmpty));
        var optionalTelefone = Optional.ofNullable(telefone).filter(not(String::isEmpty));
        var optionalValidadeCnh = Optional.ofNullable(validadeCnh);

        try (var motoristaRepository = new MotoristaRepository()) {
            LocalDate localDate = optionalValidadeCnh.orElseThrow(() -> new IllegalArgumentException("A data de validade da CNH é inválida."));
            Date convertedValidadeCnh = Date.from(Instant.from(localDate.atStartOfDay(ZoneId.systemDefault())));
            motoristaRepository.saveMotorista(new Motorista(
                null,
                optionalNomeCompleto.orElseThrow(() -> new IllegalArgumentException("O nome não pode ser vazio.")),
                optionalCpf.orElseThrow(() -> new IllegalArgumentException("O CPF inserido é inválido.")),
                optionalTelefone.orElseThrow(() -> new IllegalArgumentException("O telefone precisa ser preenchido.")),
                convertedValidadeCnh
            ));
        }
    }

}
