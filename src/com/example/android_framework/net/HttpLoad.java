package com.example.android_framework.net;

import android.content.Context;

import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.example.android_framework.util.Constant;

/**
 * 所有网络请求方法
 * 
 * @author stone
 * 
 */
public class HttpLoad {

	/**
	 * 取消请求
	 * 
	 * @param tag
	 */
	public static void loadCancel(String tag) {
		HttpUtils.cancelAll(tag);
	}

	/**
	 * 测试方法
	 * 
	 * @param context
	 * @param tag
	 * @param listener
	 * @param errorListener
	 */
	public static void test(Context context, String tag,
			Listener<String> listener, ErrorListener errorListener) {
		HttpUtils.post(context, tag, Constant.API_TEST, null, null, listener,
				errorListener);
	}

}
