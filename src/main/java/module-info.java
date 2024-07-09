module ProjectPhase1 {
    requires javafx.controls;
    requires javafx.media;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;

    exports Controller;
    exports Module;
    exports View;
    exports example;
    opens View to javafx.fxml;
    opens Module to javafx.fxml;
    opens Controller to javafx.fxml, java.sql;
    opens example to javafx.fxml, java.sql;
}
