/**
 * 
 */
package com.app.demo.base;


import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;

/**
 * @author LiuJie
 * 
 */
public abstract class BasePage {
	
	public String TAG;
	public Context ct;
	/**@注释：传递activity  */
	public AppCompatActivity appCompatActivity;
	public View contentView;
	public boolean isInitDataSuccess=false;
	
	public BasePage(Context context){
		ct=context;
		appCompatActivity=(AppCompatActivity) context;
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
