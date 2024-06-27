package br.ufms.apsoo.controlador.service;

import br.ufms.apsoo.controlador.model.Veiculo;
import br.ufms.apsoo.controlador.repository.VeiculoRepository;
import javafx.collections.FXCollections;
import javafx.scene.control.ListView;

import java.util.Optional;

import static java.util.function.Predicate.not;

public class VeiculoService {

    public void loadMVeiculoListView(ListView<Veiculo> veiculoListView) {
        try (var veiculoRepository = new VeiculoRepository()) {
            veiculoListView.setItems(FXCollections.observableArrayList(veiculoRepository.getListVeiculo()));
        }
    }

    public void saveVehicle(String marca, String modelo, String placa, String quilometragem) throws IllegalArgumentException {
        var optionalMarca = Optional.ofNullable(marca).filter(not(String::isEmpty));
        var optionalModelo = Optional.ofNullable(modelo).filter(not(String::isEmpty));
        var optionalPlaca = Optional.ofNullable(placa).filter(not(String::isEmpty));
        var optionalQuilometragem = Optional.ofNullable(quilometragem).filter(not(String::isEmpty));

        try (var veiculoRepository = new VeiculoRepository()) {
            var convertedQuilometragem = Integer.parseInt(optionalQuilometragem.orElseThrow(() -> new IllegalArgumentException("A quilometragem inserida é inválida.")));
            veiculoRepository.saveVehicle(new Veiculo(
                null,
                optionalMarca.orElseThrow(() -> new IllegalArgumentException("O campo marca não pode ser vazio.")),
                optionalModelo.orElseThrow(() -> new IllegalArgumentException("O modelo escrito não é válido.")),
                optionalPlaca.orElseThrow(() -> new IllegalArgumentException("A placa precisa ser preenchida.")),
                convertedQuilometragem
            ));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("A quilometragem deve ser um valor numérico.");
        }
    }

}
