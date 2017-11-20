package com.example.pdg.careerd.Toast;

import android.content.Context;
import android.graphics.Paint;
import android.widget.Toast;

/**
 * Created by pdg on 2017-11-20.
 */

public class ToastMessage extends Toast {
    private Context context;

    public ToastMessage(Context context) {
        super(context);
        this.context = context;
    }

    public void printToast(String message) {
        this.makeText(context, message, this.LENGTH_SHORT).show();
    }
}
