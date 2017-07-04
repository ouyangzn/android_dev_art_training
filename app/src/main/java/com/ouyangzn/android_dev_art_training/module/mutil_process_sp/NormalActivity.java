package com.ouyangzn.android_dev_art_training.module.mutil_process_sp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import com.ouyangzn.android_dev_art_training.R;
import com.ouyangzn.android_dev_art_training.base.BaseActivity;
import com.ouyangzn.android_dev_art_training.utils.Log;
import com.ouyangzn.android_dev_art_training.utils.SpUtils;
import com.ouyangzn.android_dev_art_training.utils.Utils;

public class NormalActivity extends BaseActivity {

  public static String KEY_TEST_DATA = "testData";

  @Override protected int getContentResId() {
    return R.layout.activity_normal;
  }

  @Override protected void initData() {

  }

  @Override protected void initView(Bundle savedInstanceState) {

  }

  public void startProcess(View v) {
    Intent intent = new Intent(this, ProcessActivity.class);
    startActivity(intent);
    new Thread(new Runnable() {
      @Override public void run() {
        final SharedPreferences sp = SpUtils.getSpMultiProcess(getApplicationContext());
        SharedPreferences.Editor editor = sp.edit();
        for (int i = 0; i <= 999; i++) {
          //editor.putInt(KEY_TEST_DATA, i).apply();
          editor.putInt(KEY_TEST_DATA, i).commit();
          Log.d(TAG, "当前进程: "
              + Utils.getProcessName(mContext.getApplicationContext())
              + ", i = "
              + i
              + " ,sp中i = "
              + SpUtils.getSpMultiProcess(getApplicationContext()).getInt(KEY_TEST_DATA, -1));
        }
        try {
          Thread.sleep(1000);
          //Log.d(TAG, sp.getInt(KEY_TEST_DATA, -1) + "");
          Log.d(TAG,
              SpUtils.getSpMultiProcess(getApplicationContext()).getInt(KEY_TEST_DATA, -1) + "");
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }).start();
  }
}
