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
	
	public String TAG;
	public Context ct;
	public View contentView;
	public boolean isInitDataSuccess=false;
	
	public BasePage(Context context){
		ct=context;
		LayoutInflater layoutInflater=(LayoutInflater) ct.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		contentView=initView(layoutInflater);
		
	}
	public abstract View initView(LayoutInflater inflater);
	public abstract void initData();
	
	public  void onResume(){
		initData();
	};
	
	public void onDestory(){
		
	}
	
	public View getContentView() {
		return contentView;
	}
	
}
