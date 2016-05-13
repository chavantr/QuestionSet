package com.mywings.questionset.Process;

import android.databinding.ObservableList;

import com.mywings.questionset.Model.PracticeQuestionPaperUser;

/**
 * Created by Tatyabhau Chavan on 3/8/2016.
 */
public interface OnSolvedQuestionPaperListener {
    public void onSolvedQuestionPaperComplete(ObservableList<PracticeQuestionPaperUser> result);
}
