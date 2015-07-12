package io.tylerchesley.apped.debug.widget;

import java.util.ArrayList;
import java.util.List;

import io.tylerchesley.apped.debug.setting.HasChildren;
import io.tylerchesley.apped.debug.setting.Setting;
import io.tylerchesley.rendered.data.DataProvider;

public class SettingsDataProvider implements DataProvider<Setting> {

    private final List<Setting> settings;

    public SettingsDataProvider(List<Setting> settings) {
        this.settings = new FlatMapper().map(settings);
    }

    @Override
    public int getCount() {
        return settings.size();
    }

    @Override
    public Setting get(int position) {
        return settings.get(position);
    }

    private static final class FlatMapper {

        private final List<Setting> mapped = new ArrayList<>();

        public List<Setting> map(List<? extends Setting> items) {
            for (Setting item : items) {
                add(item);
            }
            return mapped;
        }

        private void add(Setting item) {
            mapped.add(item);
            if (item instanceof HasChildren) {
                final HasChildren parent = (HasChildren) item;
                final int numChildren = parent.numChildren();
                for (int i = 0; i < numChildren; i++) {
                    add(parent.getChild(i));
                }
            }

        }

    }


}
