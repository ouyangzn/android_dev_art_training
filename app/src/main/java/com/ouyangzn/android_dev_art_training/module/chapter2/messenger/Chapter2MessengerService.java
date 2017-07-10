package com.ouyangzn.android_dev_art_training.module.chapter2.messenger;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import com.ouyangzn.android_dev_art_training.utils.Log;
import java.lang.ref.WeakReference;

import static com.ouyangzn.android_dev_art_training.module.chapter2.messenger.Chapter2MessengerActivity.MSG_DATA_KEY_HELLO;
import static com.ouyangzn.android_dev_art_training.module.chapter2.messenger.Chapter2MessengerActivity.MSG_HELLO;
import static com.ouyangzn.android_dev_art_training.module.chapter2.messenger.Chapter2MessengerActivity.MSG_HELLO_2;

public class Chapter2MessengerService extends Service {

  private static final String TAG = Chapter2MessengerService.class.getSimpleName();

  private Messenger mMessenger;

  public Chapter2MessengerService() {
    Handler handler = new MessengerHandler(this);
    mMessenger = new Messenger(handler);
  }

  @Override public IBinder onBind(Intent intent) {
    return mMessenger.getBinder();
  }

  @Override public int onStartCommand(Intent intent, int flags, int startId) {
    return super.onStartCommand(intent, flags, startId);
  }

  private static class MessengerHandler extends Handler {

    private WeakReference<Chapter2MessengerService> mReference;

    MessengerHandler(Chapter2MessengerService service) {
      mReference = new WeakReference<Chapter2MessengerService>(service);
    }

    @Override public void handleMessage(Message msg) {
      Chapter2MessengerService service = mReference.get();
      if (service == null) return;
      switch (msg.what) {
        case MSG_HELLO: {
          Log.d(TAG, "----------received message from activity：" + msg.getData()
              .getString(MSG_DATA_KEY_HELLO));
          if (msg.replyTo != null) {
            Message reply = Message.obtain();
            reply.what = MSG_HELLO;
            reply.replyTo = service.mMessenger;
            Bundle bundle = new Bundle(1);
            bundle.putString(MSG_DATA_KEY_HELLO, "hi client, i have received your message.");
            reply.setData(bundle);
            try {
              msg.replyTo.send(reply);
            } catch (RemoteException e) {
              Log.e(TAG, "----------reply message error：" + e.getMessage(), e);
            }
          }
          break;
        }
        case MSG_HELLO_2: {
          Log.d(TAG, "----------received message from activity：" + msg.getData()
              .getString(MSG_DATA_KEY_HELLO));
          if (msg.replyTo != null) {
            Message reply = Message.obtain();
            reply.what = MSG_HELLO_2;
            Bundle bundle = new Bundle(1);
            bundle.putString(MSG_DATA_KEY_HELLO, "received.");
            reply.setData(bundle);
            try {
              msg.replyTo.send(reply);
            } catch (RemoteException e) {
              Log.e(TAG, "----------reply message error：" + e.getMessage(), e);
            }
          }
          break;
        }
        default:
          super.handleMessage(msg);
      }
    }
  }
}
