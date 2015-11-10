package com.app.demo.net;

import android.app.ActivityManager;
import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

/**
 * @author :LiuJie 2015年11月9日 下午4:39:21
 * @注释:Volley ResquestManager
 */
public class ResquestManager {
	
   private static RequestQueue mRequestQueue;
   private static ImageLoader mImageLoader;
   
   public ResquestManager() {
	// TODO Auto-generated constructor stub
   }
   
   public static void init(Context context){
	   mRequestQueue=Volley.newRequestQueue(context);
	   int memClass = ((ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE))
				.getMemoryClass();
		// Use 1/8th of the available memory for this memory cache.
		int cacheSize = 1024 * 1024 * memClass / 8;
		mImageLoader=new ImageLoader(mRequestQueue, new BitmapLruCache(cacheSize));
   }
   
   public static RequestQueue getRequestQueue() {
		if (mRequestQueue != null) {
			return mRequestQueue;
		} else {
			throw new IllegalStateException("RequestQueue not initialized");
		}
	}
   
   public static void addRequest(Request<?> request,Object tag){
	   if (tag != null) {
           request.setTag(tag);
       }
       mRequestQueue.add(request);
   }
   
   public static void cancelAll(Object tag) {
       mRequestQueue.cancelAll(tag);
   }
   
   /**
	 * Returns instance of ImageLoader initialized with {@see FakeImageCache}
	 * which effectively means that no memory caching is used. This is useful
	 * for images that you know that will be show only once.
	 * 
	 * @return
	 */
	public static ImageLoader getImageLoader() {
		if (mImageLoader != null) {
			return mImageLoader;
		} else {
			throw new IllegalStateException("ImageLoader not initialized");
		}
	}
   
}
