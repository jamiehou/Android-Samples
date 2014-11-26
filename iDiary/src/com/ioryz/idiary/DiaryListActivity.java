package com.ioryz.idiary;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class DiaryListActivity extends Activity {
	
	private ListView diaryList;
	private List<Map<String, String>> diaryData;
	
	private static String DIARY_ID = "dairy_id";
	private static String DIARY_TITLE = "dairy_title";
	private static String DIARY_DATE = "dairy_date";
	private static String TAG = "DiaryListActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        
        diaryList = (ListView) findViewById(R.id.list_diary);
        SimpleAdapter adapter = new SimpleAdapter(this, getDiaryListData(), R.layout.cell_diary, 
        		new String[]{DIARY_TITLE, DIARY_DATE}, new int[]{R.id.txt_diary_title, R.id.txt_diary_date});
        diaryList.setAdapter(adapter);
        diaryList.setOnItemClickListener(new DiaryItemClickListener());
    }
    
    private List<Map<String, String>> getDiaryListData() {
    	Log.i(TAG, "get diary list data...");
    	DBHelper dbHelper = new DBHelper(DiaryListActivity.this, DBDefiniations.TBL_DIARY);
    	SQLiteDatabase db = dbHelper.getReadableDatabase();
    	Cursor cursor = db.query(DBDefiniations.TBL_DIARY, new String[]{DBDefiniations.COL_DIARY_ID, 
    			DBDefiniations.COL_DIARY_DATE, DBDefiniations.COL_DIARY_TITLE, DBDefiniations.COL_DIARY_CONTENT},
    			null, null, null, null, null);
    	diaryData = new ArrayList<Map<String, String>>();
    	while (cursor.moveToNext()) {
    		Map<String, String> map = new HashMap<String, String>();
    		map.put(DIARY_ID, String.valueOf(cursor.getInt(cursor.getColumnIndex(DBDefiniations.COL_DIARY_ID))));
    		map.put(DIARY_TITLE, cursor.getString(cursor.getColumnIndex(DBDefiniations.COL_DIARY_TITLE)));
    		map.put(DIARY_DATE, cursor.getString(cursor.getColumnIndex(DBDefiniations.COL_DIARY_DATE)));
    		diaryData.add(map);
    	}
    	Log.i(TAG, "get diary list data complete...");
    	
    	return diaryData;
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	MenuInflater inflater = getMenuInflater();
    	inflater.inflate(R.menu.list, menu);
    	return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	Log.i(TAG, "menu clicked...");
    	switch (item.getItemId()) {
    	case R.id.action_add:
    		Log.i(TAG, "menu item add selected...");
    		Intent intent = new Intent(this, EditDiaryActivity.class);
        	startActivity(intent);
        	break;
    	}
    	return super.onOptionsItemSelected(item);
    }
    
    class DiaryItemClickListener implements OnItemClickListener {

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			Log.i(TAG, "item " + arg2 + " clicked...");
			Map<String, String> map = diaryData.get(arg2);
			int id = Integer.valueOf(map.get(DIARY_ID));
			Intent intent = new Intent(DiaryListActivity.this, EditDiaryActivity.class);
			intent.putExtra(Constants.EXT_DIARY_ID, id);
			startActivity(intent);
		}
    }
}
