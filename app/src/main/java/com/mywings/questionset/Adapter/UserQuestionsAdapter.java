package com.mywings.questionset.Adapter;

import android.app.Activity;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableArrayList;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.mywings.questionset.Locally.MyDatabase;
import com.mywings.questionset.Model.Question;
import com.mywings.questionset.R;
import com.mywings.questionset.databinding.QuestionlistBinding;


/**
 * Created by Tatyabhau Chavan on 12/31/2015.
 */
public class UserQuestionsAdapter extends RecyclerView.Adapter<UserQuestionsAdapter.ViewHolder> {

    private ObservableArrayList<Question> questions;
    private Context context;
    private LayoutInflater layoutInflater;
    private MyDatabase db;
    private boolean isLike;


    public UserQuestionsAdapter(ObservableArrayList<Question> questions, MyDatabase db, boolean isLike) {
        this.questions = questions;
        this.db = db;
        this.isLike = isLike;
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
     * {@link #}. Since it will be re-used to display
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
        context = parent.getContext();
        if (null == layoutInflater) {
            layoutInflater = LayoutInflater.from(parent.getContext());
        }
        final QuestionlistBinding questionListBinding = DataBindingUtil.inflate(layoutInflater, R.layout.questionlist, parent, false);
        return new ViewHolder(questionListBinding);
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
     * Override {@link #(ViewHolder, int, )} instead if Adapter can
     * handle effcient partial bind.
     *
     * @param holder   The ViewHolder which should be updated to represent the contents of the
     *                 item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.questionListBinding.setQuestionlist(questions.get(position));
        if (holder.questionListBinding.tlbQuestion.getMenu().size() == 0) {
            if (isLike) {
                holder.questionListBinding.tlbQuestion.inflateMenu(R.menu.menu_main);
            } else {
                holder.questionListBinding.tlbQuestion.inflateMenu(R.menu.favorite_menu);
            }
        }
        holder.questionListBinding.tlbQuestion.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_unlike:
                        int updatedLike = db.makeIsUnlike(String.valueOf(questions.get(position).getQuestionId()));
                        if (updatedLike >= 1) {
                            questions.remove(position);
                            notifyItemRemoved(position);
                            notifyItemRangeChanged(position, getItemCount());
                            show("Question has been removed.");
                        }
                        break;
                    case R.id.action_unfavorite:
                        int updateFavorited = db.makeIsUnFavourite(String.valueOf(questions.get(position).getQuestionId()));
                        if (updateFavorited >= 1) {
                            questions.remove(position);
                            notifyItemRemoved(position);
                            notifyItemRangeChanged(position, getItemCount());
                            show("Question has been removed.");
                        }
                        break;
                }
                return true;
            }
        });
    }

    private void show(String message) {
        Snackbar.make(((Activity) context).findViewById(R.id.toolbar), message, Snackbar.LENGTH_LONG)
                .setAction("OK", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                }).show();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * Returns the total number of items in the data set hold by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    @Override
    public int getItemCount() {
        return questions.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        QuestionlistBinding questionListBinding;

        public ViewHolder(QuestionlistBinding questionListBinding) {
            super(questionListBinding.getRoot());
            this.questionListBinding = questionListBinding;
        }
    }
}
