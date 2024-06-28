package br.ufms.apsoo.controlador.repository;

import br.ufms.apsoo.controlador.model.Motorista;
import br.ufms.apsoo.controlador.model.Veiculo;
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
            var originalDriver = entityManager.find(Motorista.class, viagemToBeSaved.getMotoristaDesignado().getId());
            var originalVehicle = entityManager.find(Veiculo.class, viagemToBeSaved.getVeiculoDesignado().getId());
            viagemToBeSaved.setMotoristaDesignado(originalDriver);
            viagemToBeSaved.setVeiculoDesignado(originalVehicle);
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

    public void updateTrip(Viagem tripToBeUpdated) {
        try (var entityManager = entityManagerFactory.createEntityManager()) {
            var originalDriver = entityManager.find(Motorista.class, tripToBeUpdated.getMotoristaDesignado().getId());
            var originalVehicle = entityManager.find(Veiculo.class, tripToBeUpdated.getVeiculoDesignado().getId());
            var originalTrip = entityManager.find(Viagem.class, tripToBeUpdated.getId());
            originalTrip.setMotoristaDesignado(originalDriver);
            originalTrip.setVeiculoDesignado(originalVehicle);
            originalTrip.setDestino(tripToBeUpdated.getDestino());
            originalTrip.setHoraInicial(tripToBeUpdated.getHoraInicial());
            originalTrip.setHoraFinal(tripToBeUpdated.getHoraFinal());
            var transaction = entityManager.getTransaction();
            try {
                transaction.begin();
                entityManager.persist(originalTrip);
                transaction.commit();
            } catch (Exception e) {
                if (transaction.isActive()) transaction.rollback();
                throw e;
            }
        }
    }

    public void removeTrip(Viagem tripToBeRemoved) {
        try (var entityManager = entityManagerFactory.createEntityManager()) {
            var originalTrip = entityManager.find(Viagem.class, tripToBeRemoved.getId());
            var transaction = entityManager.getTransaction();
            try {
                transaction.begin();
                entityManager.remove(originalTrip);
                transaction.commit();
            } catch (Exception e) {
                if (transaction.isActive()) transaction.rollback();
                throw e;
            }
        }
    }

    public void startTrip(Viagem tripToBeStarted) {
        try (var entityManager = entityManagerFactory.createEntityManager()) {
            var originalTrip = entityManager.find(Viagem.class, tripToBeStarted.getId());
            originalTrip.setHoraInicial(tripToBeStarted.getHoraInicial());
            var transaction = entityManager.getTransaction();
            try {
                transaction.begin();
                entityManager.persist(originalTrip);
                transaction.commit();
            } catch (Exception e) {
                if (transaction.isActive()) transaction.rollback();
                throw e;
            }
        }
    }

    public void finishTrip(Viagem tripToBeFinished) {
        try (var entityManager = entityManagerFactory.createEntityManager()) {
            var originalTrip = entityManager.find(Viagem.class, tripToBeFinished.getId());
            originalTrip.setHoraFinal(tripToBeFinished.getHoraFinal());
            var transaction = entityManager.getTransaction();
            try {
                transaction.begin();
                entityManager.persist(originalTrip);
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
