package io.tylerchesley.apped.debug.setting;

import io.tylerchesley.apped.debug.pref.StringPreference;

public class EnumChoicePersister<T extends Enum<T>> implements ChoicePersister<T> {

    private final T[] values;
    private final StringPreference preference;

    public EnumChoicePersister(T[] values, StringPreference preference) {
        this.values = values;
        this.preference = preference;
    }

    @Override
    public T getSelectedItem() {
        final String value = preference.get();
        for (int i = 0; i < values.length; i++) {
            if (values[i].name().equals(value)) {
                return values[i];
            }
        }
        return null;
    }

    @Override
    public void setSelectedItem(T item) {
        preference.set(item.name());
    }

}
