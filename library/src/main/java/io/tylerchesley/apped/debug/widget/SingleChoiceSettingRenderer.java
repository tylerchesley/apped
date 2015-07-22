package io.tylerchesley.apped.debug.widget;

import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import io.tylerchesley.apped.R;
import io.tylerchesley.apped.debug.setting.SingleChoiceSetting;
import io.tylerchesley.apped.debug.widget.adapter.SingleChoiceAdapter;

public class SingleChoiceSettingRenderer extends SettingRenderer<SingleChoiceSetting> {

    private final Spinner spinner;

    public SingleChoiceSettingRenderer(View itemView) {
        super(itemView);
        spinner = (Spinner) itemView.findViewById(R.id.spinner);
    }

    @Override
    public void bindView(final SingleChoiceSetting setting) {
        super.bindView(setting);
        // TODO: consider cashing adapters
        final SingleChoiceAdapter adapter = new SingleChoiceAdapter(spinner.getContext(), setting);
        spinner.setAdapter(adapter);
        spinner.setSelection(setting.getSelectedItem());
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // TODO: consider another way to do this besides using a final argument
                setting.setSelectedItem(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

}
