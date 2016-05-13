package com.mywings.questionset.Model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.mywings.questionset.BR;

/**
 * Created by Tatyabhau Chavan on 2/27/2016.
 */
public class PracticeQuestionPaperUser extends BaseObservable {

    private static PracticeQuestionPaperUser _instance;
    private QuestionPaperDetails questionPaperDetails;
    private Question question;
    private int totalMarks;

    public static synchronized PracticeQuestionPaperUser getInstance() {
        if (null == _instance) {
            _instance = new PracticeQuestionPaperUser();
        }
        return _instance;
    }

    @Bindable
    public QuestionPaperDetails getQuestionPaperDetails() {
        return questionPaperDetails;
    }

    /**
     * @param questionPaperDetails
     * @return
     */
    public PracticeQuestionPaperUser setQuestionPaperDetails(QuestionPaperDetails questionPaperDetails) {
        this.questionPaperDetails = questionPaperDetails;
        notifyPropertyChanged(BR.questionPaperDetails);
        return this;
    }

    @Bindable
    public Question getQuestion() {
        return question;
    }

    /**
     * @param question
     * @return
     */
    public PracticeQuestionPaperUser setQuestion(Question question) {
        this.question = question;
        notifyPropertyChanged(BR.question);
        return this;
    }

    @Bindable
    public int getTotalMarks() {
        return totalMarks;
    }

    /**
     * @param totalMarks
     * @return
     */

    public PracticeQuestionPaperUser setTotalMarks(int totalMarks) {
        this.totalMarks = totalMarks;
        notifyPropertyChanged(BR.totalMarks);
        return this;
    }

}
