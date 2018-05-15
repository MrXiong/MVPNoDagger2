package com.mvp.ultimate;

import android.support.design.widget.Snackbar;
import android.view.View;

/**
 * Created by ZX on 2018/5/8.
 */

public class SnackbarUtil {
    public static void show(View view, String msg) {
        Snackbar.make(view, msg, Snackbar.LENGTH_LONG).show();
    }

    public static void showShort(View view, String msg) {
        Snackbar.make(view, msg, Snackbar.LENGTH_SHORT).show();
    }
}
