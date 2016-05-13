package com.mywings.questionset.Adapter;

import android.databinding.DataBindingUtil;
import android.databinding.ObservableList;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.mywings.questionset.Model.QuestionPaperMaster;
import com.mywings.questionset.R;
import com.mywings.questionset.databinding.QuestionPaperRowBinding;


/**
 * Created by Tatyabhau Chavan on 2/11/2016.
 */
public class QuestionPaperAdapter extends RecyclerView.Adapter<QuestionPaperAdapter.ViewHolder> {

    private ObservableList<QuestionPaperMaster> questionPapers;
    private LayoutInflater layoutInflater;


    private OnItemClickListener onItemClickListener;

    public QuestionPaperAdapter(ObservableList<QuestionPaperMaster> questionPapers) {
        this.questionPapers = questionPapers;
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
     * <p/>
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
        if (null == layoutInflater) {
            layoutInflater = LayoutInflater.from(parent.getContext());
        }

        final QuestionPaperRowBinding questionPaperRowBinding = DataBindingUtil.inflate(layoutInflater, R.layout.question_paper_row, parent, false);

        return new ViewHolder(questionPaperRowBinding);
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
     * Override  instead if Adapter can
     * handle effcient partial bind.
     *
     * @param holder   The ViewHolder which should be updated to represent the contents of the
     *                 item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.questionPaperRowBinding.setQuestionPaper(questionPapers.get(position));

        holder.questionPaperRowBinding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != onItemClickListener) {
                    onItemClickListener.onItemClick(position);
                }
            }
        });
    }

    /**
     * Returns the total number of items in the data set hold by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    @Override
    public int getItemCount() {
        return this.questionPapers.size();
    }


    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(int id);
    }

    /**
     *
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private QuestionPaperRowBinding questionPaperRowBinding;

        public ViewHolder(QuestionPaperRowBinding questionPaperRowBinding) {
            super(questionPaperRowBinding.getRoot());
            this.questionPaperRowBinding = questionPaperRowBinding;
        }
    }
}
