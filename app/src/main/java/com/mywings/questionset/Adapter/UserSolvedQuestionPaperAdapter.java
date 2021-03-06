package com.mywings.questionset.Adapter;

import android.databinding.ObservableList;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.ListView;

import com.mywings.questionset.Model.PracticeQuestionPaperUser;
import com.mywings.questionset.R;
import com.mywings.questionset.UserQuestionPaperSolved;
import com.mywings.questionset.databinding.UserSolvedQuestionPaperBinding;

/**
 * Created by Tatyabhau Chavan on 3/8/2016.
 */
public class UserSolvedQuestionPaperAdapter extends RecyclerView.Adapter<UserSolvedQuestionPaperAdapter.ViewHolder> {


    private static UserSolvedQuestionPaperAdapter _instance;
    private ObservableList<PracticeQuestionPaperUser> practiceQuestionPaperUsers;


    public UserSolvedQuestionPaperAdapter(ObservableList<PracticeQuestionPaperUser> practiceQuestionPaperUsers) {
        this.practiceQuestionPaperUsers = practiceQuestionPaperUsers;
    }

    public static synchronized UserSolvedQuestionPaperAdapter getInstance(ObservableList<PracticeQuestionPaperUser> practiceQuestionPaperUsers) {
        if (null == _instance) {
            _instance = new UserSolvedQuestionPaperAdapter(practiceQuestionPaperUsers);
        }
        return _instance;
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
     * the View to avoid unnecessary
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
        UserQuestionPaperSolved userQuestionPaperSolved = (UserQuestionPaperSolved) (AppCompatActivity) parent.getContext();
        UserSolvedQuestionPaperBinding userSolvedQuestionPaperBinding = userQuestionPaperSolved.inflate(R.layout.user_solved_question_paper);
        return new ViewHolder(userSolvedQuestionPaperBinding);
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

        holder.userSolvedQuestionPaperBinding.setUserPracticeQuestionPaper(practiceQuestionPaperUsers.get(position));
    }

    /**
     * Returns the total number of items in the data set hold by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    @Override
    public int getItemCount() {
        return practiceQuestionPaperUsers.size();
    }




    public static class ViewHolder extends RecyclerView.ViewHolder {
        UserSolvedQuestionPaperBinding userSolvedQuestionPaperBinding;

        public ViewHolder(UserSolvedQuestionPaperBinding userSolvedQuestionPaperBinding) {
            super(userSolvedQuestionPaperBinding.getRoot());
            this.userSolvedQuestionPaperBinding=userSolvedQuestionPaperBinding;
        }
    }
}
