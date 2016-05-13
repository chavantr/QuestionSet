package com.mywings.questionset.Model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.mywings.questionset.BR;

/**
 * Created by Tatyabhau Chavan on 12/28/2015.
 */
public class QuestionSetSubMenu extends BaseObservable {

    private int subSubjectId;
    private String subSubjectName;
    private String subjectId;
    private int icon;


    @Bindable
    public String getSubSubjectName() {
        return subSubjectName;
    }

    public QuestionSetSubMenu setSubSubjectName(String subSubjectName) {
        this.subSubjectName = subSubjectName;
        notifyPropertyChanged(BR.subSubjectName);
        return this;
    }

    @Bindable
    public int getSubSubjectId() {
        return subSubjectId;
    }

    public QuestionSetSubMenu setSubSubjectId(int subSubjectId) {
        this.subSubjectId = subSubjectId;
        notifyPropertyChanged(BR.subSubjectId);
        return this;
    }

    @Bindable
    public String getSubjectId() {
        return subjectId;
    }

    public QuestionSetSubMenu setSubjectId(String subjectId) {
        this.subjectId = subjectId;
        notifyPropertyChanged(BR.subjectId);
        return this;
    }

    @Bindable
    public int getIcon() {
        return icon;
    }

    public QuestionSetSubMenu setIcon(int icon) {
        this.icon = icon;
        notifyPropertyChanged(BR.icon);
        return this;
    }
}
