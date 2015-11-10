package com.app.demo.net;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;

import com.android.volley.toolbox.ImageLoader;
public class BitmapLruCache extends LruCache<String, Bitmap> 
        implements  ImageLoader.ImageCache{

	public BitmapLruCache(int maxSize) {
		super(maxSize);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Bitmap getBitmap(String url) {
		return get(url);
	}

	@Override
	public void putBitmap(String url, Bitmap bitmap) {
         put(url, bitmap);		
	}

}
