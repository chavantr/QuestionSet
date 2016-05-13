package com.mywings.questionset.Adapter;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableArrayList;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.mywings.questionset.Model.Question;
import com.mywings.questionset.QuestionSetMenu;
import com.mywings.questionset.R;
import com.mywings.questionset.Utils.Email;
import com.mywings.questionset.databinding.QuestionMenuItemBinding;

/**
 * Created by Tatyabhau Chavan on 12/31/2015.
 */
public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.ViewHolder> {

    private ObservableArrayList<Question> questions;
    private LayoutInflater layoutInflater;
    private int lastPosition = 0;
    private Context context;


    public QuestionAdapter(ObservableArrayList<Question> questions) {
        this.questions = questions;
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

        context=parent.getContext();

        if (null == layoutInflater) {
            layoutInflater = LayoutInflater.from(parent.getContext());
        }
        final QuestionMenuItemBinding questionMenuItemBinding = DataBindingUtil.inflate(layoutInflater, R.layout.question_menu_item, parent, false);
        questionMenuItemBinding.setContext(parent.getContext());
        return new ViewHolder(questionMenuItemBinding);
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
    public void onBindViewHolder(ViewHolder holder, final int position) {

        holder.questionMenuItemBinding.setQuestionlist(questions.get(position));


        holder.questionMenuItemBinding.imgLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int islike = 0;

                islike = QuestionSetMenu.db.makeIsLike(String.valueOf(questions.get(position).getQuestionId()));

                if (islike > 0) {
                    questions.get(position).setIsLike("1");
                }

            }
        });

        holder.questionMenuItemBinding.imgFavourite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int isFavorite = 0;
                isFavorite = QuestionSetMenu.db.makeIsFavourite(String.valueOf(questions.get(position).getQuestionId()));
                if (isFavorite > 0) {
                    questions.get(position).setIsFavourite("1");
                }
            }
        });

        holder.questionMenuItemBinding.imgShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                launchShareIntent(position);

            }
        });

    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     *
     */
    void launchShareIntent(int position) {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_SUBJECT,
                context.getString(R.string.lbl_share_to_your_network));
        shareIntent.putExtra(Intent.EXTRA_TEXT,
                Email.composeMail(questions.get(position)));
        context.getApplicationContext().startActivity(
                Intent.createChooser(shareIntent,
                        context.getString(R.string.lbl_share_to_your_network))
                        .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
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
        QuestionMenuItemBinding questionMenuItemBinding;

        public ViewHolder(QuestionMenuItemBinding questionMenuItemBinding) {
            super(questionMenuItemBinding.getRoot());
            this.questionMenuItemBinding = questionMenuItemBinding;
        }
    }

}
