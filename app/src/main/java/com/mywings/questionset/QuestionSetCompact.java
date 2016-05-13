package com.mywings.questionset;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.mywings.questionset.Utils.InitHelperLimit;
import com.mywings.questionset.Utils.OnInformativeListener;
import com.mywings.questionset.Utils.OnInputManagerListener;

/**
 * Created by Tatyabhau Chavan on 2/25/2016.
 */
public abstract class QuestionSetCompact extends AppCompatActivity implements OnInformativeListener, OnInputManagerListener {

    public InitHelperLimit initHelperLimit;
    private LayoutInflater inflater = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        initHelperLimit = InitHelperLimit.getInstance();

    }

    @Override
    public void show(String message) {
        Toast.makeText(QuestionSetCompact.this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void show(String message, View id) {
        Snackbar.make(id, message, Snackbar.LENGTH_LONG)
                .setAction("OK", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    }
                }).show();
    }

    /**
     * @return
     */
    public ViewGroup getGroup() {
        final ViewGroup viewGroup = (ViewGroup) ((ViewGroup) this
                .findViewById(android.R.id.content)).getChildAt(0);
        return viewGroup;
    }

    @Override
    public LayoutInflater inflate() {
        if (inflater == null) {
            inflater = LayoutInflater.from(this);
        }
        return inflater;
    }

    @Override
    public <T extends ViewDataBinding> T inflate(int id) {
        return DataBindingUtil.inflate(inflate(), id, getGroup(), false);
    }

    @Override
    public <T extends ViewDataBinding> T attach(int id) {
        return DataBindingUtil.inflate(inflate(), id, getGroup(), true);
    }

    @Override
    public <T extends ViewDataBinding> T setContentLayout(int id) {
        return DataBindingUtil.setContentView(this, id);
    }

    @Override
    public RecyclerView.LayoutManager getLayoutManager(int flow) {
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setOrientation(flow);
        return linearLayoutManager;
    }

    @Override
    public void hide(View view) {
        if (null != view) {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromInputMethod(view.getApplicationWindowToken(), InputMethodManager.HIDE_IMPLICIT_ONLY);
        }
    }

    @Override
    public void show(View view) {
        if (null != view) {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromInputMethod(view.getWindowToken(), 1);
        }
    }
}
