module ca.bcit.comp2522.termproject.valhalla {
    requires javafx.controls;
    requires javafx.fxml;


    opens ca.bcit.comp2522.termproject.valhalla to javafx.fxml;
    exports ca.bcit.comp2522.termproject.valhalla;
}