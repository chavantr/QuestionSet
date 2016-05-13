package com.mywings.questionset.Model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.mywings.questionset.BR;

/**
 * Created by Tatyabhau Chavan on 1/20/2016.
 */
public class QuestionPaperMaster extends BaseObservable {

    private int questionPaperID;
    private String questionPaperDate;
    private String questionPaperName;
    private String userIdentity;
    private String language;
    private String numberOfQuestions;
    private String obtainedQuestions;


    @Bindable
    public int getQuestionPaperID() {
        return questionPaperID;
    }

    public QuestionPaperMaster setQuestionPaperID(int questionPaperID) {
        this.questionPaperID = questionPaperID;
        notifyPropertyChanged(BR.questionPaperID);
        return this;
    }

    @Bindable
    public String getQuestionPaperDate() {
        return questionPaperDate;
    }

    public QuestionPaperMaster setQuestionPaperDate(String questionPaperDate) {
        this.questionPaperDate = questionPaperDate;
        notifyPropertyChanged(BR.questionPaperDate);
        return this;
    }

    @Bindable
    public String getQuestionPaperName() {
        return questionPaperName;
    }

    public QuestionPaperMaster setQuestionPaperName(String questionPaperName) {
        this.questionPaperName = questionPaperName;
        notifyPropertyChanged(BR.questionPaperName);
        return this;
    }

    @Bindable
    public String getUserIdentity() {
        return userIdentity;
    }

    public QuestionPaperMaster setUserIdentity(String userIdentity) {
        this.userIdentity = userIdentity;
        notifyPropertyChanged(BR.userIdentity);
        return this;
    }

    @Bindable
    public String getLanguage() {
        return language;
    }

    public QuestionPaperMaster setLanguage(String language) {
        this.language = language;
        notifyPropertyChanged(BR.language);
        return this;
    }

    @Bindable
    public String getNumberOfQuestions() {
        return numberOfQuestions;
    }

    public QuestionPaperMaster setNumberOfQuestions(String numberOfQuestions) {
        this.numberOfQuestions = numberOfQuestions;
        notifyPropertyChanged(BR.numberOfQuestions);
        return this;
    }

    @Bindable
    public String getObtainedQuestions() {
        return obtainedQuestions;
    }

    public QuestionPaperMaster setObtainedQuestions(String obtainedQuestions) {
        this.obtainedQuestions = obtainedQuestions;
        notifyPropertyChanged(BR.obtainedQuestions);
        return this;
    }
}
