package com.mywings.questionset;

import android.databinding.DataBindingUtil;
import android.databinding.ObservableList;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.mywings.questionset.Adapter.PracticeQuestionPaperAdapter;
import com.mywings.questionset.Model.Question;
import com.mywings.questionset.Model.QuestionPaperMaster;
import com.mywings.questionset.Process.CreateQuestionPaper;
import com.mywings.questionset.Process.CreateQuestionPaperDetails;
import com.mywings.questionset.Process.GetPracticeQuestionPaper;
import com.mywings.questionset.Process.OnCreateQuestionPaperListener;
import com.mywings.questionset.Process.OnPracticeQuestionPaperListener;
import com.mywings.questionset.Process.OnQuestionPaperDetailListener;
import com.mywings.questionset.Utils.AlertDialogBuilder;
import com.mywings.questionset.databinding.PracticeQuestionPaperBinding;

import java.util.Calendar;

public class PracticeQuestionPaper extends QuestionSetCompact implements OnPracticeQuestionPaperListener, OnCreateQuestionPaperListener, OnQuestionPaperDetailListener {

    public static int id;
    private PracticeQuestionPaperBinding practiceQuestionPaperBinding;
    private RecyclerView lstPracticeQuestionPaper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        practiceQuestionPaperBinding = setContentLayout(R.layout.practice_question_paper);
        Toolbar toolbar = practiceQuestionPaperBinding.toolbar;
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        init();
        initPracticeQuestionPaper(getIntent().getExtras().getString("language"));

    }

    /**
     *
     * @param language
     */
    private void initPracticeQuestionPaper(String language) {
        practiceQuestionPaperBinding.contentloading.setVisibility(View.VISIBLE);
        GetPracticeQuestionPaper practiceQuestionPaper = initHelperLimit.practiceQuestionPaper(QuestionSetMenu.db);
        practiceQuestionPaper.setOnPracticeQuestionPaperListener(this);
        practiceQuestionPaper.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, language);
    }

    /**
     *
     * @throws NullPointerException
     */
    private void init() throws NullPointerException {
        lstPracticeQuestionPaper = practiceQuestionPaperBinding.lstPracticeQuestionPaper;
        lstPracticeQuestionPaper.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
    }

    @Override
    public void onPracticeQuestionPaperComplete(ObservableList<Question> result, Exception exception) {
        if (null != result && exception == null) {
            CreateQuestionPaper createQuestionPaper = initHelperLimit.createQuestionPaper(QuestionSetMenu.db, result, generateQuestionPaperMaster(result, getIntent().getExtras().getString("language")));
            createQuestionPaper.setOnCreateQuestionPaperListener(this);
            createQuestionPaper.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, result);
        } else {
            practiceQuestionPaperBinding.contentloading.setVisibility(View.GONE);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                //AlertDialogBuilder.exitQuestionPaper(getString(R.string.app_name), "Enter question paper name", PracticeQuestionPaper.this);
                //return true;
                break;

            default:
                super.onOptionsItemSelected(item);

        }
        return false;
    }

    /**
     * @param questionPaper
     * @return QuestionPaperMaster
     */
    private QuestionPaperMaster generateQuestionPaperMaster(
            ObservableList<Question> questionPaper, String language) {
        QuestionPaperMaster objQuestionPaperMaster = new QuestionPaperMaster();
        objQuestionPaperMaster.setLanguage(language);
        objQuestionPaperMaster.setNumberOfQuestions("" + questionPaper.size());
        objQuestionPaperMaster.setObtainedQuestions("Not Updated");
        Calendar mCalendar = Calendar.getInstance();
        objQuestionPaperMaster.setQuestionPaperDate(""
                + mCalendar.getTimeInMillis());
        objQuestionPaperMaster.setQuestionPaperName("QuestionPaper");
        objQuestionPaperMaster.setUserIdentity("Unknown");
        return objQuestionPaperMaster;
    }

    @Override
    public void onQuestionPaperCreated(ObservableList<Question> input, int id, Exception exception) {
        if (null != input && id > 0 && exception == null) {
            this.id = id;
            initQuestionPaperDetails(input, id);
        } else {
            practiceQuestionPaperBinding.contentloading.setVisibility(View.GONE);
        }
    }

    /**
     *
     * @param input
     * @param id
     */
    private void initQuestionPaperDetails(ObservableList<Question> input, int id) {
        CreateQuestionPaperDetails createQuestionPaperDetails = initHelperLimit.createQuestionPaperDetails(QuestionSetMenu.db, id);
        createQuestionPaperDetails.setOnQuestionPaperDetailListener(this);
        createQuestionPaperDetails.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, input);
    }

    @Override
    public void onQuestionPaperDetailsComplete(ObservableList<Question> questions, Exception exception) {
        if (null != questions && exception == null) {
            final PracticeQuestionPaperAdapter practiceQuestionPaperAdapter = new PracticeQuestionPaperAdapter(questions);
            practiceQuestionPaperBinding.lstPracticeQuestionPaper.setAdapter(practiceQuestionPaperAdapter);
        } else {

        }
        practiceQuestionPaperBinding.contentloading.setVisibility(View.GONE);
    }
}
