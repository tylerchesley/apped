package io.tylerchesley.apped.samples;

import java.util.List;

import io.tylerchesley.apped.debug.DebugSettingsActivity;
import io.tylerchesley.apped.debug.setting.Setting;

public class SamplesDebugSettingsActivity extends DebugSettingsActivity {

    @Override
    protected List<Setting> getSettings() {
        return DebugSettingsProvider.get(this).getSettings();
    }

}
