module com.game2048.game2048 {
    requires javafx.controls;
    requires javafx.fxml;
    requires junit;


    opens src to javafx.fxml;
    exports src;
}