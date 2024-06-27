package br.ufms.apsoo.controlador.service;

import br.ufms.apsoo.controlador.model.Veiculo;
import br.ufms.apsoo.controlador.repository.VeiculoRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class SearchVehicleService {

    public ObservableList<Veiculo> getVehicleList() {
        List<Veiculo> vehicleList;
        try (var veiculoRepository = new VeiculoRepository()) {
            vehicleList = veiculoRepository.getListVeiculo();
        }
        return FXCollections.observableArrayList(vehicleList);
    }

}
