/**
 * 
 */
package com.app.demo.activity;

import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.android.volley.AuthFailureError;
import com.android.volley.VolleyError;
import com.android.volley.Request.Method;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.app.demo.R;
import com.app.demo.base.BasePage;
import com.app.demo.model.NewsChannel;
import com.app.demo.net.ResquestManager;
import com.app.demo.util.ApplicationUtil;
import com.lidroid.xutils.ViewUtils;

/**
 * @author LiuJie
 *
 */
public class NewPage extends BasePage {
	
	private String TAG="NewPage";
	/**
	 * @param context
	 */
	public NewPage(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see com.app.demo.base.BasePage#initView(android.view.LayoutInflater)
	 */
	@Override
	public View initView(LayoutInflater inflater) {
		View view=inflater.inflate(R.layout.view_radio_new_list, null);
		
		ViewUtils.inject(this, view);
		return view;
	}

	/* (non-Javadoc)
	 * @see com.app.demo.base.BasePage#initData()
	 */
	@Override
	public void initData() {
		Log.i(TAG, "数据初始化成功！");
		isInitDataSuccess=true;
		ResquestManager.addRequest(
				new StringRequest(Method.POST, "http://route.showapi.com/109-34", 
						responseListener(), errorListener()){
					@Override
					protected Map<String, String> getParams()
							throws AuthFailureError {
						Map<String, String> params=new HashMap<String, String>();
						params.put("showapi_appid", "12041");
						params.put("showapi_sign", "67f7892db890407f95cdf39f870b1234");
						params.put("showapi_timestamp", ApplicationUtil.getCurrentTime("yyyyMMddHHmmss"));
						return params;
					}
				}, this);
	}

	/**
	 * @author :LiuJie 2015年11月10日 上午9:13:51
	 * @注释:结构监听
	 */
	private Response.Listener<String> responseListener(){
		return new Response.Listener<String>() {

			@Override
			public void onResponse(String response) {
				Log.i(TAG, response);
				NewsChannel cEntity=JSON.parseObject(response, NewsChannel.class);
				Log.i(TAG, "chanel list:"+cEntity.getShowapi_res_body().getChannelList().size());
			}
		};
	}
	
	protected Response.ErrorListener errorListener() {
		return new Response.ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError error) {
				Toast.makeText(ct, error.getMessage(), Toast.LENGTH_LONG).show();
			}
		};
	}
}
