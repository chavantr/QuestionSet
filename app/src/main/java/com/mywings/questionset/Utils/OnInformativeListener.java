package com.mywings.questionset.Utils;

import android.view.View;

/**
 * Created by Tatyabhau Chavan on 2/25/2016.
 */
public interface OnInformativeListener extends OnLayoutListener {
    void show(String message);
    void show(String message, View id);
}
