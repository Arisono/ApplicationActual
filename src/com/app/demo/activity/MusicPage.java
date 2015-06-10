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
public class MusicPage extends BasePage {
	private String TAG="MusicPage";
	/**
	 * @param context
	 */
	public MusicPage(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see com.app.demo.base.BasePage#initView(android.view.LayoutInflater)
	 */
	@Override
	public View initView(LayoutInflater inflater) {
		View view=inflater.inflate(R.layout.view_radio_music_list, null);
		ViewUtils.inject(this, view);
		return view;
	}

	/* (non-Javadoc)
	 * @see com.app.demo.base.BasePage#initData()
	 */
	@Override
	public void initData() {
     Log.i(TAG, "initData");
     isInitDataSuccess=true;
	}

}
