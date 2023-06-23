module com.example.pfa {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.xml;
    requires java.xml.bind;


    opens com.example.pfa to javafx.fxml;
    exports com.example.pfa;
}