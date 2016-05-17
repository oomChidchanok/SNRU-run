package wungsri.chidchanok.snrurun;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;

public class SignUp extends AppCompatActivity {

    //Explicit = ประกาศตัวแปร
    private EditText nameEditText, userEditText, passwordEditText;
    private RadioGroup radioGroup;
    private RadioButton choice1RadioButton, choice2RadioButton,
            choice3RadioButton, choice4RadioButton, choice5RadioButton;
    private String nameString, userString, passwordString, avatarString = "0";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //Bind Wiget =
        bindWidget();

        //Radio Button Controller
        radioButtonController();


    }//End of Main Method

    private void radioButtonController() {

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int i) {

                switch (i) {
                    case R.id.radioButton:
                        avatarString = "0";
                        break;
                    case R.id.radioButton2:
                        avatarString = "1";
                        break;
                    case R.id.radioButton3:
                        avatarString = "2";
                        break;
                    case R.id.radioButton4:
                        avatarString = "3";
                        break;
                    case R.id.radioButton5:
                        avatarString = "4";
                        break;
                }
            }
        });
    }

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
            updateUserTABLE();
        }


    }//End of Click SignUp Button on SignUp Class

    private void updateUserTABLE() {

        OkHttpClient okHttpClient = new OkHttpClient();
        RequestBody requestBody = new FormEncodingBuilder()
                .add("isAdd", "true")
                .add("Name", nameString)
                .add("User", userString)
                .add("Password", passwordString)
                .add("Avata", avatarString)
                .build();
        Request.Builder builder = new Request.Builder();
        Request request = builder.url("http://swiftcodingthai.com/snru/add_user_master.php").post(requestBody).build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) throws IOException {
                finish();
            }
        });

    }//End of updateUserTABLE

}//End of Main Class
