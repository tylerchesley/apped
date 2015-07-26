package io.tylerchesley.apped.debug;

import android.app.Activity;
import android.support.annotation.LayoutRes;

import java.util.List;

import io.tylerchesley.apped.DrawerActivityViewDelegate;
import io.tylerchesley.apped.R;
import io.tylerchesley.apped.debug.setting.Setting;

public class DebugDrawerActivityDelegate extends DrawerActivityViewDelegate {

    private final List<Setting> settings;

    public DebugDrawerActivityDelegate(List<Setting> settings) {
        super(R.layout.debug_activity_container_drawer, R.id.drawer, R.id.debug_content);
        this.settings = settings;
    }

    @Override
    public void setContentView(Activity activity, @LayoutRes int layoutResource) {
        super.setContentView(activity, layoutResource);

        final DebugSettingsView debugSettingsView = (DebugSettingsView)
                activity.findViewById(R.id.debug_settings);
        if (debugSettingsView == null) {
            throw new NullPointerException("DebugSettingsView not found");
        }

        debugSettingsView.initialize(settings);
    }

}
