package com.app.demo.activity;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.R.anim;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.SimpleAdapter;

import com.app.demo.R;
import com.app.demo.base.BaseAcivity;
import com.app.demo.util.ApplicationUtil;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
/**
 * @author :LiuJie ʱ��: 2015��5��19�� ����7:48:35
 * @ע��:�ղص����Ͳ鿴�Լ������
 */
public class BtSelectActivity extends BaseAcivity {
	
	@ViewInject(R.id.prl_lib_listView)
	private PullToRefreshListView list;
	
	@ViewInject(R.id.bt_collect)
	private Button bt_collect;
	@ViewInject(R.id.bt_jinlian)
	private Button bt_jinlian;
	
	private SimpleAdapter adapter; 
	private ArrayList<Map<String,Object>> data ;
   
	@Override
	public void setView() {
       setContentView(R.layout.act_list_gitlib);
       ct=this;
       ViewUtils.inject(this);
	}

	@Override
	public void initView() {
	 getData();
     adapter=new SimpleAdapter(this, data, 
    		 R.layout.item_simple_gitlib,
    		 new String[]{"libname"}, 
    		 new int[]{R.id.tv_item_libtitle});
     list.setAdapter(adapter);
	}

	@Override
	public void setListener() {
		
		list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				switch (position) {
				case 1:
					ApplicationUtil.ToastMessage(ct, "����������ScrollView");
					break;
				case 2:
					ApplicationUtil.ToastMessage(ct, "������");
					break;
				default:
					break;
				}
				
			}
		});

	}
	
	public void getData(){
		data= new ArrayList<Map<String,Object>>();
		Map<String,Object> item;
		item=new HashMap<String,Object>();
		item.put("libname", "����������ScrollView");
		data.add(item);
		item=new HashMap<String,Object>();
		item.put("libname", "������");
		data.add(item);
		item=new HashMap<String,Object>();
		item.put("libname", "�Ի���");
		data.add(item);
		
	}
	
	

}
