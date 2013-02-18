package javapadawan.android;
import com.ITAMCO.MTConnect.*;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.ITAMCO.MTConnect.R;

public class Account implements ActiveRecord {

	public static final String COL_ROW_ID = "_id";
	public static final String COL_SERVER_NAME = "server_name";
	public static final String COL_USERNAME = "username";
	public static final String COL_PASSWORD = "password";

	public static final String SQL_TABLE_NAME = "account_db";
	public static final String SQL_CREATE_TABLE = "CREATE TABLE "
			+ SQL_TABLE_NAME + " "
			+ " (_id integer primary key autoincrement, "
			+ "server_name text not null, username text not null, "
			+ "password text not null); ";

	private SQLiteDatabase sqliteDatabase;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getServerName() {
		return serverName;
	}

	public void setServerName(String serverName) {
		this.serverName = serverName;
	}

	private String username;

	private String password;

	private String serverName;

	private int id;

	@Override
	public void load(Activity activity) {
		Cursor cursor = sqliteDatabase.query(true, SQL_TABLE_NAME,
				new String[] { COL_ROW_ID, COL_SERVER_NAME, COL_USERNAME,
						COL_PASSWORD }, COL_ROW_ID + "=" + id, null, null,
				null, null, null);
		if (cursor != null) {
			cursor.moveToFirst();
			activity.startManagingCursor(cursor);
			setId(cursor.getInt(cursor
					.getColumnIndex(COL_ROW_ID)));
			setPassword(cursor.getString(cursor
					.getColumnIndex(COL_PASSWORD)));
			setUsername(cursor.getString(cursor
					.getColumnIndex(COL_USERNAME)));
			setServerName(cursor.getString(cursor
					.getColumnIndex(COL_SERVER_NAME)));
		}
	}

	@Override
	public Cursor retrieveAll() {
		return sqliteDatabase.query(SQL_TABLE_NAME, new String[] { COL_ROW_ID,
				COL_SERVER_NAME, COL_USERNAME, COL_PASSWORD }, null, null,
				null, null, null);
	}

	@Override
	public long save() {
		ContentValues values = new ContentValues();
		if (id <= 0) {
			values.put(COL_SERVER_NAME, serverName);
			values.put(COL_USERNAME, username);
			values.put(COL_PASSWORD, password);
			return sqliteDatabase.insert(SQL_TABLE_NAME, null, values);
		} else {
			values.put(COL_SERVER_NAME, serverName);
			values.put(COL_USERNAME, username);
			values.put(COL_PASSWORD, password);
			return sqliteDatabase.update(SQL_TABLE_NAME, values, COL_ROW_ID
					+ "=" + id, null);
		}
	}

	public boolean delete() {
		return sqliteDatabase.delete(SQL_TABLE_NAME, COL_ROW_ID + "=" + id,
				null) > 0;
	}

	@Override
	public void setSQLiteDatabase(SQLiteDatabase sqliteDatabase) {
		this.sqliteDatabase = sqliteDatabase;
	}
}