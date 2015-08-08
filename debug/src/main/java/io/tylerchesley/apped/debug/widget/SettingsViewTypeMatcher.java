package io.tylerchesley.apped.debug.widget;

import android.util.Log;

import java.util.HashMap;
import java.util.Map;

import io.tylerchesley.apped.debug.R;
import io.tylerchesley.apped.debug.setting.ApplicationHeader;
import io.tylerchesley.apped.debug.setting.BooleanSetting;
import io.tylerchesley.apped.debug.setting.ImmuttableSetting;
import io.tylerchesley.apped.debug.setting.Section;
import io.tylerchesley.apped.debug.setting.Setting;
import io.tylerchesley.apped.debug.setting.SingleChoiceSetting;
import io.tylerchesley.rendered.matcher.ViewTypeMatcher;

public class SettingsViewTypeMatcher implements ViewTypeMatcher<Setting> {

    private static final Map<Class<? extends Setting>, Integer> DEFAULT_VIEW_TYPE_MAPPINGS;

    static {
        DEFAULT_VIEW_TYPE_MAPPINGS = new HashMap<>();
        DEFAULT_VIEW_TYPE_MAPPINGS.put(ApplicationHeader.class,
                R.layout.list_item_application_header);
        DEFAULT_VIEW_TYPE_MAPPINGS.put(Section.class,
                R.layout.list_item_section);
        DEFAULT_VIEW_TYPE_MAPPINGS.put(ImmuttableSetting.class,
                R.layout.list_item_immutable_setting);
        DEFAULT_VIEW_TYPE_MAPPINGS.put(BooleanSetting.class,
                R.layout.list_item_boolean_setting);
        DEFAULT_VIEW_TYPE_MAPPINGS.put(SingleChoiceSetting.class,
                R.layout.list_item_single_choice_setting);
    }

    private final Map<Class<? extends Setting>, Integer> map;

    public SettingsViewTypeMatcher() {
        this(DEFAULT_VIEW_TYPE_MAPPINGS);
    }

    public SettingsViewTypeMatcher(Map<Class<? extends Setting>, Integer> map) {
        this.map = map;
    }

    @Override
    public int match(Setting setting) {
        final int match = map.get(setting.getClass());
        Log.d("XXXX", "match " + match);
        return match;
    }

}
