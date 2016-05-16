package wungsri.chidchanok.snrurun;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }//End of Main Method เมธอด คือสิ่งที่ห่อหุ้ม statement

    public void clickSignUpMain(View view) {
        startActivity(new Intent(MainActivity.this, SignUp.class));
    }//End of Method คลิกปุ่ม SignUpMain หน้า Main แล้วให้เปิดหน้า SignUP

} //End of Main Class
