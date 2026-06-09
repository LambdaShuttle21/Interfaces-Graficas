module es.pedro.address {
    requires javafx.controls;
    requires javafx.fxml;

    exports es.pedro.address;
    exports es.pedro.address.model;

    opens es.pedro.address.view to javafx.fxml;
}
