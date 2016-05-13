package com.mywings.questionset.Process;

import android.databinding.ObservableList;

import com.mywings.questionset.Model.Question;

/**
 * Created by Tatyabhau Chavan on 1/22/2016.
 */
public interface OnQuestionPaperDetailListener {
    public void onQuestionPaperDetailsComplete(ObservableList<Question> questions, Exception exception);
}
