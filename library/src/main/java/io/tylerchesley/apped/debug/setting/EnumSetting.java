package io.tylerchesley.apped.debug.setting;

import io.tylerchesley.apped.debug.pref.StringPreference;

public class EnumSetting<T extends Enum<T>> extends Setting {

    public final T[] values;
    private final StringPreference preference;

    public EnumSetting(String title, Class<T> enumClass, StringPreference preference) {
        super(title);
        this.values = enumClass.getEnumConstants();
        this.preference = preference;
    }

    public T[] getItems() {
        return values;
    }

    public T getSelectedItem() {
        final String value = preference.get();
        for (int i = 0; i < values.length; i++) {
            if (values[i].name().equals(value)) {
                return values[i];
            }
        }
        return null;
    }

    public void setSelectedItem(T selectedItem) {
        preference.set(selectedItem.name());
    }

}
