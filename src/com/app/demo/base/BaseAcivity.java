/**
 * 
 */
package com.app.demo.base;

import com.app.demo.R;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

/**
 * @author LiuJie
 * Activity
 */
public abstract class BaseAcivity extends Activity {
    
	protected String TAG;
	public abstract void setView();
	public abstract void initView();
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
	
	
	
	/**@annotation：if return  true  is not function or lead to  action*/
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
	    if(keyCode == KeyEvent.KEYCODE_BACK) { //监控/拦截/屏蔽返回键
	       // processExit();
	        return true;
	    } else if(keyCode == KeyEvent.KEYCODE_MENU) {
	        //监控/拦截菜单键
	    	
	    	return true;
	    } else if(keyCode == KeyEvent.KEYCODE_HOME) {
	        //由于Home键为系统键，此处不能捕获，需要重写onAttachedToWindow()
	    	return true;
	    }
	    return super.onKeyDown(keyCode, event);
	}

	
	
	/* (non-Javadoc)
	 * @see android.app.Activity#onAttachedToWindow()
	 */
//	@Override
//	public void onAttachedToWindow() {
//		/**@annotation： */
//		 this.getWindow().setType(WindowManager.LayoutParams.TYPE_KEYGUARD);     
//	     super.onAttachedToWindow();    
//	}
}
