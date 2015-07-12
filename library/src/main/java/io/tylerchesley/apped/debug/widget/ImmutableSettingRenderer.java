package io.tylerchesley.apped.debug.widget;

import android.view.View;
import android.widget.TextView;

import io.tylerchesley.apped.R;
import io.tylerchesley.apped.debug.setting.ImmuttableSetting;
import io.tylerchesley.rendered.renderer.Renderer;

public class ImmutableSettingRenderer extends Renderer<ImmuttableSetting> {

    private final TextView title;
    private final TextView value;

    public ImmutableSettingRenderer(View itemView) {
        super(itemView);
        title = (TextView) itemView.findViewById(R.id.title);
        value = (TextView) itemView.findViewById(R.id.value);
    }

    @Override
    public void bindView(ImmuttableSetting immuttableSetting) {
        title.setText(immuttableSetting.getTitle());
        value.setText(immuttableSetting.getValue());
    }

}
