/**
 * 
 */
package com.app.demo.app;


import android.app.Activity;
import android.os.Handler;
import android.os.Looper;
import android.view.KeyEvent;
import android.widget.Toast;

/**
 * ˫���˳�
 * �������� 2014-05-12
 * @author ���� (http://my.oschina.net/LittleDY)
 * 
 */
public class DoubleClickExitHelper {

	private final Activity mActivity;
	
	private boolean isOnKeyBacking;
	private Handler mHandler;
	private Toast mBackToast;
	
	//���췽��    
	public DoubleClickExitHelper(Activity activity) {
		mActivity = activity;
		//������Handler��Ҫ��ˢ�²���UI�ģ���ô����Ҫ�����߳�����
		mHandler = new Handler(Looper.getMainLooper());
	}
	
	/**
	 * Activity onKeyDown�¼�
	 * */
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if(keyCode != KeyEvent.KEYCODE_BACK) {
			return false;
		}
		if(isOnKeyBacking) {
			//���ؼ����Σ��̶߳���ɾ��onBackTimeRunnable
			mHandler.removeCallbacks(onBackTimeRunnable);
			if(mBackToast != null){
				mBackToast.cancel();
			}
			// �˳�
			AppManager.getAppManager().AppExit(mActivity);;
			return true;
		} else {
			//��һ�ΰ����ؼ�
			isOnKeyBacking = true;
			if(mBackToast == null) {
		// ��������������ʾ��ʱ�䳤�̡�ToastĬ�ϵ�������LENGTH_LONG(��)��LENGTH_SHORT(��)��Ҳ����ʹ�ú�����2000ms 
				mBackToast = Toast.makeText(mActivity, "�ٴε�����˳�Ӧ�ã�", 2000);
			}
			mBackToast.show();
			//����postDelayed���������ӳٶ��ٺ����ʼ����
			//�ӳ�������ִ�У���������֮���ٴΰ����ؼ���isOnKeyBacking ==true
			mHandler.postDelayed(onBackTimeRunnable, 5000);
			return true;
		}
	}
	
	private Runnable onBackTimeRunnable = new Runnable() {
		
		@Override
		public void run() {
			isOnKeyBacking = false;
			if(mBackToast != null){
				mBackToast.cancel();
			}
		}
	};
}
