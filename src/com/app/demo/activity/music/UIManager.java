package com.app.demo.activity.music;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import com.app.demo.R;
import com.app.demo.activity.music.MainContentActivity.OnBackListener;
import com.app.demo.invalidate.SPStorage;
import com.app.demo.util.Constants;

public class UIManager implements Constants,OnBackListener {
	
	private Activity mActivity;
	private MainContentActivity mMainActivity;
	private View mView;
	private LayoutInflater mInflater;
	private RelativeLayout mMainLayout;
	private ChangeBgReceiver mReceiver;
	
	/** mViewPager为第一层 mViewPagerSub为第二层（例如从文件夹或歌手进入列表，点击列表会进入第二层） */
	private ViewPager mViewPager, mViewPagerSub;
	private List<View> mListViews, mListViewsSub;
	
	public UIManager(Activity activity,View view) {
		this.mActivity = activity;
		this.mView = view;
		mMainActivity = (MainContentActivity) activity;
		this.mInflater = LayoutInflater.from(activity);
		initBroadCast();
		initBg();
		init();
	}
	
	
	private void init() {
		mViewPager=(ViewPager)findViewById(R.id.viewPager);
        mViewPagerSub = (ViewPager) findViewById(R.id.viewPagerSub);
		mListViews = new ArrayList<View>();
		mListViewsSub = new ArrayList<View>();
		mViewPager.addOnPageChangeListener(new MyOnPageChangeListener());
		mViewPagerSub.addOnPageChangeListener(new MyOnPageChangeListenerSub());
	}
	
	private void initBg() {
		SPStorage mSp = new SPStorage(mActivity);
		String mDefaultBgPath = mSp.getPath();
		mMainLayout = (RelativeLayout) findViewById(R.id.main_layout);
		Bitmap bitmap = getBitmapByPath(mDefaultBgPath);
		if(bitmap != null) {
			mMainLayout.setBackground(new BitmapDrawable(mActivity.getResources(), bitmap));
		}
		//如果第一次进来 SharedPreference中没有数据
		if(TextUtils.isEmpty(mDefaultBgPath)) {
			mSp.savePath("004.jpg");
			}
	}
	
	private void initBroadCast() {
		mReceiver = new ChangeBgReceiver();
		IntentFilter filter = new IntentFilter(BROADCAST_CHANGEBG);
		mActivity.registerReceiver(mReceiver, filter);
	}
	
	private View findViewById(int id) {
		return mView.findViewById(id);
	}
	
	public Bitmap getBitmapByPath(String path) {
		AssetManager am = mActivity.getAssets();
		Bitmap bitmap = null;
		try {
			InputStream is = am.open("bkgs/" + path);
			bitmap = BitmapFactory.decodeStream(is);
			is.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bitmap;
	}
	
	public void setCurrentItem() {
		if (mViewPagerSub.getChildCount() > 0) {
			mViewPagerSub.setCurrentItem(0, true);
		} else {
			mViewPager.setCurrentItem(0, true);
		}
	}
	
	public void setContentType(int type) {
		// 此处可以根据传递过来的view和type分开来处理
		setContentType(type, null);
	}
	public void setContentType(int type, Object obj) {
		
	}
	
	
	
	
	private class MyOnPageChangeListener implements OnPageChangeListener {
		int onPageScrolled = -1;
		@Override
		public void onPageScrollStateChanged(int arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onPageSelected(int arg0) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	private class MyOnPageChangeListenerSub implements OnPageChangeListener {

		@Override
		public void onPageScrollStateChanged(int arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onPageSelected(int arg0) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	
	private class ChangeBgReceiver extends BroadcastReceiver {
		@Override
		public void onReceive(Context context, Intent intent) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	private OnRefreshListener mRefreshListener;
	public interface OnRefreshListener {
		public void onRefresh();
	}
	public void setOnRefreshListener(OnRefreshListener listener) {
		mRefreshListener = listener;
	}
	
	@Override
	public void onBack() {
		if (mViewPagerSub.isShown()) {
			mViewPagerSub.setCurrentItem(0, true);
		} else if (mViewPager.isShown()) {
			mViewPager.setCurrentItem(0, true);
		}
	}

}
