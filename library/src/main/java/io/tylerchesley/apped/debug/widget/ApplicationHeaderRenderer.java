package io.tylerchesley.apped.debug.widget;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import io.tylerchesley.apped.R;
import io.tylerchesley.apped.debug.setting.ApplicationHeader;
import io.tylerchesley.rendered.renderer.Renderer;

public class ApplicationHeaderRenderer extends Renderer<ApplicationHeader> {

    private final TextView title;
    private final TextView subtitle;
    private final ImageView icon;

    public ApplicationHeaderRenderer(View itemView) {
        super(itemView);
        title = (TextView) itemView.findViewById(R.id.title);
        subtitle = (TextView) itemView.findViewById(R.id.subtitle);
        icon = (ImageView) itemView.findViewById(R.id.icon);
    }

    @Override
    public void bindView(ApplicationHeader applicationHeader) {
        title.setText(applicationHeader.getTitle());
        subtitle.setText(applicationHeader.getSubtitle());
        icon.setImageDrawable(applicationHeader.getIcon());
    }

}
