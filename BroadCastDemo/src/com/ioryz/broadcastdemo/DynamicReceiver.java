package com.ioryz.broadcastdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;

public class DynamicReceiver extends BroadcastReceiver {
	
	private static final String TAG = "DynamicReceiver";
	
	public DynamicReceiver() {
		Log.i(TAG, "DynamicReceiver init...");
	}

	@Override
	public void onReceive(Context context, Intent intent) {
		Log.i(TAG, "Method onReceive execute...");
		Bundle bundle = intent.getExtras();
		Object[] messages = (Object[]) bundle.get("pdus");
		SmsMessage[] smsMsgs = new SmsMessage[messages.length];
		for (int i = 0; i < messages.length; i++) {
			smsMsgs[i] = SmsMessage.createFromPdu((byte[]) messages[i]);
			Log.i(TAG, "Received sms message=>" + smsMsgs[i].getDisplayMessageBody() +
					" from " + smsMsgs[i].getDisplayOriginatingAddress());
		}
	}
}
