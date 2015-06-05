package com.app.demo.activity;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.app.demo.R;
import com.app.demo.base.BaseAcivity;
import com.app.demo.util.ApplicationUtil;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
/**
 * @author :LiuJie ʱ��: 2015��5��19�� ����7:48:35
 * @ע��:������
 */
public class BtSelectActivity extends BaseAcivity {
	
	@ViewInject(R.id.prl_lib_listView)
	private PullToRefreshListView list;
	
	@ViewInject(R.id.bt_collect)
	private Button bt_collect;
	@ViewInject(R.id.bt_jinlian)
	private Button bt_jinlian;
	@ViewInject(R.id.ly_topView)
	private LinearLayout ly_topView;
	
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
    		 new String[]{"libname","no","url","intent"}, 
    		 new int[]{R.id.tv_item_libtitle,
    		           R.id.tv_item_no,
    		           R.id.tv_address_url,
    		           R.id.tv_address_intent});
     list.setAdapter(adapter);
	}

	@Override
	public void setListener() {
		
		list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
			   TextView tView=(TextView) view.findViewById(R.id.tv_address_intent);
			   Intent it_scroll=null;
			   switch (position) {
				case 1:
				    ApplicationUtil.ToastMessage(ct, tView.getText().toString());
					break;
				case 2:
					ApplicationUtil.ToastMessage(ct, tView.getText().toString());
					break;
				case 3:
					ApplicationUtil.ToastMessage(ct, tView.getText().toString());
					break;
				case 4:
					ApplicationUtil.ToastMessage(ct, tView.getText().toString());
					break;
				case 5:
					ApplicationUtil.ToastMessage(ct, tView.getText().toString());
					break;
				case 6:
					ApplicationUtil.ToastMessage(ct, tView.getText().toString());
					break;	
				case 15:
					ApplicationUtil.ToastMessage(ct, tView.getText().toString());
					break;	
				default:
					break;
				}
				try {
					it_scroll = new Intent(BtSelectActivity.this, Class.forName(tView.getText().toString()));
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
				if (it_scroll!=null) {
					startActivity(it_scroll);
				}
				
			}
		});
		
		

	}
	
	public void getData(){
		if (data==null) {
			data= new ArrayList<Map<String,Object>>();
		}
		Map<String,Object> item;
		item=new HashMap<String,Object>();
		item.put("libname", "����������ScrollView");
		item.put("no", 1);
		item.put("url", "δ��д");
		item.put("intent", "com.app.demo.activity.ScrollStretchActivity");
		data.add(item);
		item=new HashMap<String,Object>();
		item.put("libname", "������");
		item.put("url", "δ��д");
		item.put("no", 2);
		data.add(item);
		item=new HashMap<String,Object>();
		item.put("libname", "�Ի���");
		item.put("url", "δ��д");
		item.put("no", 3);
		data.add(item);
		item=new HashMap<String,Object>();
		item.put("libname", "ScrollView ViewPager ListView Ƕ�����");
		item.put("no", 4);
		item.put("url", "δ��д");
		data.add(item);
		item=new HashMap<String,Object>();
		item.put("libname", "ScrollView ����ˢ��");
		item.put("no", 5);
		item.put("url", "δ��д");
		data.add(item);
		item=new HashMap<String,Object>();
		item.put("libname", "�ɻ����� ScrollView");
		item.put("no", 6);
		item.put("url", "δ��д");
		data.add(item);
		item=new HashMap<String,Object>();
		item.put("libname", "����");
		item.put("no", 7);
		item.put("url", "δ��д");
		data.add(item);
		item=new HashMap<String,Object>();
		item.put("libname", "�������GridView����");
		item.put("no",8);
		item.put("url", "δ��д");
		data.add(item);
		item=new HashMap<String,Object>();
		item.put("libname", "ͼ��ͳ��");
		item.put("no", 9);
		item.put("url", "δ��д");
		data.add(item);
		item=new HashMap<String,Object>();
		item.put("libname", "MenuDrawerSample �໬�˵�");
		item.put("no", 10);
		item.put("url", "δ��д");
		data.add(item);
		item=new HashMap<String,Object>();
		item.put("libname", "����ListView");
		item.put("no", 11);
		item.put("url", "δ��д");
		data.add(item);
		item=new HashMap<String,Object>();
		item.put("libname", "Volley");
		item.put("no", 12);
		item.put("url", "δ��д");
		data.add(item);
		item=new HashMap<String,Object>();
		item.put("libname", "ORM");
		item.put("no", 13);
		item.put("url", "δ��д");
		data.add(item);
		item=new HashMap<String,Object>();
		item.put("libname", "ImageLoader");
		item.put("no",14);
		item.put("url", "δ��д");
		
		data.add(item);
		item=new HashMap<String,Object>();
		item.put("libname", "ExpandableListView˫��Ƕ��ʵ���������β˵�");
		item.put("no",15);
		item.put("intent", "com.app.demo.activity.TreeListActivity");
		item.put("url", "http://download.csdn.net/detail/shexiaoheng/8212209");
		data.add(item);
		
		item=new HashMap<String,Object>();
		item.put("libname", "�ʼ�");
		item.put("no",16);
		item.put("intent", "com.app.demo.activity.NotesActivity");
		item.put("url", "http://download.csdn.net/detail/shexiaoheng/8212209");
		data.add(item);
		
		
	}
	
	public void getCreativeData(){
		if (data==null) {
			/**@ע�ͣ�  if data equal to null   so that it  means to new data source*/
			data= new ArrayList<Map<String,Object>>();
		}
		Map<String,Object> item;
		item=new HashMap<String,Object>();
		item.put("libname", "ScrollView ViewPager ListView Ƕ�����");
		item.put("intent", "com.app.demo.activity.common.CalendarActivity");
		item.put("no",1);
		item.put("url", "δ��д");
		data.add(item);
		
	}
	
	
	@OnClick((R.id.bt_collect))
	public void collectByOnClick(View v){
		data.clear();
		getData();
		if (adapter==null) {
			 adapter=new SimpleAdapter(this, data, 
		    		 R.layout.item_simple_gitlib,
		    		 new String[]{"libname"}, 
		    		 new int[]{R.id.tv_item_libtitle});
			 list.setAdapter(adapter);
		}
		adapter.notifyDataSetChanged();
	}
	
	@OnClick((R.id.bt_jinlian))
	public void jinlianByOnClick(View v){
		data.clear();
		getCreativeData();
		if (adapter==null) {
			 adapter=new SimpleAdapter(this, data, 
		    		 R.layout.item_simple_gitlib,
		    		 new String[]{"libname"}, 
		    		 new int[]{R.id.tv_item_libtitle});
			 list.setAdapter(adapter);
		}
		adapter.notifyDataSetChanged();
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu_lib_bar, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {
		case R.id.mt_collect:
			data.clear();
			getData();
			if (adapter==null) {
				 adapter=new SimpleAdapter(this, data, 
			    		 R.layout.item_simple_gitlib,
			    		 new String[]{"libname"}, 
			    		 new int[]{R.id.tv_item_libtitle});
				 list.setAdapter(adapter);
			}
			adapter.notifyDataSetChanged();
			return true;
		case R.id.mt_better:
			data.clear();
			getCreativeData();
			if (adapter==null) {
				 adapter=new SimpleAdapter(this, data, 
			    		 R.layout.item_simple_gitlib,
			    		 new String[]{"libname"}, 
			    		 new int[]{R.id.tv_item_libtitle});
				 list.setAdapter(adapter);
			}
			adapter.notifyDataSetChanged();
		    return true;
		case R.id.mt_visible:
			ly_topView.setVisibility(View.VISIBLE);
			return true;
		case R.id.mt_gone:
			ly_topView.setVisibility(View.GONE);
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}
	
	

}
