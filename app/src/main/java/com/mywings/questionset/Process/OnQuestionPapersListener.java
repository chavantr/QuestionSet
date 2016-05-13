package com.mywings.questionset.Process;

import android.databinding.ObservableList;

import com.mywings.questionset.Model.QuestionPaperMaster;

/**
 * Created by Tatyabhau Chavan on 2/8/2016.
 */
public interface OnQuestionPapersListener {
    public void onQuestionPapersComplete(ObservableList<QuestionPaperMaster> result, Exception exception);
}
