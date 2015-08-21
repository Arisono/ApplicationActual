package com.app.demo.activity.music;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.DisplayMetrics;
import android.widget.Toast;

import com.app.demo.R;
import com.app.demo.R.layout;
import com.app.demo.util.Constants;

/**
 * @author :LiuJie 2015年8月25日 下午7:36:05
 * @注释:music 
 */
public class MainContentActivity extends FragmentActivity implements Constants {
  
	public static final String ALARM_CLOCK_BROADCAST = "alarm_clock_broadcast";
	
	private int mScreenWidth;
	
	public MainFragment mMainFragment;
	
	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.act_music_frame_main);
		
		initView();
		initData();
		
	}
	
	private void initView() {
         //
		DisplayMetrics metrics=new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(metrics);
		mScreenWidth=metrics.widthPixels;
		//initSDcard
		initSDCard();
		//init广播
		IntentFilter filter = new IntentFilter();
		filter.addAction(ALARM_CLOCK_BROADCAST);
		registerReceiver(mAlarmReceiver, filter);
		
		//init碎片
		mMainFragment=new MainFragment();
		//getSupportFragmentManager().beginTransaction().
		//replace(R.id.frame_main, mMainFragment).commit();
		
		
	}
	
	private void initData() {

	}
	
	private void initSDCard() {
		IntentFilter intentFilter = new IntentFilter();
		intentFilter.setPriority(1000);// 设置最高优先级
		intentFilter.addAction(Intent.ACTION_MEDIA_MOUNTED);// sd卡被插入，且已经挂载
		intentFilter.addAction(Intent.ACTION_MEDIA_UNMOUNTED);// sd卡存在，但还没有挂载
		intentFilter.addAction(Intent.ACTION_MEDIA_REMOVED);// sd卡被移除
		intentFilter.addAction(Intent.ACTION_MEDIA_SHARED);// sd卡作为
															// USB大容量存储被共享，挂载被解除
		intentFilter.addAction(Intent.ACTION_MEDIA_BAD_REMOVAL);// sd卡已经从sd卡插槽拔出，但是挂载点还没解除
		// intentFilter.addAction(Intent.ACTION_MEDIA_SCANNER_STARTED);// 开始扫描
		// intentFilter.addAction(Intent.ACTION_MEDIA_SCANNER_FINISHED);// 扫描完成
		intentFilter.addDataScheme("file");
		registerReceiver(sdCardReceiver, intentFilter);// 注册监听函数
	}
	
	
	
	@Override
	public void onBackPressed() {
		super.onBackPressed();
	}
	
	
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
	}
	 
	
	/**@注释：广播  */
	private BroadcastReceiver mAlarmReceiver=new BroadcastReceiver(){

		@Override
		public void onReceive(Context context, Intent intent) {
			//退出程序
			finish();
		}
	      
	};
	
	private final BroadcastReceiver sdCardReceiver = new BroadcastReceiver() {
		@Override
		public void onReceive(Context context, Intent intent) {
			String action = intent.getAction();
			if (action.equals("android.intent.action.MEDIA_REMOVED")// 各种未挂载状态
					|| action.equals("android.intent.action.MEDIA_UNMOUNTED")
					|| action.equals("android.intent.action.MEDIA_BAD_REMOVAL")
					|| action.equals("android.intent.action.MEDIA_SHARED")) {
				finish();
				Toast.makeText(MainContentActivity.this, "SD卡以外拔出，本地数据没法初始化!",
						Toast.LENGTH_SHORT).show();
			}
		}
	};
}
