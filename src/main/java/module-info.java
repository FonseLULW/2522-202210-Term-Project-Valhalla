module ca.bcit.comp2522.termproject.valhalla {
    requires javafx.controls;
    requires javafx.fxml;


    opens ca.bcit.comp2522.termproject.valhalla to javafx.fxml;
    exports ca.bcit.comp2522.termproject.valhalla;
    exports ca.bcit.comp2522.termproject.valhalla.entities;
    opens ca.bcit.comp2522.termproject.valhalla.entities to javafx.fxml;
    exports ca.bcit.comp2522.termproject.valhalla.game;
    opens ca.bcit.comp2522.termproject.valhalla.game to javafx.fxml;
    exports ca.bcit.comp2522.termproject.valhalla.towers;
    opens ca.bcit.comp2522.termproject.valhalla.towers to javafx.fxml;
}