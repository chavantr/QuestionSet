package com.mywings.questionset.Model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.mywings.questionset.BR;

/**
 * Created by Tatyabhau Chavan on 1/21/2016.
 */
public class QuestionPaperDetails extends BaseObservable {

    private int id;
    private int questionPaperID;
    private int questionID;
    private int userAnswer;

    @Bindable
    public int getUserAnswer() {
        return userAnswer;
    }

    public QuestionPaperDetails setUserAnswer(int userAnswer) {
        this.userAnswer = userAnswer;
        notifyPropertyChanged(BR.userAnswer);
        return this;
    }

    @Bindable
    public int getQuestionID() {
        return questionID;
    }

    public QuestionPaperDetails setQuestionID(int questionID) {
        this.questionID = questionID;
        notifyPropertyChanged(BR.questionID);
        return this;
    }

    @Bindable
    public int getQuestionPaperID() {
        return questionPaperID;
    }

    public QuestionPaperDetails setQuestionPaperID(int questionPaperID) {
        this.questionPaperID = questionPaperID;
        notifyPropertyChanged(BR.questionPaperID);
        return this;
    }

    @Bindable
    public int getId() {
        return id;
    }

    public QuestionPaperDetails setId(int id) {
        this.id = id;
        notifyPropertyChanged(BR.id);
        return this;
    }

}
