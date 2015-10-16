package com.app.demo.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;
/**
 * @author Administrator
 * @ע�ͣ� ����������
 */
public class ApplicationUtil {

	public static int dip2px(Context context, float dpValue) {
	  final float scale = context.getResources().getDisplayMetrics().density;
	  return (int) (dpValue * scale + 0.5f);
	}
	
	public static int px2dip(Context context, float pxValue) {
	  final float scale = context.getResources().getDisplayMetrics().density;
	  return (int) (pxValue / scale + 0.5f);
	} 
	
	public static void ToastMessage(Context cont, String msg) {
		Toast.makeText(cont, msg, Toast.LENGTH_SHORT).show();
	}

	public static void ToastMessage(Context cont, int msg) {
		Toast.makeText(cont, msg, Toast.LENGTH_SHORT).show();
	}

	public static void ToastMessage(Context cont, String msg, int time) {
		Toast.makeText(cont, msg, time).show();
	}
	
	public static boolean setSharedPreferences(Context ct,String key,String value,String name){
		if (key!=null) {
			SharedPreferences sPreferences=ct.getSharedPreferences(name,Context.MODE_PRIVATE);
			boolean falg= sPreferences.edit().putString(key, value).commit();
			return falg;
		}else{
			return false;
		}
	 
	}
	public static boolean clearSharedPreferences(Context ct,String key,String name){
		if (key!=null) {
			SharedPreferences sPreferences=ct.getSharedPreferences(name,Context.MODE_PRIVATE);
			boolean falg= sPreferences.edit().remove(key).commit();
			return falg;
		}else{
			return false;
		}
	}
	
	public static String getSharedPreferences(Context ct,String key,String name){
		  if (key==null) {
			return null;
		   }
		  SharedPreferences sPreferences=ct.getSharedPreferences(name,Context.MODE_PRIVATE);
		  String value=sPreferences.getString(key, null);
		  return value;
   }
	/**
	 * @author LiuJie
	 * @功能:isNetworkConnected
	 */
	public static boolean isNetworkConnected(Context ct) {
		ConnectivityManager cm = (ConnectivityManager)ct.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo ni = cm.getActiveNetworkInfo();
		return ni != null && ni.isConnectedOrConnecting();
	}
	
	
	
}
