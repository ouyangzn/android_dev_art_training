package com.ouyangzn.android_dev_art_training.module.mutil_process_sp;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import com.ouyangzn.android_dev_art_training.R;
import com.ouyangzn.android_dev_art_training.base.BaseActivity;
import com.ouyangzn.android_dev_art_training.utils.Log;
import com.ouyangzn.android_dev_art_training.utils.SpUtils;
import com.ouyangzn.android_dev_art_training.utils.Utils;

import static com.ouyangzn.android_dev_art_training.module.mutil_process_sp.NormalActivity.KEY_TEST_DATA;

public class ProcessActivity extends BaseActivity {

  @Override protected int getContentResId() {
    return R.layout.activity_process;
  }

  @Override protected void initData() {
    final SharedPreferences sp = SpUtils.getSpMultiProcess(getApplicationContext());
    SharedPreferences.Editor editor = sp.edit();
    for (int i = 999; i >= 0; i--) {
      //editor.putInt(KEY_TEST_DATA, i).apply();
      editor.putInt(KEY_TEST_DATA, i).commit();
      Log.d(TAG, "当前进程: "
          + Utils.getProcessName(mContext.getApplicationContext())
          + ", i = "
          + i
          + " ,sp中i = "
          + SpUtils.getSpMultiProcess(getApplicationContext()).getInt(KEY_TEST_DATA, -1));
    }
    new Handler().postDelayed(new Runnable() {
      @Override public void run() {
        //Log.d(TAG, sp.getInt(KEY_TEST_DATA, -1) + "");
        Log.d(TAG,
            SpUtils.getSpMultiProcess(getApplicationContext()).getInt(KEY_TEST_DATA, -1) + "");
      }
    }, 1000);
  }

  @Override protected void initView(Bundle savedInstanceState) {

  }
}
