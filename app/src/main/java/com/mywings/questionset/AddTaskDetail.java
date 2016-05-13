package com.mywings.questionset;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;

import com.mywings.questionset.databinding.ContentAddTaskDetailBinding;
import com.mywings.questionset.databinding.LayoutAddTaskDetailBinding;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class AddTaskDetail extends QuestionSetCompact {

    private LayoutAddTaskDetailBinding layoutAddTaskDetailBinding;
    private ContentAddTaskDetailBinding contentAddTaskDetailBinding;
    private Calendar startDate = Calendar.getInstance();
    private Calendar endDate = Calendar.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        layoutAddTaskDetailBinding = setContentLayout(R.layout.layout_add_task_detail);
        contentAddTaskDetailBinding = attach(R.layout.content_add_task_detail);
        Toolbar toolbar = layoutAddTaskDetailBinding.toolbar;
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        contentAddTaskDetailBinding.txtStartDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contentAddTaskDetailBinding.txtTitle.clearFocus();
                initDatePicker();
            }
        });


        contentAddTaskDetailBinding.txtEndDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contentAddTaskDetailBinding.txtTitle.clearFocus();
                initenddatePicker();
            }
        });
    }

    private void initDatePicker() {
        new DatePickerDialog(AddTaskDetail.this, startdateSetListener, startDate
                .get(Calendar.YEAR), startDate.get(Calendar.MONTH),
                startDate.get(Calendar.DAY_OF_MONTH)).show();
    }


    private void initenddatePicker() {
        new DatePickerDialog(AddTaskDetail.this, endDateSetListner, endDate
                .get(Calendar.YEAR), endDate.get(Calendar.MONTH),
                endDate.get(Calendar.DAY_OF_MONTH)).show();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_task_detail_done_item, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_done:
                validate();
                return true;
            default:
                return false;
        }
    }

    /**
     * @return
     */
    private boolean validate() {
        if (contentAddTaskDetailBinding.txtTitle.getText().toString().isEmpty() && contentAddTaskDetailBinding.txtStartDate.getText().toString().isEmpty() && contentAddTaskDetailBinding.txtEndDate.getText().toString().isEmpty()) {
            show("All fields are mandatory", contentAddTaskDetailBinding.txtEndDate);
            return false;
        } else if (contentAddTaskDetailBinding.txtTitle.getText().toString().isEmpty()) {
            show("Please enter title", contentAddTaskDetailBinding.txtEndDate);
            return false;
        } else if (contentAddTaskDetailBinding.txtStartDate.getText().toString().isEmpty()) {
            show("Please select start date", contentAddTaskDetailBinding.txtEndDate);
            return false;
        } else if (contentAddTaskDetailBinding.txtEndDate.getText().toString().isEmpty()) {
            show("Please select end date", contentAddTaskDetailBinding.txtEndDate);
            return false;
        } else {
            return true;
        }
    }

    private DatePickerDialog.OnDateSetListener startdateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            startDate.set(Calendar.YEAR, year);
            startDate.set(Calendar.MONTH, monthOfYear);
            startDate.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updatestartdateLabel();
        }
    };

    private DatePickerDialog.OnDateSetListener endDateSetListner = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            endDate.set(Calendar.YEAR, year);
            endDate.set(Calendar.MONTH, monthOfYear);
            endDate.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateenddateLabel();
        }
    };

    /**
     *
     */
    private void updatestartdateLabel() {
        String myFormat = "dd/MM/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        contentAddTaskDetailBinding.txtStartDate.setText(sdf.format(startDate.getTime()));
    }

    private void updateenddateLabel() {
        String myFormat = "dd/MM/yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        contentAddTaskDetailBinding.txtEndDate.setText(sdf.format(endDate.getTime()));
    }
}
