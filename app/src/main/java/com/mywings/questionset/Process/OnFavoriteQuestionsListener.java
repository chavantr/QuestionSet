package com.mywings.questionset.Process;

import android.databinding.ObservableArrayList;

import com.mywings.questionset.Model.Question;

/**
 * Created by Tatyabhau Chavan on 1/16/2016.
 */
public interface OnFavoriteQuestionsListener {
    public void onFavoriteQuestionsComplete(ObservableArrayList<Question> result, Exception exception);
}
