package com.app.demo.activity;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.content.Intent;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.app.demo.R;
import com.app.demo.base.BaseComplexActivity;
import com.app.demo.view.refresh.PullToRefreshLayout;
import com.app.demo.view.refresh.PullToRefreshLayout.OnRefreshListener;
import com.lidroid.xutils.view.annotation.ViewInject;
/**
 * @author :LiuJie 时间: 2015年5月19日 下午7:48:35
 * @注释:案例类
 */
public class ExampleActivity extends BaseComplexActivity {
	
	@ViewInject(R.id.refresh_view)
	private PullToRefreshLayout layout_refresh;
	
	@ViewInject(R.id.prl_lib_listView)
	private ListView list;
	
	/*@ViewInject(R.id.bt_collect)
	private Button bt_collect;
	@ViewInject(R.id.bt_jinlian)
	private Button bt_jinlian;
	@ViewInject(R.id.ly_topView)*/
	private LinearLayout ly_topView;
	
	private SimpleAdapter adapter; 
	private ArrayList<Map<String,Object>> data ;
   

	@Override
	public void initView() {
		  setContentView(R.layout.act_list_gitlib);
	      ct=this;
	}
	
	@Override
	public void initData() {
		super.initData();
		 setListener();
		 getData();
	     adapter=new SimpleAdapter(this, data, 
	    		 R.layout.item_simple_gitlib,
	    		 new String[]{"libname","no","url","intent"}, 
	    		 new int[]{R.id.tv_item_libtitle,
	    		           R.id.tv_item_no,
	    		           R.id.tv_address_url,
	    		           R.id.tv_address_intent});
	     list.setAdapter(adapter);
	     
	     layout_refresh.setOnRefreshListener(new OnRefreshListener() {
			
			@Override
			public void onRefresh(final PullToRefreshLayout pullToRefreshLayout) {
                 
				new Handler().postDelayed(new Runnable() {
					
					@Override
					public void run() {
                         pullToRefreshLayout.refreshFinish(PullToRefreshLayout.SUCCEED);					
					}
				},1000);
			}
			
			@Override
			public void onLoadMore(PullToRefreshLayout pullToRefreshLayout) {
				 pullToRefreshLayout.loadmoreFinish(PullToRefreshLayout.SUCCEED);									
			}
		});
	}
	
	

	public void setListener() {
		list.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
			   TextView tView=(TextView) view.findViewById(R.id.tv_address_intent);
			   Intent it_scroll=null;
			
				try {
					it_scroll = new Intent(ExampleActivity.this, Class.forName(tView.getText().toString()));
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
		item.put("libname", "上拉下拉的ScrollView");
		item.put("no", 1);
		item.put("url", "未填写");
		item.put("intent", "com.app.demo.activity.ScrollViewRefreshActivity");
		data.add(item);
		item=new HashMap<String,Object>();
		item.put("libname", "进度条");
		item.put("url", "未填写");
		item.put("no", 2);
		data.add(item);
		item=new HashMap<String,Object>();
		item.put("libname", "对话框");
		item.put("url", "未填写");
		item.put("no", 3);
		data.add(item);
		item=new HashMap<String,Object>();
		item.put("libname", "ScrollView ViewPager ListView 嵌套组合");
		item.put("no", 4);
		item.put("url", "未填写");
		data.add(item);
		item=new HashMap<String,Object>();
		item.put("libname", "ScrollView 下拉刷新");
		item.put("no", 5);
		item.put("url", "未填写");
		data.add(item);
		item=new HashMap<String,Object>();
		item.put("libname", "可滑动的 ScrollView");
		item.put("no", 6);
		item.put("url", "未填写");
		data.add(item);
		item=new HashMap<String,Object>();
		item.put("libname", "日历");
		item.put("no", 7);
		item.put("url", "未填写");
		data.add(item);
		item=new HashMap<String,Object>();
		item.put("libname", "不规则的GridView布局");
		item.put("no",8);
		item.put("url", "未填写");
		data.add(item);
		item=new HashMap<String,Object>();
		item.put("libname", "图表统计");
		item.put("no", 9);
		item.put("url", "未填写");
		data.add(item);
		item=new HashMap<String,Object>();
		item.put("libname", "MenuDrawerSample 侧滑菜单");
		item.put("no", 10);
		item.put("url", "未填写");
		data.add(item);
		item=new HashMap<String,Object>();
		item.put("libname", "二级ListView");
		item.put("no", 11);
		item.put("url", "未填写");
		data.add(item);
		item=new HashMap<String,Object>();
		item.put("libname", "Volley");
		item.put("no", 12);
		item.put("url", "未填写");
		data.add(item);
		item=new HashMap<String,Object>();
		item.put("libname", "ORM");
		item.put("no", 13);
		item.put("url", "未填写");
		data.add(item);
		item=new HashMap<String,Object>();
		item.put("libname", "ImageLoader");
		item.put("no",14);
		item.put("url", "未填写");
		
		data.add(item);
		item=new HashMap<String,Object>();
		item.put("libname", "ExpandableListView双层嵌套实现三级树形菜单");
		item.put("no",15);
		item.put("intent", "com.app.demo.activity.TreeListActivity");
		item.put("url", "http://download.csdn.net/detail/shexiaoheng/8212209");
		data.add(item);
		
		item=new HashMap<String,Object>();
		item.put("libname", "笔记");
		item.put("no",16);
		item.put("intent", "com.app.demo.activity.EntryNoteActivity");
		item.put("url", "http://download.csdn.net/detail/shexiaoheng/8212209");
		data.add(item);
		
		item=new HashMap<String,Object>();
		item.put("libname", "IOS风格的对话框");
		item.put("no",17);
		item.put("intent", "com.app.demo.activity.common.DialogStyleIOS");
		item.put("url", "http://download.csdn.net/detail/shexiaoheng/8212209");
		data.add(item);
		
		item=new HashMap<String,Object>();
		item.put("libname", "理财工具");
		item.put("no",18);
		item.put("intent", "com.app.demo.activity.tool.InterestActivity");
		item.put("url", "http://download.csdn.net/detail/shexiaoheng/8212209");
		data.add(item);
		
	
		item=new HashMap<String,Object>();
		item.put("libname", "EventBus");
		item.put("no",19);
		item.put("intent", "com.app.demo.activity.eventbus.EventBusActivity");
		item.put("url", "http://download.csdn.net/detail/shexiaoheng/8212209");
		data.add(item);
	}
	
	public void getCreativeData(){
		if (data==null) {
			/**@注释：  if data equal to null   so that it  means to new data source*/
			data= new ArrayList<Map<String,Object>>();
		}
		Map<String,Object> item;
		item=new HashMap<String,Object>();
		item.put("libname", "ScrollView ViewPager ListView 嵌套组合");
		item.put("intent", "com.app.demo.activity.common.CalendarActivity");
		item.put("no",1);
		item.put("url", "未填写");
		data.add(item);
		
	}
	
	
	/*@OnClick((R.id.bt_collect))
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
	}*/
	
	
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
