/**
 * 
 */
package com.app.demo.activity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import com.app.demo.R;
import com.app.demo.base.BasePage;
import com.lidroid.xutils.ViewUtils;

/**
 * @author LiuJie
 * Ê×Ò³
 */
public class HomePage extends BasePage {

	/**
	 * @param context
	 */
	public HomePage(Context context) {
		super(context);
	}

	/* (non-Javadoc)
	 * @see com.app.demo.base.BasePage#initView(android.view.LayoutInflater)
	 */
	@Override
	protected View initView(LayoutInflater inflater) {
		View view=inflater.inflate(R.layout.view_radio_home_list, null);
		
		ViewUtils.inject(this, view);
		return view;
	}

	/* (non-Javadoc)
	 * @see com.app.demo.base.BasePage#initData()
	 */
	@Override
	public void initData() {
		// TODO Auto-generated method stub

	}

}
