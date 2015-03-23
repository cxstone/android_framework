package com.example.android_framework.net;

import android.content.Context;

import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.ImageLoader.ImageListener;
import com.android.volley.toolbox.NetworkImageView;
import com.example.android_framework.R;
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
		HttpUtils.getInstance(context).post(tag, Constant.API_TEST, null, null,
				listener, errorListener);
	}

	/**
	 * 加载图片
	 * 
	 * @param context
	 * @param url
	 * @param listener
	 */
	public static void setImageView(Context context, String url,
			ImageListener listener) {
		HttpUtils.getInstance(context).getImageLoader().get(url, listener);
	}

	/**
	 * 把下载的图片设置到NetworkImageView中
	 * 
	 * @param context
	 * @param view
	 * @param url
	 */
	public static void setNetworkImageView(Context context,
			NetworkImageView view, String url) {
		view.setDefaultImageResId(android.R.drawable.btn_dialog);
		view.setErrorImageResId(R.drawable.abc_ic_search);
		view.setImageUrl(url, HttpUtils.getInstance(context).getImageLoader());
	}

}
