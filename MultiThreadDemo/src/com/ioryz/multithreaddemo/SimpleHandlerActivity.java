package com.ioryz.multithreaddemo;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;

public class SimpleHandlerActivity extends Activity {
	
	private ProgressBar progress;
	private Button btnGo;
	private int pValue;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_simple_handler);
		
		progress = (ProgressBar) findViewById(R.id.shandler_progress);
		progress.setMax(100);
		btnGo = (Button) findViewById(R.id.btn_shandler_go);
		btnGo.setOnClickListener(new ButtonOnClickListener());
	}
	
	class ButtonOnClickListener implements OnClickListener {

		@Override
		public void onClick(View arg0) {
			pValue = 0;
			progress.setVisibility(View.VISIBLE);
			handler.post(thread);
		}
	}
	
	@SuppressLint("HandlerLeak") Handler handler = new Handler(){
		
		@Override
		public void handleMessage(Message msg) {
			progress.setProgress(msg.arg1);
		}
	};
	
	Runnable thread = new Runnable() {
		
		@Override
		public void run() {
			pValue += 10;
			Message msg = handler.obtainMessage();
			msg.arg1 = pValue;
			handler.sendMessage(msg);
			if (pValue <= 100) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				handler.post(thread);
			}
		}
	};
}
