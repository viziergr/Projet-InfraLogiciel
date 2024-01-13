module com.timefusion {
  requires transitive javafx.controls;
  requires transitive javafx.fxml;
  requires transitive javafx.graphics;
  requires transitive javafx.base;
  requires transitive java.sql;
  requires transitive java.desktop;
  requires transitive jbcrypt;
  requires transitive de.jensd.fx.glyphs.fontawesome;
  requires transitive org.controlsfx.controls;
  requires transitive com.jfoenix;
  requires transitive com.google.gson;

  opens com.timefusion.ui to javafx.fxml;
  opens com.timefusion.util to java.sql;
  opens com.timefusion.jfxcalendar.controllers to javafx.fxml;
  opens com.timefusion.jfxcalendar.views to com.jfoenix;
  opens com.timefusion.jfxcalendar.JSON.Entities to com.google.gson;
  opens com.timefusion.jfxcalendar.JSON to com.google.gson;

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
