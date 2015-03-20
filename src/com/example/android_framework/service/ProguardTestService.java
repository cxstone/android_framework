package com.example.android_framework.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.example.android_framework.util.Logger;

public class ProguardTestService extends Service {
	private static final String TAG = ProguardTestService.class.getSimpleName();

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Logger.d(TAG, "onStartCommand");
		return START_STICKY;
	}

	@Override
	public void onCreate() {
		Logger.d(TAG, "onCreate");
		super.onCreate();
	}

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public void onDestroy() {
		Logger.d(TAG, "onDestroy");
//		stopForeground(true);
//		Intent intent = new Intent("com.example.android_framework.destroy");
//		sendBroadcast(intent);
		super.onDestroy();
	}

}
