package br.ufms.apsoo.controlador;

import br.ufms.apsoo.controlador.model.Motorista;
import br.ufms.apsoo.controlador.model.Veiculo;
import br.ufms.apsoo.controlador.model.Viagem;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Date;

import static jakarta.persistence.Persistence.createEntityManagerFactory;

public class ControladorApplication extends Application {
    public static final String PERSISTENCE_UNIT_NAME = "br.ufms.apsoo.controlador";

    @Override
    public void start(Stage stage) throws IOException {
        try (var factory = createEntityManagerFactory(PERSISTENCE_UNIT_NAME)) {
            var entityManager = factory.createEntityManager();
            var transaction = entityManager.getTransaction();
            //noinspection TryFinallyCanBeTryWithResources
            try {
                var motorista = new Motorista(null, "José da Silva", "123.456.789-00");
                var veiculo = new Veiculo(null, "Fiat", "Uno", 18500);
                var viagem = new Viagem(null, motorista, veiculo, null, null, 0, new Date());
                transaction.begin();
                entityManager.persist(motorista);
                entityManager.persist(veiculo);
                entityManager.persist(viagem);
                transaction.commit();
            } catch (Exception error) {
                if (transaction.isActive()) transaction.rollback();
                throw error;
            } finally {
                entityManager.close();
            }
        }

        FXMLLoader fxmlLoader = new FXMLLoader(ControladorApplication.class.getResource("main-frame.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}