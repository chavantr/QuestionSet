package com.mywings.questionset.Process;

import android.database.Cursor;
import android.databinding.ObservableArrayList;
import android.os.AsyncTask;

import com.mywings.questionset.Locally.MyDatabase;
import com.mywings.questionset.Model.Question;
import com.mywings.questionset.Utils.QuestionSetConstatnts;

/**
 * Created by Tatyabhau Chavan on 1/20/2016.
 */
public class GetPracticeQuestionPaper extends AsyncTask<String, Void, ObservableArrayList<Question>> {


    public OnPracticeQuestionPaperListener onPracticeQuestionPaperListener;
    private Exception exception = null;
    private MyDatabase db;

    public GetPracticeQuestionPaper(MyDatabase db) {
        this.db = db;
    }

    /**
     * Override this method to perform a computation on a background thread. The
     * specified parameters are the parameters passed to {@link #execute}
     * by the caller of this task.
     * <p/>
     * This method can call {@link #publishProgress} to publish updates
     * on the UI thread.
     *
     * @param params The parameters of the task.
     * @return A result, defined by the subclass of this task.
     * @see #onPreExecute()
     * @see #onPostExecute
     * @see #publishProgress
     */
    @Override
    protected ObservableArrayList<Question> doInBackground(String... params) {
        ObservableArrayList<Question> questionPaper = new ObservableArrayList<>();
        Cursor mCursor = db.getQuestionPaper(params[0]);
        mCursor.moveToFirst();
        while (!mCursor.isAfterLast()) {

            Question mQuestionSet = new Question();

            mQuestionSet.setQuestion(mCursor.getString(mCursor
                    .getColumnIndex(QuestionSetConstatnts.Questions.QUESTION)));
            mQuestionSet.setOptionA(mCursor.getString(mCursor
                    .getColumnIndex(QuestionSetConstatnts.Questions.OPTION_A)));
            mQuestionSet.setOptionB(mCursor.getString(mCursor
                    .getColumnIndex(QuestionSetConstatnts.Questions.OPTION_B)));
            mQuestionSet.setOptionC(mCursor.getString(mCursor
                    .getColumnIndex(QuestionSetConstatnts.Questions.OPTION_C)));
            mQuestionSet.setOptionD(mCursor.getString(mCursor
                    .getColumnIndex(QuestionSetConstatnts.Questions.OPTION_D)));
            mQuestionSet
                    .setQuestionId(Integer.parseInt(mCursor.getString(mCursor
                            .getColumnIndex(QuestionSetConstatnts.Questions.QUESTION_ID))));
            mQuestionSet.setAnswer(mCursor.getString(mCursor
                    .getColumnIndex(QuestionSetConstatnts.Questions.ANSWER)));
            mQuestionSet
                    .setIsFavourite(mCursor.getString(mCursor
                            .getColumnIndex(QuestionSetConstatnts.Questions.ISFAVOURITE)));
            mQuestionSet.setIsLike(mCursor.getString(mCursor
                    .getColumnIndex(QuestionSetConstatnts.Questions.ISLIKE)));

            mQuestionSet.setSubmit(false);


            mQuestionSet.setUserAnswer("");

            mQuestionSet.setCorrectAnswer(getCorrectAnswer(mQuestionSet.getAnswer(), mQuestionSet));

            mCursor.moveToNext();

            questionPaper.add(mQuestionSet);
        }


        return questionPaper;
    }

    /**
     * @param answer
     * @param node
     * @return
     */
    private String getCorrectAnswer(String answer, Question node) {
        String correctAnswer = null;
        switch (answer) {
            case "1":
                correctAnswer = node.getOptionA();
                break;
            case "2":
                correctAnswer = node.getOptionB();
                break;
            case "3":
                correctAnswer = node.getOptionC();
                break;
            case "4":
                correctAnswer = node.getOptionD();
                break;
        }
        return correctAnswer;
    }


    @Override
    protected void onPostExecute(ObservableArrayList<Question> questions) {
        super.onPostExecute(questions);
        onPracticeQuestionPaperListener.onPracticeQuestionPaperComplete(questions, exception);
    }

    public void setOnPracticeQuestionPaperListener(OnPracticeQuestionPaperListener onPracticeQuestionPaperListener) {
        this.onPracticeQuestionPaperListener = onPracticeQuestionPaperListener;
    }
}
