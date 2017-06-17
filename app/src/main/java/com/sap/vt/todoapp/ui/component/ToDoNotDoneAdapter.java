package com.sap.vt.todoapp.ui.component;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.sap.vt.todoapp.R;
import com.sap.vt.todoapp.domain.ToDoNote;

import java.util.ArrayList;

/**
 * Created by I327891 on 14-Jun-17.
 */

public class ToDoNotDoneAdapter extends BaseAdapter {
    protected Context mContext;
    protected LayoutInflater mInflater;
    protected ArrayList<ToDoNote> mDataSource;
    protected IToDoItemListener mListener;

    public ToDoNotDoneAdapter(Context context, ArrayList<ToDoNote> items) {
        this.mContext = context;
        this.mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (items != null) {
            this.mDataSource = items;
        } else {
            this.mDataSource = new ArrayList<>();
        }
    }

    public void setToDoItemListener(IToDoItemListener listener) {
        this.mListener = listener;
    }

    public void setData(ArrayList<ToDoNote> items) {
        this.mDataSource = items;
    }

    @Override
    public int getCount() {
        return mDataSource.size();
    }

    @Override
    public Object getItem(int position) {
        return mDataSource.get(position);
    }

    @Override
    public long getItemId(int position) {
        return this.mDataSource.get(position).getId();
    }

    public void customPaint(TextView titleTextView, TextView descTextView) {
        //Do nothing
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // Get view for row item
        View rowView = mInflater.inflate(R.layout.list_item_todo, parent, false);

        TextView titleTextView = rowView.findViewById(R.id.done_title);
        TextView descTextView = rowView.findViewById(R.id.done_description);
        CheckBox doneCheckBox = rowView.findViewById(R.id.done_checkbox);
        Button closeButton = rowView.findViewById(R.id.done_close);

        titleTextView.setText(mDataSource.get(position).getTitle());
        descTextView.setText(mDataSource.get(position).getDescription());
        doneCheckBox.setChecked(mDataSource.get(position).isDone());

        this.customPaint(titleTextView, descTextView);

        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mListener != null) {
                    mListener.onToDoItemCloseClicked(mDataSource.get(position));
                }
            }
        });
        doneCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                mDataSource.get(position).setDone(b);
                if (mListener != null) {
                    mListener.onToDoItemCheckedClicked(mDataSource.get(position));
                }
            }
        });

        return rowView;
    }
}
