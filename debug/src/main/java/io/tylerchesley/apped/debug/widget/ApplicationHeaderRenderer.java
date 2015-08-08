package io.tylerchesley.apped.debug.widget;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import io.tylerchesley.apped.debug.R;
import io.tylerchesley.apped.debug.setting.ApplicationHeader;

public class ApplicationHeaderRenderer extends SettingRenderer<ApplicationHeader> {

    private final TextView subtitle;
    private final ImageView icon;

    public ApplicationHeaderRenderer(View itemView) {
        super(itemView);
        subtitle = (TextView) itemView.findViewById(R.id.subtitle);
        icon = (ImageView) itemView.findViewById(R.id.icon);
    }

    @Override
    public void bindView(ApplicationHeader header) {
        super.bindView(header);
        subtitle.setText(header.getSubtitle());
        icon.setImageDrawable(header.getIcon());
    }

}
