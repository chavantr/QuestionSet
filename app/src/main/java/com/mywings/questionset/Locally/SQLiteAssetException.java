/**
 * Copyright (C) 2013 MyWings Software Private Limited.
 */
package com.mywings.questionset.Locally;

import android.database.sqlite.SQLiteException;

/**
 * @author Tatyabhau Chavan
 * 
 */
public class SQLiteAssetException extends SQLiteException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SQLiteAssetException() {
	}

	public SQLiteAssetException(String message) {
		super(message);
	}

}
