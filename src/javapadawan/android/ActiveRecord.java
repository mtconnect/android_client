package javapadawan.android;
import com.ITAMCO.MTConnect.*;
import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.ITAMCO.MTConnect.R;

public interface ActiveRecord {
	public long save();
	public boolean delete();
	public void load(Activity activity);
	public Cursor retrieveAll();
	public void setSQLiteDatabase(SQLiteDatabase sqliteDb);
}
