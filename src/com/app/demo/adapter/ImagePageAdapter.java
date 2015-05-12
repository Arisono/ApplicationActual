package com.app.demo.adapter;

import com.app.demo.R;

import android.content.Context;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
/**
 * @author :LiuJie 时间: 2015年4月20日 上午9:16:05
 * @注释:图片轮询适配器
 */
public class ImagePageAdapter extends PagerAdapter {
	private int[] resImageIds;
	private Context context;
	private boolean isShowResImage=true;
    public ImagePageAdapter(Context context,int [] imageIds){
    	this.resImageIds=imageIds;
    	this.context=context;
    	
    }
	@Override
	public void destroyItem(View view, int position, Object arg2) {
	//	System.out.println("销毁 arg2="+position);
		((ViewPager) view).removeView((View) arg2);
	}

	@Override
	public void finishUpdate(View arg0) {

	}

	@Override
	public int getCount() {
		return resImageIds.length;
	}
 
	@Override
	public Object instantiateItem(View container, final int position) {
		View view=View.inflate(context, R.layout.vp_item_image, null);
		((ViewPager)container).addView(view);
		ImageView imageView=(ImageView) view.findViewById(R.id.iv_vp_image);
		if (isShowResImage) {
			imageView.setImageResource(resImageIds[position]);
		} else {
			//bitmapUtils.display(imageView, uriList.get(position));
		}
		
		imageView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
            System.out.println("v id="+resImageIds[position]);	
			}
		});
		return view;
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		return arg0 == arg1;
	}

	@Override
	public void restoreState(Parcelable arg0, ClassLoader arg1) {
	}

	@Override
	public Parcelable saveState() {
		return null;
	}

	@Override
	public void startUpdate(View arg0) {
	}

}
