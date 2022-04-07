module at.htl.test3 {
    requires javafx.controls;
    requires javafx.fxml;

    requires java.sql;

    opens at.htl.test3 to javafx.fxml;
    exports at.htl.test3;
}