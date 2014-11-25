package com.ioryz.sqlitedemo;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {
	
	private Button btnCreateDB;
	private Button btnUpdateDB;
	private Button btnDeleteDB;
	private Button btnInsert;
	private Button btnUpdate;
	private Button btnQuery;
	
	private final static String TAG = "MainActivity";
	private int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        btnCreateDB = (Button) findViewById(R.id.btn_create_db);
        btnUpdateDB = (Button) findViewById(R.id.btn_udpate_db);
        btnDeleteDB = (Button) findViewById(R.id.btn_delete_db);
        btnInsert = (Button) findViewById(R.id.btn_insert);
        btnUpdate = (Button) findViewById(R.id.btn_update);
        btnQuery = (Button) findViewById(R.id.btn_query);
        
        btnCreateDB.setOnClickListener(new CreateDBListener());
        btnUpdateDB.setOnClickListener(new UpdateDBListener());
        btnDeleteDB.setOnClickListener(new DeleteDBListener());
        btnInsert.setOnClickListener(new InsertListener());
        btnUpdate.setOnClickListener(new UpdateListener());
        btnQuery.setOnClickListener(new QueryListener());
    }
    
    class CreateDBListener implements OnClickListener {

		@Override
		public void onClick(View arg0) {
			Log.i(TAG, "create database...");
			DBHelper dbHelper = new DBHelper(MainActivity.this, DBDefiniations.TBL_USER);
			dbHelper.getReadableDatabase();
			Log.i(TAG, "database created...");
		}
    }
    
    class UpdateDBListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			DBHelper.VERSION += 1;
			Log.i(TAG, "update database...");
			DBHelper dbHelper = new DBHelper(MainActivity.this, DBDefiniations.TBL_USER, DBHelper.VERSION);
			dbHelper.getReadableDatabase();
			Log.i(TAG, "database udpated...");
		}
    }
    
    class DeleteDBListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			Log.i(TAG, "delete table user...");
			DBHelper dbHelper = new DBHelper(MainActivity.this, DBDefiniations.TBL_USER, DBHelper.VERSION);
			SQLiteDatabase db = dbHelper.getWritableDatabase();
			db.execSQL("drop table user");
			Log.i(TAG, "table user deleted...");
		}
    }
    
    class InsertListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			Log.i(TAG, "insert record...");
			count += 1;
			ContentValues values = new ContentValues();
			values.put(DBDefiniations.COL_USER_ID, 1);
			values.put(DBDefiniations.COL_USER_NAME, "Jamie_" + count);
			DBHelper dbHelper = new DBHelper(MainActivity.this, DBDefiniations.TBL_USER);
			SQLiteDatabase db = dbHelper.getWritableDatabase();
			db.insert(DBDefiniations.TBL_USER, null, values);
			Log.i(TAG, "Jamie_" + count + " inserted...");
		}
    }
    
    class UpdateListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			Log.i(TAG, "update record...");
			ContentValues values = new ContentValues();
			values.put(DBDefiniations.COL_USER_NAME, "Jamie_Hou");
			DBHelper dbHelper = new DBHelper(MainActivity.this, DBDefiniations.TBL_USER);
			SQLiteDatabase db = dbHelper.getWritableDatabase();
			db.update(DBDefiniations.TBL_USER, values, "id=?", new String[]{"1"});
			Log.i(TAG, "record 1 updated to Jamie_Hou");
		}
    }
    
    class QueryListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			Log.i(TAG, "query users...");
			DBHelper dbHelper = new DBHelper(MainActivity.this, DBDefiniations.TBL_USER);
			SQLiteDatabase db = dbHelper.getReadableDatabase();
			Cursor cursor = db.query(DBDefiniations.TBL_USER, new String[]{DBDefiniations.COL_USER_ID, DBDefiniations.COL_USER_NAME}, "ID=?", new String[]{"1"}, null, null, null);
			Log.i(TAG, "get cursor...");
			while (cursor.moveToNext()) {
				String name = cursor.getString(cursor.getColumnIndex(DBDefiniations.COL_USER_NAME));
				Log.i(TAG, "query user=>" + name);
			}
			Log.i(TAG, "query done...");
		}
    }
}
