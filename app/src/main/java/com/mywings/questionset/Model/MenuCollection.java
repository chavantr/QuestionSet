package com.mywings.questionset.Model;

import android.databinding.ObservableArrayList;

import com.mywings.questionset.R;

/**
 * Created by Tatyabhau Chavan on 10/23/2015.
 */
public class MenuCollection {

    public static ObservableArrayList<QuestionSetMainMenu> getMENUS() {
        return MENUS;
    }

    public static ObservableArrayList<QuestionSetMainMenu> MENUS;

    static {
        MENUS = new ObservableArrayList<>();
        MENUS.add(new QuestionSetMainMenu().setIcon(R.drawable.questions).setName("All MCQ's"));
        MENUS.add(new QuestionSetMainMenu().setIcon(R.drawable.favourite_icon).setName("Distinguished"));
        MENUS.add(new QuestionSetMainMenu().setIcon(R.drawable.like_icon).setName("My Favourites"));
        MENUS.add(new QuestionSetMainMenu().setIcon(R.drawable.question_paper).setName("Practice"));
        MENUS.add(new QuestionSetMainMenu().setIcon(R.drawable.question_paper).setName("My Question Paper"));
        //MENUS.add(new QuestionSetMainMenu().setIcon(R.drawable.questions).setName("My Task"));
        //MENUS.add(new QuestionSetMainMenu().setIcon(R.drawable.questions).setName("Add New Question"));
    }
}
