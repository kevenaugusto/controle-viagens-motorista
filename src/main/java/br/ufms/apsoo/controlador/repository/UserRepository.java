package br.ufms.apsoo.controlador.repository;

import br.ufms.apsoo.controlador.model.Usuario;
import jakarta.persistence.EntityManagerFactory;

import java.io.Closeable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static br.ufms.apsoo.controlador.ControladorApplication.PERSISTENCE_UNIT_NAME;
import static jakarta.persistence.Persistence.createEntityManagerFactory;

public class UserRepository implements Closeable {

    private final EntityManagerFactory entityManagerFactory;

    public UserRepository() {
        this.entityManagerFactory = createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
    }

    public List<Usuario> getUserList() {
        Optional<List<Usuario>> userOptional;
        try (var entityManager = entityManagerFactory.createEntityManager()) {
            userOptional = Optional.ofNullable(entityManager.createQuery("from Usuario", Usuario.class).getResultList());
        }
        return userOptional.orElse(new ArrayList<>());
    }

    public void saveUser(Usuario userToBeSaved) {
        try (var entityManager = entityManagerFactory.createEntityManager()) {
            var transaction = entityManager.getTransaction();
            try {
                transaction.begin();
                entityManager.persist(userToBeSaved);
                transaction.commit();
            } catch (Exception error) {
                if (transaction.isActive()) transaction.rollback();
                throw error;
            } finally {
                entityManager.close();
            }
        }
    }

    public void updateUser(Usuario userToBeUpdated) {
        try (var entityManager = entityManagerFactory.createEntityManager()) {
            var originalUser = entityManager.find(Usuario.class, userToBeUpdated.getId());
            originalUser.setNomeCompleto(userToBeUpdated.getNomeCompleto());
            originalUser.setCpf(userToBeUpdated.getCpf());
            originalUser.setTelefone(userToBeUpdated.getTelefone());
            var transaction = entityManager.getTransaction();
            try {
                transaction.begin();
                entityManager.persist(originalUser);
                transaction.commit();
            } catch (Exception error) {
                if (transaction.isActive()) transaction.rollback();
                throw error;
            } finally {
                entityManager.close();
            }
        }
    }

    public void removeUser(Usuario userToBeRemoved) {
        try (var entityManager = entityManagerFactory.createEntityManager()) {
            var originalUser = entityManager.find(Usuario.class, userToBeRemoved.getId());
            var transaction = entityManager.getTransaction();
            try {
                transaction.begin();
                entityManager.remove(originalUser);
                transaction.commit();
            } catch (Exception error) {
                if (transaction.isActive()) transaction.rollback();
                throw error;
            } finally {
                entityManager.close();
            }
        }
    }

    @Override
    public void close() {
        this.entityManagerFactory.close();
    }

}
