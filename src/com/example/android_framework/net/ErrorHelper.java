package com.example.android_framework.net;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ServerError;
import com.android.volley.VolleyError;

public class ErrorHelper {
	private static boolean isNetworkProblem(VolleyError error) {
		return (error instanceof NetworkError || error instanceof NoConnectionError);
	}

	private static boolean isServerProblem(VolleyError error) {
		return (error instanceof AuthFailureError || error instanceof ServerError);
	}
}
