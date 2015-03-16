package com.example.android_framework.net;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.HttpHeaderParser;
import com.example.android_framework.util.Logger;
import com.google.gson.JsonSyntaxException;

/**
 * 网络请求基类
 * 
 * @author stone
 * 
 */
public class BaseRequest extends Request<String> {
	private static final String TAG = BaseRequest.class.getSimpleName();
	private static final String CONTENT_TYPE = "application/json";
	private static final String CHARSET = "UTF-8";
	private static final String PLAT_FORM = "android";
	// 请求超时时间
	private static final int SOCKET_TIMEOUT = 60 * 1000;

	// 请求完成后回调接口
	private final Listener<String> mListener;
	// 头部信息
	private static Map<String, String> mHeader = new HashMap<String, String>();
	// 请求体信息
	private String body;

	public BaseRequest(int method, String url, Map<String, String> headers,
			String body, Listener<String> mListener, ErrorListener errorListener) {
		super(method, url, errorListener);
		Logger.d(TAG, "REQUEST_URL : " + url);
		this.mListener = mListener;
		this.body = body;
		mHeader.put("Charset", CHARSET);
		mHeader.put("Plat-Form", PLAT_FORM);
		if (headers != null)
			mHeader.putAll(headers);
		setSocketTimeout(SOCKET_TIMEOUT);
	}

	@Override
	public Map<String, String> getHeaders() throws AuthFailureError {
		return mHeader != null ? mHeader : super.getHeaders();
	}

	@Override
	public byte[] getBody() throws AuthFailureError {
		Logger.d(TAG, "REQUEST_BODY : " + body);
		if (body != null) {
			return body.getBytes();
		} else {
			return null;
		}
	}

	@Override
	public String getBodyContentType() {
		return CONTENT_TYPE;
	}

	@Override
	protected void deliverResponse(String response) {
		mListener.onResponse(response);
	}

	@Override
	protected Response<String> parseNetworkResponse(NetworkResponse response) {
		try {
			String json = new String(response.data,
					HttpHeaderParser.parseCharset(response.headers));
			Logger.d(TAG, "RESPONSE : " + json);
			return Response.success(json,
					HttpHeaderParser.parseCacheHeaders(response));
		} catch (UnsupportedEncodingException e) {
			return Response.error(new ParseError(e));
		} catch (JsonSyntaxException e) {
			return Response.error(new ParseError(e));
		}
	}

	/**
	 * 设置请求延迟
	 * 
	 * @param timeOut
	 */
	public void setSocketTimeout(int timeOut) {
		setRetryPolicy(new DefaultRetryPolicy(timeOut, 0,
				DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
	}

}
