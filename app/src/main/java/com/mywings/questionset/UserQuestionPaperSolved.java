package com.mywings.questionset;

import android.databinding.ObservableList;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.mywings.questionset.Adapter.UserSolvedQuestionPaperAdapter;
import com.mywings.questionset.Model.PracticeQuestionPaperUser;
import com.mywings.questionset.Process.GetSolvedUserPracticeQuestion;
import com.mywings.questionset.Process.OnSolvedQuestionPaperListener;
import com.mywings.questionset.Views.ParticleSystem;
import com.mywings.questionset.databinding.ContentUserQuestionPaperSolvedBinding;
import com.mywings.questionset.databinding.UserQuestionPaperSolvedBinding;

public class UserQuestionPaperSolved extends QuestionSetCompact implements OnSolvedQuestionPaperListener {

    private UserQuestionPaperSolvedBinding userQuestionPaperSolvedBinding;
    private ContentUserQuestionPaperSolvedBinding contentUserQuestionPaperSolvedBinding;
    private RecyclerView lstSolvedQuestionPapers;
    private UserSolvedQuestionPaperAdapter userSolvedQuestionPaperAdapter;
    private GetSolvedUserPracticeQuestion solvedUserPracticeQuestion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userQuestionPaperSolvedBinding = setContentLayout(R.layout.user_question_paper_solved);
        Toolbar toolbar = userQuestionPaperSolvedBinding.toolbar;
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        init();


    }

    /**
     *
     */
    private void init() {
        contentUserQuestionPaperSolvedBinding = attach(R.layout.content_user_question_paper_solved);
        lstSolvedQuestionPapers = contentUserQuestionPaperSolvedBinding.lstMyQuestionPapers;
        lstSolvedQuestionPapers.setLayoutManager(getLayoutManager(LinearLayoutManager.VERTICAL));
        initUserSolvedQuestionPapers();
    }

    @Override
    public void onSolvedQuestionPaperComplete(ObservableList<PracticeQuestionPaperUser> result) {
        contentUserQuestionPaperSolvedBinding.contentloading.setVisibility(View.GONE);
        if (null != result) {
            String resultFormat = "Result : ";
            if (result.get(0).getTotalMarks() >= 7) {
                resultFormat = resultFormat + "PASS";
            } else {
                resultFormat = resultFormat + "FAIL";
            }
            contentUserQuestionPaperSolvedBinding.txtExamResult.setText(resultFormat);
            contentUserQuestionPaperSolvedBinding.txtExamTotal.setText("Marks : " + result.get(0).getTotalMarks() + "/20");
            userSolvedQuestionPaperAdapter = new UserSolvedQuestionPaperAdapter(result);
            lstSolvedQuestionPapers.setAdapter(userSolvedQuestionPaperAdapter);
            if (result.get(0).getTotalMarks() >= 7) {
                initCongrats(contentUserQuestionPaperSolvedBinding.congrats, R.drawable.star_pink);
            } else {
                contentUserQuestionPaperSolvedBinding.congrats.setVisibility(View.GONE);
            }
        }
    }

    /**
     * @param greet
     * @param id
     */
    private void initCongrats(View greet, int id) {
        new ParticleSystem(this, 500, id, 5000).setSpeedRange(0.1f, 0.2f).oneShot(greet, 250);
    }

    /**
     *
     */
    private void initUserSolvedQuestionPapers() {
        solvedUserPracticeQuestion = initHelperLimit.getSolvedUserPracticeQuestion(QuestionSetMenu.db);
        if (null != solvedUserPracticeQuestion) {
            contentUserQuestionPaperSolvedBinding.contentloading.setVisibility(View.VISIBLE);
            solvedUserPracticeQuestion.setOnSolvedQuestionPaperListener(this, getIntent().getExtras().getString("id"));
        }
    }


}
