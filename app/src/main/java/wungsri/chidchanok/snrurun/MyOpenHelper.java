package wungsri.chidchanok.snrurun;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Computer on 17/5/2559.
 */
public class MyOpenHelper extends SQLiteOpenHelper{

    //Explicit ประกาศตัวแปร
    public static final String database_name = "Snru.db";
    private static final int database_version = 1;

    private static final String create_user_table = "create table userTABLE(" +
            "_id integer primary key, " +
            "Name text," +
            "User text," +
            "Password text," +
            "Avata text);";


    public MyOpenHelper(Context context) {
        super(context, database_name, null, database_version);

    }//Constructor (Main Method)

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(create_user_table);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

    }
}//Main Class
