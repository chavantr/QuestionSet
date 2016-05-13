package com.mywings.questionset.Process;

import android.databinding.ObservableList;
import android.os.AsyncTask;

import com.mywings.questionset.Locally.MyDatabase;
import com.mywings.questionset.Model.QuestionPaperMaster;

/**
 * Created by Tatyabhau Chavan on 2/8/2016.
 */
public class GetQuestionPapers extends AsyncTask<Void, Void, ObservableList<QuestionPaperMaster>> {

    public OnQuestionPapersListener onQuestionPapersListener;
    private MyDatabase db;
    private Exception exception = null;

    public GetQuestionPapers(MyDatabase db) {
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
    protected ObservableList<QuestionPaperMaster> doInBackground(Void... params) {
        return db.getQuestionPapers();
    }

    @Override
    protected void onPostExecute(ObservableList<QuestionPaperMaster> questionPaperMasters) {
        super.onPostExecute(questionPaperMasters);
        onQuestionPapersListener.onQuestionPapersComplete(questionPaperMasters, exception);
    }

    public void setOnQuestionPapersListener(OnQuestionPapersListener onQuestionPapersListener) {
        this.onQuestionPapersListener = onQuestionPapersListener;
    }

}
