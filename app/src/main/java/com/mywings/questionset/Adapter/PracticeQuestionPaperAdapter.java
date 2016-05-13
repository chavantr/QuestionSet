package com.mywings.questionset.Adapter;


import android.databinding.DataBindingUtil;
import android.databinding.ObservableList;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RadioButton;

import com.mywings.questionset.Model.Question;
import com.mywings.questionset.PracticeQuestionPaper;
import com.mywings.questionset.QuestionSetMenu;
import com.mywings.questionset.R;
import com.mywings.questionset.databinding.PracticequestionlistBinding;

/**
 * Created by Tatyabhau Chavan on 1/19/2016.
 */
public class PracticeQuestionPaperAdapter extends RecyclerView.Adapter<PracticeQuestionPaperAdapter.ViewHolder> {

    private static LayoutInflater layoutInflater;
    private ObservableList<Question> lstPracticeQuestionPaper;
    private int solved = 1;


    public PracticeQuestionPaperAdapter(ObservableList<Question> lstPracticeQuestionPaper) {
        this.lstPracticeQuestionPaper = lstPracticeQuestionPaper;
    }


    /**
     * Called when RecyclerView needs a new {@link ViewHolder} of the given type to represent
     * an item.
     * <p/>
     * This new ViewHolder should be constructed with a new View that can represent the items
     * of the given type. You can either create a new View manually or inflate it from an XML
     * layout file.
     * <p/>
     * The new ViewHolder will be used to display items of the adapter using
     * {@link (ViewHolder, int, )}. Since it will be re-used to display
     * different items in the data set, it is a good idea to cache references to sub views of
     * the View to avoid unnecessary {@link View#findViewById(int)} calls.
     *
     * @param parent   The ViewGroup into which the new View will be added after it is bound to
     *                 an adapter position.
     * @param viewType The view type of the new View.
     * @return A new ViewHolder that holds a View of the given view type.
     * @see #getItemViewType(int)
     * @see #onBindViewHolder(ViewHolder, int)
     */
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (layoutInflater == null) {
            layoutInflater = layoutInflater.from(parent.getContext());
        }
        final PracticequestionlistBinding practicequestionlistBinding = DataBindingUtil.inflate(layoutInflater, R.layout.practicequestionlist, parent, false);
        return new ViewHolder(practicequestionlistBinding);
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    /**
     * Called by RecyclerView to display the data at the specified position. This method should
     * update the contents of the {@link ViewHolder#itemView} to reflect the item at the given
     * position.
     * <p/>
     * Note that unlike {@link ListView}, RecyclerView will not call this method
     * again if the position of the item changes in the data set unless the item itself is
     * invalidated or the new position cannot be determined. For this reason, you should only
     * use the <code>position</code> parameter while acquiring the related data item inside
     * this method and should not keep a copy of it. If you need the position of an item later
     * on (e.g. in a click listener), use {@link ViewHolder#getAdapterPosition()} which will
     * have the updated adapter position.
     * <p/>
     * Override {@link (ViewHolder, int, )} instead if Adapter can
     * handle effcient partial bind.
     *
     * @param holder   The ViewHolder which should be updated to represent the contents of the
     *                 item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.practicequestionlistBinding.setQuestionlist(lstPracticeQuestionPaper.get(position));
        if (!lstPracticeQuestionPaper.get(position).isSubmit()) {
            holder.practicequestionlistBinding.grpAnswer.clearCheck();
        }
        holder.practicequestionlistBinding.setSubmit(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isUserAnswerSelected(holder)) {
                    int i = (int) QuestionSetMenu.db.updateQuestionAnswer(getUserOption(holder), String.valueOf(PracticeQuestionPaper.id),
                            String.valueOf(lstPracticeQuestionPaper.get(position).getQuestionId()), solved);
                    lstPracticeQuestionPaper.get(position).setUserAnswer(getUserAnswer(holder));
                    lstPracticeQuestionPaper.get(position).setSubmit(true);
                    solved = solved + 1;
                } else {
                    Snackbar.make(holder.practicequestionlistBinding.btnSubmit, R.string.lbl_select_your_answer, Snackbar.LENGTH_LONG)
                            .setAction("OK", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                }
                            }).show();
                }
            }
        });
    }


    /**
     * @param holder
     * @return
     */
    private String getUserAnswer(ViewHolder holder) {
        int id = holder.practicequestionlistBinding.grpAnswer.getCheckedRadioButtonId();

        RadioButton rdbSelected = (RadioButton) holder.practicequestionlistBinding.grpAnswer.findViewById(id);

        return rdbSelected.getText().toString();
    }


    /**
     * @param holder
     * @return userOption
     */
    private String getUserOption(ViewHolder holder) {

        String userOption = null;


        if (holder.practicequestionlistBinding.rdbOptionA.isChecked()) {
            userOption = "1";
        } else if (holder.practicequestionlistBinding.rdbOptionB.isChecked()) {
            userOption = "2";
        } else if (holder.practicequestionlistBinding.rdbOptionC.isChecked()) {
            userOption = "3";
        } else if (holder.practicequestionlistBinding.rdbOptionD.isChecked()) {
            userOption = "4";
        } else {
            userOption = "";
        }


        return userOption;


    }


    /**
     * @param holder
     * @return selected
     */
    private boolean isUserAnswerSelected(ViewHolder holder) {
        return holder.practicequestionlistBinding.rdbOptionA.isChecked() || holder.practicequestionlistBinding.rdbOptionB.isChecked() || holder.practicequestionlistBinding.rdbOptionC.isChecked() || holder.practicequestionlistBinding.rdbOptionD.isChecked();
    }

    /**
     * Returns the total number of items in the data set hold by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    @Override
    public int getItemCount() {
        return lstPracticeQuestionPaper.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        PracticequestionlistBinding practicequestionlistBinding;

        public ViewHolder(PracticequestionlistBinding practicequestionlistBinding) {
            super(practicequestionlistBinding.getRoot());
            this.practicequestionlistBinding = practicequestionlistBinding;
        }
    }
}
