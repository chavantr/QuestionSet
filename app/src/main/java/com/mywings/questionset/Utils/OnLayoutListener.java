package com.mywings.questionset.Utils;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;

/**
 * Created by Tatyabhau Chavan on 3/3/2016.
 */
public interface OnLayoutListener {
    public LayoutInflater inflate();
    public <T extends ViewDataBinding> T inflate(int id);
    public <T extends ViewDataBinding> T attach(int id);
    public <T extends ViewDataBinding> T setContentLayout(int id);
    public RecyclerView.LayoutManager getLayoutManager(int flow);
}
