package com.mywings.questionset.Process;


import android.databinding.ObservableList;
import android.os.AsyncTask;

import com.mywings.questionset.Locally.MyDatabase;
import com.mywings.questionset.Model.Question;
import com.mywings.questionset.Model.QuestionPaperMaster;

/**
 * Created by Tatyabhau Chavan on 1/21/2016.
 */
public class CreateQuestionPaper extends AsyncTask<ObservableList<Question>, Void, Integer> {

    private Exception exception;
    private MyDatabase db;
    private ObservableList<Question> practiceQuestionPaper;
    private QuestionPaperMaster questionPaperMaster;
    private OnCreateQuestionPaperListener onCreateQuestionPaperListener;

    public CreateQuestionPaper(MyDatabase db, QuestionPaperMaster questionPaperMaster) {
        this.db = db;
        this.questionPaperMaster = questionPaperMaster;
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
    protected Integer doInBackground(ObservableList<Question>... params) {
        int inserted = -1;
        practiceQuestionPaper = params[0];
        inserted = (int) db.masterCreateQuestionPaper(questionPaperMaster);
        return inserted;
    }

    @Override
    protected void onPostExecute(Integer result) {
        super.onPostExecute(result);
        onCreateQuestionPaperListener.onQuestionPaperCreated(practiceQuestionPaper, result, exception);
    }

    public void setOnCreateQuestionPaperListener(OnCreateQuestionPaperListener onCreateQuestionPaperListener) {
        this.onCreateQuestionPaperListener = onCreateQuestionPaperListener;
    }

}
