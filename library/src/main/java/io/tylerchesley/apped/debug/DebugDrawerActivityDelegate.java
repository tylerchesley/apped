package io.tylerchesley.apped.debug;

import android.app.Activity;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import io.tylerchesley.apped.DrawerActivityViewDelegate;
import io.tylerchesley.apped.R;
import io.tylerchesley.apped.debug.setting.ApplicationHeader;
import io.tylerchesley.apped.debug.setting.BooleanSetting;
import io.tylerchesley.apped.debug.setting.ImmuttableSetting;
import io.tylerchesley.apped.debug.setting.Section;
import io.tylerchesley.apped.debug.setting.Setting;
import io.tylerchesley.apped.debug.setting.SingleChoiceSetting;
import io.tylerchesley.apped.debug.widget.ConditionalDividerDecoration;
import io.tylerchesley.apped.debug.widget.SettingsDataProvider;
import io.tylerchesley.apped.debug.widget.SettingsRendererFactory;
import io.tylerchesley.rendered.RendererAdapter;
import io.tylerchesley.rendered.data.DataProvider;
import io.tylerchesley.rendered.matcher.ClassPropertyGetter;
import io.tylerchesley.rendered.matcher.MapViewTypeMatcher;

public class DebugDrawerActivityDelegate extends DrawerActivityViewDelegate {

    private final List<Setting> settings;

    public DebugDrawerActivityDelegate(List<Setting> settings) {
        super(R.layout.debug_activity_container_drawer, R.id.drawer, R.id.debug_content);
        this.settings = settings;
    }

    @Override
    public void setContentView(Activity activity, @LayoutRes int layoutResource) {
        super.setContentView(activity, layoutResource);

        final DataProvider<Setting> dataProvider = new SettingsDataProvider(settings);
        final RecyclerView recyclerView = (RecyclerView) activity.findViewById(R.id.debug_drawer);
        if (recyclerView == null) {
            throw new NullPointerException("RecyclerView not found");
        }

        final RendererAdapter<Setting> adapter = RendererAdapter
                .from(dataProvider)
                .factory(new SettingsRendererFactory<>())
                .matcher(MapViewTypeMatcher
                        .from(new ClassPropertyGetter<Setting>())
                        .add(R.layout.list_item_application_header, ApplicationHeader.class)
                        .add(R.layout.list_item_section, Section.class)
                        .add(R.layout.list_item_immutable_setting, ImmuttableSetting.class)
                        .add(R.layout.list_item_boolean_setting, BooleanSetting.class)
                        // TODO: add ability to check if subclass
                        .add(R.layout.list_item_single_choice_setting, SingleChoiceSetting.class)
                        .build())
                .build();

        final ConditionalDividerDecoration.Condition condition = new ConditionalDividerDecoration.Condition() {
            @Override
            public boolean shouldDraw(int position) {
                final Setting setting = dataProvider.get(position);
                if (setting instanceof ApplicationHeader) {
                    return true;
                }

                final int nextPosition = position + 1;
                final Setting nextSetting = dataProvider.getCount() > nextPosition
                        ? dataProvider.get(nextPosition) : null;
                return nextSetting instanceof Section;
            }
        };

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(activity));
        recyclerView.addItemDecoration(new ConditionalDividerDecoration(activity, condition,
                ConditionalDividerDecoration.VERTICAL_LIST));
        recyclerView.setAdapter(adapter);

    }

}
