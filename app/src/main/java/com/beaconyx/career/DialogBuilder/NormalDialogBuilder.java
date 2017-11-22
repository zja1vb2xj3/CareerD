package com.beaconyx.career.DialogBuilder;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;

import com.beaconyx.career.Activity.LoadActivity;
import com.beaconyx.career.Activity.MainActivity;

/**
 * Created by pdg on 2017-11-20.
 */

public class NormalDialogBuilder extends AlertDialog.Builder {

    public NormalDialogBuilder(@NonNull Context context) {
        super(context);
    }

    public interface OnFinishActivityDialogCancelListener {
        void onCancel();
    }

    private OnFinishActivityDialogCancelListener onFinishActivityDialogCancelListener;

    public void setOnFinishActivityDialogCancelListener(OnFinishActivityDialogCancelListener onFinishActivityDialogCancelListener) {
        this.onFinishActivityDialogCancelListener = onFinishActivityDialogCancelListener;
    }

    public void finishActivityNormalDialogBuilder(final String title, final String message, final String positiveButtonStr, final String negativeButtonStr) {

        this
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(positiveButtonStr, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        if (onFinishActivityDialogCancelListener != null)
                            onFinishActivityDialogCancelListener.onCancel();

                    }
                })
                .setNegativeButton(negativeButtonStr, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                })
                .show();

    }


    public void appPushNotiNormalDialogBuilder(final String title, final String message, final String positiveButtonStr) {

        this
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(positiveButtonStr, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();

                        if (onPushDialogCancelListener != null) {
                            onPushDialogCancelListener.onCancel();
                        }
                    }
                })
                .show();
    }

    public interface OnPushDialogCancelListener {
        void onCancel();
    }

    private OnPushDialogCancelListener onPushDialogCancelListener;

    public void setOnPushDialogCancelListener(OnPushDialogCancelListener onPushDialogCancelListener) {
        this.onPushDialogCancelListener = onPushDialogCancelListener;
    }
}
