package br.ufms.apsoo.controlador.repository;

import br.ufms.apsoo.controlador.model.Motorista;
import jakarta.persistence.EntityManagerFactory;

import java.io.Closeable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static br.ufms.apsoo.controlador.ControladorApplication.PERSISTENCE_UNIT_NAME;
import static jakarta.persistence.Persistence.createEntityManagerFactory;

public class MotoristaRepository implements Closeable {

    private final EntityManagerFactory entityManagerFactory;

    public MotoristaRepository() {
        this.entityManagerFactory = createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
    }

    public List<Motorista> getListMotorista() {
        Optional<List<Motorista>> optionalMotoristas;
        try (var entityManager = entityManagerFactory.createEntityManager()) {
            optionalMotoristas = Optional.ofNullable(entityManager.createQuery("from Motorista", Motorista.class).getResultList());
        }
        return optionalMotoristas.orElse(new ArrayList<>());
    }

    @Override
    public void close() {
        this.entityManagerFactory.close();
    }
}