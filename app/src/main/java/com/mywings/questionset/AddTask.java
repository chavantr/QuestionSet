package com.mywings.questionset;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.mywings.questionset.databinding.LayoutAddTaskBinding;

public class AddTask extends QuestionSetCompact {

    private LayoutAddTaskBinding layoutAddTaskBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        layoutAddTaskBinding = setContentLayout(R.layout.layout_add_task);
        Toolbar toolbar = layoutAddTaskBinding.toolbar;
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        events();
    }

    /**
     *
     */
    private void events() {
        layoutAddTaskBinding.setOnClickEvent(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddTask.this, AddTaskDetail.class);
                startActivity(intent);
            }
        });
    }
}
