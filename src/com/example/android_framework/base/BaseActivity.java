package com.example.android_framework.base;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.NetworkImageView;
import com.example.android_framework.R;
import com.example.android_framework.net.HttpLoad;
import com.example.android_framework.util.Constant;
import com.example.android_framework.util.Logger;

public class BaseActivity extends ActionBarActivity implements OnClickListener {
	private static final String TAG = BaseActivity.class.getSimpleName();

	private Dialog dialog;
	private Button btn;
	private NetworkImageView img;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		btn = (Button) findViewById(R.id.btn);
		img = (NetworkImageView) findViewById(R.id.img);
		btn.setOnClickListener(this);
		Logger.d(TAG, "onCreate");
		// Intent intent = new Intent(this, TestService.class);
		// startService(intent);

		// if (Utils.isNetworkConnected(this)) {
		HttpLoad.setNetworkImageView(this, img, Constant.IMG_TEST);

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

	// }

	@Override
	protected void onDestroy() {
		if (dialog != null) {
			dialog.dismiss();
		}
		super.onDestroy();
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

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn:
			// unregisterReceiver(new TestReceiver());
			// if (dialog == null) {
			// Logger.d(TAG, "Init Dialog");
			// dialog = DialogUtils.createDialog(this);
			// dialog.setOnDismissListener(new OnDismissListener() {
			//
			// @Override
			// public void onDismiss(DialogInterface dialog) {
			// Logger.d(TAG, "Dialog was dismissed!");
			// }
			// });
			// }
			// dialog.show();
			break;

		default:
			break;
		}
	}
}
