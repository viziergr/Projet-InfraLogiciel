package com.timefusion.sync;

import com.timefusion.ui.calendar.DisplaySync;
import demo.Main;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class SyncScheduler {

  private static final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(
    1
  );

  public static void main(String[] args) {
    scheduleSync();

    // Launch the JavaFX application
    Main.main(args);

    // Add a shutdown hook to shut down the scheduler when the JavaFX application exits
    Runtime
      .getRuntime()
      .addShutdownHook(
        new Thread(() -> {
          scheduler.shutdown();
          try {
            if (!scheduler.awaitTermination(1, TimeUnit.SECONDS)) {
              scheduler.shutdownNow();
            }
          } catch (InterruptedException e) {
            scheduler.shutdownNow();
          }
        })
      );
  }

  public static void scheduleSync() {
    scheduler.scheduleAtFixedRate(
      () -> {
        try {
          // LocalToRemoteEventSync.synchronize(new EventDao());
          // RemoteToLocalEventSync.synchronizeEvents(new DatabaseUtil());
          DisplaySync.synchronizeDisplay();
        } catch (Exception e) {
          e.printStackTrace();
        }
      },
      0,
      10,
      TimeUnit.SECONDS
    );
  }
}
