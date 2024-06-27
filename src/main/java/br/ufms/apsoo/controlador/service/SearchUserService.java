package br.ufms.apsoo.controlador.service;

import br.ufms.apsoo.controlador.model.Usuario;
import br.ufms.apsoo.controlador.repository.UserRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class SearchUserService {

    public ObservableList<Usuario> getUserList() {
        List<Usuario> userList;
        try (var userRepository = new UserRepository()) {
            userList = userRepository.getUserList();
        }
        return FXCollections.observableArrayList(userList);
    }

}
