package com.mywings.questionset;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableArrayList;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.mywings.questionset.Adapter.QuestionSetSubMenuAdapter;
import com.mywings.questionset.Model.QuestionSetSubMenu;
import com.mywings.questionset.Process.GetQuestionSubMenuList;
import com.mywings.questionset.Process.OnQuestionSubMenuListener;
import com.mywings.questionset.databinding.QuestionSetReadAllSectionWiseBinding;

public class QuestionSetReadAllSectionWise extends QuestionSetCompact implements OnQuestionSubMenuListener {

    private QuestionSetReadAllSectionWiseBinding questionSetReadAllSectionWiseBinding;
    private RecyclerView lstQuestionSetSubMenu;
    private static GetQuestionSubMenuList objQuestionSetSubmenus = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        questionSetReadAllSectionWiseBinding = setContentLayout(R.layout.question_set_read_all_section_wise);
        Toolbar toolbar = questionSetReadAllSectionWiseBinding.toolbar;
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        init();
        initQuestionsSubmenu();
    }

    /**
     *
     */
    private void initQuestionsSubmenu() {
        questionSetReadAllSectionWiseBinding.contentloading.setVisibility(View.VISIBLE);
        objQuestionSetSubmenus = initHelperLimit.getQuestionSubMenuList(QuestionSetMenu.db);
        if (null != objQuestionSetSubmenus) {
            objQuestionSetSubmenus.setOnQuestionSubMenuListener(this);
            objQuestionSetSubmenus.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        }
    }

    /**
     *
     */
    private void init() {
        lstQuestionSetSubMenu = questionSetReadAllSectionWiseBinding.lstQuestionSetSubMenu;
        lstQuestionSetSubMenu.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
    }


    @Override
    public void onQuestionSubMenuProcessComplete(final ObservableArrayList<QuestionSetSubMenu> questionSetSubMenus, Exception exception) {
        questionSetReadAllSectionWiseBinding.contentloading.setVisibility(View.GONE);
        if (null != questionSetSubMenus && exception == null) {
            final QuestionSetSubMenuAdapter questionSetSubMenuAdapter = new QuestionSetSubMenuAdapter(questionSetSubMenus);
            questionSetSubMenuAdapter.setOnItemClickListener(new QuestionSetSubMenuAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(int id) {
                    startQuestions(String.valueOf(questionSetSubMenus.get(id).getSubSubjectId()));
                }
            });
            lstQuestionSetSubMenu.setAdapter(questionSetSubMenuAdapter);
        } else {
            show("Exception : \n " + exception.getMessage(),null);
        }
    }

    /**
     *
     * @param id
     */
    public void startQuestions(String id) {
        Intent starter = new Intent(QuestionSetReadAllSectionWise.this, Questions.class);
        starter.putExtra("id", id);
        startActivity(starter);
    }
}
