module br.ufms.apsoo.controlador {
    requires javafx.controls;
    requires javafx.fxml;
    requires static lombok;
    requires jakarta.persistence;


    opens br.ufms.apsoo.controlador to javafx.fxml;
    exports br.ufms.apsoo.controlador;
}