package io.tylerchesley.apped.debug.setting;

import android.content.Context;

public class SettingBuilder<S extends Setting> {

    private final Context context;

    String title;

    public SettingBuilder(Context context) {
        this.context = context;
    }

    public SettingBuilder<S> title(String title) {
        this.title = title;
        return this;
    }

}
