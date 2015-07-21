package io.tylerchesley.apped.debug.setting;

import io.tylerchesley.apped.debug.pref.StringPreference;

public class EnumSetting<T extends Enum<T>> extends Setting {

    public interface Callback<T extends Enum<T>> {

        boolean onItemSelected(EnumSetting<T> setting, T item);

    }

    public final T[] values;
    private final StringPreference preference;
    private final Callback<T> callback;

    public EnumSetting(String title, Class<T> enumClass, StringPreference preference) {
        this(title, enumClass, preference, null);
    }

    public EnumSetting(String title, Class<T> enumClass, StringPreference preference,
                       Callback<T> callback) {
        super(title);
        this.values = enumClass.getEnumConstants();
        this.preference = preference;
        this.callback = callback;
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
        // TODO: optimize this check
        if (selectedItem == getSelectedItem()) {
            return;
        }

        if (callback != null && callback.onItemSelected(this, selectedItem)) {
            return;
        }
        preference.set(selectedItem.name());
    }

}
