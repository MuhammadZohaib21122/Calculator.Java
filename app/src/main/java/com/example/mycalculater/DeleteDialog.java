package com.example.mycalculater;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

public class DeleteDialog {

    public interface DeleteDialogListener {
        void onDeleteConfirmed();
    }

    public static void show(Context context, final DeleteDialogListener listener) {
        new AlertDialog.Builder(context)
                .setTitle("Delete Entry")
                .setMessage("Are you sure you want to delete this entry?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        listener.onDeleteConfirmed();
                    }
                })
                .setNegativeButton(android.R.string.no, null)
                .show();
    }
}
