package brain.birthday;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Саня on 18.08.2016.
 */
public class ItemDAO {
    private final DBHelper2 dbHelper;
    public ItemDAO(DBHelper2 dbHelper) {
        this.dbHelper = dbHelper;
    }

    public void persist(Item item){
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBHelper2.NAME,item.name);
        contentValues.put(DBHelper2.DATES,item.date);
        long newId = database.insert(DBHelper2.ITEMS,null,contentValues);
        item.id = newId;
    }
    public ArrayList<Item> findAll(){//select
        //класс singlton
        ArrayList<Item> list =new ArrayList<Item>();
        //list.add(item);
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        //DBHelper dbHelpe = new BDHelper();
        Cursor cursor = database.query(DBHelper2.ITEMS,null,null,null,null,null,null);
        if(cursor.moveToFirst()){
            int idIndex = cursor.getColumnIndex(DBHelper2.ID);
            int nameIndex = cursor.getColumnIndex(DBHelper2.NAME);
            int dateIndex = cursor.getColumnIndex(DBHelper2.DATES);
            do{
                Item item = new Item();
                item.id = cursor.getInt(idIndex);
                item.name = cursor.getString(nameIndex);
                item.date = cursor.getString(dateIndex);
                list.add(item);
            }while(cursor.moveToNext());
        }
        cursor.close();
        return list;
    }
    public void delete(Item item){
        SQLiteDatabase database  =  dbHelper.getWritableDatabase();
        database.delete(DBHelper2.ITEMS,"_id = ?", new String[] {String.valueOf(item.id)});
    }
}
