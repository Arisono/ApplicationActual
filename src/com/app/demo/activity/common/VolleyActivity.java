/**
 * 
 */
package com.app.demo.activity.common;

import java.io.IOException;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;


import com.app.demo.R;
import com.app.demo.model.Weather;
import com.app.demo.model.WeatherInfo;
import com.app.demo.util.GsonRequest;
import com.app.demo.util.XMLRequest;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

/**Volley通信框架
 * @author LiuJie
 * 测试第一个联网请求的例子
 */
public class VolleyActivity extends Activity {
	//public String url="http://api.yi18.net/drug/list?page=2&limit=10&type=id";
	
	public String TAG="VolleyActivity";
	public String url="http://flash.weather.com.cn/wmaps/xml/china.xml";
	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.service_demo_binder_main);
		final RequestQueue mQueue=Volley.newRequestQueue(this);
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				//doNetTask(mQueue);
				//doNetGetXMLTask(mQueue);
				doNetGetGsonTask(mQueue);
			}
		}).start();
	}
	
	public void doNetTask(RequestQueue mQueue){
		
		StringRequest request=new StringRequest(url, new Response.Listener<String>() {
		
			@Override
			public void onResponse(String response) {
				Log.d("TAG", response);
			}
		}, new Response.ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError error) {
				Log.e("TAG", error.getMessage(), error);  
			}
		}
		);
		mQueue.add(request);
	}
	
	public void doNetGetXMLTask(RequestQueue mQueue){
		
		XMLRequest xmlRequest=new XMLRequest(url, new Response.Listener<XmlPullParser>() {

			@Override
			public void onResponse(XmlPullParser response) {
				try {  
                    int eventType = response.getEventType();  
                    while (eventType != XmlPullParser.END_DOCUMENT) {  
                        switch (eventType) {  
                        case XmlPullParser.START_TAG:  
                            String nodeName = response.getName();  
                            if ("city".equals(nodeName)) {  
                                String pName = response.getAttributeValue(0);  
                                Log.d("TAG", "pName is " + pName);  
                            }  
                            break;  
                        }  
                        eventType = response.next();  
                    }  
                } catch (XmlPullParserException e) {  
                    e.printStackTrace();  
                } catch (IOException e) {  
                    e.printStackTrace();  
                }  
            }  				
			
		}, 
		new Response.ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError error) {
				Log.e("TAG", error.getMessage(), error);  
			}
		});
		
		mQueue.add(xmlRequest);
	}
	
	/**
	 * 
	 */
	private void doNetGetGsonTask(RequestQueue mQueue) {
		GsonRequest<Weather> gsonRequest = new GsonRequest<Weather>(  
		        "http://www.weather.com.cn/data/sk/101010100.html", Weather.class,  
		        new Response.Listener<Weather>() {  
		            @Override  
		            public void onResponse(Weather weather) {  
		                WeatherInfo weatherInfo = weather.getWeatherinfo();  
		                Log.d("TAG", "city is " + weatherInfo.getCity());  
		                Log.d("TAG", "temp is " + weatherInfo.getTemp());  
		                Log.d("TAG", "time is " + weatherInfo.getTime());  
		            }  
		        }, new Response.ErrorListener() {  
		            @Override  
		            public void onErrorResponse(VolleyError error) {  
		                Log.e("TAG", error.getMessage(), error);  
		            }  
		        });  
		mQueue.add(gsonRequest);  
	}
}
