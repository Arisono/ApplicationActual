/**
 * 
 */
package com.app.demo.base;

import javax.security.auth.PrivateCredentialPermission;

import com.app.demo.R;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

/**
 * @author LiuJie
 * ������ Activity
 */
public abstract class BaseAcivity extends Activity {
    
	protected String TAG;
	

	/**
	 * ���ò����ļ�
	 */
	public abstract void setView();

	/**
	 * ��ʼ�������ļ��еĿؼ�
	 */
	public abstract void initView();

	/**
	 * ���ÿؼ��ļ���
	 */
	public abstract void setListener();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.i(TAG, "onCreate！");
		setView();
		initView();
		setListener();
	}
	
	/* (non-Javadoc)
	 * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main_tab_menu_actionbar, menu);
		return true;
	}
	
	/* (non-Javadoc)
	 * @see android.app.Activity#onOptionsItemSelected(android.view.MenuItem)
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {
		case R.id.menu_about:
			return true;
		case R.id.menu_feedback:
		    return true;
		case R.id.menu_share:
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		Log.i(TAG, "onResume！");
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		Log.i(TAG, "onDestroy！");
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		Log.i(TAG, "onPause！");
	}
	
	@Override
	protected void onStop() {
		super.onStop();
		Log.i(TAG, "onStop！");
	}

}
