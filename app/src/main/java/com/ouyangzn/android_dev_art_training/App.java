package com.ouyangzn.android_dev_art_training;

import android.app.Application;
import com.ouyangzn.android_dev_art_training.utils.SpUtils;

/**
 * Created by ouyangzn on 2017/7/4.<br/>
 * Description：
 */
public class App extends Application {

  @Override public void onCreate() {
    super.onCreate();
    SpUtils.put(this, "app", "I'm application");
  }
}
