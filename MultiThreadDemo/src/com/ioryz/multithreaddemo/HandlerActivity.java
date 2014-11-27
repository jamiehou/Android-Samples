package com.ioryz.multithreaddemo;

import android.app.Activity;
import android.os.Bundle;
import android.os.HandlerThread;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;

public class HandlerActivity extends Activity {
	
	private ProgressBar progress;
	private Button btnGo;
	private int pValue;
	private HandlerThread thread;
	private MyHandler myHandler;
	
	private static final String TAG = "HandlerActivity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_handler);
		
		progress = (ProgressBar) findViewById(R.id.handler_progress);
		btnGo = (Button) findViewById(R.id.btn_handler_go);
		progress.setMax(100);
		btnGo.setOnClickListener(new ButtonOnClickListener());
	}
	
	class ButtonOnClickListener implements OnClickListener {

		@Override
		public void onClick(View arg0) {
			progress.setVisibility(View.VISIBLE);
			pValue = 0;
			if (thread == null) {
				thread = new HandlerThread("Handler thread...");
				thread.start();
				Log.i(TAG, "handler thread start...");
			}
			if (myHandler == null) {
				myHandler = new MyHandler(progress, thread.getLooper());
			}
			Message msg = myHandler.obtainMessage();
			msg.arg1 = pValue;
			msg.sendToTarget();
			Log.i(TAG, "message send to target...");
		}
	}
}
