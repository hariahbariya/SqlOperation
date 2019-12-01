package com.example.admin.sqloperation;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;
public class MyHelperClass extends SQLiteOpenHelper {

    private final static String table_name="allmy";
    private final static String db_name="kinjal.db";
    private final static String COL_1="ID";
    private final static String COL_2="NAME";
    private final static String COL_3="EMAIL";
    private final static String COL_4="GENDER";

    public MyHelperClass(@Nullable Context context) {
        super(context, db_name, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table "+ table_name +" (ID INTEGER PRIMARY KEY AUTOINCREMENT,NAME TEXT,EMAIL TEXT,GENDER TEXT) ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists "+table_name);
    }


    public boolean insert_data(String name,String email,String gender)
    {
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COL_2,name);
        contentValues.put(COL_3,email);
        contentValues.put(COL_4,gender);
        long i=sqLiteDatabase.insert(table_name,null,contentValues);

        if(i==-1)
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    public Cursor getAll()
    {
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery("select * from allmy",null);

        return cursor;
    }

    public boolean update_data(String id,String name,String email)
    {
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COL_2,name);
        contentValues.put(COL_3,email);

        long c=sqLiteDatabase.update(table_name,contentValues,"ID=?",new String[]{id});

        if(c==-1)
        {
            return  false;
        }
        else
        {
            return true;
        }
    }

    public  Integer delete_data(String id)
    {
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        return sqLiteDatabase.delete(table_name,"ID=?",new String[] {id});
    }
}
