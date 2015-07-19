package io.tylerchesley.apped.debug.widget;

import android.view.View;
import android.widget.TextView;

import io.tylerchesley.apped.R;
import io.tylerchesley.apped.debug.setting.Setting;
import io.tylerchesley.rendered.renderer.Renderer;

public class SettingRenderer<S extends Setting> extends Renderer<S> {

    private final TextView title;

    public SettingRenderer(View itemView) {
        super(itemView);
        title = (TextView) itemView.findViewById(R.id.title);
    }

    @Override
    public void bindView(S s) {
        title.setText(s.getTitle());
    }

}
