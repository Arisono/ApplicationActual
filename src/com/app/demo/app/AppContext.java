package com.app.demo.app;

import java.io.File;






import com.app.demo.activity.music.ServiceManager;
import com.app.demo.net.ResquestManager;

import android.app.Application;
import android.os.Environment;
import android.util.Log;
/**
 * @author :LiuJie 2015年8月25日 下午7:09:59
 * @注释:全局应用类
 */
public class AppContext extends Application {
     
	
	private static final String TAG = "AppContext";
	public static String lrcPath = "/lrc";
	private static String rootPath = "/ApplicationActual";
	public static boolean mIsSleepClockSetting = false;
	public static ServiceManager mServiceManager = null;
	
	@Override
	public void onCreate() {
		super.onCreate();
		init_volley();
		initPath();//音乐
		mServiceManager = new ServiceManager(this);
	}
	
	/**
	 * @author LiuJie
	 * @功能:music 初始化路径
	 */
    private void initPath() {
    	Log.i(TAG, "initPath()执行");
    	String ROOT = "";
		if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
			ROOT = Environment.getExternalStorageDirectory().getPath();
		}
		rootPath = ROOT + rootPath;
		lrcPath = rootPath + lrcPath;
		File lrcFile = new File(lrcPath);
		if(lrcFile.exists()) {
			lrcFile.mkdirs();
		}
	}
    
    private void init_volley(){
    	ResquestManager.init(this);
    }
}
