package com.app.demo.fragment;

import java.util.ArrayList;

import com.app.demo.R;
import com.github.ksoichiro.android.observablescrollview.ObservableListView;
import com.github.ksoichiro.android.observablescrollview.ObservableScrollViewCallbacks;
import com.github.ksoichiro.android.observablescrollview.ScrollUtils;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

/**
 * @author :LiuJie 2015年11月10日 下午6:33:58
 * @注释:需要显示新闻的碎片
 */
public class ViewPagerTabListViewFragment extends BaseRefeshFragment {
    
	private ObservableListView listView;
	 public static final String ARG_INITIAL_POSITION = "ARG_INITIAL_POSITION";
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view =inflater.inflate(R.layout.fragment_news_list, container, false);
		listView=(ObservableListView) view.findViewById(R.id.lv_newsList);
	    
		Activity parentActivity = getActivity();
		initData();
		Log.i("NewPage", ""+(parentActivity instanceof ObservableScrollViewCallbacks));
		  if (parentActivity instanceof ObservableScrollViewCallbacks) {
	            // Scroll to the specified position after layout
	            Bundle args = getArguments();
	            if (args != null && args.containsKey(ARG_INITIAL_POSITION)) {
	                final int initialPosition = args.getInt(ARG_INITIAL_POSITION, 0);
	                ScrollUtils.addOnGlobalLayoutListener(listView, new Runnable() {
	                    @Override
	                    public void run() {
	                        // scrollTo() doesn't work, should use setSelection()
	                        listView.setSelection(initialPosition);
	                    }
	                });
	            }

	            // TouchInterceptionViewGroup should be a parent view other than ViewPager.
	            // This is a workaround for the issue #117:
	            // https://github.com/ksoichiro/Android-ObservableScrollView/issues/117
	            listView.setTouchInterceptionViewGroup((ViewGroup) parentActivity.findViewById(R.id.root));

	            listView.setScrollViewCallbacks((ObservableScrollViewCallbacks) parentActivity);
	        }
		return view;
	}
	
	
	private void initData(){
	View header=	 getActivity().getLayoutInflater().inflate(R.layout.list_header_padding, listView, false);
	listView.addHeaderView(header);	
	listView.setAdapter(new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, getDummyData(15)));
		
	}
	
	  public static ArrayList<String> getDummyData(int num) {
	        ArrayList<String> items = new ArrayList<>();
	        for (int i = 1; i <= num; i++) {
	            items.add("Item " + i);
	        }
	        return items;
	    }
}
