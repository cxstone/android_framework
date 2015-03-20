package com.example.android_framework.service;

import java.io.FileDescriptor;
import java.io.PrintWriter;

import android.app.Service;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.IBinder;

import com.example.android_framework.util.Logger;

public class TestService extends Service {
	private static final String TAG = TestService.class.getSimpleName();
	private int NUMBER = 0;

	private static final String PATH = "com.example.android_framework";
	private Thread task;

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Logger.d(TAG, "onStartCommand");
//		TestReceiver receiver = new TestReceiver();
//		IntentFilter filter = new IntentFilter();
//		filter.addAction("android.intent.action.TIME_TICK");
//		filter.addAction("android.intent.action.USER_PRESENT");
//		registerReceiver(receiver, filter);
		return START_STICKY;
	}

	@Override
	public void onCreate() {
		Logger.d(TAG, "onCreate");
		super.onCreate();
	}

	@Override
	protected void dump(FileDescriptor fd, PrintWriter writer, String[] args) {
		Logger.d(TAG, "dump");
		super.dump(fd, writer, args);
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		Logger.d(TAG, "onConfigurationChanged");
		super.onConfigurationChanged(newConfig);
	}

	@Override
	public void onLowMemory() {
		Logger.d(TAG, "onLowMemory");
		super.onLowMemory();
	}

	@Override
	public void onRebind(Intent intent) {
		Logger.d(TAG, "onRebind");
		super.onRebind(intent);
	}

	@Override
	@Deprecated
	public void onStart(Intent intent, int startId) {
		Logger.d(TAG, "onStart");
		super.onStart(intent, startId);
	}


	@Override
	public boolean onUnbind(Intent intent) {
		Logger.d(TAG, "onUnbind");
		return super.onUnbind(intent);
	}

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public void onDestroy() {
		Logger.d(TAG, "onDestroy");
		stopForeground(true);
		Intent intent = new Intent("com.example.android_framework.destroy");
		sendBroadcast(intent);
		super.onDestroy();
	}

	class TaskThread implements Runnable {

		@Override
		public void run() {
			while (true) {
				try {
					Logger.d(TAG, NUMBER++ + " --> work!!!");
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}

	}

}
