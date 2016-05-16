package wungsri.chidchanok.snrurun;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

/**
 * Created by Computer on 16/5/2559.
 */
public class MyAlert {
    public void myDialog(Context context,
                         String strTitle,
                         String strMessage) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setCancelable(false); //เมื่อป็อบอัพเด้งขึ้นมาแล้ว จะไม่สามารถกด Undo หรือ cancel ได้
        builder.setIcon(R.drawable.bird48);
        builder.setTitle(strTitle);
        builder.setMessage(strMessage);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss(); //กด ok แล้ว dialog หายไป
            }
        });
        builder.show();
    }
}//Main Class
