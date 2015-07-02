package com.app.demo.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import android.content.Context;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

@SuppressWarnings("deprecation")
public class HttpClient {

	public boolean isWiFiActive(Context context) {
		ConnectivityManager connectivity = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (connectivity != null) {
			NetworkInfo[] infos = connectivity.getAllNetworkInfo();
			if (infos != null) {
				for (NetworkInfo ni : infos) {
					if (ni.getTypeName().equals("WIFI") && ni.isConnected()) {
						return true;
					}
				}
			}
		}
		return false;
	}

	public static boolean isWifi(Context context) {
		ConnectivityManager cm = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkINfo = cm.getActiveNetworkInfo();
		if (networkINfo != null
				&& networkINfo.getType() == ConnectivityManager.TYPE_WIFI) {
			return true;
		}
		return false;
	}

	public static boolean is3rd(Context context) {
		ConnectivityManager cm = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkINfo = cm.getActiveNetworkInfo();
		if (networkINfo != null
				&& networkINfo.getType() == ConnectivityManager.TYPE_MOBILE) {
			return true;
		}
		return false;
	}

	public static boolean isNetworkAvailable(Context context) {
		ConnectivityManager cm = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (cm == null) {
		} else {
			NetworkInfo[] info = cm.getAllNetworkInfo();
			if (info != null) {
				for (int i = 0; i < info.length; i++) {
					if (info[i].getState() == NetworkInfo.State.CONNECTED) {
						return true;
					}
				}
			}
		}
		return false;
	}

	public static boolean isGpsEnabled(Context context) {
		LocationManager lm = ((LocationManager) context
				.getSystemService(Context.LOCATION_SERVICE));
		List<String> accessibleProviders = lm.getProviders(true);
		return accessibleProviders != null && accessibleProviders.size() > 0;
	}

	/**
	 * 发送GET请求
	 * 
	 * @param url
	 * @param params
	 * @param headers
	 * @return
	 * @throws Exception
	 */
	public String sendGetRequest(String url, Map<String, String> params)
			throws Exception {
		String result = null;
		DefaultHttpClient httpclient = new DefaultHttpClient();
		StringBuilder buf = new StringBuilder(url);
		Set<Entry<String, String>> entrys = null;
		if (params != null && !params.isEmpty()) {
			if (buf.indexOf("?") == -1)
				buf.append("?");
			entrys = params.entrySet();
			for (Map.Entry<String, String> entry : entrys) {
				buf.append(entry.getKey()).append("=")
						.append(URLEncoder.encode(entry.getValue(), "UTF-8"))
						.append("&");
			}
			buf.deleteCharAt(buf.length() - 1);
		}
		HttpGet httpGet = new HttpGet(buf.toString());
		httpclient.getParams().setParameter(
				CoreProtocolPNames.HTTP_CONTENT_CHARSET, "UTF-8");
		httpclient.getParams().setParameter(
				CoreConnectionPNames.CONNECTION_TIMEOUT, 300000);
		httpclient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT,
				300000);
		HttpResponse response = httpclient.execute(httpGet);

		if (response.getStatusLine().getStatusCode() == 200) {
			String temp = EntityUtils.toString(response.getEntity());
			if (temp.length() > 0) {
				result = temp.trim().toString();
			} else {
				result = "201";
			}
		} else {
			InputStream stream = response.getEntity().getContent();
			BufferedReader br = new BufferedReader(new InputStreamReader(
					stream, "UTF-8"));
			StringBuffer bufer = new StringBuffer();
			String line;
			while (null != (line = br.readLine())) {
				bufer.append(line).append("\n");
			}
			result = "error response code:"
					+ response.getStatusLine().getStatusCode();
			result = null;
		}

		return result;
	}

	/**
	 * 
	 * */
	public String sendPostRequest(String url, Map<String, String> params)
			throws IOException {
		String result = null;
		HttpResponse response = null;
		DefaultHttpClient httpclient = new DefaultHttpClient();
		HttpPost httpPost = new HttpPost(url);
		try {
			List<NameValuePair> nvps = new ArrayList<NameValuePair>();
			if (params != null && !params.isEmpty()) {
				Set<Entry<String, String>> entrys = params.entrySet();
				for (Map.Entry<String, String> entry : entrys) {
					nvps.add(new BasicNameValuePair(entry.getKey(), URLDecoder
							.decode(entry.getValue(), "UTF-8")));
				}
			}
			httpPost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
			httpPost.setHeader("Content-Type",
					"application/x-www-form-urlencoded; charset=utf-8");
			httpPost.setHeader("Cookie",
					"JSESSIONID=" + params.get("sessionId"));
			httpclient.getParams().setParameter(
					CoreConnectionPNames.CONNECTION_TIMEOUT, 300000);
			httpclient.getParams().setParameter(
					CoreConnectionPNames.SO_TIMEOUT, 300000);
			response = httpclient.execute(httpPost);
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (response.getStatusLine().getStatusCode() == 200) {
			String temp = EntityUtils.toString(response.getEntity());
			if (temp.length() > 0) {
				result = temp.trim().toString();
			} else {
				result = null;
			}
		} else {
			prinInfo(response, "500");
			result = null;
		}

		return result;
	}

	public void prinInfo(HttpResponse response, String branch)
			throws IllegalStateException, IOException {
		InputStream stream = response.getEntity().getContent();
		BufferedReader br = new BufferedReader(new InputStreamReader(stream,
				"UTF-8"));
		StringBuffer bufer = new StringBuffer();
		String line;
		while (null != (line = br.readLine())) {
			bufer.append(line).append("\n");
		}
		System.out.println("分支" + branch + " 请求打印结果=" + bufer.toString());
	}
}
