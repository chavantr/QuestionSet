package com.mywings.questionset.Process;

import android.database.Cursor;
import android.databinding.ObservableArrayList;
import android.os.AsyncTask;

import com.mywings.questionset.Locally.MyDatabase;
import com.mywings.questionset.Model.QuestionSetSubMenu;
import com.mywings.questionset.R;
import com.mywings.questionset.Utils.QuestionSetConstatnts;

/**
 * Created by Tatyabhau Chavan on 12/29/2015.
 */
public class GetQuestionSubMenuList extends AsyncTask<String, Void, ObservableArrayList<QuestionSetSubMenu>> {


    public OnQuestionSubMenuListener onQuestionSubMenuListener;
    private Exception exception=null;
    private MyDatabase db;

    public  GetQuestionSubMenuList(MyDatabase db){
        this.db=db;
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
    protected ObservableArrayList<QuestionSetSubMenu> doInBackground(String... params) {

        ObservableArrayList<QuestionSetSubMenu> questionSetSubMenus =new ObservableArrayList<QuestionSetSubMenu>();


        Cursor mCursor = db.getQuestionSetSubMenu();

        mCursor.moveToFirst();

        while (!mCursor.isAfterLast()) {
            QuestionSetSubMenu mQuestionSetSubMenu = new QuestionSetSubMenu();

            mQuestionSetSubMenu
                    .setSubjectId(mCursor.getString(mCursor
                            .getColumnIndex(QuestionSetConstatnts.QuestionSetSubMenu.SUBJECTID)));
            mQuestionSetSubMenu
                    .setSubSubjectId(Integer.parseInt(mCursor.getString(mCursor
                            .getColumnIndex(QuestionSetConstatnts.QuestionSetSubMenu.SUBSUBJECT_ID))));
            mQuestionSetSubMenu
                    .setSubSubjectName(mCursor.getString(mCursor
                            .getColumnIndex(QuestionSetConstatnts.QuestionSetSubMenu.SUBSUBJECT_NAME)).trim());

            mQuestionSetSubMenu.setIcon(R.drawable.question_paper);

            mCursor.moveToNext();

            questionSetSubMenus.add(mQuestionSetSubMenu);
        }




        return questionSetSubMenus;
    }

    @Override
    protected void onPostExecute(ObservableArrayList<QuestionSetSubMenu> questionSetSubMenus) {

        super.onPostExecute(questionSetSubMenus);

        onQuestionSubMenuListener.onQuestionSubMenuProcessComplete(questionSetSubMenus,exception);

    }

    public void setOnQuestionSubMenuListener(OnQuestionSubMenuListener onQuestionSubMenuListener) {
        this.onQuestionSubMenuListener = onQuestionSubMenuListener;
    }

}
