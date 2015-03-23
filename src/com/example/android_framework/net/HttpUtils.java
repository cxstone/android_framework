package com.example.android_framework.net;

import java.util.Map;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.Request.Method;
import com.android.volley.RequestQueue;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

/**
 * 网络请求工具类
 * 
 * @author stone
 * 
 */
public class HttpUtils {
	private static final String TAG = HttpUtils.class.getSimpleName();

	private static HttpUtils mInstance;
	private static RequestQueue mRequestQueue;
	private ImageLoader mImageLoader;
	private static Context mCtx;

	private HttpUtils(Context context) {
		mCtx = context;
		// 创建所有请求的队列
		mRequestQueue = getRequestQueue();
		// 初始化图片加载器
		mImageLoader = new ImageLoader(mRequestQueue, new LruBitmapCache(
				LruBitmapCache.getCacheSize(context)));
	}

	/**
	 * 创建网络请求队列
	 * 
	 * @param context
	 * @return
	 */
	public RequestQueue getRequestQueue() {
		if (mRequestQueue == null) {
			/**
			 * 取得Application的Context，防止因为Activity的重新加载导致网络请求和图片加载的抖动
			 */
			mRequestQueue = Volley
					.newRequestQueue(mCtx.getApplicationContext());
		}
		return mRequestQueue;
	}

	public static synchronized HttpUtils getInstance(Context context) {
		if (mInstance == null) {
			mInstance = new HttpUtils(context);
		}
		return mInstance;
	}

	public <T> void addToRequestQueue(Request<T> req) {
		getRequestQueue().add(req);
	}

	public ImageLoader getImageLoader() {
		return mImageLoader;
	}

	/**
	 * 发起网络数据请求
	 * 
	 * @param context
	 * @param tag
	 * @param url
	 * @param param
	 * @param body
	 * @param listener
	 * @param errorListener
	 */
	public void post(Object tag, String url, Map<String, String> param,
			String body, Listener<String> listener, ErrorListener errorListener) {
		BaseRequest request = new BaseRequest(Method.GET, url, param, body,
				listener, errorListener);
		if (tag != null) {
			request.setTag(tag);
		} else {
			request.setTag(TAG);
		}
		addToRequestQueue(request);
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
