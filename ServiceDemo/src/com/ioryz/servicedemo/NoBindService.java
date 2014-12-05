package com.ioryz.servicedemo;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class NoBindService extends Service {

	private static final String TAG = "NoBindService";
	
	@Override
	public void onCreate() {
		Log.i(TAG, "===>onCreate...");
		super.onCreate();
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Log.i(TAG, "===>onStartCommand...");
		return super.onStartCommand(intent, flags, startId);
	}
	
	@Override
	public void onDestroy() {
		Log.i(TAG, "===>onDestory");
		super.onDestroy();
	}
	
	@Override
	public IBinder onBind(Intent intent) {
		Log.i(TAG, "===>onBind...");
		return null;
	}

}
