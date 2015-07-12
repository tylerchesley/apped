package io.tylerchesley.apped.debug.widget;

import android.view.View;
import android.widget.TextView;

import io.tylerchesley.apped.debug.setting.Section;
import io.tylerchesley.rendered.renderer.Renderer;

public class SectionRenderer extends Renderer<Section> {

    private final TextView title;

    public SectionRenderer(View itemView) {
        super(itemView);
        title = (TextView) itemView;
    }

    @Override
    public void bindView(Section section) {
        title.setText(section.getTitle());
    }

}
