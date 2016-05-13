package com.mywings.questionset.Process;

import android.database.Cursor;
import android.databinding.ObservableArrayList;
import android.os.AsyncTask;

import com.mywings.questionset.Locally.MyDatabase;
import com.mywings.questionset.Model.Question;
import com.mywings.questionset.Utils.QuestionSetConstatnts;

/**
 * Created by Tatyabhau Chavan on 12/30/2015.
 */
public class QuestionsList extends AsyncTask<String, Void, ObservableArrayList<Question>> {

    public OnQuestionsListener onQuestionsListener;
    private Exception exception = null;
    private MyDatabase db;


    public QuestionsList(MyDatabase db) {
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

        ObservableArrayList<Question> questions = new ObservableArrayList<Question>();

        Cursor mCursor = null;

        try {
            mCursor = db.getQuestionSetCategory(params[0]);
        } catch (Exception e) {
            exception=e;
        }

        mCursor.moveToFirst();

        int i = 1;

        while (!mCursor.isAfterLast()) {
            Question mQuestionSet = new Question();

            mQuestionSet.setQuestion(" " + i + ") " + mCursor.getString(mCursor
                    .getColumnIndex(QuestionSetConstatnts.Questions.QUESTION)).trim());
            mQuestionSet.setOptionA(mCursor.getString(mCursor
                    .getColumnIndex(QuestionSetConstatnts.Questions.OPTION_A)).trim());
            mQuestionSet.setOptionB(mCursor.getString(mCursor
                    .getColumnIndex(QuestionSetConstatnts.Questions.OPTION_B)).trim());
            mQuestionSet.setOptionC(mCursor.getString(mCursor
                    .getColumnIndex(QuestionSetConstatnts.Questions.OPTION_C)).trim());
            mQuestionSet.setOptionD(mCursor.getString(mCursor
                    .getColumnIndex(QuestionSetConstatnts.Questions.OPTION_D)).trim());
            mQuestionSet
                    .setQuestionId(Integer.parseInt(mCursor.getString(mCursor
                            .getColumnIndex(QuestionSetConstatnts.Questions.QUESTION_ID))));
            mQuestionSet.setAnswer(mCursor.getString(mCursor
                    .getColumnIndex(QuestionSetConstatnts.Questions.ANSWER)).trim());
            mQuestionSet
                    .setIsFavourite(mCursor.getString(mCursor
                            .getColumnIndex(QuestionSetConstatnts.Questions.ISFAVOURITE)));

            mQuestionSet.setIsLike(mCursor.getString(mCursor
                    .getColumnIndex(QuestionSetConstatnts.Questions.ISLIKE)));

            mCursor.moveToNext();

            i += 1;

            questions.add(mQuestionSet);

        }

        return questions;
    }

    @Override
    protected void onPostExecute(ObservableArrayList<Question> questions) {
        super.onPostExecute(questions);
        onQuestionsListener.onQuestionComplete(questions, exception);
    }

    public void setOnQuestionsListener(OnQuestionsListener onQuestionsListener) {
        this.onQuestionsListener = onQuestionsListener;
    }

}
