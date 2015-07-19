package io.tylerchesley.apped.debug.setting;

import android.content.Context;
import android.support.annotation.StringRes;

import java.util.ArrayList;
import java.util.List;

import io.tylerchesley.apped.debug.pref.BooleanPreference;
import io.tylerchesley.apped.debug.pref.StringPreference;

public class SectionBuilder {

    protected final Context context;
    protected final List<Setting> children;
    protected String title;
    public SectionBuilder(Context context) {
        this.context = context;
        this.children = new ArrayList<>();
    }

    public static SectionBuilder from(Context context) {
        return new SectionBuilder(context);
    }

    public SectionBuilder title(@StringRes int titleRes) {
        title(context.getString(titleRes));
        return this;
    }

    public SectionBuilder title(String title) {
        this.title = title;
        return this;
    }

    public SectionBuilder add(@StringRes int titleRes, String value) {
        add(context.getString(titleRes), value);
        return this;
    }

    public SectionBuilder add(String title, String value) {
        add(new ImmuttableSetting(title, value));
        return this;
    }

    public SectionBuilder add(Setting child) {
        children.add(child);
        return this;
    }

    public <T extends Enum> SectionBuilder add(@StringRes int titleRes, Class<T> enumClass,
                                               StringPreference preference) {
        add(context.getString(titleRes), enumClass, preference);
        return this;
    }

    public <T extends Enum> SectionBuilder add(String title, Class<T> enumClass,
                                               StringPreference preference) {
        add(new EnumSetting<>(title, enumClass, preference));
        return this;
    }

    public SectionBuilder add(@StringRes int titleRes, BooleanPreference preference) {
        add(context.getString(titleRes), preference);
        return this;
    }

    public SectionBuilder add(String title, BooleanPreference preference) {
        add(new BooleanSetting(title, preference));
        return this;
    }

    public Section build() {
        return new Section(title, children);
    }

}
