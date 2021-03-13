package com.example.appcaretask.Common;

import android.content.Context;
import android.widget.Toast;

public class Constants {

    public static void displayLongToast(Context mContext, String message) {
        try {
            Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
