package io.tylerchesley.apped.debug.widget;

import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import io.tylerchesley.apped.R;
import io.tylerchesley.apped.debug.setting.EnumSetting;
import io.tylerchesley.apped.debug.widget.adapter.EnumAdapter;

public class EnumSettingRenderer<T extends Enum<T>> extends SettingRenderer<EnumSetting<T>>
        implements AdapterView.OnItemSelectedListener {

    private final Spinner spinner;

    private EnumSetting<T> setting;

    public EnumSettingRenderer(View itemView) {
        super(itemView);
        spinner = (Spinner) itemView.findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);
    }

    @Override
    public void bindView(EnumSetting<T> setting) {
        super.bindView(setting);
        this.setting = setting;
        final EnumAdapter<T> adapter = new EnumAdapter<>(spinner.getContext(), setting.getItems());
        final T selectedItem = setting.getSelectedItem();
        spinner.setAdapter(adapter);
        spinner.setSelection(selectedItem != null ?
                selectedItem.ordinal() : Spinner.INVALID_POSITION);
        spinner.setTag(setting);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // TODO: find better way to get references
        final EnumSetting<T> setting = (EnumSetting<T>) parent.getTag();
        final EnumAdapter<T> adapter = (EnumAdapter<T>) parent.getAdapter();
        setting.setSelectedItem(adapter.getItem(position));
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

}
