package com.app.demo.activity;


import com.app.demo.R;
import com.app.demo.base.BaseAcivity;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

/**
 * @author Administrator
 *  Ö÷½çÃæ
 */
public class MainActivity extends BaseAcivity {
	
	@ViewInject(R.id.rg_main_radio)
	private RadioGroup m_radiogroup;
	@ViewInject(R.id.vp_viewpager)
	private ViewPager m_viewpager;
	
	private int curCheckId = R.id.rb_function;

	/* (non-Javadoc)
	 * @see com.app.demo.base.BaseAcivity#setView()
	 */
	@Override
	public void setView() {
		setContentView(R.layout.activity_mian_tab_radio);
		ViewUtils.inject(this);
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
	    m_radiogroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				switch (checkedId) {
				case R.id.rb_function:
					
					break;
				case R.id.rb_gov_affairs:
					
					break;
				case R.id.rb_news_center:
					
					break;
				case R.id.rb_setting:
					
					break;
					
				case R.id.rb_smart_service:
					
					break;

				default:
					break;
				}
				curCheckId=checkedId;
			}
		});	
	       
	       m_radiogroup.check(curCheckId);
   
	}


}
