package io.tylerchesley.apped.debug.widget;

import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;

import io.tylerchesley.apped.R;
import io.tylerchesley.apped.debug.setting.BooleanSetting;

public class BooleanSettingRenderer extends SettingRenderer<BooleanSetting>
        implements CompoundButton.OnCheckedChangeListener {

    private final Switch switchView;

    public BooleanSettingRenderer(View itemView) {
        super(itemView);
        switchView = (Switch) itemView.findViewById(R.id.switch_view);
        switchView.setOnCheckedChangeListener(this);
    }

    @Override
    public void bindView(BooleanSetting setting) {
        super.bindView(setting);
        switchView.setChecked(setting.get());
        switchView.setTag(setting);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        final BooleanSetting setting = (BooleanSetting) buttonView.getTag();
        setting.set(isChecked);
    }

}
