package com.ioryz.multithreaddemo;

import android.os.AsyncTask;
import android.widget.ProgressBar;

public class ProgressAsnycTask extends AsyncTask<Integer, Integer, String> {
	
	private ProgressBar progress;
	private int progressValue;

	public ProgressAsnycTask(ProgressBar p) {
		progress = p;
	}
	
	@Override
	protected String doInBackground(Integer... arg0) {
		while (progressValue < 100) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			progressValue += 10;
			publishProgress(progressValue);
		}
		
		return null;
	}
	
	@Override
	protected void onPreExecute() {
		// DO nothing.
	}
	
	@Override
	protected void onPostExecute(String result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
	}
	
	@Override
	protected void onProgressUpdate(Integer... values) {
		progress.setProgress(values[0]);
	}
}
