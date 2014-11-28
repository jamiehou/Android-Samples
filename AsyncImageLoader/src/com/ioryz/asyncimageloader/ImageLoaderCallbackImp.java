package com.ioryz.asyncimageloader;

import android.graphics.drawable.Drawable;
import android.util.Log;
import android.widget.ImageView;

public class ImageLoaderCallbackImp implements ImageLoadCallback {
	
	private ImageView image;
	
	private static final String TAG = "ImageLoaderCallbackImp";
	
	public ImageLoaderCallbackImp(ImageView image) {
		super();
		this.image = image;
	}

	@Override
	public void imageLoaded(Drawable d) {
		Log.i(TAG, "image loader callback=>imageLoaded...");
		if (d != null) {
			image.setImageDrawable(d);
		}
	}
}
