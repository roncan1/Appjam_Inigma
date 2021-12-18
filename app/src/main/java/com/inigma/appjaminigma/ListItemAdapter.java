package com.inigma.appjaminigma;

import android.app.LauncherActivity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ListItemAdapter extends BaseAdapter {
    ArrayList<DataModel> items = new ArrayList<DataModel>();
    Context context;

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int i) {
        return items.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        context = viewGroup.getContext();
        DataModel listItem = items.get(i);

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.listview_custum, viewGroup, false);
        }

        TextView TV_title = view.findViewById(R.id.TV_title);
        TextView TV_Explain = view.findViewById(R.id.TV_explain);

        TV_title.setText(listItem.getTitleName());
        TV_Explain.setText(listItem.getExplain());

        return view;
    }

    public void addItem(DataModel item) {
        items.add(item);
    }
}
