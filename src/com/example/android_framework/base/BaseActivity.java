package com.example.android_framework.base;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.example.android_framework.R;
import com.example.android_framework.net.HttpLoad;
import com.example.android_framework.util.Logger;
import com.example.android_framework.util.Utils;

public class BaseActivity extends ActionBarActivity {
	private static final String TAG = BaseActivity.class.getSimpleName();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		if (Utils.isNetworkConnected(this)) {
			HttpLoad.test(this, TAG, new Listener<String>() {

				@Override
				public void onResponse(String result) {
					Logger.d("SUCEES", result);
				}
			}, new ErrorListener() {

				@Override
				public void onErrorResponse(VolleyError error) {
					Logger.d("ERROR", error.getMessage());
				}
			});
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
