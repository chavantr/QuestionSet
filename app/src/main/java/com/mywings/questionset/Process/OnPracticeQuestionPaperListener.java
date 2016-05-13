package com.mywings.questionset.Process;

import android.databinding.ObservableList;

import com.mywings.questionset.Model.Question;

/**
 * Created by Tatyabhau Chavan on 1/20/2016.
 */
public interface OnPracticeQuestionPaperListener {
    public void onPracticeQuestionPaperComplete(ObservableList<Question> result, Exception exception);
}
