package br.ufms.apsoo.controlador.repository;

import br.ufms.apsoo.controlador.model.Veiculo;
import jakarta.persistence.EntityManagerFactory;

import java.io.Closeable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static br.ufms.apsoo.controlador.ControladorApplication.PERSISTENCE_UNIT_NAME;
import static jakarta.persistence.Persistence.createEntityManagerFactory;

public class VeiculoRepository implements Closeable {

    private final EntityManagerFactory entityManagerFactory;

    public VeiculoRepository() {
        this.entityManagerFactory = createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
    }

    public List<Veiculo> getListVeiculo() {
        Optional<List<Veiculo>> optionalVeiculo;
        try (var entityManager = entityManagerFactory.createEntityManager()) {
            optionalVeiculo = Optional.ofNullable(entityManager.createQuery("from Veiculo", Veiculo.class).getResultList());
        }
        return optionalVeiculo.orElse(new ArrayList<>());
    }

    public void saveVehicle(Veiculo vehicleToBeSaved) {
        try (var entityManager = entityManagerFactory.createEntityManager()) {
            var transaction = entityManager.getTransaction();
            try {
                transaction.begin();
                entityManager.persist(vehicleToBeSaved);
                transaction.commit();
            } catch (Exception error) {
                if (transaction.isActive()) transaction.rollback();
                throw error;
            } finally {
                entityManager.close();
            }
        }
    }

    public void updateVehicle(Veiculo vehicleToBeUpdated) {
        try (var entityManager = entityManagerFactory.createEntityManager()) {
            var originalVehicle = entityManager.find(Veiculo.class, vehicleToBeUpdated.getId());
            originalVehicle.setMarca(vehicleToBeUpdated.getMarca());
            originalVehicle.setModelo(vehicleToBeUpdated.getModelo());
            originalVehicle.setPlaca(vehicleToBeUpdated.getPlaca());
            originalVehicle.setQuilometragem(vehicleToBeUpdated.getQuilometragem());
            var transaction = entityManager.getTransaction();
            try {
                transaction.begin();
                entityManager.persist(originalVehicle);
                transaction.commit();
            } catch (Exception e) {
                if (transaction.isActive()) transaction.rollback();
                throw e;
            }
        }
    }

    public void removeVehicle(Veiculo vehicleToBeRemoved) {
        try (var entityManager = entityManagerFactory.createEntityManager()) {
            var originalVehicle = entityManager.find(Veiculo.class, vehicleToBeRemoved.getId());
            var transction  = entityManager.getTransaction();
            try {
                transction.begin();
                entityManager.remove(originalVehicle);
                transction.commit();
            } catch (Exception e) {
                if (transction.isActive()) transction.rollback();
                throw e;
            }
        }
    }

    @Override
    public void close() {
        this.entityManagerFactory.close();
    }
}
