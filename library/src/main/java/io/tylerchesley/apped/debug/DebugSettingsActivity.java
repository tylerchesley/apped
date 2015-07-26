package io.tylerchesley.apped.debug;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.List;

import io.tylerchesley.apped.R;
import io.tylerchesley.apped.debug.setting.Setting;

public abstract class DebugSettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_debug_settings);

        final DebugSettingsView debugSettingsView =
                (DebugSettingsView) findViewById(R.id.debug_settings);
        debugSettingsView.initialize(getSettings());
    }

    protected abstract List<Setting> getSettings();

}
