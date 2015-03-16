package com.example.android_framework.net;

import java.util.Map;

import android.content.Context;

import com.android.volley.Request.Method;
import com.android.volley.RequestQueue;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.Volley;

/**
 * 网络请求工具类
 * 
 * @author stone
 * 
 */
public class HttpUtils {
	private static final String TAG = HttpUtils.class.getSimpleName();
	private static RequestQueue mRequestQueue;

	private HttpUtils() {
	}

	/**
	 * 获取网络请求队列
	 * 
	 * @param context
	 * @return
	 */
	private synchronized static RequestQueue getRequestQueue(Context context) {
		if (mRequestQueue == null) {
			mRequestQueue = Volley.newRequestQueue(context);
		}
		return mRequestQueue;
	}

	/**
	 * 发起POST网络请求
	 * 
	 * @param context
	 * @param tag
	 * @param url
	 * @param param
	 * @param body
	 * @param listener
	 * @param errorListener
	 */
	public static void post(Context context, Object tag, String url,
			Map<String, String> param, String body, Listener<String> listener,
			ErrorListener errorListener) {
		BaseRequest request = new BaseRequest(Method.POST, url, param, body,
				listener, errorListener);
		if (tag != null) {
			request.setTag(tag);
		} else {
			request.setTag(TAG);
		}
		getRequestQueue(context).add(request);
	}

	/**
	 * 取消设置对应Tag的网络请求
	 * 
	 * @param tag
	 *            为空时，取消所有默认请求
	 */
	public static void cancelAll(String tag) {
		if (mRequestQueue != null) {
			mRequestQueue.cancelAll(tag);
		} else {
			cancelAll(TAG);
		}
	}

}
