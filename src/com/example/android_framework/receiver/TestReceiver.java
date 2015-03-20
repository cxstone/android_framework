package com.example.android_framework.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.example.android_framework.util.Logger;

public class TestReceiver extends BroadcastReceiver {
	private static final String TAG = TestReceiver.class.getSimpleName();

	@Override
	public void onReceive(Context context, Intent intent) {
		if ("com.example.android_framework.destroy".equals(intent.getAction())) {
			Logger.d(TAG, "Service被销毁的通知！");
		} else if (Intent.ACTION_TIME_TICK.equals(intent.getAction())) {
			Logger.d(TAG, "系统时间改变通知！");
		} else if (Intent.ACTION_USER_PRESENT.equals(intent.getAction())) {
			Logger.d(TAG, "解锁通知！");
		} else if (Intent.ACTION_BOOT_COMPLETED.equals(intent.getAction())) {
			Logger.d(TAG, "开机完成！");
		}

	}

}
