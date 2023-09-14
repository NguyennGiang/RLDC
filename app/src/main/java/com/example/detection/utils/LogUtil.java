package com.example.detection.utils;

import android.util.Log;

public class LogUtil {
    public static final String TAG = "DETECTION";

    public static void e(String message) {
        Log.e(TAG, message);
    }

    public static void w(String message) {
        Log.w(TAG, message);
    }

    public static void d(String message) { Log.d(TAG, message); }
}
