package com.ioryz.multithreaddemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;

public class AsyncTaskActivity extends Activity {
	
	private ProgressBar progress;
	private Button btnGo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async_task);
        
        progress = (ProgressBar) findViewById(R.id.async_progress);
        progress.setMax(100);
        btnGo = (Button) findViewById(R.id.btn_async_go);
        btnGo.setOnClickListener(new ButtonOnClickListener());
    }
    
    class ButtonOnClickListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			progress.setVisibility(View.VISIBLE);
			ProgressAsnycTask task = new ProgressAsnycTask(progress);
			task.execute();
		}
    }
}
