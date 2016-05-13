package com.mywings.questionset.Utils;

import com.mywings.questionset.Model.Question;

/**
 * Created by Tatyabhau Chavan on 1/16/2016.
 */
public class Email {

    /**
     * @param question
     * @return message
     */
    public static String composeMail(Question question) {
        StringBuilder message = new StringBuilder();
        message.append(question.getQuestion() + "\n");
        message.append("     A) " + question.getOptionA() + "\n");
        message.append("     B) " + question.getOptionB() + "\n");
        message.append("     C) " + question.getOptionC() + "\n");
        message.append("     D) " + question.getOptionD() + "\n\n");
        message.append("             Download from https://play.google.com/store/apps/details?id=com.mywings.questionset");
        return message.toString();
    }
}
