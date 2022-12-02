module RobloxMidi {
    requires javafx.controls;
    requires javafx.fxml;

    requires java.desktop;

    exports uk.gsck.midi to javafx.graphics, javafx.fxml;
    opens uk.gsck.midi to javafx.fxml;
}