package com.mywings.questionset.Model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.mywings.questionset.BR;

/**
 * Created by Tatyabhau Chavan on 10/23/2015.
 */
public class QuestionSetMainMenu extends BaseObservable {


    private int icon;
    private String name;

    public QuestionSetMainMenu() {}


    @Bindable
    public int getIcon() {
        return icon;
    }

    public QuestionSetMainMenu setIcon(int icon) {
        this.icon = icon;
        notifyPropertyChanged(BR.icon);
        return this;
    }

    @Bindable
    public String getName() {
        return name;
    }

    public QuestionSetMainMenu setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.name);
        return this;
    }


}
