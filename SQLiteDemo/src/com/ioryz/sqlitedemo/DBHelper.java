package com.ioryz.sqlitedemo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper {
	
	public static int VERSION = 1;
	private final static String TAG = "DBHelper";
	
	public DBHelper(Context context, String name, CursorFactory factory, int version) {
		super(context, name, factory, version);
	}
	
	public DBHelper(Context context, String name, int version) {
		this(context, name, null, version);
	}
	
	public DBHelper(Context context, String name) {
		this(context, name, VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		Log.i(TAG, "Create DB");
		db.execSQL("create table " + DBDefiniations.TBL_USER + "(" + DBDefiniations.COL_USER_ID + 
				" int, " + DBDefiniations.COL_USER_NAME + " varchar(20))");
	}
	

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		Log.i(TAG, "Update database...");
	}
}
