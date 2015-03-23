package com.example.android_framework.dialog;

import android.app.Activity;
import android.app.Dialog;

public class DialogUtils {

	/**
	 * 生成Dialog必须传入Activity或者Service
	 * 
	 * @param activity
	 * @return
	 */
	public static Dialog createDialog(Activity activity) {
		Dialog dialog = null;
		if (activity != null) {
			dialog = new Dialog(activity);
			dialog.setCanceledOnTouchOutside(false);
		}
		return dialog;
	}
}
