package com.ioryz.multithreaddemo;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.widget.ProgressBar;

public class MyHandler extends Handler {
	
	private ProgressBar progress;
	
	private static final String TAG = "MyHandler";
	
	public MyHandler(Looper looper) {
		super(looper);
	}
	
	public MyHandler(ProgressBar p, Looper looper) {
		super(looper);
		this.progress = p;
	}
	
	@Override
	public void handleMessage(Message msg) {
		Log.d(TAG, "handle message... => arg1 = " + msg.arg1);
		int value = msg.arg1;
		while (value < 100) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			value += 10;
			Log.i(TAG, "update progress... => progress = " + value);
			this.progress.setProgress(value);
		}
	}
}
