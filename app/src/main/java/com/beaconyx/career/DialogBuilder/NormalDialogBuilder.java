package com.beaconyx.career.DialogBuilder;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;

/**
 * Created by pdg on 2017-11-20.
 */

public class NormalDialogBuilder extends AlertDialog.Builder {

    private Activity activity;

    public NormalDialogBuilder(@NonNull Context context) {
        super(context);
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public void setNormalDialogBuilder(final String title, final String message, final String positiveButtonStr, final String negativeButtonStr) {

        this
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(positiveButtonStr, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        activity.finish();
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

}
