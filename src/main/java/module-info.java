module br.ufms.apsoo.controlador {
    requires javafx.controls;
    requires javafx.fxml;


    opens br.ufms.apsoo.controlador to javafx.fxml;
    exports br.ufms.apsoo.controlador;
}