package wungsri.chidchanok.snrurun;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    //Explicit
    private MyManage myManage;
    private ImageView imageView;
    private EditText userEditText, passwordEditText;
    private String userString, passwordString;
    private String[] userStrings;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Bind Widget
        imageView = (ImageView) findViewById(R.id.imageView6);
        userEditText = (EditText) findViewById(R.id.editText);
        passwordEditText = (EditText) findViewById(R.id.editText2);

        myManage = new MyManage(MainActivity.this);

        //Test Add User
        //myManage.addUser("ออมมี่", "oommiiz", "123456", "0");

        //Delete All SQLite
        deleteAllSQLite();

        //Synchronize
        MySynchronize mySynchronize = new MySynchronize();
        mySynchronize.execute();

        //show logo
        Picasso.with(MainActivity.this)
                .load("http://swiftcodingthai.com/snru/image/logo_snru.png")
                .resize(200,250)
                .into(imageView);


    }//End of Main Method เมธอด คือสิ่งที่ห่อหุ้ม statement

    public void clickSignIn(View view) {

        userString = userEditText.getText().toString().trim();
        passwordString = passwordEditText.getText().toString().trim();

        //Check Space

        if (userString.equals("")|| passwordString.equals("")) {

            MyAlert myAlert = new MyAlert();
            myAlert.myDialog(this, "มีช่องว่าง", "โปรดกรอกข้อมูลให้ครบทุกช่อง");

        } else {

            checkUser();

        }

    }//Click SignIn Method

    private void checkUser() {

        try {

            SQLiteDatabase sqLiteDatabase = openOrCreateDatabase(MyOpenHelper.database_name
                ,MODE_PRIVATE, null);
            Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM userTABLE WHERE User = " + "'" + userString + "'", null);
            cursor.moveToFirst();
            userStrings = new String[cursor.getColumnCount()];


            for (int i=0;i<cursor.getColumnCount();i++) {
                userStrings[i] = cursor.getString(i);
            }

            //Check Password
            if (passwordString.equals(userStrings[3])) {

                Toast.makeText(this, "ยินดีต้อนรับ " + userStrings[1], Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(MainActivity.this, MapsActivity.class);
                intent.putExtra("User", userStrings);
                startActivity(intent);
                finish();


            } else {

                MyAlert myAlert = new MyAlert();
                myAlert.myDialog(this, "Password ผิด", "กรุณาตรวจสอบ User และ Password ก่อน Sign In อีกครั้ง");
            }

        } catch (Exception e) {

            MyAlert myAlert = new MyAlert();
            myAlert.myDialog(this, "ไม่มี User นี้ในระบบ", "ไม่มี" + userString + "ในฐานข้อมูลของเรา");

        }

    }//Check User Method


    //Create Inner Class
    public class MySynchronize extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... params) {

            try {

                OkHttpClient okHttpClient = new OkHttpClient();
                Request.Builder builder = new Request.Builder();
                Request request = builder.url("http://swiftcodingthai.com/snru/get_user.php").build();
                Response response = okHttpClient.newCall(request).execute();

                return response.body().string();

            } catch (Exception e) {
                return null;
            }
            //return null


        }// doInBack

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            Log.d("Snru", "JSON ==> " + s);

            try {

                JSONArray jsonArray = new JSONArray(s);
                for (int i=0;i<jsonArray.length();i++) {

                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    String strName = jsonObject.getString(MyManage.column_name);
                    String strUser = jsonObject.getString(MyManage.column_user);
                    String strPassword = jsonObject.getString(MyManage.column_password);
                    String strAvata = jsonObject.getString(MyManage.column_avata);

                    myManage.addUser(strName, strUser, strPassword, strAvata);

                }//for

            } catch (Exception e) {
                e.printStackTrace();
            }
        }//OnPost Method

    }// MySync Class

    private void deleteAllSQLite() {

        SQLiteDatabase sqLiteDatabase = openOrCreateDatabase(MyOpenHelper.database_name,
                MODE_PRIVATE, null);

        sqLiteDatabase.delete(MyManage.user_table, null, null);
    }

    public void clickSignUpMain(View view) {
        startActivity(new Intent(MainActivity.this, SignUp.class));
    }//End of Method คลิกปุ่ม SignUpMain หน้า Main แล้วให้เปิดหน้า SignUP

} //End of Main Class
