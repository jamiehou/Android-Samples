package com.ioryz.asyncimageloader;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;

public class MainActivity extends Activity {
	
	AsyncImageLoader loader = new AsyncImageLoader();
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        loadImage("http://img34.ddimg.cn/53/10/1090586924-1_l.jpg", R.id.img_01);
        loadImage("http://img33.ddimg.cn/8/1/1122846623-1_l.jpg", R.id.img_02);
        loadImage("http://img39.ddimg.cn/29/0/410251079-1_l.jpg", R.id.img_03);
        loadImage("http://img32.ddimg.cn/79/13/60548182-1_l.jpg", R.id.img_04);
    }
    
    private void loadImage(String url, int id) {
    	ImageView image = (ImageView) findViewById(id);
    	ImageLoaderCallbackImp callback = new ImageLoaderCallbackImp(image);
    	Drawable d = loader.loadImage(url, callback);
    	if (d != null) {
    		image.setImageDrawable(d);
    	}
    }
}
