package com.app.demo.base;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.app.demo.net.ResquestManager;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public abstract class BaseVolleyActivity extends Activity {
	protected Activity activity;
	protected String TAG;
	public boolean isDebug=true;
	
	public abstract void initView();
	public abstract void initData();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (isDebug) {
			Log.i(TAG, "onCreate");
		}
		activity = this;
		initView();
		initData();
	}

	@Override
	public void onStop() {
		super.onStop();
		if (isDebug) {
		Log.i(TAG, "onStop");
		}
		ResquestManager.cancelAll(this);
	}

	protected void executeRequest(Request<?> request) {
		ResquestManager.addRequest(request, this);
	}

	protected Response.ErrorListener errorListener() {
		return new Response.ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError error) {
				Toast.makeText(activity, error.getMessage(), Toast.LENGTH_LONG).show();
			}
		};
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		if (isDebug) {
			Log.i(TAG, "onResume");
		}
		
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		if (isDebug) {
			Log.i(TAG, "onDestroy");
		}
		
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		if (isDebug) {
			Log.i(TAG, "onPause");
		}
	
	}
	
	@Override
	protected void onRestart() {
		super.onRestart();
		if (isDebug) {
			Log.i(TAG, "onRestart");
		}
	}
}
