package com.timefusion.sync;

import com.jibbow.fastis.DisplaySync;
import com.timefusion.dao.EventDao;
import com.timefusion.util.DatabaseUtil;
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
    Main.main(args);
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
