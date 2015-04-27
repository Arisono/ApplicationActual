/**
 * 
 */
package com.app.demo.base;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

/**
 * @author LiuJie
 * 抽象父类View
 */
public abstract class BasePage {
	
	protected Context ct;
	protected View contentView;
	
	public BasePage(Context context){
		ct=context;
		LayoutInflater layoutInflater=(LayoutInflater) ct.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		contentView=initView(layoutInflater);
		
	}
	
	/**注释：抽象方法 */
	protected abstract View initView(LayoutInflater inflater);
	
	/**注释：初始化数据 */
	public abstract void initData();
	
	
	public View getContentView() {
		return contentView;
	}
	
	//定义其它自定义的公共 方法   网络，进度条，空白页，加载页，为生命周期方法；
	/**注释：onResume */
	
	/**注释：onDestory */
	
	/**注释：onPause */
	
	
	
	
	
	
	
	
}
