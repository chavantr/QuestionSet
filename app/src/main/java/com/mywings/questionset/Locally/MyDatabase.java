/**
 * Copyright (C) 2013 MyWings Software Private Limited.
 */
package com.mywings.questionset.Locally;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteQueryBuilder;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;

import com.mywings.questionset.Model.PracticeQuestionPaperUser;
import com.mywings.questionset.Model.Question;
import com.mywings.questionset.Model.QuestionPaperDetails;
import com.mywings.questionset.Model.QuestionPaperMaster;
import com.mywings.questionset.Utils.QuestionSetConstatnts;

/**
 * @author Tatyabhau Chavan
 * @Created Dec 21, 2014
 */
public class MyDatabase extends SQLiteAssetHelper {

    public MyDatabase(Context context, String name, CursorFactory factory,
                      int version) {
        super(context, name, factory, version);
    }

    /**
     * @return questionset menu
     */
    public Cursor getQuestionSetSubMenu() {
        Cursor cursor = null;
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder query = new SQLiteQueryBuilder();
        query.setTables(QuestionSetConstatnts.TableNames.QUESTION_SUB_SUBJECT);
        cursor = query.query(db, null, null, null, null, null, null);
        return cursor;
    }

    /**
     * @param category
     * @return category wise question
     * @throws Exception
     */
    public Cursor getQuestionSetCategory(String category) throws Exception {
        Cursor cursor = null;
        SQLiteDatabase db = getReadableDatabase();
        String[] where = {category.trim()};
        SQLiteQueryBuilder query = new SQLiteQueryBuilder();
        query.setTables(QuestionSetConstatnts.TableNames.QUESTION);
        cursor = query.query(db, null,
                QuestionSetConstatnts.QuestionSetSubMenu.SUBSUBJECT_ID.trim()
                        + "=?", where, null, null, null);
        return cursor;
    }

    public Cursor getQuestionPaper(String language) {
        Cursor cursor;

        SQLiteDatabase db = getReadableDatabase();

        String query;

        if (language.equalsIgnoreCase("Marathi")) {
            query = "SELECT * FROM question where sub_subject_id NOT like '%15%' ORDER BY RANDOM() LIMIT 20";
        } else {
            query = "SELECT * FROM question where sub_subject_id like '%15%' ORDER BY RANDOM() LIMIT 20";
        }

        cursor = db.rawQuery(query, null);

        return cursor;
    }

    /**
     * @param id
     * @return updated
     */
    public int makeIsFavourite(String id) {
        int updated;

        SQLiteDatabase db = getWritableDatabase();

        String[] where = {id};

        ContentValues _contentValues = new ContentValues();

        _contentValues.put(QuestionSetConstatnts.Questions.ISFAVOURITE, 1);

        updated = db.update(QuestionSetConstatnts.TableNames.QUESTION,
                _contentValues,
                QuestionSetConstatnts.Questions.QUESTION_ID.trim() + "=?",
                where);

        return updated;
    }


    public long masterCreateQuestionPaper(
            QuestionPaperMaster questionPaperMaster) {
        long inserted = -1L;

        if (questionPaperMaster != null) {
            SQLiteDatabase db = getReadableDatabase();
            ContentValues _contentValues = new ContentValues();

            _contentValues
                    .put(QuestionSetConstatnts.QuestionPaperMaster.QUESTIONPAPERDATE,
                            questionPaperMaster.getQuestionPaperDate());
            _contentValues
                    .put(QuestionSetConstatnts.QuestionPaperMaster.QUESTIONPAPERNAME,
                            questionPaperMaster.getQuestionPaperName());

            _contentValues.put(
                    QuestionSetConstatnts.QuestionPaperMaster.USERIDENTITY,
                    questionPaperMaster.getUserIdentity());

            _contentValues.put(
                    QuestionSetConstatnts.QuestionPaperMaster.LANGUAGE,
                    questionPaperMaster.getLanguage());

            _contentValues
                    .put(QuestionSetConstatnts.QuestionPaperMaster.NUMBEROFQUESTIONS,
                            questionPaperMaster.getNumberOfQuestions());

            _contentValues
                    .put(QuestionSetConstatnts.QuestionPaperMaster.OBTAINEDQUESTIONS,
                            questionPaperMaster.getObtainedQuestions());

            db.insert(QuestionSetConstatnts.TableNames.QUESTIONPAPERMASTER,
                    null, _contentValues);

            Cursor cursorQuestionPaperMaster = db.rawQuery("select * from "
                            + QuestionSetConstatnts.TableNames.QUESTIONPAPERMASTER,
                    null);

            cursorQuestionPaperMaster.moveToLast();

            inserted = (long) cursorQuestionPaperMaster
                    .getInt(cursorQuestionPaperMaster
                            .getColumnIndex(QuestionSetConstatnts.QuestionPaperMaster.QUESTIONPAPERID));

            if (cursorQuestionPaperMaster != null) {
                cursorQuestionPaperMaster.close();
            }

        }

        return inserted;

    }


    /**
     * @param id
     * @return
     */
    public ObservableList<PracticeQuestionPaperUser> getQuestionPaperQuestionIdWise(String id) {

        SQLiteDatabase db = getReadableDatabase();

        ObservableList<PracticeQuestionPaperUser> lstQuestionPaperDetails = new ObservableArrayList<PracticeQuestionPaperUser>();

        SQLiteQueryBuilder query = new SQLiteQueryBuilder();

        query.setTables(QuestionSetConstatnts.TableNames.QUESTIONPAPERDETAIL);

        Cursor cursor;

        String[] where = {id.trim()};

        cursor = query.query(db, null, QuestionSetConstatnts.QuestionPaperDetails.QUESTIONPAPERID + "=?", where, null, null, null);

        cursor.moveToFirst();

        int totalMarks = 0;

        while (!cursor.isAfterLast()) {

            PracticeQuestionPaperUser practiceQuestionPaperUser = new PracticeQuestionPaperUser();

            QuestionPaperDetails questionPaperDetails = new QuestionPaperDetails();


            questionPaperDetails.setId(cursor.getInt(cursor.getColumnIndex(QuestionSetConstatnts.QuestionPaperDetails.ID)));

            questionPaperDetails.setQuestionID(cursor.getInt(cursor.getColumnIndex(QuestionSetConstatnts.QuestionPaperDetails.QUESTIONID)));

            questionPaperDetails.setQuestionPaperID(cursor.getInt(cursor.getColumnIndex(QuestionSetConstatnts.QuestionPaperDetails.QUESTIONPAPERID)));

            questionPaperDetails.setUserAnswer(cursor.getInt(cursor.getColumnIndex(QuestionSetConstatnts.QuestionPaperDetails.USERANSWER)));

            practiceQuestionPaperUser.setQuestion(getQuestionUsingId(String.valueOf(cursor.getInt(cursor.getColumnIndex(QuestionSetConstatnts.QuestionPaperDetails.QUESTIONID))), questionPaperDetails));

            practiceQuestionPaperUser.setQuestionPaperDetails(questionPaperDetails);

            if (practiceQuestionPaperUser.getQuestion().getAnswer().equalsIgnoreCase(String.valueOf(questionPaperDetails.getUserAnswer()))) {
                totalMarks = totalMarks + 1;
            }

            // practiceQuestionPaperUser.setTotalMarks(totalMarks);

            lstQuestionPaperDetails.add(practiceQuestionPaperUser);

            cursor.moveToNext();
        }

        if (null != lstQuestionPaperDetails) {
            lstQuestionPaperDetails.get(0).setTotalMarks(totalMarks);
        }

        return lstQuestionPaperDetails;
    }


    private Question getQuestionUsingId(String id, QuestionPaperDetails details) {

        SQLiteDatabase db = getReadableDatabase();

        SQLiteQueryBuilder query = new SQLiteQueryBuilder();

        query.setTables(QuestionSetConstatnts.TableNames.QUESTION);

        Cursor cursor;

        String[] where = {String.valueOf(id)};

        cursor = query.query(db, null, QuestionSetConstatnts.Questions.QUESTION_ID + "=?", where, null, null, null);

        cursor.moveToFirst();

        Question questionSet = new Question();

        questionSet.setQuestion(cursor.getString(cursor
                .getColumnIndex(QuestionSetConstatnts.Questions.QUESTION)).trim());

        questionSet.setOptionA(cursor.getString(cursor
                .getColumnIndex(QuestionSetConstatnts.Questions.OPTION_A)).trim());

        questionSet.setOptionB(cursor.getString(cursor
                .getColumnIndex(QuestionSetConstatnts.Questions.OPTION_B)).trim());
        questionSet.setOptionC(cursor.getString(cursor
                .getColumnIndex(QuestionSetConstatnts.Questions.OPTION_C)).trim());
        questionSet.setOptionD(cursor.getString(cursor
                .getColumnIndex(QuestionSetConstatnts.Questions.OPTION_D)).trim());
        questionSet
                .setQuestionId(Integer.parseInt(cursor.getString(cursor
                        .getColumnIndex(QuestionSetConstatnts.Questions.QUESTION_ID))));
        questionSet.setAnswer(cursor.getString(cursor
                .getColumnIndex(QuestionSetConstatnts.Questions.ANSWER)).trim());
        questionSet
                .setIsFavourite(cursor.getString(cursor
                        .getColumnIndex(QuestionSetConstatnts.Questions.ISFAVOURITE)));

        questionSet.setIsLike(cursor.getString(cursor
                .getColumnIndex(QuestionSetConstatnts.Questions.ISLIKE)));


        questionSet.setCorrectAnswer(get(Integer.parseInt(cursor.getString(cursor
                .getColumnIndex(QuestionSetConstatnts.Questions.ANSWER)).trim()), questionSet));

        questionSet.setUserAnswer(get(details.getUserAnswer(), questionSet));

        return questionSet;
    }

    private String get(int id, Question question) {
        String answer;
        switch (id) {

            case 0:

                answer = "NA";

                break;

            case 1:
                answer = question.getOptionA();
                break;
            case 2:
                answer = question.getOptionB();
                break;
            case 3:
                answer = question.getOptionC();
                break;
            case 4:
                answer = question.getOptionD();
                break;
            default:
                answer = "";
                break;

        }
        return answer;
    }

    /**
     * @return QuestionPaperMaster
     */
    public ObservableList<QuestionPaperMaster> getQuestionPapers() {

        SQLiteDatabase db = getReadableDatabase();

        ObservableList<QuestionPaperMaster> lstQuestionPaperMaster = new ObservableArrayList<>();

        SQLiteQueryBuilder query = new SQLiteQueryBuilder();

        query.setTables(QuestionSetConstatnts.TableNames.QUESTIONPAPERMASTER);

        Cursor cursor;

        cursor = query.query(db, null, null, null, null, null, QuestionSetConstatnts.QuestionPaperMaster.QUESTIONPAPERDATE + " DESC");

        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {

            QuestionPaperMaster objQuestionPaperMaster = new QuestionPaperMaster();

            objQuestionPaperMaster
                    .setLanguage(cursor.getString(cursor
                            .getColumnIndex(QuestionSetConstatnts.QuestionPaperMaster.LANGUAGE)));

            objQuestionPaperMaster
                    .setNumberOfQuestions(cursor.getString(cursor
                            .getColumnIndex(QuestionSetConstatnts.QuestionPaperMaster.NUMBEROFQUESTIONS)));

            objQuestionPaperMaster
                    .setObtainedQuestions(cursor.getString(cursor
                            .getColumnIndex(QuestionSetConstatnts.QuestionPaperMaster.OBTAINEDQUESTIONS)).replace("Not Updated", "0"));

            objQuestionPaperMaster
                    .setQuestionPaperDate(cursor.getString(cursor
                            .getColumnIndex(QuestionSetConstatnts.QuestionPaperMaster.QUESTIONPAPERDATE)));

            objQuestionPaperMaster
                    .setQuestionPaperID(Integer.parseInt(cursor.getString(cursor
                            .getColumnIndex(QuestionSetConstatnts.QuestionPaperMaster.QUESTIONPAPERID))));

            objQuestionPaperMaster
                    .setQuestionPaperName(cursor.getString(cursor
                            .getColumnIndex(QuestionSetConstatnts.QuestionPaperMaster.QUESTIONPAPERNAME)));

            objQuestionPaperMaster
                    .setUserIdentity(cursor.getString(cursor
                            .getColumnIndex(QuestionSetConstatnts.QuestionPaperMaster.USERIDENTITY)));

            cursor.moveToNext();

            lstQuestionPaperMaster.add(objQuestionPaperMaster);
        }

        return lstQuestionPaperMaster;
    }

    public long updateQuestionAnswer(String strUserAnswer,
                                     String strQuestionPaperID, String strQuestionID, int mCurrentAnswer) {

        long updated;

        SQLiteDatabase db = getWritableDatabase();

        String[] whereObtained = new String[]{strQuestionPaperID};

        ContentValues mContentValues = new ContentValues();

        mContentValues.put(
                QuestionSetConstatnts.QuestionPaperMaster.OBTAINEDQUESTIONS,
                mCurrentAnswer);

        long updateObatain = db.update(
                QuestionSetConstatnts.TableNames.QUESTIONPAPERMASTER,
                mContentValues,
                QuestionSetConstatnts.QuestionPaperMaster.QUESTIONPAPERID
                        .trim() + "=?", whereObtained);


        String[] where = new String[]{strQuestionPaperID, strQuestionID};

        ContentValues _contentValues = new ContentValues();

        _contentValues.put(
                QuestionSetConstatnts.QuestionPaperDetails.USERANSWER,
                strUserAnswer);

        updated = db
                .update(QuestionSetConstatnts.TableNames.QUESTIONPAPERDETAIL,
                        _contentValues,
                        QuestionSetConstatnts.QuestionPaperDetails.QUESTIONPAPERID
                                .trim()
                                + "=? and "
                                + QuestionSetConstatnts.QuestionPaperDetails.QUESTIONID
                                .trim() + "=?", where);

        return updated;

    }

    /**
     * @param objQuestionPaperDetails
     * @param mQuestionPaperID
     * @return inserted;
     */
    public long createQuestionPaperDetails(
            ObservableList<Question> objQuestionPaperDetails, int mQuestionPaperID) {
        SQLiteDatabase db = getWritableDatabase();
        long questionPapersAdded = 0;
        if (null != objQuestionPaperDetails) {
            for (int i = 0; i < objQuestionPaperDetails.size(); i++) {
                ContentValues questionPaperDetails = new ContentValues();
                questionPaperDetails.put("QuestionPaperID", ""
                        + mQuestionPaperID);
                questionPaperDetails.put("QuestionID", ""
                        + objQuestionPaperDetails.get(i).getQuestionId());
                questionPaperDetails.put("UserAnswer", "");
                db.insertOrThrow(
                        QuestionSetConstatnts.TableNames.QUESTIONPAPERDETAIL,
                        null, questionPaperDetails);

                questionPapersAdded++;
            }
        }
        return questionPapersAdded;
    }


    /**
     * @param id
     * @return updated
     */
    public int makeIsUnFavourite(String id) {
        int updated;

        SQLiteDatabase db = getWritableDatabase();

        String[] where = {id};

        ContentValues _contentValues = new ContentValues();

        _contentValues.put(QuestionSetConstatnts.Questions.ISFAVOURITE, 0);

        updated = db.update(QuestionSetConstatnts.TableNames.QUESTION,
                _contentValues,
                QuestionSetConstatnts.Questions.QUESTION_ID.trim() + "=?",
                where);

        return updated;
    }

    /**
     * @param id
     * @return
     */
    public int makeIsLike(String id) {

        int updated;
        SQLiteDatabase db = getWritableDatabase();
        String[] where = {id};
        ContentValues _contentValues = new ContentValues();
        _contentValues.put(QuestionSetConstatnts.Questions.ISLIKE, 1);

        updated = db.update(QuestionSetConstatnts.TableNames.QUESTION,
                _contentValues,
                QuestionSetConstatnts.Questions.QUESTION_ID.trim() + "=?",
                where);

        return updated;
    }

    /**
     * @param id
     * @return updated
     */
    public int makeIsUnlike(String id) {

        int updated = -1;
        SQLiteDatabase db = getWritableDatabase();
        String[] where = {id};
        ContentValues _contentValues = new ContentValues();
        _contentValues.put(QuestionSetConstatnts.Questions.ISLIKE, 0);

        updated = db.update(QuestionSetConstatnts.TableNames.QUESTION,
                _contentValues,
                QuestionSetConstatnts.Questions.QUESTION_ID.trim() + "=?",
                where);

        return updated;
    }


    /**
     * @param isFavourite
     * @return favorite list.
     */
    public Cursor getQuestionSetFavourite(String isFavourite) {
        Cursor cursor = null;

        SQLiteDatabase db = getReadableDatabase();
        String[] where = {isFavourite};
        SQLiteQueryBuilder query = new SQLiteQueryBuilder();
        query.setTables(QuestionSetConstatnts.TableNames.QUESTION);

        cursor = query.query(db, null,
                QuestionSetConstatnts.Questions.ISFAVOURITE.trim() + "=?",
                where, null, null, null);
        return cursor;
    }

    /**
     * @param isLike
     * @return favorite list.
     */
    public Cursor getQuestionSetLike(String isLike) {
        Cursor cursor;
        SQLiteDatabase db = getReadableDatabase();
        String[] where = {isLike};
        SQLiteQueryBuilder query = new SQLiteQueryBuilder();
        query.setTables(QuestionSetConstatnts.TableNames.QUESTION);
        cursor = query.query(db, null,
                QuestionSetConstatnts.Questions.ISLIKE.trim() + "=?", where,
                null, null, null);
        return cursor;
    }


}
