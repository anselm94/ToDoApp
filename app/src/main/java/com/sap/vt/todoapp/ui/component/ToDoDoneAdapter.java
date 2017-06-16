package com.sap.vt.todoapp.ui.component;

import android.content.Context;
import android.graphics.Paint;
import android.widget.TextView;

import com.sap.vt.todoapp.domain.ToDoNote;

import java.util.ArrayList;

/**
 * Created by I327891 on 14-Jun-17.
 */

public class ToDoDoneAdapter extends ToDoNotDoneAdapter {

    public ToDoDoneAdapter(Context context, ArrayList<ToDoNote> items) {
        super(context, items);
    }

    @Override
    public void customPaint(TextView titleTextView, TextView descTextView) {
        super.customPaint(titleTextView, descTextView);
        titleTextView.setPaintFlags(titleTextView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        descTextView.setPaintFlags(descTextView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
    }
}
