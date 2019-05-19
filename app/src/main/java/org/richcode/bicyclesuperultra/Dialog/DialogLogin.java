package org.richcode.bicyclesuperultra.Dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import org.richcode.bicyclesuperultra.DataBase.DBHelper;
import org.richcode.bicyclesuperultra.DataClass.DataMember;
import org.richcode.bicyclesuperultra.R;

import java.util.ArrayList;

public class DialogLogin extends Dialog implements View.OnClickListener {

    private Context context;

    Button LoginButton;
    Button RegisterButton;
    EditText EditID;
    EditText EditPassword;

    DBHelper dbHelper;

    ArrayList<DataMember> MemberList = new ArrayList<DataMember>();

    public DialogLogin(@NonNull Context context) {
        super(context);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_login);
        getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);

        dbHelper = new DBHelper(context,"RecordDataBase.db",null,2);
        MemberList = dbHelper.getResultMemberData();

        LoginButton = (Button)findViewById(R.id.loginButton);
        RegisterButton = (Button)findViewById(R.id.registerButton);
        EditID = (EditText)findViewById(R.id.login_idText);
        EditPassword = (EditText)findViewById(R.id.login_passwordText);

        LoginButton.setOnClickListener(this);
        RegisterButton.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.loginButton:

                boolean idCheck = false;

                Log.d("회원정보 : ","입력"+" : "+EditID.getText().toString() + " : " + EditPassword.getText().toString());
                for(int i=0; i<MemberList.size(); i++){

                    idCheck = false;

                    Log.d("회원정보 : ","멤버리스트"+" : "+MemberList.get(i).getName() + " : "+MemberList.get(i).getPassword());
                    if(EditID.getText().toString().equals(MemberList.get(i).getName())){
                        idCheck = true;
                        if(!EditPassword.getText().toString().equals(MemberList.get(i).getPassword())) {

                            AlertDialog.Builder builder = new AlertDialog.Builder(context);
                            builder.setTitle("해당 비밀번호가 일치하지 않습니다");
                            builder.setMessage("비밀번호를 다시 입력해주세요");
                            builder.setCancelable(true);
                            builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.cancel();
                                }
                            });
                            builder.create().show();
                            break;
                        }else{
                            dismiss();
                            break;
                        }
                    }
                }

                if(!idCheck) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setTitle("해당 아이디가 존재하지 않습니다");
                    builder.setMessage("아이디를 올바르게 입력해주세요");
                    builder.setCancelable(true);
                    builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.cancel();
                        }
                    });
                    builder.create().show();
                }

                break;
            case R.id.registerButton:
                DialogRegister dialogRegister = new DialogRegister(context);
                dialogRegister.setDialogListener(new MyDialogListener() {
                    @Override
                    public void onPositiveClicked(String input) {

                    }

                    @Override
                    public void onNegativeClicked() {
                        MemberList = new ArrayList<DataMember>();
                        MemberList = dbHelper.getResultMemberData();
                    }
                });
                dialogRegister.setCanceledOnTouchOutside(false);
                dialogRegister.getWindow().setGravity(Gravity.TOP);
                dialogRegister.setCancelable(false);
                dialogRegister.show();
                break;
        }

    }
}
