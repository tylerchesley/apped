package io.tylerchesley.apped.debug.setting;

import io.tylerchesley.apped.debug.pref.BooleanPreference;

public class BooleanSetting extends Setting {

    private final BooleanPreference preference;

    public BooleanSetting(String title, BooleanPreference preference) {
        super(title);
        this.preference = preference;
    }

    public boolean get() {
        return preference.get();
    }

    public void set(boolean value) {
        preference.set(value);
    }

}
