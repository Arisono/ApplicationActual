package com.app.demo.activity;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;

import com.app.demo.R;
import com.app.demo.activity.common.CalendarActivity;
import com.app.demo.adapter.DefaultListAdapter;
import com.app.demo.adapter.DemoAdapter;
import com.app.demo.adapter.ImagePageAdapter;
import com.app.demo.base.BasePage;
import com.app.demo.model.DemoItem;
import com.felipecsl.asymmetricgridview.library.Utils;
import com.felipecsl.asymmetricgridview.library.widget.AsymmetricGridView;
import com.felipecsl.asymmetricgridview.library.widget.AsymmetricGridViewAdapter;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.viewpagerindicator.TitlePageIndicator;

/**
 * @author LiuJie
 *  
 */
public class HomePage extends BasePage implements AdapterView.OnItemClickListener{
	
	private String TAG="HomePage";
	
	@ViewInject(R.id.listView)
	private AsymmetricGridView listView;
	@ViewInject(R.id.vp_image_display)
	private TitlePageIndicator vpPageIndicator;
	private ViewPager viewpager;
	private DemoAdapter adapter;
	private ImagePageAdapter iPageAdapter;
	private int[] imageIds;
	private int currentOffset;
	private static final boolean USE_CURSOR_ADAPTER = true;

	/**
	 * @param context
	 */
	public HomePage(Context context) {
		super(context);
	}

	/* (non-Javadoc)
	 * @see com.app.demo.base.BasePage#initView(android.view.LayoutInflater)
	 */
	@Override
	protected View initView(LayoutInflater inflater) {
		View view=inflater.inflate(R.layout.view_radio_home_list, null);
		listView=(AsymmetricGridView) view.findViewById(R.id.listView);
		ViewUtils.inject(ct,view);
		return view;
	}

	/* (non-Javadoc)
	 * @see com.app.demo.base.BasePage#initData()
	 */ 
	@Override
	public void initData() {
		
		iPageAdapter=new ImagePageAdapter(ct, imageIds);
		viewpager.setAdapter(iPageAdapter);
		((TitlePageIndicator) vpPageIndicator).setViewPager(viewpager);
		
		
		Log.i(TAG, "HomePage init");
		isInitDataSuccess=true;
		adapter = new DefaultListAdapter(ct, getMoreItems(51));
	    listView.setRequestedColumnCount(3);
	    listView.setRequestedHorizontalSpacing(Utils.dpToPx(ct, 1));
	    listView.setAdapter(getNewAdapter());
	    /**   */
	   // listView.setDebugging(true);
	    listView.setOnItemClickListener(this);
	}
	
	
	private List<DemoItem> getMoreItems(int qty) {
	    List<DemoItem> items = new ArrayList<DemoItem>();
	   /* for (int i = 0; i < qty; i++) {
	      int colSpan = Math.random() < 0.2f ? 2 : 1;
	      // column/row span.
	      //int rowSpan = Math.random() < 0.2f ? 2 : 1;
	      int rowSpan = colSpan;
	      DemoItem item = new DemoItem(colSpan, rowSpan, currentOffset + i);
	      items.add(item);
	    }*/
	    DemoItem item = new DemoItem(2, 2, "日历");
	    DemoItem item1 = new DemoItem(1, 1, "记账");
	    DemoItem item2 = new DemoItem(1, 1, "消费");
	    DemoItem item3 = new DemoItem(2, 2, "音乐收藏");
	    DemoItem item4 = new DemoItem(1, 1, "记事");
	    DemoItem item5 = new DemoItem(1, 1, "习惯");
	    DemoItem item6 = new DemoItem(1, 1, "统计");
	    DemoItem item7 = new DemoItem(1, 1, "相册");
	    DemoItem item8 = new DemoItem(2, 2, "加密");
	    DemoItem item9 = new DemoItem(2, 1, "日程");
	    DemoItem item10 = new DemoItem(1, 1,"通讯录");
	    DemoItem item11= new DemoItem(2, 2, "好友");
	    DemoItem item12= new DemoItem(1, 1, "目标");
	    DemoItem item13= new DemoItem(1, 1, "备份");
	    items.add(item);
	    items.add(item1);
	    items.add(item2);
	    items.add(item3);
	    items.add(item4);
	    items.add(item5);
	    items.add(item6);
	    items.add(item7);
	    items.add(item8);
	    items.add(item9);
	    items.add(item10);
	    items.add(item11);
	    items.add(item12);
	    items.add(item13);
	    currentOffset += qty;
	    return items;
	  }
	
	  private AsymmetricGridViewAdapter<?> getNewAdapter() {
		    return new AsymmetricGridViewAdapter<DemoItem>(ct, listView, adapter);
		  }

	/* (non-Javadoc)
	 * @see android.widget.AdapterView.OnItemClickListener#onItemClick(android.widget.AdapterView, android.view.View, int, long)
	 */
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		
		switch (position) {
		case 0:
			Intent it_calander =new Intent(ct,CalendarActivity.class);
			ct.startActivity(it_calander);
			break;
		case 1:
					
			break;
		case 2:
			
			break;
		case 3:
			
			break;
		case 4:
			
			break;
		case 5:
			
			break;
		case 6:
			
			break;
		case 7:
			
			break;
		case 8:
			
			break;
		case 9:
			
			break;
		case 10:
			
			break;
		case 11:
			
			break;
		case 12:
					
			break;
		case 13:
			
			break;
		default:
			break;
		}
	
	}

}
