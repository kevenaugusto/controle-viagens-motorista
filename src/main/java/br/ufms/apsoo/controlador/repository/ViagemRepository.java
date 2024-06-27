package br.ufms.apsoo.controlador.repository;

import br.ufms.apsoo.controlador.model.Viagem;
import jakarta.persistence.EntityManagerFactory;

import java.io.Closeable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static br.ufms.apsoo.controlador.ControladorApplication.PERSISTENCE_UNIT_NAME;
import static jakarta.persistence.Persistence.createEntityManagerFactory;

public class ViagemRepository implements Closeable {

    private final EntityManagerFactory entityManagerFactory;

    public ViagemRepository() {
        this.entityManagerFactory = createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
    }

    public List<Viagem> getListViagem() {
        Optional<List<Viagem>> optionalViagem;
        try (var entityManager = entityManagerFactory.createEntityManager()) {
            optionalViagem = Optional.ofNullable(entityManager.createQuery("from Viagem", Viagem.class).getResultList());
        }
        return optionalViagem.orElse(new ArrayList<>());
    }

    public void saveViagem(Viagem viagemToBeSaved) {
        try (var entityManager = entityManagerFactory.createEntityManager()) {
            var transaction = entityManager.getTransaction();
            try {
                transaction.begin();
                entityManager.persist(viagemToBeSaved);
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
