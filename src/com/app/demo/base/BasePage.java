/**
 * 
 */
package com.app.demo.base;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

/**
 * @author LiuJie
 * ������View
 */
public abstract class BasePage {
	
	protected Context ct;
	protected View contentView;
	
	public BasePage(Context context){
		ct=context;
		LayoutInflater layoutInflater=(LayoutInflater) ct.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		contentView=initView(layoutInflater);
		
	}
	
	/**ע�ͣ����󷽷� */
	protected abstract View initView(LayoutInflater inflater);
	
	/**ע�ͣ���ʼ������ */
	public abstract void initData();
	
	
	public View getContentView() {
		return contentView;
	}
	
	//���������Զ���Ĺ��� ����   ���磬���������հ�ҳ������ҳ��Ϊ�������ڷ�����
	/**ע�ͣ�onResume */
	
	/**ע�ͣ�onDestory */
	
	/**ע�ͣ�onPause */
	
	
	
	
	
	
	
	
}
