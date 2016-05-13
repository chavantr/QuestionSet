package com.mywings.questionset;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.mywings.questionset.Adapter.QuestionSetMenuAdapter;
import com.mywings.questionset.Locally.MyDatabase;
import com.mywings.questionset.Model.MenuCollection;
import com.mywings.questionset.Utils.QuestionSetConstatnts;
import com.mywings.questionset.databinding.QuestionSetMenuBinding;

public class QuestionSetMenu extends QuestionSetCompact {

    public static MyDatabase db;
    private QuestionSetMenuBinding questionSetMenuBinding;
    private RecyclerView lstQuestionSet;
    private String[] strPopUpData = {"English", "Marathi"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        questionSetMenuBinding = setContentLayout(R.layout.question_set_menu);
        getSupportActionBar().setLogo(R.mipmap.ic_launcher);
        db = new MyDatabase(getApplicationContext(),
                QuestionSetConstatnts.DatabaseConstants.DATABASE_NAME, null, 2);

        initRecycleView();


    }

    private void initRecycleView() {
        lstQuestionSet = questionSetMenuBinding.lstQuestionSet;
        lstQuestionSet.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        final QuestionSetMenuAdapter questionSetMenuAdapter = new QuestionSetMenuAdapter(MenuCollection.getMENUS());
        questionSetMenuAdapter.setOnItemClickListener(new QuestionSetMenuAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int id) {
                switch (id) {
                    case 0:
                        startCategoryWiseQuestions();
                        break;
                    case 1:
                        startFavoriteQuestions();
                        break;
                    case 2:
                        startLikeQuestions();
                        break;
                    case 3:
                        questionPaperSelectConfirmation();
                        break;
                    case 4:
                        startQuestionPapers();
                        break;
                    case 5:
                        startAddTask();
                        break;
                }
            }
        });
        lstQuestionSet.setAdapter(questionSetMenuAdapter);
    }

    /**
     *
     */
    public void questionPaperSelectConfirmation() {
        AlertDialog.Builder builder = new AlertDialog.Builder(
                QuestionSetMenu.this);
        builder.setTitle(R.string.lbl_select_language).setIcon(
                getResources().getDrawable(R.mipmap.ic_launcher));
        builder.setSingleChoiceItems(strPopUpData, 0,
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String language = strPopUpData[which];
                        startPracticeQuestionPaper(language);
                        dialog.dismiss();
                    }
                });
        Dialog dialog = builder.create();
        dialog.show();
    }

    /**
     *
     */
    private void startCategoryWiseQuestions() {
        Intent intent = new Intent(QuestionSetMenu.this, QuestionSetReadAllSectionWise.class);
        startActivity(intent);
    }

    /**
     *
     */
    private void startFavoriteQuestions() {
        Intent intent = new Intent(QuestionSetMenu.this, FavoriteQuestions.class);
        startActivity(intent);
    }


    /**
     *
     */
    private void startAddTask() {
        Intent intent = new Intent(QuestionSetMenu.this, AddTask.class);
        startActivity(intent);
    }

    /**
     *
     */
    private void startLikeQuestions() {
        Intent intent = new Intent(QuestionSetMenu.this, LikeQuestions.class);
        startActivity(intent);
    }

    /**
     * @param language
     */
    private void startPracticeQuestionPaper(String language) {
        Intent intent = new Intent(QuestionSetMenu.this, PracticeQuestionPaper.class);
        intent.putExtra("language", language);
        startActivity(intent);
    }

    /**
     *
     */
    private void startQuestionPapers() {
        Intent intent = new Intent(QuestionSetMenu.this, MyQuestionPapers.class);
        startActivity(intent);
    }


}
