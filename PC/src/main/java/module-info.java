module org.example.pc {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.pc to javafx.fxml;
    exports org.example.pc;
}