package com.mywings.questionset.Process;

import android.databinding.ObservableArrayList;

import com.mywings.questionset.Model.Question;

/**
 * Created by Tatyabhau Chavan on 12/30/2015.
 */
public interface OnQuestionsListener {
    public void onQuestionComplete(ObservableArrayList<Question> result, Exception exception);
}
