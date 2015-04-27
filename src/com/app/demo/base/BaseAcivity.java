/**
 * 
 */
package com.app.demo.base;

import com.app.demo.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

/**
 * @author LiuJie
 * ������ Activity
 */
public abstract class BaseAcivity extends Activity {
   
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

}
