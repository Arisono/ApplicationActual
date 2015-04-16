/**
 * 
 */
package com.app.demo.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

/**
 * @author LiuJie Service 生命周期 初级篇
 */
public class MyService extends Service {

	private static final String TAG = "MyService";

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Service#onBind(android.content.Intent)
	 */
	@Override
	public IBinder onBind(Intent intent) {
		Log.i(TAG, "onBind called.");  
		
		return new Binder(){};
	}

	@Override
	public void onCreate() {
		super.onCreate();
		Log.i(TAG, "onCreate called.");
	}

	/*
	 * (non-Javadoc)
	 * @see android.app.Service#onStartCommand(android.content.Intent, int, int)
	 */
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Log.i(TAG, "onStartCommand called.");  
		return super.onStartCommand(intent, flags, startId);
	}
	
	
	/* (non-Javadoc)
	 * @see android.app.Service#onStart(android.content.Intent, int)
	 */
	@SuppressWarnings("deprecation")
	@Override
	public void onStart(Intent intent, int startId) {
		Log.i(TAG, "onStart called.");  
		super.onStart(intent, startId);
	}
	
	
	/* (non-Javadoc)
	 * @see android.app.Service#onUnbind(android.content.Intent)
	 */
	@Override
	public boolean onUnbind(Intent intent) {
		Log.i(TAG, "onUnbind called.");  
		return super.onUnbind(intent);
	}
	
	/* (non-Javadoc)
	 * @see android.app.Service#onDestroy()
	 */
	@Override
	public void onDestroy() {
		Log.i(TAG, "onDestroy called.");  
		super.onDestroy();
	}

}
