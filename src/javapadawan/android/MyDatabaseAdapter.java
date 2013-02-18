package javapadawan.android;
import com.ITAMCO.MTConnect.*;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.util.Log;
import com.ITAMCO.MTConnect.R;

public class MyDatabaseAdapter extends SQLiteOpenHelper {

	private static SQLiteDatabase sqliteDb;
	private static MyDatabaseAdapter instance;
	
	private static final String DATABASE_NAME = "simple_sqlite_db";
	private static final int DATABASE_VERSION = 1;

	
	private MyDatabaseAdapter(Context context, String name, CursorFactory factory, int version) {
		super(context, name, factory, version);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		 db.execSQL(Account.SQL_CREATE_TABLE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(getClass().getSimpleName(), "Upgrading database from version " + oldVersion + " to "
                + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + Account.SQL_TABLE_NAME );
        onCreate(db);
	}
	
	private static void initialize(Context context) {
		if(instance == null) {
			instance = new MyDatabaseAdapter(context, DATABASE_NAME, null, DATABASE_VERSION);
			sqliteDb = instance.getWritableDatabase();
		}
	}
	
	public static final MyDatabaseAdapter getInstance(Context context) {
		initialize(context);
		return instance;
	}
	
	public SQLiteDatabase getDatabase() {
		return sqliteDb;	
	}
	
	public void close() {
		if(instance != null ) {
			instance.close();
			instance = null;
		}
	}
}