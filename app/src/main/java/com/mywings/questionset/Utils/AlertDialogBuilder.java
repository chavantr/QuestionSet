package com.mywings.questionset.Utils;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;

import com.mywings.questionset.R;

/**
 * Created by Tatyabhau Chavan on 2/25/2016.
 */
public class AlertDialogBuilder {
    private static Dialog dialog;
    public static void exitQuestionPaper(String title, String message, Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        builder.setView(inflater.inflate(R.layout.editquestionpapername, null));
        builder.setCancelable(false);
        builder.setIcon(null);
        builder.setTitle(title);
        builder.setPositiveButton(R.string.lbl_ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.setNegativeButton(context.getString(R.string.lbl_cancel), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        dialog = builder.create();
        dialog.show();
    }
}
