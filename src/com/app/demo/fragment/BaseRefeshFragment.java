package com.app.demo.fragment;

import com.app.demo.R;

import android.app.Activity;
import android.content.res.TypedArray;
import android.support.v4.app.Fragment;
import android.util.TypedValue;

/**
 * @author :LiuJie 2015年11月10日 下午6:24:43
 * @注释:加载数据刷新功能的fragment
 */
public abstract class BaseRefeshFragment extends Fragment {

	 public BaseRefeshFragment() {
		// TODO Auto-generated constructor stub
	 }
	
	 protected int getActionBarSize() {
        Activity activity = getActivity();
        if (activity == null) {
            return 0;
        }
        TypedValue typedValue = new TypedValue();
        int[] textSizeAttr = new int[]{R.attr.actionBarSize};
        int indexOfAttrTextSize = 0;
        TypedArray a = activity.obtainStyledAttributes(typedValue.data, textSizeAttr);
        int actionBarSize = a.getDimensionPixelSize(indexOfAttrTextSize, -1);
        a.recycle();
        return actionBarSize;
	    }
	 
	  protected int getScreenHeight() {
	        Activity activity = getActivity();
	        if (activity == null) {
	            return 0;
	        }
	        return activity.findViewById(android.R.id.content).getHeight();
	    }

}
