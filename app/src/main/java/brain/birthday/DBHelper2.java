package brain.birthday;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Саня on 18.08.2016.
 */
public class DBHelper2 extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "contactDb";

    public static final String ITEMS = "Item";

    public static final String ID = "_id";
    public static final String NAME = "item";
    public static final String DATES = "dates";
    public DBHelper2(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL("create table " + ITEMS + "(" + ID + " integer primary key not NULL,"  + NAME + " text, " + DATES + " text " + ")");

    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("drop table if exists " + ITEMS);
        onCreate(db);
    }
}
