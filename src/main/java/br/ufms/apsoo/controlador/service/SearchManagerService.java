package br.ufms.apsoo.controlador.service;

import br.ufms.apsoo.controlador.model.Gerente;
import br.ufms.apsoo.controlador.repository.ManagerRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class SearchManagerService {

    public ObservableList<Gerente> getManagerList() {
        List<Gerente> managerList;
        try (var managerRepository = new ManagerRepository()) {
            managerList = managerRepository.getManagerList();
        }
        return FXCollections.observableArrayList(managerList);
    }

}
