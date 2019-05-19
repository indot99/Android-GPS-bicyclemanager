package org.richcode.bicyclesuperultra.Dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import org.richcode.bicyclesuperultra.DataBase.DBHelper;
import org.richcode.bicyclesuperultra.DataClass.DataMember;
import org.richcode.bicyclesuperultra.R;

import java.util.ArrayList;

public class DialogRegister extends Dialog implements View.OnClickListener {

    private Context context;
    private MyDialogListener dialogListener;

    Button OkButton;
    Button CancelButton;
    EditText IdText;
    EditText PasswordText;
    EditText PasswordCheckText;

    DBHelper dbHelper;

    ArrayList<DataMember> MemberList = new ArrayList<DataMember>();

    public DialogRegister(@NonNull Context context) {
        super(context);
        this.context = context;
    }

    public void setDialogListener(MyDialogListener dialogListener){
        this.dialogListener = dialogListener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_register);
        getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);

        dbHelper = new DBHelper(context,"RecordDataBase.db",null,2);
        MemberList = dbHelper.getResultMemberData();

        OkButton = (Button)findViewById(R.id.checkButton);
        CancelButton = (Button)findViewById(R.id.backButton);
        IdText = (EditText)findViewById(R.id.register_idText);
        PasswordText = (EditText)findViewById(R.id.register_passwordText);
        PasswordCheckText = (EditText)findViewById(R.id.register_passwordcheckText);

        OkButton.setOnClickListener(this);
        CancelButton.setOnClickListener(this);

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.backButton:
                dismiss();
                break;
            case R.id.checkButton:

                boolean idCheck = false;

                for(int i = 0; i<MemberList.size(); i++){
                    idCheck = false;
                    if(IdText.getText().toString().equals(MemberList.get(i).getName())){
                        AlertDialog.Builder builder = new AlertDialog.Builder(context);
                        builder.setTitle("이미 존재하는 아이디 입니다");
                        builder.setMessage("다른 아이디를 입력해주세요");
                        builder.setCancelable(true);
                        builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                                return;
                            }
                        });
                        builder.create().show();
                        idCheck = true;
                        break;
                    }
                }

                if(!idCheck){
                    dbHelper.MemberInsert(IdText.getText().toString(),PasswordText.getText().toString());
                    dialogListener.onNegativeClicked();
                    dismiss();
                }
                break;
        }
    }
}
