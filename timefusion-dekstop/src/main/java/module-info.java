module com.timefusion {
  requires javafx.controls;
  requires javafx.fxml;
  requires javafx.graphics;
  requires transitive java.sql;
  requires java.desktop; // Pour les applications Swing

  opens com.timefusion.ui to javafx.fxml; // Changer le nom du package avec le nom du package où JavaFX est utilisé (com.timefusion.ui)
  opens com.timefusion.util to java.sql;

  exports com.timefusion.config ;
  exports com.timefusion.dao ;
  exports com.timefusion.exception ;
  exports com.timefusion.model ;
  exports com.timefusion.service ;
  exports com.timefusion.ui ;
  exports com.timefusion.util ;
}
