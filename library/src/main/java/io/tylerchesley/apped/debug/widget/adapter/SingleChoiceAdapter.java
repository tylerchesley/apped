package io.tylerchesley.apped.debug.widget.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import io.tylerchesley.apped.debug.setting.SingleChoiceSetting;
import io.tylerchesley.apped.debug.widget.adapter.BindableAdapter;

public class SingleChoiceAdapter extends BindableAdapter<String> {

    private final SingleChoiceSetting setting;

    public SingleChoiceAdapter(Context context, SingleChoiceSetting setting) {
        super(context);
        this.setting = setting;
    }

    @Override
    public String getItem(int position) {
        return setting.getLabel(position);
    }

    @Override
    public View newView(LayoutInflater inflater, int position, ViewGroup container) {
        return inflater.inflate(android.R.layout.simple_spinner_item, container, false);
    }

    @Override
    public final void bindView(String item, int position, View view) {
        TextView tv = (TextView) view;
        tv.setText(item);
    }

    @Override
    public final View newDropDownView(LayoutInflater inflater, int position, ViewGroup container) {
        return inflater.inflate(android.R.layout.simple_spinner_dropdown_item, container, false);
    }

    @Override
    public int getCount() {
        return setting.getCount();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

}
