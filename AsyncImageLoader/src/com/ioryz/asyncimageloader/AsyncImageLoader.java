package com.ioryz.asyncimageloader;

import java.io.IOException;
import java.lang.ref.SoftReference;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

@SuppressLint("HandlerLeak") public class AsyncImageLoader {
	
	private Map<String, SoftReference<Drawable>> imageCache = new HashMap<String, SoftReference<Drawable>>();
	
	private static final String TAG = "AsyncImageLoader";
	
	public Drawable loadImage(final String url, final ImageLoadCallback callback) {
		Log.i(TAG, "load image from " + url + " ...");
		Drawable d = null;
		if (imageCache.containsKey(url)) {
			SoftReference<Drawable> sr = imageCache.get(url);
			if (sr.get() != null) {
				Log.i(TAG, "found image in cache, get it from cache...");
				d = sr.get();
				return d;
			}
		}
		
		final Handler handler = new Handler() {
			
			@Override
			public void handleMessage(Message msg) {
				Log.i(TAG, "handle message for image=>" + url);
				callback.imageLoaded((Drawable) msg.obj);
			}
		};
		
		new Thread() {
			public void run() {
				Log.i(TAG, "image load thread=>" + Thread.currentThread().getId());
				Drawable d = downloadImage(url);
				Message msg = handler.obtainMessage();
				msg.obj = d;
				handler.sendMessage(msg);
			};
		}.start();
		return null;
	}
	
	private Drawable downloadImage(String url) {
		Log.i(TAG, "image is not in cache, download from url " + url);
		Drawable d = null;
		try {
			d = Drawable.createFromStream(new URL(url).openStream(), "src");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Log.i(TAG, "download complete, return image...");
		return d;
	}
}
