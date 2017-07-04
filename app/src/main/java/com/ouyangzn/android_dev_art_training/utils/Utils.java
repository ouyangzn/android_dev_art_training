package com.ouyangzn.android_dev_art_training.utils;

import android.app.ActivityManager;
import android.content.Context;

/**
 * Created by ouyangzn on 2017/7/4.<br/>
 * Description：
 */
public class Utils {

  public static String getProcessName(Context context) {
    int pid = android.os.Process.myPid();
    String processName = "";
    ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
    for (ActivityManager.RunningAppProcessInfo process : manager.getRunningAppProcesses()) {
      if (process.pid == pid) {
        processName = process.processName;
      }
    }
    return processName;
  }
}
