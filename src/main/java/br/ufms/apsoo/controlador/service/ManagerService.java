package br.ufms.apsoo.controlador.service;

import br.ufms.apsoo.controlador.model.Gerente;
import br.ufms.apsoo.controlador.repository.ManagerRepository;
import br.ufms.apsoo.controlador.repository.UserRepository;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

import static java.util.function.Predicate.not;

public class ManagerService {

    public void saveManager(String nomeCompleto, String cpf, String telefone, LocalDate validadeCnh) throws IllegalArgumentException {
        var optionalNomeCompleto = Optional.ofNullable(nomeCompleto).filter(not(String::isEmpty));
        var optionalCpf = Optional.ofNullable(cpf).filter(not(String::isEmpty));
        var optionalTelefone = Optional.ofNullable(telefone).filter(not(String::isEmpty));
        var optionalValidadeCnh = Optional.ofNullable(validadeCnh);

        try (var managerRepository = new ManagerRepository()) {
            LocalDate localDate = optionalValidadeCnh.orElseThrow(() -> new IllegalArgumentException("A data de validade da CNH é inválida."));
            Date convertedValidadeCnh = Date.from(Instant.from(localDate.atStartOfDay(ZoneId.systemDefault())));
            managerRepository.saveManager(new Gerente(
                null,
                optionalNomeCompleto.orElseThrow(() -> new IllegalArgumentException("O nome não pode ser vazio.")),
                optionalCpf.orElseThrow(() -> new IllegalArgumentException("O CPF inserido é inválido.")),
                optionalTelefone.orElseThrow(() -> new IllegalArgumentException("O telefone precisa ser preenchido.")),
                convertedValidadeCnh
            ));
        }
    }

    public void updateManager(Gerente managerToBeUpdated) {
        var optionalNomeCompleto = Optional.ofNullable(managerToBeUpdated.getNomeCompleto()).filter(not(String::isEmpty));
        var optionalCpf = Optional.ofNullable(managerToBeUpdated.getCpf()).filter(not(String::isEmpty));
        var optionalTelefone = Optional.ofNullable(managerToBeUpdated.getTelefone()).filter(not(String::isEmpty));
        var optionalValidadeCnh = Optional.ofNullable(managerToBeUpdated.getValidadeCnh());

        try (var managerRepository = new ManagerRepository()) {
            optionalNomeCompleto.orElseThrow(() -> new IllegalArgumentException("O nome não pode ser vazio."));
            optionalCpf.orElseThrow(() -> new IllegalArgumentException("O CPF inserido é inválido."));
            optionalTelefone.orElseThrow(() -> new IllegalArgumentException("O telefone precisa ser preenchido."));
            optionalValidadeCnh.orElseThrow(() -> new IllegalArgumentException("A data de validade da CNH é inválida."));
            managerRepository.updateManager(managerToBeUpdated);
        }
    }

    public void removeManager(Gerente managerToBeRemoved) {
        try (var managerRepository = new ManagerRepository()) {
            managerRepository.removeManager(managerToBeRemoved);
        }
    }

}
