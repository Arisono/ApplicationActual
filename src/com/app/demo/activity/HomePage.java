/**
 * 
 */
package com.app.demo.activity;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.app.demo.R;
import com.app.demo.adapter.DefaultListAdapter;
import com.app.demo.adapter.DemoAdapter;
import com.app.demo.base.BasePage;
import com.app.demo.model.DemoItem;
import com.felipecsl.asymmetricgridview.library.Utils;
import com.felipecsl.asymmetricgridview.library.widget.AsymmetricGridView;
import com.felipecsl.asymmetricgridview.library.widget.AsymmetricGridViewAdapter;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

/**
 * @author LiuJie
 *  
 */
public class HomePage extends BasePage {
	
	private String TAG="HomePage";
	
	@ViewInject(R.id.listView)
	private AsymmetricGridView listView;
	private DemoAdapter adapter;
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
		Log.i(TAG, "数据初始化成功！");
		isInitDataSuccess=true;
		adapter = new DefaultListAdapter(ct, getMoreItems(51));
	    listView.setRequestedColumnCount(3);
	    listView.setRequestedHorizontalSpacing(Utils.dpToPx(ct, 1));
	    listView.setAdapter(getNewAdapter());
	    /**@注释：打开会出现颜色问题 2015年5月8日 */
	   // listView.setDebugging(true);
//	    listView.setOnItemClickListener(this);	
	}
	
	
	private List<DemoItem> getMoreItems(int qty) {
	    List<DemoItem> items = new ArrayList<DemoItem>();
	    for (int i = 0; i < qty; i++) {
	      int colSpan = Math.random() < 0.2f ? 2 : 1;
	      // column/row span.
	      //int rowSpan = Math.random() < 0.2f ? 2 : 1;
	      int rowSpan = colSpan;
	      DemoItem item = new DemoItem(colSpan, rowSpan, currentOffset + i);
	      items.add(item);
	    }
	    currentOffset += qty;
	    return items;
	  }
	
	  private AsymmetricGridViewAdapter<?> getNewAdapter() {
		    return new AsymmetricGridViewAdapter<DemoItem>(ct, listView, adapter);
		  }

}
