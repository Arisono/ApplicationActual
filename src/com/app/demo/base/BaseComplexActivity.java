package com.app.demo.base;

import java.util.Map;





import com.app.demo.util.ApplicationUtil;
import com.app.demo.util.Constants;
import com.app.demo.util.HttpClient;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

public abstract class BaseComplexActivity extends BaseSimpleActivity {
	
    /**@注释：公共进度条  */
	private Handler handler_net = new Handler() {
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case Constants.LOAD_SUCESS:
				break;
			case Constants.LOAD_EXCEPTION:
				ApplicationUtil.ToastMessage(ct,"服务器繁忙！");
				break;
			case Constants.LOAD_NOTNETWORK:
				ApplicationUtil.ToastMessage(ct,"网络未连接！");
				break;
			default:
				break;
			}
		};
	};
	
	/**
	 * @author LiuJie
	 * @功能:开启网络任务
	 */
	public void startHandlerThread(final String url,
			final Map<String, String> params, final Handler handler,
			final int what, final Message message, final Bundle bundle) {
		new Thread(new Runnable() {

			@Override
			public void run() {
				boolean isNetHas =ApplicationUtil.isNetworkConnected(ct);
				if (isNetHas) {
					/** @注释：处理网络请求返回结果 */
					String result = HttpResuqest(url, params);
					if (result != null) {
						bundle.putString("result", result);
						message.setData(bundle);
						message.what = what;
						handler.sendMessage(message);
					} else {
						handler_net.sendEmptyMessage(Constants.LOAD_EXCEPTION);
					}
				} else {
					handler_net.sendEmptyMessage(Constants.LOAD_NOTNETWORK);
				}
			}
		}).start();
	}
	
	/**
	 * @author LiuJie
	 * @功能:Test Thread
	 */
	public void startTestThread(final Handler handler,final int what,final long sleeptime) {
		new Thread(new Runnable() {

			@Override
			public void run() {
			try {
				Thread.sleep(sleeptime);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}	
			handler.sendEmptyMessage(what);
			
		}}).start();
	}
	
	
	
	/**
	 * @author LiuJie
	 * @功能:HttpClient get post  
	 */
	public String HttpResuqest(String url, Map<String, String> params) {
		String result = null;
		HttpClient hClient = new HttpClient();
		try {
			result = hClient.sendGetRequest(url, params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
