package br.ufms.apsoo.controlador.service;

import br.ufms.apsoo.controlador.model.Usuario;
import br.ufms.apsoo.controlador.repository.UserRepository;

import java.util.Optional;

import static java.util.function.Predicate.not;

public class UserService {

    public void saveUser(String nomeCompleto, String cpf, String telefone) throws IllegalArgumentException {
        var optionalNomeCompleto = Optional.ofNullable(nomeCompleto).filter(not(String::isEmpty));
        var optionalCpf = Optional.ofNullable(cpf).filter(not(String::isEmpty));
        var optionalTelefone = Optional.ofNullable(telefone).filter(not(String::isEmpty));

        try (var userRepository = new UserRepository()) {
            userRepository.saveUser(new Usuario(
                null,
                optionalNomeCompleto.orElseThrow(() -> new IllegalArgumentException("O nome não pode ser vazio.")),
                optionalCpf.orElseThrow(() -> new IllegalArgumentException("O CPF inserido é inválido.")),
                optionalTelefone.orElseThrow(() -> new IllegalArgumentException("O telefone precisa ser preenchido."))
            ));
        }
    }

    public void updateUser(Usuario userToBeUpdated) {
        var optionalNomeCompleto = Optional.ofNullable(userToBeUpdated.getNomeCompleto()).filter(not(String::isEmpty));
        var optionalCpf = Optional.ofNullable(userToBeUpdated.getCpf()).filter(not(String::isEmpty));
        var optionalTelefone = Optional.ofNullable(userToBeUpdated.getTelefone()).filter(not(String::isEmpty));

        try (var userRepository = new UserRepository()) {
            optionalNomeCompleto.orElseThrow(() -> new IllegalArgumentException("O nome não pode ser vazio."));
            optionalCpf.orElseThrow(() -> new IllegalArgumentException("O CPF inserido é inválido."));
            optionalTelefone.orElseThrow(() -> new IllegalArgumentException("O telefone precisa ser preenchido."));
            userRepository.updateUser(userToBeUpdated);
        }
    }

    public void removeUser(Usuario userToBeRemoved) {
        try (var userRepository = new UserRepository()) {
            userRepository.removeUser(userToBeRemoved);
        }
    }

}
