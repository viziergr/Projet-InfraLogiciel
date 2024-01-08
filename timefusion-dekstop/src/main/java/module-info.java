module com.timefusion {
  requires transitive javafx.controls;
  requires transitive javafx.fxml;
  requires transitive javafx.graphics;
  requires transitive java.sql;
  requires transitive java.desktop; // Pour les applications Swing
  requires transitive jbcrypt; // Pour le cryptage des mots de passe
  requires transitive com.jfoenix;
  requires transitive de.jensd.fx.glyphs.fontawesome;
  requires transitive org.controlsfx.controls;

  opens com.timefusion.ui to javafx.fxml; // Changer le nom du package avec le nom du package où JavaFX est utilisé (com.timefusion.ui)
  opens com.timefusion.util to java.sql;
  opens com.timefusion.jfxcalendar.controllers to javafx.fxml;

  exports com.timefusion.config ;
  exports com.timefusion.dao ;
  exports com.timefusion.exception ;
  exports com.timefusion.model ;
  exports com.timefusion.service ;
  exports com.timefusion.ui ;
  exports com.timefusion.util ;
  exports com.timefusion.jfxcalendar.controllers ;
  exports com.timefusion.jfxcalendar.dialog ;
  exports com.timefusion.jfxcalendar.model ;
  exports com.timefusion.jfxcalendar.validation ;
  exports com.timefusion.jfxcalendar.views ;
}
