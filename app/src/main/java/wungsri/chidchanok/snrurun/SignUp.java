package wungsri.chidchanok.snrurun;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class SignUp extends AppCompatActivity {

    //Explicit = ประกาศตัวแปร
    private EditText nameEditText, userEditText, passwordEditText;
    private RadioGroup radioGroup;
    private RadioButton choice1RadioButton, choice2RadioButton,
            choice3RadioButton, choice4RadioButton, choice5RadioButton;
    private String nameString, userString, passwordString, avatarString;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //Bind Wiget =
        bindWidget();



    }//End of Main Method

    private void bindWidget() {
        nameEditText = (EditText) findViewById(R.id.editTextName);
        userEditText = (EditText) findViewById(R.id.editTextUser);
        passwordEditText = (EditText) findViewById(R.id.editTextPword);
        radioGroup = (RadioGroup) findViewById(R.id.regAvatar);
        choice1RadioButton = (RadioButton) findViewById(R.id.radioButton);
        choice2RadioButton = (RadioButton) findViewById(R.id.radioButton2);
        choice3RadioButton = (RadioButton) findViewById(R.id.radioButton3);
        choice4RadioButton = (RadioButton) findViewById(R.id.radioButton4);
        choice5RadioButton = (RadioButton) findViewById(R.id.radioButton5);


    }//End of Method BindWidget


    public void clickSignUpSign(View view) {

        //Get Value From EditText
        nameString = nameEditText.getText().toString().trim(); //Trim คือ ถ้ามีช่องว่างหน้าคำ ท้ายคำจะตัดออกอัตโนมัติ
        userString = userEditText.getText().toString().trim();
        passwordString = passwordEditText.getText().toString().trim();

        //check space
        if (nameString.equals("")  || userString.equals("") || passwordString.equals("")) {
            MyAlert myAlert = new MyAlert();
            myAlert.myDialog(this, "มีช่องว่าง", "กรุณากรอกข้อมูลให้ครบถ้วน");
        } else {

        }


    }//End of Click SignUp Button on SignUp Class

}//End of Main Class
