package br.ufms.apsoo.controlador.service;

import br.ufms.apsoo.controlador.model.Motorista;
import br.ufms.apsoo.controlador.model.Veiculo;
import br.ufms.apsoo.controlador.repository.MotoristaRepository;
import br.ufms.apsoo.controlador.repository.VeiculoRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class SearchDriverService {

    public ObservableList<Motorista> getDriverList() {
        List<Motorista> driverList;
        try (var motoristaRepository = new MotoristaRepository()) {
            driverList = motoristaRepository.getListMotorista();
        }
        return FXCollections.observableArrayList(driverList);
    }

}
