package com.ouyangzn.android_dev_art_training;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.ouyangzn.android_dev_art_training.base.BaseActivity;
import com.ouyangzn.android_dev_art_training.module.chapter2.messenger.Chapter2MessengerActivity;
import com.ouyangzn.android_dev_art_training.module.mutil_process_sp.NormalActivity;

public class MainActivity extends BaseActivity {

  @Override protected int getContentResId() {
    return R.layout.activity_main;
  }

  @Override protected void initData() {

  }

  @Override protected void initView(Bundle savedInstanceState) {

  }

  public void startProcess(View v) {
    startActivity(new Intent(this, NormalActivity.class));
  }

  public void chapter2_messenger(View v) {
    startActivity(new Intent(this, Chapter2MessengerActivity.class));
  }
}
