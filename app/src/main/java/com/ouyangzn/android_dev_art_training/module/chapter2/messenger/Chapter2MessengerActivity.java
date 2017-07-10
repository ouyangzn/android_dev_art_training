package com.ouyangzn.android_dev_art_training.module.chapter2.messenger;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import com.ouyangzn.android_dev_art_training.R;
import com.ouyangzn.android_dev_art_training.base.BaseActivity;
import com.ouyangzn.android_dev_art_training.utils.Log;
import java.lang.ref.WeakReference;

/**
 * Created by ouyangzn on 2017/7/10.<br/>
 * Description：
 */
public class Chapter2MessengerActivity extends BaseActivity {

  public static final int MSG_HELLO = 1;
  public static final int MSG_HELLO_2 = 2;
  public static final String MSG_DATA_KEY_HELLO = "key_hello";

  private ServiceConnection mConnection;
  private Messenger mCommunityMessenger;

  @Override protected int getContentResId() {
    return R.layout.layout_activity_chapter2_messenger;
  }

  @Override protected void initData() {
    mCommunityMessenger = new Messenger(new MessengerHandler(Chapter2MessengerActivity.this));
    Intent intent = new Intent(this, Chapter2MessengerService.class);
    mConnection = new ServiceConnection() {
      @Override public void onServiceConnected(ComponentName name, IBinder service) {
        Messenger messenger = new Messenger(service);
        Message msg = Message.obtain();
        msg.what = MSG_HELLO;
        msg.replyTo = mCommunityMessenger;
        Bundle bundle = new Bundle(1);
        bundle.putString(MSG_DATA_KEY_HELLO, "hello service!");
        msg.setData(bundle);
        try {
          messenger.send(msg);
        } catch (RemoteException e) {
          Log.e(TAG, "send message error:" + e.getMessage(), e);
        }
      }

      @Override public void onServiceDisconnected(ComponentName name) {
        Log.d(TAG, "oh, service + " + name + " disconnected!");
      }
    };
    getApplicationContext().bindService(intent, mConnection, BIND_AUTO_CREATE);
    //startService(intent);
  }

  @Override protected void initView(Bundle savedInstanceState) {

  }

  @Override protected void onDestroy() {
    super.onDestroy();
    getApplicationContext().unbindService(mConnection);
  }

  private static class MessengerHandler extends Handler {

    private WeakReference<Chapter2MessengerActivity> mReference;

    public MessengerHandler(Chapter2MessengerActivity activity) {
      mReference = new WeakReference<Chapter2MessengerActivity>(activity);
    }

    @Override public void handleMessage(Message msg) {
      Chapter2MessengerActivity activity = mReference.get();
      if (activity != null) {
        switch (msg.what) {
          case MSG_HELLO: {
            Log.d(activity.TAG, "----------received message from service：" + msg.getData()
                .getString(MSG_DATA_KEY_HELLO));
            if (msg.replyTo != null) {
              Message reply = Message.obtain();
              reply.what = MSG_HELLO_2;
              reply.replyTo = activity.mCommunityMessenger;
              Bundle bundle = new Bundle(1);
              bundle.putString(MSG_DATA_KEY_HELLO, "haha.");
              reply.setData(bundle);
              try {
                msg.replyTo.send(reply);
              } catch (RemoteException e) {
                Log.e(activity.TAG, "----------reply message error：" + e.getMessage(), e);
              }
            }
            break;
          }
          case MSG_HELLO_2: {
            Log.d(activity.TAG, "----------received message from service：" + msg.getData()
                .getString(MSG_DATA_KEY_HELLO));
            break;
          }
          default:
            super.handleMessage(msg);
        }
      }
    }
  }
}
