package com.example.android_framework.net;

import java.util.Map;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.Request.Method;
import com.android.volley.RequestQueue;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageRequest;
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
		mRequestQueue = getRequestQueue();
		mImageLoader = new ImageLoader(mRequestQueue, new LruBitmapCache(
				LruBitmapCache.getCacheSize(context)));
	}

	/**
	 * 获取网络请求队列
	 * 
	 * @param context
	 * @return
	 */
	public RequestQueue getRequestQueue() {
		if (mRequestQueue == null) {
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
	public void post(Object tag, String url, Map<String, String> param,
			String body, Listener<String> listener, ErrorListener errorListener) {
		BaseRequest request = new BaseRequest(Method.POST, url, param, body,
				listener, errorListener);
		if (tag != null) {
			request.setTag(tag);
		} else {
			request.setTag(TAG);
		}
		addToRequestQueue(request);
	}

	/**
	 * 加载网络图片及显示
	 * 
	 * @param context
	 * @param imageRequest
	 */
	public void iamgeLoad(ImageRequest imageRequest) {
		imageRequest.setShouldCache(true);
		addToRequestQueue(imageRequest);
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
