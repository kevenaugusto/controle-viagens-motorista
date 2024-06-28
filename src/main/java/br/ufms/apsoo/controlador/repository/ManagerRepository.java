package br.ufms.apsoo.controlador.repository;

import br.ufms.apsoo.controlador.model.Gerente;
import br.ufms.apsoo.controlador.model.Motorista;
import jakarta.persistence.EntityManagerFactory;

import java.io.Closeable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static br.ufms.apsoo.controlador.ControladorApplication.PERSISTENCE_UNIT_NAME;
import static jakarta.persistence.Persistence.createEntityManagerFactory;

public class ManagerRepository implements Closeable {

    private final EntityManagerFactory entityManagerFactory;

    public ManagerRepository() {
        this.entityManagerFactory = createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
    }

    public List<Gerente> getManagerList() {
        Optional<List<Gerente>> managerOptional;
        try (var entityManager = entityManagerFactory.createEntityManager()) {
            managerOptional = Optional.ofNullable(entityManager.createQuery("from Gerente", Gerente.class).getResultList());
        }
        return managerOptional.orElse(new ArrayList<>());
    }

    public void saveManager(Gerente managerToBeSaved) {
        try (var entityManager = entityManagerFactory.createEntityManager()) {
            var transaction = entityManager.getTransaction();
            try {
                transaction.begin();
                entityManager.persist(managerToBeSaved);
                transaction.commit();
            } catch (Exception error) {
                if (transaction.isActive()) transaction.rollback();
                throw error;
            } finally {
                entityManager.close();
            }
        }
    }

    public void updateManager(Gerente managerToBeUpdated) {
        try (var entityManager = entityManagerFactory.createEntityManager()) {
            var originalManager = entityManager.find(Gerente.class, managerToBeUpdated.getId());
            originalManager.setNomeCompleto(managerToBeUpdated.getNomeCompleto());
            originalManager.setCpf(managerToBeUpdated.getCpf());
            originalManager.setTelefone(managerToBeUpdated.getTelefone());
            originalManager.setValidadeCnh(managerToBeUpdated.getValidadeCnh());
            var transaction = entityManager.getTransaction();
            try {
                transaction.begin();
                entityManager.persist(originalManager);
                transaction.commit();
            } catch (Exception e) {
                if (transaction.isActive()) transaction.rollback();
                throw e;
            }
        }
    }

    public void removeManager(Gerente managerToBeRemoved) {
        try (var entityManager = entityManagerFactory.createEntityManager()) {
            var originalManager = entityManager.find(Gerente.class, managerToBeRemoved.getId());
            var transaction = entityManager.getTransaction();
            try {
                transaction.begin();
                entityManager.remove(originalManager);
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
