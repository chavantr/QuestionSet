/**
 * Copyright (C) 2013 MyWings Software Private Limited.
 */
package com.mywings.questionset.Utils;

/**
 * @author Tatyabhau Chavan
 * 
 */
public class QuestionSetConstatnts {

	public static class Questions {
		public static String QUESTION_ID = "question_id";
		public static String QUESTION = "question";
		public static String OPTION_A = "option_a";
		public static String OPTION_B = "option_b";
		public static String OPTION_C = "option_c";
		public static String OPTION_D = "option_d";
		public static String ANSWER = "answer";
		public static String DIFFICULTY_LEVEL_ID = "difficulty_level_id";
		public static String SUB_SUBJECT_ID = "sub_subject_id";
		public static String IS_PUBLISH = "is_publish";
		public static String KEYWORDS = "keywords";
		public static String DESCRIPTION = "description";
		public static String ISLIKE = "is_Like";
		public static String ISFAVOURITE = "is_Favourite";
	}

	public static class QuestionsSet {
	}

	public static class QuestionSetSubMenu {
		public static String SUBSUBJECT_ID = "sub_subject_id";
		public static String SUBSUBJECT_NAME = "sub_subject_name";
		public static String SUBJECTID = "subject_id";
	}

	public static class DatabaseConstants {
		public static String DATABASE_NAME = "mydb";
	}

	public static class TableNames {
		public static String QUESTION_SUB_SUBJECT = "question_sub_subject";
		public static String QUESTION = "question";
		public static String QUESTIONPAPERMASTER = "QuestionPaperMaster";
		public static String QUESTIONPAPERDETAIL = "QuestionPaperDetail";
	}

	public static class QuestionPaperMaster {
		public static String QUESTIONPAPERID = "QuestionPaperID";
		public static String QUESTIONPAPERDATE = "QuestionPaperDate";
		public static String QUESTIONPAPERNAME = "QuestionPaperName";
		public static String USERIDENTITY = "UserIdentity";
		public static String LANGUAGE = "Language";
		public static String NUMBEROFQUESTIONS = "NumberOfQuestions";
		public static String OBTAINEDQUESTIONS = "ObtainedQuestions";
	}

	public static class QuestionPaperDetails {

		public static String ID = "id";
		public static String QUESTIONID = "QuestionID";
		public static String QUESTIONPAPERID = "QuestionPaperID";
		public static String USERANSWER = "UserAnswer";

	}
}
