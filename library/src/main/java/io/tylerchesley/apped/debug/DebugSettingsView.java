package io.tylerchesley.apped.debug;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

import java.util.List;

import io.tylerchesley.apped.debug.setting.Setting;
import io.tylerchesley.apped.debug.widget.SettingsDataProvider;
import io.tylerchesley.apped.debug.widget.SettingsRendererFactory;
import io.tylerchesley.apped.debug.widget.SettingsViewTypeMatcher;
import io.tylerchesley.rendered.RendererAdapter;

public class DebugSettingsView extends RecyclerView {

    public DebugSettingsView(Context context) {
        super(context);
    }

    public DebugSettingsView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void initialize(List<Setting> settings) {
        setHasFixedSize(true);
        setLayoutManager(new LinearLayoutManager(getContext()));
        final RendererAdapter<Setting> adapter = RendererAdapter
                .from(new SettingsDataProvider(settings))
                .factory(new SettingsRendererFactory<>())
                .matcher(new SettingsViewTypeMatcher())
                .build();
        setAdapter(adapter);
    }

}
