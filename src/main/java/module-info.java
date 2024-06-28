module br.ufms.apsoo.controlador {
    requires javafx.controls;
    requires javafx.fxml;
    requires static lombok;
    requires jakarta.persistence;
    requires org.hibernate.orm.core;


    opens br.ufms.apsoo.controlador to javafx.fxml;
    exports br.ufms.apsoo.controlador;

    opens br.ufms.apsoo.controlador.model to org.hibernate.orm.core, javafx.base;
    exports br.ufms.apsoo.controlador.controller;
    opens br.ufms.apsoo.controlador.controller to javafx.fxml;
}