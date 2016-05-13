package com.mywings.questionset.Model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.mywings.questionset.BR;

/**
 * Created by Tatyabhau Chavan on 12/30/2015.
 */
public class Question extends BaseObservable {

    private static Question _instance;
    private int questionId;
    private String question;
    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;
    private String answer;
    private String difficultyLevelId;
    private String subSubjectId;
    private String iPublish;
    private String keywords;
    private String description;
    private String isLike;
    private String isFavourite;
    private boolean submit;
    private String userAnswer;
    private String correctAnswer;

    private static synchronized Question getInstance() {

        if (null == _instance) {
            _instance = new Question();
        }

        return _instance;
    }

    @Bindable
    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public Question setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
        notifyPropertyChanged(BR.correctAnswer);
        return this;
    }

    @Bindable
    public String getUserAnswer() {
        return userAnswer;
    }

    public Question setUserAnswer(String userAnswer) {
        this.userAnswer = userAnswer;
        notifyPropertyChanged(BR.userAnswer);
        return this;
    }

    @Bindable
    public boolean isSubmit() {
        return submit;
    }

    public Question setSubmit(boolean submit) {
        this.submit = submit;
        notifyPropertyChanged(BR.submit);
        return this;
    }


    @Bindable
    public int getQuestionId() {
        return questionId;
    }

    public Question setQuestionId(int questionId) {
        this.questionId = questionId;
        notifyPropertyChanged(BR.questionId);
        return this;
    }

    @Bindable
    public String getQuestion() {
        return question;
    }

    public Question setQuestion(String question) {
        this.question = question;
        notifyPropertyChanged(BR.question);
        return this;
    }

    @Bindable
    public String getOptionA() {
        return optionA;
    }

    public Question setOptionA(String optionA) {
        this.optionA = optionA;
        notifyPropertyChanged(BR.optionA);
        return this;
    }

    @Bindable
    public String getOptionB() {
        return optionB;
    }

    public Question setOptionB(String optionB) {
        this.optionB = optionB;
        notifyPropertyChanged(BR.optionB);
        return this;
    }

    @Bindable
    public String getOptionC() {
        return optionC;
    }

    public Question setOptionC(String optionC) {
        this.optionC = optionC;
        notifyPropertyChanged(BR.optionC);
        return this;
    }

    @Bindable
    public String getOptionD() {
        return optionD;
    }

    public Question setOptionD(String optionD) {
        this.optionD = optionD;
        notifyPropertyChanged(BR.optionD);
        return this;
    }

    @Bindable
    public String getAnswer() {
        return answer;
    }

    public Question setAnswer(String answer) {
        this.answer = answer;
        notifyPropertyChanged(BR.answer);
        return this;
    }

    @Bindable
    public String getDifficultyLevelId() {
        return difficultyLevelId;
    }

    public Question setDifficultyLevelId(String difficultyLevelId) {
        this.difficultyLevelId = difficultyLevelId;
        notifyPropertyChanged(BR.difficultyLevelId);
        return this;
    }

    @Bindable
    public String getSubSubjectId() {
        return subSubjectId;
    }

    public Question setSubSubjectId(String subSubjectId) {
        this.subSubjectId = subSubjectId;
        notifyPropertyChanged(BR.subSubjectId);
        return this;
    }

    @Bindable
    public String getiPublish() {
        return iPublish;
    }

    public Question setiPublish(String iPublish) {
        this.iPublish = iPublish;
        notifyPropertyChanged(BR.iPublish);
        return this;
    }

    @Bindable
    public String getKeywords() {
        return keywords;
    }

    public Question setKeywords(String keywords) {
        this.keywords = keywords;
        notifyPropertyChanged(BR.keywords);
        return this;
    }

    @Bindable
    public String getDescription() {
        return description;
    }

    public Question setDescription(String description) {
        this.description = description;
        notifyPropertyChanged(BR.description);
        return this;
    }

    @Bindable
    public String getIsLike() {
        return isLike;
    }

    public Question setIsLike(String isLike) {
        this.isLike = isLike;
        notifyPropertyChanged(BR.isLike);
        return this;
    }

    @Bindable
    public String getIsFavourite() {
        return isFavourite;
    }

    public Question setIsFavourite(String isFavourite) {
        this.isFavourite = isFavourite;
        notifyPropertyChanged(BR.isFavourite);
        return this;
    }

}
