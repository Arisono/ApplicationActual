/**
 * 
 */
package com.app.demo.base;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

/**
 * @author LiuJie
 * 
 */
public abstract class BasePage {
	
	protected String TAG;
	protected Context ct;
	protected View contentView;
	public boolean isInitDataSuccess=false;
	
	public BasePage(Context context){
		ct=context;
		LayoutInflater layoutInflater=(LayoutInflater) ct.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		contentView=initView(layoutInflater);
		
	}
	/**@annotation：abstract method */
	protected abstract View initView(LayoutInflater inflater);
	/**@annotation：frist load data*/
	public abstract void initData();
	
	/**@annotation： onResume */
	public  void onResume(){
		initData();
	};
	
	/**@annotation：onDestory */
	public void onDestory(){
		
	}
	
	
	public View getContentView() {
		return contentView;
	}
	
}
