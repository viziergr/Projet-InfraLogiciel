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
  opens com.timefusion.JSON.Entities to com.google.gson;
  opens com.timefusion.JSON to com.google.gson;
  opens com.timefusion.calendar.Application to javafx.fxml;
  opens com.timefusion.calendar.controller to javafx.fxml;
  opens com.timefusion.calendar.view to javafx.fxml;
  opens com.jibbow.fastis to javafx.fxml;
  opens com.jibbow.fastis.components to javafx.fxml;
  opens com.jibbow.fastis.rendering to javafx.fxml;
  opens com.jibbow.fastis.util to javafx.fxml;
  opens demo to javafx.fxml;

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
  exports com.timefusion.calendar.Application ;
  exports com.timefusion.calendar.controller ;
  exports com.timefusion.calendar.view ;
  exports com.jibbow.fastis ;
  exports com.jibbow.fastis.components ;
  exports com.jibbow.fastis.rendering ;
  exports com.jibbow.fastis.util ;
  exports demo ;
}
