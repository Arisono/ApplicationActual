/**
 * 
 */
package com.app.demo.activity.common;

import com.app.demo.R;
import com.app.demo.service.MyService;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

/**
 * @author LiuJie
 * ���ַ�ʽ�����������ڷ���
 */
public class StartService extends Activity {
	
	private static final String TAG = "StartService";
	private boolean binded;  
	
	private TextView start;
	private TextView end;
	private TextView bind;
	private TextView unbind;
	
	private ServiceConnection conn=new ServiceConnection() {
		
		@Override
		public void onServiceDisconnected(ComponentName name) {
			Log.i(TAG, "onServiceConnected called.");  
		}
		
	   /** 
	     *  Called when a connection to the Service has been lost. 
	     *  This typically happens when the process hosting the service has crashed or been killed. 
	     *  This does not remove the ServiceConnection itself. 
	     *  this binding to the service will remain active, 
	     *  and you will receive a call to onServiceConnected when the Service is next running. 
	     */  
		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			Log.i(TAG, "onServiceConnected called.");  
			
		}
	};
	
	protected void onCreate(Bundle savedInstanceState) {
		
		setContentView(R.layout.service_demo_binder_main);
		initView();
	};
	
	public void initView(){
		
		start=(TextView) findViewById(R.id.tv_start_service);
		end=(TextView) findViewById(R.id.tv_end_service);
		bind=(TextView) findViewById(R.id.tv_bind_service);
		unbind=(TextView) findViewById(R.id.tv_unbind_service);
		
		
		start.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				start(null);
			}
		});
		
		end.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				stop(null);
			}
		});
		
		bind.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				bind(null);				
			}
		});
		
		unbind.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				unbind(null);				
			}
		});
		
	}
	
	
	/* (non-Javadoc)
	 * @see android.app.Activity#onDestroy()
	 */
	@Override
	protected void onDestroy() {
		super.onDestroy();
		unbind(null);  
	}
	/**
	 * �� 
	 * @param view 
	 */
	private void bind(View view) {
	   Intent intent = new Intent(this, MyService.class);  
	   bindService(intent, conn, Context.BIND_AUTO_CREATE);  
	}
	
	/** 
	 * ���� 
	 * @param view 
	 */  
	public void unbind(View view) {  
		/**ע�ͣ����������ж�֮�󣬲��ܽ��н��󶨲������ᱨ�쳣 */
		if (binded) {  
	        unbindService(conn);  
	        binded = false;  
	    }  
	}  
	
	/** 
     * �������� 
     * @param view 
     */  
    public void start(View view) {  
        Intent intent = new Intent(this, MyService.class);  
        startService(intent);  
    }  
    
    /** 
     * ֹͣ���� 
     * @param view 
     */  
    public void stop(View view) {  
        Intent intent = new Intent(this, MyService.class);  
        stopService(intent);  
    }  
    
    
}
