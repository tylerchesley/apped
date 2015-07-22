package io.tylerchesley.apped.debug.setting;

import io.tylerchesley.apped.debug.pref.StringPreference;

public class EnumSetting<T extends Enum<T>> extends SingleChoiceSetting {

    public interface Callback<T extends Enum<T>> {

        boolean onItemSelected(EnumSetting<T> setting, T item);

    }

    private static <T extends Enum<T>> T findSelectedItem(T[] values, String value) {
        for (int i = 0; i < values.length; i++) {
            if (values[i].name().equals(value)) {
                return values[i];
            }
        }
        return null;
    }

    public final T[] values;
    private final StringPreference preference;
    private final Callback<T> callback;

    private T selectedItem;

    public EnumSetting(String title, Class<T> enumClass, StringPreference preference) {
        this(title, enumClass, preference, null);
    }

    public EnumSetting(String title, Class<T> enumClass, StringPreference preference,
                       Callback<T> callback) {
        super(title);
        this.values = enumClass.getEnumConstants();
        this.preference = preference;
        this.callback = callback;

        selectedItem = findSelectedItem(values, preference.get());
    }

    @Override
    public int getCount() {
        return values.length;
    }

    @Override
    public int getSelectedItem() {
        return selectedItem != null ? selectedItem.ordinal() : -1;
    }

    @Override
    public void setSelectedItem(int position) {
        if (position == getSelectedItem()) {
            return;
        }

        selectedItem = values[position];
        if (callback != null && callback.onItemSelected(this, selectedItem)) {
            return;
        }
        preference.set(selectedItem.name());
    }

    @Override
    public String getLabel(int position) {
        return values[position].name();
    }

}
