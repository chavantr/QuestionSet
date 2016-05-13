package com.mywings.questionset.Process;

import android.databinding.ObservableArrayList;

import com.mywings.questionset.Model.Question;

/**
 * Created by Tatyabhau Chavan on 1/18/2016.
 */
public interface OnLikeQuestionsListener {
    public void onLikeQuestionComplete(ObservableArrayList<Question> result, Exception exception);
}
