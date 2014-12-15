package com.ioryz.downloaddemo;

import java.io.File;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	private static final String TAG = "MainActivity";
	private static String SAVE_PATH = "sd_download" + File.separator;
	
	private EditText txtUrl;
	private Button btnDown2Print;
	private Button btnDown2SD;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		txtUrl = (EditText) findViewById(R.id.txt_download_url);
		btnDown2Print = (Button) findViewById(R.id.btn_download_to_print);
		btnDown2SD = (Button) findViewById(R.id.btn_download_to_sdcard);
		
		btnDown2Print.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (validateUrl()) {
					new Thread(runDownAsString).start();
				}
			}
		});
		
		btnDown2SD.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (validateUrl()) {
					new Thread(runDown2SD).start();
				}
			}
		});
	}
	
	/**
	 * Get download result and toast to end user
	 * 
	 * @param r	-1: Download failed; 0: Download successful; 1: File already exist
	 */
	private void showDownloadResult(int r) {
		if (r == HttpDownloader.DOWNLOAD_SUCCESS) {
			Log.i(TAG, "Download successful...");
			Toast.makeText(MainActivity.this, R.string.msg_download_complete, Toast.LENGTH_SHORT).show();
		} else if (r == HttpDownloader.DOWNLOAD_FAIL) {
			Log.i(TAG, "Download fialed...");
			Toast.makeText(MainActivity.this, R.string.msg_download_fail, Toast.LENGTH_SHORT).show();
		} else if (r == HttpDownloader.FILE_EXIST) {
			Log.i(TAG, "File already exist.");
			Toast.makeText(MainActivity.this, R.string.msg_file_exist, Toast.LENGTH_SHORT).show();
		}
	}
	
	/**
	 * Empty validation for input url
	 * 
	 * @return	true: Url is empty, validation fail; false: Url is not empty, validation pass
	 */
	private boolean isUrlEmpty() {
		String url = txtUrl.getText().toString();
		if (url == null || url.isEmpty()) {
			Log.i(TAG, "Validation failed => Empty url");
			Toast.makeText(MainActivity.this, R.string.msg_url_empty, Toast.LENGTH_SHORT).show();
			return true;
		} else {
			Log.i(TAG, "Validation passed...");
			return false;
		}
	}
	
	private boolean validateUrl() {
		if (isUrlEmpty())
			return false;
		String url = txtUrl.getText().toString();
		if (StringUtil.isUrlValid(url)) {
			Log.i(TAG, "Url is valid, is going to download from url=>" + url);
		} else {
			Toast.makeText(MainActivity.this, R.string.msg_invalid_download_url, Toast.LENGTH_SHORT).show();
			return false;
		}
		return true;
	}
	
	@SuppressLint("HandlerLeak")
	Handler handler = new Handler() {
		
		@Override
		public void handleMessage(Message msg) {
			int result = msg.arg1;
			showDownloadResult(result);
		};
	};

	Runnable runDownAsString = new Runnable() {
		
		@Override
		public void run() {
			String url = txtUrl.getText().toString();
			Log.i(TAG, "Url is valid, is going to download from url=>" + url);
			HttpDownloader downloader = new HttpDownloader();
			int r = downloader.download(url);
			Message msg = new Message();
			msg.arg1 = r;
			handler.sendMessage(msg);
		}
	};
	
	Runnable runDown2SD = new Runnable() {
		
		@Override
		public void run() {
			String url = txtUrl.getText().toString();
			Log.i(TAG, "Url is valid, is going to download from url=>" + url);
			HttpDownloader downloader = new HttpDownloader();
			int r = downloader.download(url, SAVE_PATH, null);
			Message msg = new Message();
			msg.arg1 = r;
			handler.sendMessage(msg);
		}
	};
}
