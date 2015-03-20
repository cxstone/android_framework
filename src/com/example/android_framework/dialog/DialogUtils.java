package com.example.android_framework.dialog;

import android.app.Activity;
import android.app.Dialog;

public class DialogUtils {

	public static Dialog createDialog(Activity activity) {
		Dialog dialog = null;
		if (activity != null) {
			dialog = new Dialog(activity);
			dialog.setCanceledOnTouchOutside(false);
		}
		return dialog;
	}
}
