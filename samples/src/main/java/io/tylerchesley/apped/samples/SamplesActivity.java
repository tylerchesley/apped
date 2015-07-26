package io.tylerchesley.apped.samples;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import io.tylerchesley.apped.ActivityViewDelegate;
import io.tylerchesley.apped.Apped;
import io.tylerchesley.apped.Title;
import io.tylerchesley.apped.debug.DebugDrawerActivityDelegate;

@Title(R.string.app_name)
public class SamplesActivity extends AppCompatActivity {

    private ActivityViewDelegate delegate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        delegate = new DebugDrawerActivityDelegate(DebugSettingsProvider.get(this).getSettings());

        Apped.configure(this);
        delegate.setContentView(this, R.layout.activity_samples);
    }

}
