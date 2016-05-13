package com.mywings.questionset;

import android.content.Intent;
import android.databinding.ObservableList;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.mywings.questionset.Adapter.QuestionPaperAdapter;
import com.mywings.questionset.Model.QuestionPaperMaster;
import com.mywings.questionset.Process.GetQuestionPapers;
import com.mywings.questionset.Process.OnQuestionPapersListener;
import com.mywings.questionset.databinding.ContentMyQuestionPapersBinding;
import com.mywings.questionset.databinding.MyQuestionPapersBinding;

public class MyQuestionPapers extends QuestionSetCompact implements OnQuestionPapersListener {

    private RecyclerView lstMyQuestionPapers;
    private ContentMyQuestionPapersBinding contentMyQuestionPapersBinding;
    private MyQuestionPapersBinding myQuestionPapersBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myQuestionPapersBinding = setContentLayout(R.layout.my_question_papers);
        Toolbar toolbar = myQuestionPapersBinding.toolbar;
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        init();
        initQuestionPapers();
    }

    /**
     *
     */
    private void init() {
        contentMyQuestionPapersBinding = attach(R.layout.content_my_question_papers);
        lstMyQuestionPapers = contentMyQuestionPapersBinding.lstMyQuestionPapers;
        lstMyQuestionPapers.setLayoutManager(getLayoutManager(LinearLayoutManager.VERTICAL));
    }


    @Override
    public void onQuestionPapersComplete(ObservableList<QuestionPaperMaster> result, Exception exception) {
        contentMyQuestionPapersBinding.contentloading.setVisibility(View.GONE);
        setQuestion(result);
    }

    /**
     *
     */
    private void initQuestionPapers() {
        GetQuestionPapers questionPapers = initHelperLimit.getQuestionPapers(QuestionSetMenu.db);
        if (null != questionPapers) {
            contentMyQuestionPapersBinding.contentloading.setVisibility(View.VISIBLE);
            questionPapers.setOnQuestionPapersListener(this);
            questionPapers.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        }
    }

    /**
     * @param result
     */
    private void setQuestion(final ObservableList<QuestionPaperMaster> result) {
        if (null == result) return;
        final QuestionPaperAdapter questionPaperAdapter = new QuestionPaperAdapter(result);
        questionPaperAdapter.setOnItemClickListener(new QuestionPaperAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int id) {
                int ids = result.get(id).getQuestionPaperID();
                startQuestiondetail(String.valueOf(ids));
            }
        });
        lstMyQuestionPapers.setAdapter(questionPaperAdapter);
    }

    private void startQuestiondetail(String id) {
        Intent intent = new Intent(MyQuestionPapers.this, UserQuestionPaperSolved.class);
        intent.putExtra("id", id);
        startActivity(intent);
    }
}
