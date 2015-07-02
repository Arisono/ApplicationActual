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
import android.view.View.OnClickListener;
/**
 * @author :LiuJie 2015��6��23�� ����9:48:17
 * @ע��:����������
 *      Xutils ע��
 */
public abstract class BaseSimpleActivity extends Activity implements OnClickListener{
	
	public Context ct;
	protected String TAG;
	
	
	public abstract void setView();
	public abstract void initView();
	public abstract void initData();
	
	
	public BaseSimpleActivity() {
		ct=this;
	}
	
	@Override 
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.i(TAG, "onCreate");
		setView();
		ViewUtils.inject(this);
		initView();
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
