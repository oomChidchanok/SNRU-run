package wungsri.chidchanok.snrurun;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    //Explicit
    private MyManage myManage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myManage = new MyManage(MainActivity.this);

        //Test Add User
        //myManage.addUser("ออมมี่", "oommiiz", "123456", "0");

        //Delete All SQLite
        deleteAllSQLite();


    }//End of Main Method เมธอด คือสิ่งที่ห่อหุ้ม statement

    private void deleteAllSQLite() {

        SQLiteDatabase sqLiteDatabase = openOrCreateDatabase(MyOpenHelper.database_name,
                MODE_PRIVATE, null);

        sqLiteDatabase.delete(MyManage.user_table, null, null);
    }

    public void clickSignUpMain(View view) {
        startActivity(new Intent(MainActivity.this, SignUp.class));
    }//End of Method คลิกปุ่ม SignUpMain หน้า Main แล้วให้เปิดหน้า SignUP

} //End of Main Class
