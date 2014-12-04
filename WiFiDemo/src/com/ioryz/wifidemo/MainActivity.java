package com.ioryz.wifidemo;

import android.support.v7.app.ActionBarActivity;
import android.app.Service;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {
	
	private Button btnEnable;
	private Button btnDisable;
	private Button btnCheckStatus;
	
	private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        btnEnable = (Button) findViewById(R.id.btn_enable_wifi);
        btnDisable = (Button) findViewById(R.id.btn_disable_wifi);
        btnCheckStatus = (Button) findViewById(R.id.btn_check_wifi_status);
        btnEnable.setOnClickListener(new ButtonOnClickListener());
        btnDisable.setOnClickListener(new ButtonOnClickListener());
        btnCheckStatus.setOnClickListener(new ButtonOnClickListener());
    }

    class ButtonOnClickListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			Log.i(TAG, "In button listener...");
			WifiManager manager = (WifiManager) MainActivity.this.getSystemService(Service.WIFI_SERVICE);
			int status  = manager.getWifiState();
			
			switch (v.getId()) {
			case R.id.btn_enable_wifi:
				Log.i(TAG, "Enable button clicked...");
				if (status == WifiManager.WIFI_STATE_ENABLED) {
					Toast.makeText(MainActivity.this, R.string.msg_wifi_already_enabled, Toast.LENGTH_SHORT).show();
				} else if (status == WifiManager.WIFI_STATE_DISABLED) {
					manager.setWifiEnabled(true);
					Toast.makeText(MainActivity.this, R.string.msg_wifi_enable, Toast.LENGTH_SHORT).show();
				} else if (status == WifiManager.WIFI_STATE_DISABLING || status == WifiManager.WIFI_STATE_ENABLING) {
					Toast.makeText(MainActivity.this, R.string.msg_wifi_proceeding, Toast.LENGTH_SHORT).show();
				} else if (status == WifiManager.WIFI_STATE_UNKNOWN) {
					Toast.makeText(MainActivity.this, R.string.msg_wifi_status_unknow, Toast.LENGTH_SHORT).show();
				}
				break;
			case R.id.btn_disable_wifi:
				Log.i(TAG, "Disable button clicked...");
				if (status == WifiManager.WIFI_STATE_ENABLED) {
					manager.setWifiEnabled(false);
					Toast.makeText(MainActivity.this, R.string.msg_wifi_disable, Toast.LENGTH_SHORT).show();
				} else if (status == WifiManager.WIFI_STATE_DISABLED) {
					Toast.makeText(MainActivity.this, R.string.msg_wifi_already_disabled, Toast.LENGTH_SHORT).show();
				} else if (status == WifiManager.WIFI_STATE_DISABLING || status == WifiManager.WIFI_STATE_ENABLING) {
					Toast.makeText(MainActivity.this, R.string.msg_wifi_proceeding, Toast.LENGTH_SHORT).show();
				} else if (status == WifiManager.WIFI_STATE_UNKNOWN) {
					Toast.makeText(MainActivity.this, R.string.msg_wifi_status_unknow, Toast.LENGTH_SHORT).show();
				}
				break;
			case R.id.btn_check_wifi_status:
				Log.i(TAG, "Check status button clicked...");
				Toast.makeText(MainActivity.this, getWiFiStatus(status), Toast.LENGTH_SHORT).show();
			}
		}
    	
		private int getWiFiStatus(int s) {
			switch (s) {
			case 0:
				return R.string.msg_wifi_status_Disabling;
			case 1:
				return R.string.msg_wifi_status_disabled;
			case 2:
				return R.string.msg_wifi_status_enabling;
			case 3:
				return R.string.msg_wifi_status_enabled;
			default:
				return R.string.msg_wifi_status_unknow;
			}
		}
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
