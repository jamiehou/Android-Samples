package com.ioryz.idiary;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class DiaryListActivity extends Activity {
	
	private ListView diaryList;
	
	private static String DIARY_TITLE = "dairy_title";
	private static String DIARY_DATE = "dairy_date";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	Log.d("Debug", "onCreate start...");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        
        diaryList = (ListView) findViewById(R.id.list_diary);
        Log.d("Debug", "Before adpter create...");
        SimpleAdapter adapter = new SimpleAdapter(this, getDiaryListData(), R.layout.cell_diary, new String[]{DIARY_TITLE, DIARY_DATE}, new int[]{R.id.txt_diary_title, R.id.txt_diary_date});
        Log.d("Debug", "After adpter create...");
        diaryList.setAdapter(adapter);
    }
    
    private List<Map<String, String>> getDiaryListData() {
    	List<Map<String, String>> list = new ArrayList<Map<String, String>>();
    	
    	Map<String, String> map = new HashMap<String, String>();
    	map.put(DIARY_TITLE, "Bought an mp3 player");
    	map.put(DIARY_DATE, "2014-11-11");
    	list.add(map);
    	
    	map = new HashMap<String, String>();
    	map.put(DIARY_TITLE, "Bought an earphone");
    	map.put(DIARY_DATE, "2014-11-13");
    	list.add(map);
    	
    	map = new HashMap<String, String>();
    	map.put(DIARY_TITLE, "MX4 Pro is coming");
    	map.put(DIARY_DATE, "2014-11-19");
    	list.add(map);
    	
    	return list;
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	// TODO Auto-generated method stub
    	super.onCreateOptionsMenu(menu);
    	MenuItem add = menu.add("+");
    	add.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
    	return true;
    }
}
