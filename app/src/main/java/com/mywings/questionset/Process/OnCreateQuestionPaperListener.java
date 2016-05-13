package com.mywings.questionset.Process;


import android.databinding.ObservableList;

import com.mywings.questionset.Model.Question;

/**
 * Created by Tatyabhau Chavan on 1/21/2016.
 */
public interface OnCreateQuestionPaperListener {
    public void onQuestionPaperCreated(ObservableList<Question> input, int id, Exception exception);
}
