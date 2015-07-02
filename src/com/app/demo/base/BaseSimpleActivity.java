package com.app.demo.base;

import com.app.demo.R;

import com.lidroid.xutils.ViewUtils;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

/**
 * @author :LiuJie 2015年7月2日 上午10:14:46
 * @注释:Base class
 */
public abstract class BaseSimpleActivity extends Activity{
	
	public Context ct;
	protected String TAG;
	public abstract void initView();
	public void initData(){
		Log.i(TAG, "initData");
	};
	
	public BaseSimpleActivity() {
		ct=this;
	}
	
	@Override 
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.i(TAG, "onCreate");
		initView();
		ViewUtils.inject(this);
		initData();
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		Log.i(TAG, "onResume");
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		Log.i(TAG, "onDestroy");
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		Log.i(TAG, "onPause");
	}
	
	@Override
	protected void onStop() {
		super.onStop();
		Log.i(TAG, "onStop");
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main_tab_menu_actionbar, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		default:
			return super.onOptionsItemSelected(item);
		}
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
	    if(keyCode == KeyEvent.KEYCODE_BACK) { 
	    	 return super.onKeyDown(keyCode, event);
	    } else if(keyCode == KeyEvent.KEYCODE_MENU) {
	    	return true;
	    } else if(keyCode == KeyEvent.KEYCODE_HOME) {
	    	return true;
	    }
	    return super.onKeyDown(keyCode, event);
	}
}
