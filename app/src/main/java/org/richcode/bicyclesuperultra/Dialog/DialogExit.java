package org.richcode.bicyclesuperultra.Dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;



import org.richcode.bicyclesuperultra.R;

public class DialogExit extends Dialog implements View.OnClickListener {

    private MyDialogListener dialogListener;
    private Context context;

    Button ExitButton;
    Button ReturnButton;

    public DialogExit(@NonNull Context context) {
        super(context);
        this.context = context;
    }

    public void setDialogListener(MyDialogListener dialogListener){
        this.dialogListener = dialogListener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exit_dialog);



        ExitButton = (Button)findViewById(R.id.exit_true_button);
        ReturnButton = (Button)findViewById(R.id.exit_false_button);

        ExitButton.setOnClickListener(this);
        ReturnButton.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.exit_true_button:
                dialogListener.onPositiveClicked("EXIT");
                dismiss();
                break;
            case R.id.exit_false_button:
                dismiss();
                break;
        }
    }
}
