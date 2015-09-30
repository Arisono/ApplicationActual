package com.app.demo.activity.music;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.media.AudioManager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.app.demo.R;
import com.app.demo.app.AppContext;
import com.app.demo.invalidate.SPStorage;
import com.app.demo.util.Constants;

/**
 * @author LiuJie
 * @功能:本地音乐列表展示
 */
public class MyMusicManager extends MainUIManager implements Constants,
		OnTouchListener {
	
	private LayoutInflater mInflater;
	private Activity mActivity;
	private String TAG = MyMusicManager.class.getSimpleName();
	private UIManager mUIManager;
	private RelativeLayout mBottomLayout, mMainLayout;
	private Bitmap defaultArtwork;
	private ListView mListView;
	
	private ServiceManager mServiceManager = null;
	private MusicPlayBroadcast mPlayBroadcast;

	private int mFrom;
	private Object mObj;
    /**
	 * @author LiuJie
	 * @功能:构造方法
	 */
	public MyMusicManager(Activity activity,UIManager manager) {
		this.mActivity=activity;
		mInflater=LayoutInflater.from(activity);
		this.mUIManager=manager;
	}
	
	public View getView(int from, Object object) {
		//View
		View contentView = mInflater.inflate(R.layout.music_list_local, null);
		mFrom = from;
		mObj = object;
		initBg(contentView);
		initView(contentView);
		return contentView;
	}
	
	private void initBg(View view) {
		mMainLayout = (RelativeLayout) view
				.findViewById(R.id.main_mymusic_layout);
		mMainLayout.setOnTouchListener(this);
		SPStorage mSp = new SPStorage(mActivity);
		String mDefaultBgPath = mSp.getPath();
		Bitmap bitmap = mUIManager.getBitmapByPath(mDefaultBgPath);
		if (bitmap != null) {
			mMainLayout.setBackground(new BitmapDrawable(mActivity
					.getResources(), bitmap));
		} else {
			mMainLayout.setBackgroundResource(R.drawable.bg);
		}
	}
	
	private void initView(View view) {
		defaultArtwork = BitmapFactory.decodeResource(mActivity.getResources(),
				R.drawable.img_album_background);
		//ServiceManager   全局初始化后的对象
		mServiceManager=AppContext.mServiceManager;
		
		mBottomLayout = (RelativeLayout) view.findViewById(R.id.bottomLayout);
		mListView = (ListView) view.findViewById(R.id.music_listview);
		/**@注释：音乐回放即媒体音量  */
		mActivity.setVolumeControlStream(AudioManager.STREAM_MUSIC);
		/**@注释：设置广播  */
		mPlayBroadcast = new MusicPlayBroadcast();
		IntentFilter filter = new IntentFilter(BROADCAST_NAME);
		filter.addAction(BROADCAST_NAME);
		filter.addAction(BROADCAST_QUERY_COMPLETE_NAME);
		mActivity.registerReceiver(mPlayBroadcast, filter);
		
		//
	}
	
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected void setBgByPath(String path) {
		// TODO Auto-generated method stub

	}

	@Override
	public View getView() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public View getView(int from) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	
	private class MusicPlayBroadcast extends BroadcastReceiver {

		@Override
		public void onReceive(Context context, Intent intent) {
			if (intent.getAction().equals(BROADCAST_NAME)) {
				
			}			
		}
		
	}
	

}
