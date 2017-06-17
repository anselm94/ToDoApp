package com.sap.vt.todoapp.ui.fragment;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.sap.vt.todoapp.R;
import com.sap.vt.todoapp.ui.activity.MainActivity;

/**
 * Created by I327891 on 17-Jun-17.
 */

public class NewToDoNoteDialog extends DialogFragment implements View.OnClickListener {
    private EditText titleEditText;
    private EditText descEditText;
    private Button okButton;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_new_todo, container);
        titleEditText = view.findViewById(R.id.title_dialog_newnote);
        descEditText = view.findViewById(R.id.desc_dialog_newnote);
        okButton = view.findViewById(R.id.button_dialog_newnote);

        okButton.setOnClickListener(this);
        titleEditText.requestFocus();

        getDialog().setTitle("New To-Do Note");
        return view;
    }

    @Override
    public void onClick(View view) {
        if (view == okButton) {
            MainActivity mainActivity = (MainActivity) getActivity();
            mainActivity.createToDoNote(titleEditText.getText().toString(), descEditText.getText().toString());
            this.dismiss();
        }
    }
}
