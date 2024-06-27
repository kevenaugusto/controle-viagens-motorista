package br.ufms.apsoo.controlador;

import br.ufms.apsoo.controlador.controller.MainFrameController;
import javafx.application.Application;
import javafx.stage.Stage;

public class ControladorApplication extends Application {
    public static final String PERSISTENCE_UNIT_NAME = "br.ufms.apsoo.controlador";

    @Override
    public void start(Stage stage) {
//        try (var factory = createEntityManagerFactory(PERSISTENCE_UNIT_NAME)) {
//            var entityManager = factory.createEntityManager();
//            var transaction = entityManager.getTransaction();
//            //noinspection TryFinallyCanBeTryWithResources
//            try {
//                var motorista = new Motorista(null, "José da Silva", "9 1234-5678", "123.456.789-00", new Date());
//                var veiculo = new Veiculo(null, "Fiat", "Uno", "HSU-3E53", 18500);
//                var viagem = new Viagem(null, motorista, veiculo, "Rua dos Bobos, nº 0", null, null, 0, new Date());
//                transaction.begin();
//                entityManager.persist(motorista);
//                entityManager.persist(veiculo);
//                entityManager.persist(viagem);
//                transaction.commit();
//            } catch (Exception error) {
//                if (transaction.isActive()) transaction.rollback();
//                throw error;
//            } finally {
//                entityManager.close();
//            }
//        }
        MainFrameController.startMainFrame();
    }

    public static void main(String[] args) {
        launch();
    }
}