package io.tylerchesley.apped.debug.setting;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

public class SectionBuilder {

    public static SectionBuilder from(Context context) {
        return new SectionBuilder(context);
    }

    protected final Context context;
    protected final List<Setting> children;
    protected String title;

    public SectionBuilder(Context context) {
        this.context = context;
        this.children = new ArrayList<>();
    }

    public SectionBuilder title(int titleRes) {
        title(context.getString(titleRes));
        return this;
    }

    public SectionBuilder title(String title) {
        this.title = title;
        return this;
    }

    public SectionBuilder add(int titleRes, String value) {
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

    public Section build() {
        return new Section(title, children);
    }

}
