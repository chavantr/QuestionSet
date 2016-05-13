package com.mywings.questionset.Process;

import android.databinding.ObservableList;
import android.os.AsyncTask;

import com.mywings.questionset.Locally.MyDatabase;
import com.mywings.questionset.Model.Question;

/**
 * Created by Tatyabhau Chavan on 1/22/2016.
 */
public class CreateQuestionPaperDetails extends AsyncTask<ObservableList<Question>, Void, ObservableList<Question>> {

    private MyDatabase db;
    private int questionPaperId;
    private Exception exception = null;
    public OnQuestionPaperDetailListener onQuestionPaperDetailListener;

    public CreateQuestionPaperDetails(MyDatabase db, int questionPaperId) {
        this.db = db;
        this.questionPaperId = questionPaperId;
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
    protected ObservableList<Question> doInBackground(ObservableList<Question>... params) {
        int inserted;
        inserted = (int) db.createQuestionPaperDetails(params[0], questionPaperId);
        if (inserted > 0) {
            return params[0];
        } else {
            return null;
        }
    }

    @Override
    protected void onPostExecute(ObservableList<Question> questions) {
        super.onPostExecute(questions);
        onQuestionPaperDetailListener.onQuestionPaperDetailsComplete(questions,exception);
    }

    public void setOnQuestionPaperDetailListener(OnQuestionPaperDetailListener onQuestionPaperDetailListener) {
        this.onQuestionPaperDetailListener = onQuestionPaperDetailListener;
    }
}
