/**
 * 
 */
package com.app.demo.activity.common;

import android.content.Context;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.app.demo.R;
import com.app.demo.base.BaseAcivity;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;


/**
 * @author LiuJie
 * �½��ճ�
 */
public class NewSchedule extends BaseAcivity {
    
	public static Context mContext;
	private InputMethodManager imm;
	@ViewInject(R.id.et_sd_content)
	private EditText mContent;
	/* (non-Javadoc)
	 * @see com.app.demo.base.BaseAcivity#setView()
	 */
	@Override
	public void setView() {
		
		setContentView(R.layout.act_new_shedule_view);// ����̹�����
		ViewUtils.inject(this);
		imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
		
	}

	/* (non-Javadoc)
	 * @see com.app.demo.base.BaseAcivity#initView()
	 */
	@Override
	public void initView() {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see com.app.demo.base.BaseAcivity#setListener()
	 */
	@Override
	public void setListener() {
		
		mContent.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
			//	showOrHideIMM();
			}
		});
	}
	
	
	/* (non-Javadoc)
	 * @see com.app.demo.base.BaseAcivity#onCreateOptionsMenu(android.view.Menu)
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.calendar_new_sheduler_menu, menu);// TODO Auto-generated method stub
		/**@ע�ͣ�����true ���θ���   2015��5��12�� */
		return true;
	}
	
	
	/* (non-Javadoc)
	 * @see com.app.demo.base.BaseAcivity#onOptionsItemSelected(android.view.MenuItem)
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.mu_it_save_schedule:
			return true;
		case R.id.menu_feedback:
		    return true;
		case R.id.menu_share:
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}
	
	
	private void showOrHideIMM() {
//		if (mFace.getTag() == null) {
//			// ���������
//			imm.hideSoftInputFromWindow(mContent.getWindowToken(), 0);
			// ��ʾ����
//			showFace();
//		} else {
			// ��ʾ�����
			imm.showSoftInput(mContent, 0);
			// ���ر���
//			hideFace();
//		}
	}

}
