package com.mywings.questionset.Utils;

import android.databinding.ObservableList;

import com.mywings.questionset.Locally.MyDatabase;
import com.mywings.questionset.Model.Question;
import com.mywings.questionset.Model.QuestionPaperMaster;
import com.mywings.questionset.Process.CreateQuestionPaper;
import com.mywings.questionset.Process.CreateQuestionPaperDetails;
import com.mywings.questionset.Process.GetPracticeQuestionPaper;
import com.mywings.questionset.Process.GetQuestionPapers;
import com.mywings.questionset.Process.GetQuestionSubMenuList;
import com.mywings.questionset.Process.GetSolvedUserPracticeQuestion;
import com.mywings.questionset.Process.QuestionsList;

/**
 * Created by Tatyabhau Chavan on 12/30/2015.
 */
public class InitHelperLimit {

    private static InitHelperLimit _instance;

    public static synchronized InitHelperLimit getInstance() {
        if (null == _instance) {
            _instance = new InitHelperLimit();
        }
        return _instance;
    }

    /**
     * @param database
     * @return Questions
     */
    public QuestionsList questionsList(MyDatabase database) {
        QuestionsList questionsList = new QuestionsList(database);
        return questionsList;
    }

    /**
     * @param database
     * @return PracticeQuestionPaper
     */
    public GetPracticeQuestionPaper practiceQuestionPaper(MyDatabase database) {
        GetPracticeQuestionPaper practiceQuestionPaper = new GetPracticeQuestionPaper(database);
        return practiceQuestionPaper;
    }

    /**
     * @param database
     * @return QuestionSubMenu
     */
    public GetQuestionSubMenuList getQuestionSubMenuList(MyDatabase database) {
        GetQuestionSubMenuList objQuestionSetSubmenu = new GetQuestionSubMenuList(database);
        return objQuestionSetSubmenu;
    }

    /**
     * @param database
     * @param practiceQuestionPaper
     * @param questionPaperMaster
     * @return CreateQuestionPaper
     */
    public CreateQuestionPaper createQuestionPaper(MyDatabase database, ObservableList<Question> practiceQuestionPaper, QuestionPaperMaster questionPaperMaster) {
        CreateQuestionPaper createQuestionPaper = new CreateQuestionPaper(database, questionPaperMaster);
        return createQuestionPaper;
    }

    /**
     * @param db
     * @param questionPaperId
     * @return createQuestionPaperDetails
     */
    public CreateQuestionPaperDetails createQuestionPaperDetails(MyDatabase db, int questionPaperId) {
        CreateQuestionPaperDetails createQuestionPaperDetails = new CreateQuestionPaperDetails(db, questionPaperId);
        return createQuestionPaperDetails;
    }

    /**
     * @param db
     * @return questionPaper
     */
    public GetQuestionPapers getQuestionPapers(MyDatabase db) {
        GetQuestionPapers questionPapers = new GetQuestionPapers(db);
        return questionPapers;
    }

    /**
     * @param database
     * @return
     */
    public GetSolvedUserPracticeQuestion getSolvedUserPracticeQuestion(MyDatabase database) {
        //return GetSolvedUserPracticeQuestion.getInstance(database);
        return new GetSolvedUserPracticeQuestion(database);
    }
}
