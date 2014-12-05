package com.ioryz.servicedemo;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

@SuppressLint("Recycle")
public class MainActivity extends Activity {
	
	private Button btnBind;
	private Button btnUnbind;
	private Button btnStartNoBind;
	private Button btnStopNoBind;
	private Button btnTransact;
	private Binder binder;
	
	private static final String TAG = "MainActivity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		btnStartNoBind = (Button) findViewById(R.id.btn_start_nobind_service);
		btnStopNoBind = (Button) findViewById(R.id.btn_stop_nobind_service);
		btnBind = (Button) findViewById(R.id.btn_bind_service);
		btnUnbind = (Button) findViewById(R.id.btn_unbind_service);
		btnTransact = (Button) findViewById(R.id.btn_transact_bind_service);
		
		btnStartNoBind.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Log.i(TAG, "Button StartNoBind clicked...");
				Intent intent = new Intent(MainActivity.this, NoBindService.class);
				MainActivity.this.startService(intent);
			}
		});
		
		btnStopNoBind.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Log.i(TAG, "Button StopNoBind clicked...");
				Intent intent = new Intent(MainActivity.this, NoBindService.class);
				MainActivity.this.stopService(intent);
			}
		});
		
		btnBind.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Log.i(TAG, "Button BindService clicked...");
				Intent intent = new Intent(MainActivity.this, BindService.class);
				bindService(intent, conn, BIND_AUTO_CREATE);
			}
		});
		
		btnUnbind.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Log.i(TAG, "Button UnBindService clicked...");
				unbindService(conn);
			}
		});
		
		btnTransact.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Parcel data = Parcel.obtain();
				Parcel reply = Parcel.obtain();
				data.writeString("Jamie");
				data.writeInt(29);
				try {
					binder.transact(0, data, reply, 0);
					Log.i(TAG, "Read String=>" + reply.readString());
					Log.i(TAG, "Read int=>" + reply.readInt());
				} catch (RemoteException e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	private ServiceConnection conn = new ServiceConnection() {
		
		@Override
		public void onServiceDisconnected(ComponentName name) {
			Log.i(TAG, "===>onServiceDisconnected...");
		}
		
		@SuppressLint("Recycle")
		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			Log.i(TAG, "===>onServiceConnected...");
			MainActivity.this.binder = (Binder) service;
		}
	};
}
