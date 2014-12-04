package com.ioryz.broadcastdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class DynamicReceiver extends BroadcastReceiver {
	
	private static final String TAG = "DynamicReceiver";
	
	public DynamicReceiver() {
		super();
		Log.i(TAG, "DynamicReceiver init...");
	}

	@Override
	public void onReceive(Context context, Intent intent) {
		Log.i(TAG, "Method onReceive execute...");
	}
}
