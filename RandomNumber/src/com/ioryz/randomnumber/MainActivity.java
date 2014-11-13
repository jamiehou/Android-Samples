package com.ioryz.randomnumber;

import java.util.Random;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity {
	
	private EditText txtNumFrom;
	private EditText txtNumTo;
	private TextView lbResult;
	private Button btnPlay;
	int from;
	int to;
	int result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        txtNumFrom = (EditText) findViewById(R.id.num_from);
        txtNumTo = (EditText) findViewById(R.id.num_to);
        lbResult = (TextView) findViewById(R.id.txt_result);
        btnPlay = (Button) findViewById(R.id.btn_play);
        
        btnPlay.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				from = Integer.parseInt(txtNumFrom.getText().toString());
		        to = Integer.parseInt(txtNumTo.getText().toString());
		        System.out.println("***************from=>" + from + ", to=>" + to);
		        if (from <= to) {
		        	Random r = new Random();
		        	result = r.nextInt(to - from) + from;
		        	System.out.println("********result=>" + result);
		        } else {
		        	result = 0;
		        	Toast.makeText(MainActivity.this, "Invalid input!", Toast.LENGTH_SHORT).show();
		        }
		        lbResult.setText(result + "");
			}
		});
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
