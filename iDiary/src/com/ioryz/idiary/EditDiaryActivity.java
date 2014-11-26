package com.ioryz.idiary;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

public class EditDiaryActivity extends Activity {
	
	private EditText txtTitle;
	private EditText txtDate;
	private EditText txtContent;
	private int diaryId;
	
	private static String TAG = "EditDiaryActivity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_diary);
		
		txtTitle = (EditText) findViewById(R.id.txt_diary_title);
		txtDate = (EditText) findViewById(R.id.txt_diary_date);
        txtContent = (EditText) findViewById(R.id.txt_diary_content);
        
        txtDate.setOnTouchListener(new DateOnTouchListener());
        
        Intent intent = getIntent();
        diaryId = intent.getIntExtra(Constants.EXT_DIARY_ID, 0);
        
        if (diaryId != 0) {
        	getDiaryData(diaryId);
        }
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.edit, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		Log.i(TAG, "menu clicked...");
		switch (item.getItemId()) {
		case R.id.action_save:
			if (!validateForm()) {
				Toast.makeText(EditDiaryActivity.this, R.string.msg_form_not_empty, Toast.LENGTH_LONG).show();
				return false;
			}
			Log.i(TAG, "item save selected...");
			String title = txtTitle.getText().toString();
			String date = txtDate.getText().toString();
			String content = txtContent.getText().toString();
			ContentValues values = new ContentValues();
			values.put(DBDefiniations.COL_DIARY_TITLE, title);
			values.put(DBDefiniations.COL_DIARY_DATE, date);
			values.put(DBDefiniations.COL_DIARY_CONTENT, content);
			DBHelper dbHelper = new DBHelper(EditDiaryActivity.this, DBDefiniations.TBL_DIARY);
			SQLiteDatabase db = dbHelper.getWritableDatabase();
			// Insert or update diary to database
			if (diaryId == 0) {
				Log.i(TAG, "execute insert sql...");
				db.insert(DBDefiniations.TBL_DIARY, null, values);
			} else {
				Log.i(TAG, "execute update sql...");
				db.update(DBDefiniations.TBL_DIARY, values, "id=?", new String[]{"" + diaryId});
			}
			break;
		}
		Log.i(TAG, "diary save complete...");
		
		Intent intent = new Intent(EditDiaryActivity.this, DiaryListActivity.class);
		startActivity(intent);
		
		return super.onOptionsItemSelected(item);
	}
	
	private void getDiaryData(int id) {
		Log.i(TAG, "query diary data...");
		DBHelper dbHelper = new DBHelper(EditDiaryActivity.this, DBDefiniations.TBL_DIARY);
		SQLiteDatabase db = dbHelper.getReadableDatabase();
		Cursor cursor = db.query(DBDefiniations.TBL_DIARY, new String[]{DBDefiniations.COL_DIARY_TITLE, 
				DBDefiniations.COL_DIARY_DATE, DBDefiniations.COL_DIARY_CONTENT}, 
				"id=?", new String[]{String.valueOf(diaryId)}, null, null, null);
		while (cursor.moveToNext()) {
			Log.i(TAG, "diary data query loop...");
			txtTitle.setText(cursor.getString(cursor.getColumnIndex(DBDefiniations.COL_DIARY_TITLE)));
			txtDate.setText(cursor.getString(cursor.getColumnIndex(DBDefiniations.COL_DIARY_DATE)));
			txtContent.setText(cursor.getString(cursor.getColumnIndex(DBDefiniations.COL_DIARY_CONTENT)));
		}
	}
	
	@SuppressLint("SimpleDateFormat") class DateOnTouchListener implements OnTouchListener {

		@SuppressLint("ClickableViewAccessibility") @Override
		public boolean onTouch(View v, MotionEvent e) {
			if (e.getAction() == MotionEvent.ACTION_DOWN) {
				DatePickerDialog dateDialog = new DatePickerDialog(EditDiaryActivity.this, new DatePickerDialog.OnDateSetListener() {
					
					@Override
					public void onDateSet(DatePicker v, int y, int m, int d) {
						String dateStr = DateUtil.getDateStr(y, m, d);
						txtDate.setText(dateStr);
					}
				}, 2014, 10, 26);
				
				dateDialog.show();
			}
			return true;
		}
	}
	
	private boolean validateForm() {
		if (txtTitle.getText().toString().isEmpty()) {
			return false;
		}
		if (txtDate.getText().toString().isEmpty()) {
			return false;
		}
		if (txtContent.getText().toString().isEmpty()) {
			return false;
		}
		return true;
	}
}
