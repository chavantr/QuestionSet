package com.mywings.questionset;

import android.databinding.ObservableArrayList;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.mywings.questionset.Adapter.UserQuestionsAdapter;
import com.mywings.questionset.Model.Question;
import com.mywings.questionset.Process.GetFavoritesQuestion;
import com.mywings.questionset.Process.OnFavoriteQuestionsListener;
import com.mywings.questionset.databinding.QuestionSetReadAllSectionWiseBinding;

public class FavoriteQuestions extends QuestionSetCompact implements OnFavoriteQuestionsListener {


    private QuestionSetReadAllSectionWiseBinding questionSetReadAllSectionWiseBinding;
    private RecyclerView lstQuestionSetSubMenu;
    private GetFavoritesQuestion favoritesQuestion;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        questionSetReadAllSectionWiseBinding = setContentLayout(R.layout.question_set_read_all_section_wise);
        Toolbar toolbar = questionSetReadAllSectionWiseBinding.toolbar;
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        init();
        initFavoriteQuestions();
    }

    /**
     *
     */
    private void initFavoriteQuestions() {
        questionSetReadAllSectionWiseBinding.contentloading.setVisibility(View.VISIBLE);
        favoritesQuestion = new GetFavoritesQuestion(QuestionSetMenu.db);
        favoritesQuestion.setOnFavoriteQuestionsListener(this);
        favoritesQuestion.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, "1");
    }


    /**
     *
     */
    private void init() {
        lstQuestionSetSubMenu = questionSetReadAllSectionWiseBinding.lstQuestionSetSubMenu;
        lstQuestionSetSubMenu.setLayoutManager(getLayoutManager(LinearLayoutManager.VERTICAL));
    }


    @Override
    public void onFavoriteQuestionsComplete(ObservableArrayList<Question> result, Exception exception) {
        questionSetReadAllSectionWiseBinding.contentloading.setVisibility(View.GONE);
        if (null != result && exception == null) {
            final UserQuestionsAdapter userQuestionsAdapter = new UserQuestionsAdapter(result, QuestionSetMenu.db, false);
            lstQuestionSetSubMenu.setAdapter(userQuestionsAdapter);
        } else {
            show("Exception : \n " + exception.getMessage(), null);
        }
    }
}
