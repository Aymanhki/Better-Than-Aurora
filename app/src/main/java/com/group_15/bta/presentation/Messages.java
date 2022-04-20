package com.group_15.bta.presentation;

import android.app.Activity;
import android.app.AlertDialog;

import com.group_15.bta.R;

public class Messages {
    public static void fatalError(final Activity owner, String message) {
        AlertDialog alertDialog = new AlertDialog.Builder(owner).create();

        alertDialog.setTitle(owner.getString(R.string.fatalError));
        alertDialog.setMessage(message);
        alertDialog.setOnCancelListener(dialog -> owner.finish());

        alertDialog.show();
    }

    public static void message(final Activity owner, String message) {
        AlertDialog alertDialog = new AlertDialog.Builder(owner).create();
        alertDialog.setTitle(owner.getString(R.string.message_title_text_for_system_prompt));
        alertDialog.setMessage(message);
        alertDialog.show();
    }

    public static void warning(Activity owner, String message) {
        AlertDialog alertDialog = new AlertDialog.Builder(owner).create();

        alertDialog.setTitle(owner.getString(R.string.warning));
        alertDialog.setMessage(message);

        alertDialog.show();
    }
}