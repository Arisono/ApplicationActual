/**
 * 
 */
package com.app.demo.activity;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.app.demo.R;
import com.app.demo.base.BasePage;
import com.lidroid.xutils.ViewUtils;

/**
 * @author LiuJie
 *
 */
public class NotePage extends BasePage {
	private String TAG="NotePage";
	/**
	 * @param context
	 */
	public NotePage(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see com.app.demo.base.BasePage#initView(android.view.LayoutInflater)
	 */
	@Override
	protected View initView(LayoutInflater inflater) {
		View view=inflater.inflate(R.layout.view_radio_note_list, null);
		
		ViewUtils.inject(this, view);
		return view;
	}

	/* (non-Javadoc)
	 * @see com.app.demo.base.BasePage#initData()
	 */
	@Override
	public void initData() {
		Log.i(TAG, "数据初始化成功！");
	}

}
