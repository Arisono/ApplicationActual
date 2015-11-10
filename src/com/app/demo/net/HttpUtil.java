package com.app.demo.net;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.message.BasicNameValuePair;

/**
 * @author :LiuJie 2015年11月9日 上午10:20:51
 * @注释:
 */
@SuppressWarnings("deprecation")
public class HttpUtil {
	
	/**
	 * @author LiuJie
	 * @功能:HttpClient4.3
	 */
	public static Response sendGetRequest(
			String url,
			LinkedHashMap<String, Object> headers
			,Map<String, Object> params) throws Exception{
		DefaultHttpClient httpclient = new DefaultHttpClient();
		HttpResponse response = null;
		StringBuilder buf = new StringBuilder(url);
		if (url.indexOf("?") == -1)
			buf.append("?");
		else if (!url.endsWith("&"))
			buf.append("&");
		if (params != null && !params.isEmpty()) {
			Set<Entry<String, Object>> entrys = params.entrySet();
			for (Map.Entry<String, Object> entry : entrys) {
				buf.append(entry.getKey())
						.append("=")
						.append(URLEncoder.encode(entry.getValue().toString(), "UTF-8"))
						.append("&");
			}
		}
		HttpGet httpGet = new HttpGet(buf.toString());
		if (headers!=null) {
			for(String key:headers.keySet()){
				httpGet.setHeader(key, headers.get(key).toString());
			}
		}
		response =httpclient.execute(httpGet);
		//默认重连
		DefaultHttpRequestRetryHandler dhr = new DefaultHttpRequestRetryHandler(3,true);  
		httpclient.setHttpRequestRetryHandler(dhr);
		return Response.getResponse(response,httpclient);
	};
	
	
	public static Response sendPostRequest(
			String url,
			LinkedHashMap<String, Object> headers
			,Map<String, Object> params
			) throws Exception{
		HttpResponse response = null;
		DefaultHttpClient httpclient = new DefaultHttpClient();
		HttpPost httpPost = new HttpPost(url);
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		if (params != null && !params.isEmpty()) {
			Set<Entry<String, Object>> entrys = params.entrySet();
			for (Map.Entry<String, Object> entry : entrys) {
				nvps.add(new BasicNameValuePair(entry.getKey(), URLEncoder
						.encode(entry.getValue().toString(), "utf-8")));
			}
		}
		httpPost.setEntity(new UrlEncodedFormEntity(nvps));
		if (headers!=null) {
			for(String key:headers.keySet()){
				System.out.println("add header:"+key+" value:"+headers.get(key).toString());
				httpPost.setHeader(key, headers.get(key).toString());
			}
		}
		response =httpclient.execute(httpPost);
		return Response.getResponse(response,httpclient);
	}
	
	
	public static Response sendPostRequest(
			String url,
			LinkedHashMap<String, Object> headers
			,String body
			) throws Exception{
		HttpResponse response = null;
		DefaultHttpClient httpclient = new DefaultHttpClient();
		HttpPost httpPost = new HttpPost(url);
		if (headers!=null) {
			for(String key:headers.keySet()){
				System.out.println("add header:"+key+" value:"+headers.get(key).toString());
				httpPost.setHeader(key, headers.get(key).toString());
			}
		}
		if (body!=null) {
			httpPost.setEntity(new StringEntity(body,"UTF-8"));
		}
		response =httpclient.execute(httpPost);
		return Response.getResponse(response,httpclient);
	}
	
	/**@注释：
	 * 响应结果封装  */
	public static class Response{
		private int statusCode;//响应状态码
		private String responseText;//响应正文
		private CookieStore cookieStore;//cookie栈
		private Header[] heads;//请求头
		
		public Response(HttpResponse response
				,DefaultHttpClient httpclient) 
				throws IllegalStateException, IOException, Exception{
			this.statusCode=response.getStatusLine().getStatusCode();
			this.responseText=read2String(response.getEntity().getContent());
			this.heads=response.getAllHeaders();
			this.cookieStore=httpclient.getCookieStore();
		}
		
		public static Response getResponse(HttpResponse response,DefaultHttpClient httpclient)
				throws IllegalStateException, IOException, Exception {
			if (response != null)
				return new Response(response,httpclient);
			return null;
		}
		
		public int getStatusCode() {
			return statusCode;
		}
		public void setStatusCode(int statusCode) {
			this.statusCode = statusCode;
		}
		public String getResponseText() {
			return responseText;
		}
		public void setResponseText(String responseText) {
			this.responseText = responseText;
		}
		public CookieStore getCookieStore() {
			return cookieStore;
		}
		public void setCookieStore(CookieStore cookieStore) {
			this.cookieStore = cookieStore;
		}
		public Header[] getHeads() {
			return heads;
		}
		public void setHeads(Header[] heads) {
			this.heads = heads;
		} 
	}
	
	
	public static String read2String(InputStream inStream) throws Exception {
		ByteArrayOutputStream outSteam = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int len = 0;
		while ((len = inStream.read(buffer)) != -1) {
			outSteam.write(buffer, 0, len);
		}
		try {
			outSteam.close();
			inStream.close();
		} catch (Exception e) {
           e.printStackTrace();
		}
		return new String(outSteam.toByteArray(), "UTF-8");
	}
	
	
}
