module org.example.todolistappproject {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens org.example.todolistappproject to javafx.fxml;
    exports org.example.todolistappproject;
}