package com.ioryz.broadcastdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class StaticReceiver extends BroadcastReceiver {
	
	private static final String TAG = "StaticReceiver";
	
	public StaticReceiver() {
		super();
		Log.i(TAG, "Static Receiver init...");
	}

	@Override
	public void onReceive(Context context, Intent intent) {
		Log.i(TAG, "Method onReceive execute...");
	}
}
