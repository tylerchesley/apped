package io.tylerchesley.apped.debug.widget;

import android.view.View;
import android.widget.TextView;

import io.tylerchesley.apped.debug.R;
import io.tylerchesley.apped.debug.setting.ImmuttableSetting;

public class ImmutableSettingRenderer extends SettingRenderer<ImmuttableSetting> {

    private final TextView value;

    public ImmutableSettingRenderer(View itemView) {
        super(itemView);
        value = (TextView) itemView.findViewById(R.id.value);
    }

    @Override
    public void bindView(ImmuttableSetting setting) {
        super.bindView(setting);
        value.setText(setting.getValue());
    }

}
