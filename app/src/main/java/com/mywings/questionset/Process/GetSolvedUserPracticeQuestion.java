package com.mywings.questionset.Process;

import android.databinding.ObservableList;
import android.os.AsyncTask;

import com.mywings.questionset.Locally.MyDatabase;
import com.mywings.questionset.Model.PracticeQuestionPaperUser;

/**
 * Created by Tatyabhau Chavan on 3/8/2016.
 */
public class GetSolvedUserPracticeQuestion extends AsyncTask<String, Integer, ObservableList<PracticeQuestionPaperUser>> {

    private static GetSolvedUserPracticeQuestion _instance;
    private OnSolvedQuestionPaperListener onSolvedQuestionPaperListener;
    private MyDatabase database;

    public GetSolvedUserPracticeQuestion(MyDatabase database) {
        this.database = database;
    }

    public static synchronized GetSolvedUserPracticeQuestion getInstance(MyDatabase database) {
        if (null == _instance) {
            _instance = new GetSolvedUserPracticeQuestion(database);
        }
        return _instance;
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
    protected ObservableList<PracticeQuestionPaperUser> doInBackground(String... params) {
        return database.getQuestionPaperQuestionIdWise(params[0]);
    }

    /**
     * <p>Runs on the UI thread after {@link #doInBackground}. The
     * specified result is the value returned by {@link #doInBackground}.</p>
     * <p/>
     * <p>This method won't be invoked if the task was cancelled.</p>
     *
     * @param practiceQuestionPaperUsers The result of the operation computed by {@link #doInBackground}.
     * @see #onPreExecute
     * @see #doInBackground
     * @see #onCancelled(Object)
     */
    @Override
    protected void onPostExecute(ObservableList<PracticeQuestionPaperUser> practiceQuestionPaperUsers) {
        super.onPostExecute(practiceQuestionPaperUsers);
        onSolvedQuestionPaperListener.onSolvedQuestionPaperComplete(practiceQuestionPaperUsers);
    }

    /**
     * Runs on the UI thread after {@link #publishProgress} is invoked.
     * The specified values are the values passed to {@link #publishProgress}.
     *
     * @param values The values indicating progress.
     * @see #publishProgress
     * @see #doInBackground
     */
    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
    }

    /**
     * @param onSolvedQuestionPaperListener
     */
    public void setOnSolvedQuestionPaperListener(OnSolvedQuestionPaperListener onSolvedQuestionPaperListener, String id) {
        this.onSolvedQuestionPaperListener = onSolvedQuestionPaperListener;
        this.doLoadInbackground(id);
    }

    /**
     * @param id
     */
    private void doLoadInbackground(String id) {
        super.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, id);
    }

}
