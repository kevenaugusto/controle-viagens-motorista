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

    public void saveMotorista(Motorista motoristaToBeSaved) {
        try (var entityManager = entityManagerFactory.createEntityManager()) {
            var transaction = entityManager.getTransaction();
            try {
                transaction.begin();
                entityManager.persist(motoristaToBeSaved);
                transaction.commit();
            } catch (Exception error) {
                if (transaction.isActive()) transaction.rollback();
                throw error;
            } finally {
                entityManager.close();
            }
        }
    }

    public void updateMotorista(Motorista motoristaToBeUpdated) {
        try (var entityManager = entityManagerFactory.createEntityManager()) {
            var originalMotorista = entityManager.find(Motorista.class, motoristaToBeUpdated);
            originalMotorista.setNomeCompleto(motoristaToBeUpdated.getNomeCompleto());
            originalMotorista.setCpf(motoristaToBeUpdated.getCpf());
            originalMotorista.setTelefone(motoristaToBeUpdated.getTelefone());
            originalMotorista.setValidadeCnh(motoristaToBeUpdated.getValidadeCnh());
            var transaction = entityManager.getTransaction();
            try {
                transaction.begin();
                entityManager.persist(originalMotorista);
                transaction.commit();
            } catch (Exception e) {
                if (transaction.isActive()) transaction.rollback();
                throw e;
            }
        }
    }

    public void removeMotorista(Motorista motoristaToBeRemoved) {
        try (var entityManager = entityManagerFactory.createEntityManager()) {
            var originalMotorista = entityManager.find(Motorista.class, motoristaToBeRemoved.getId());
            var transaction = entityManager.getTransaction();
            try {
                transaction.begin();
                entityManager.remove(originalMotorista);
                transaction.commit();
            } catch (Exception e) {
                if (transaction.isActive()) transaction.rollback();
                throw e;
            }
        }
    }

    @Override
    public void close() {
        this.entityManagerFactory.close();
    }
}
