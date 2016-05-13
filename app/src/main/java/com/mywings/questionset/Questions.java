package com.mywings.questionset;

import android.databinding.DataBindingUtil;
import android.databinding.ObservableArrayList;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.mywings.questionset.Adapter.QuestionAdapter;
import com.mywings.questionset.Model.Question;
import com.mywings.questionset.Process.OnQuestionsListener;
import com.mywings.questionset.Process.QuestionsList;
import com.mywings.questionset.Utils.InitHelperLimit;
import com.mywings.questionset.databinding.QuestionsBinding;


public class Questions extends QuestionSetCompact implements OnQuestionsListener {

    private QuestionsBinding questionsBinding;
    private RecyclerView lstQuestion;
    private QuestionsList questionsList;
    private QuestionAdapter questionAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        questionsBinding =setContentLayout(R.layout.questions);
        Toolbar toolbar = questionsBinding.toolbar;
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        init();
        questionsBinding.contentloading.setVisibility(View.VISIBLE);
        questionsList = InitHelperLimit.getInstance().questionsList(QuestionSetMenu.db);
        questionsList.setOnQuestionsListener(this);
        questionsList.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, getIntent().getExtras().getString("id"));
    }

    /**
     *
     */
    private void init() {
        lstQuestion = questionsBinding.lstQuestion;
        lstQuestion.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
    }

    @Override
    public void onQuestionComplete(ObservableArrayList<Question> result, Exception exception) {
        questionsBinding.contentloading.setVisibility(View.GONE);
        if (null != result && exception == null) {
            questionAdapter = new QuestionAdapter(result);
            lstQuestion.setAdapter(questionAdapter);
        } else {
        }
    }
}
