package com.app.demo.activity.music;

import java.util.Timer;
import java.util.TimerTask;


import android.os.Handler;
import android.os.Message;

/**
 * @author :LiuJie 2015年10月9日 上午11:36:51
 * @注释:定时器类
 */
public class MusicTimer {
	public final static int REFRESH_PROGRESS_EVENT = 0x100;
	private static final int INTERVAL_TIME = 1000;
	private Handler[] mHandler;
	private Timer mTimer;
	private TimerTask mTimerTask;
	private int what;
	private boolean mTimerStart = false;
	
	public MusicTimer(Handler... handler) {
		this.mHandler = handler;
		this.what = REFRESH_PROGRESS_EVENT;
		mTimer = new Timer();
	}
	
	public void startTimer() {
		if (mHandler == null || mTimerStart) {
			return;
		}
		mTimerTask = new MyTimerTask();
		mTimer.schedule(mTimerTask, INTERVAL_TIME, INTERVAL_TIME);
		mTimerStart = true;
	}

	public void stopTimer() {
		if (!mTimerStart) {
			return;
		}
		mTimerStart = false;
		if (mTimerTask != null) {
			mTimerTask.cancel();
			mTimerTask = null;
		}
	}
	
	class MyTimerTask extends TimerTask {
		@Override
		public void run() {
			if (mHandler != null) {
				for (Handler handler : mHandler) {
					Message msg = handler.obtainMessage(what);
					msg.sendToTarget();
				}
			}
		}
	}
}
