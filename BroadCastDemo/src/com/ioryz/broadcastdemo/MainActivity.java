package com.ioryz.broadcastdemo;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {
	
	private Button btnSend;
	private Button btnRegister;
	private Button btnUnregister;
	private DynamicReceiver receiver;
	
	private static final String TAG = "MainActivity";
	private static final String ACTION_VIEW = "android.intent.action.VIEW";
	private static final String ACTION_MSG_RECEIVED = "android.provider.Telephony.SMS_RECEIVED";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		btnSend = (Button) findViewById(R.id.btn_send_intent);
		btnRegister = (Button) findViewById(R.id.btn_register_receiver);
		btnUnregister = (Button) findViewById(R.id.btn_unregister_receiver);
		
		btnSend.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Log.i(TAG, "Send button clicked...");
				Intent intent = new Intent();
				intent.setAction(ACTION_VIEW);
				MainActivity.this.sendBroadcast(intent);
			}
		});
		
		btnRegister.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Log.i(TAG, "Register button clicked...");
				receiver = new DynamicReceiver();
				IntentFilter filter = new IntentFilter();
				filter.addAction(ACTION_MSG_RECEIVED);
				MainActivity.this.registerReceiver(receiver, filter);
				Log.i(TAG, "Receiver registered...");
			}
		});
		
		btnUnregister.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Log.i(TAG, "Unregister button clicked...");
				MainActivity.this.unregisterReceiver(receiver);
				Log.i(TAG, "Receiver unregistered...");
			}
		});
	}
}
