package io.tylerchesley.apped.debug.setting;

import android.content.Context;
import android.preference.PreferenceManager;
import android.support.annotation.StringRes;

import io.tylerchesley.apped.debug.pref.StringPreference;

public class SingleChoiceSetting<C> extends Setting {

    private final ChoiceProvider<C> provider;
    private final ChoicePersister<C> persister;
    private final ChoiceFormatter<C> formatter;
    private final OnChangeListener<C> listener;
    private final C defaultValue;

    public SingleChoiceSetting(String title, ChoiceProvider<C> provider, ChoicePersister<C> persister,
                               ChoiceFormatter<C> formatter, OnChangeListener<C> listener, C defaultValue) {
        super(title);
        this.provider = provider;
        this.persister = persister;
        this.formatter = formatter;
        this.listener = listener;
        this.defaultValue = defaultValue;
    }

    public static <C extends Enum<C>> SingleChoiceSetting from(Context context, Class<C> enumClass) {
        return build(context, enumClass).build();
    }

    public static <C extends Enum<C>> SingleChoiceSetting.Builder<C> build(Context context,
                                                                           Class<C> enumClass) {
        final C[] values = enumClass.getEnumConstants();
        final String name = enumClass.getSimpleName();
        final Builder<C> builder = new Builder<>(context);
        builder.provider(new ArrayChoiceProvider<>(values))
                .persister(new EnumChoicePersister<>(values,
                        new StringPreference(PreferenceManager.getDefaultSharedPreferences(context), name)))
                .formatter(new EnumChoiceFormatter<C>());
        return builder;
    }

    public static <C> SingleChoiceSetting.Builder<C> build(Context context, C[] choices) {
        final Builder<C> builder = new Builder<>(context);
        builder.provider(new ArrayChoiceProvider<>(choices));
        return builder;
    }

    public int getCount() {
        return provider.getCount();
    }

    public String getLabelFor(int position) {
        return formatter.format(provider.getItem(position));
    }

    public int getSelectedPosition() {
        final C selectedItem = persister.getSelectedItem();
        return provider.positionOf(selectedItem != null ? selectedItem : defaultValue);
    }

    public void setSelectedPosition(int position) {
        final C item = provider.getItem(position);
        if (item == persister.getSelectedItem() ||
                (listener != null && listener.onItemSelected(this, item))) {
            return;
        }

        persister.setSelectedItem(item);
    }

    public interface OnChangeListener<C> {

        boolean onItemSelected(SingleChoiceSetting<C> setting, C item);

    }

    public final static class Builder<C> {

        private final Context context;
        private String title;
        private ChoiceProvider<C> provider;
        private ChoicePersister<C> persister;
        private ChoiceFormatter<C> formatter;
        private OnChangeListener<C> listener;
        private C defaultValue = null;

        public Builder(Context context) {
            this.context = context;
        }

        public Builder<C> title(@StringRes int titleRes) {
            title(context.getString(titleRes));
            return this;
        }

        public Builder<C> title(String title) {
            this.title = title;
            return this;
        }

        public Builder<C> provider(ChoiceProvider<C> provider) {
            this.provider = provider;
            return this;
        }

        public Builder<C> persister(ChoicePersister<C> persister) {
            this.persister = persister;
            return this;
        }

        public Builder<C> formatter(ChoiceFormatter<C> formatter) {
            this.formatter = formatter;
            return this;
        }

        public Builder<C> listener(OnChangeListener<C> listener) {
            this.listener = listener;
            return this;
        }

        public Builder<C> defaultValue(C defaultValue) {
            this.defaultValue = defaultValue;
            return this;
        }

        public SingleChoiceSetting<C> build() {
            if (provider == null) {
                throw new NullPointerException("choice provider may not be null");
            }

            if (persister == null) {
                throw new NullPointerException("choice persister may not be null");
            }

            if (formatter == null) {
                formatter = new ChoiceFormatter<C>() {
                    @Override
                    public String format(C item) {
                        return item.toString();
                    }
                };
            }
            return new SingleChoiceSetting<>(title, provider, persister, formatter, listener,
                    defaultValue);
        }

    }

}
