package com.mywings.questionset.Process;

import android.databinding.ObservableArrayList;

import com.mywings.questionset.Model.QuestionSetSubMenu;

/**
 * Created by Tatyabhau Chavan on 12/29/2015.
 */
public interface OnQuestionSubMenuListener {
    public void onQuestionSubMenuProcessComplete(ObservableArrayList<QuestionSetSubMenu> questionSetSubMenus, Exception exception);
}
