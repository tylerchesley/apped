package io.tylerchesley.apped.samples;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import io.tylerchesley.apped.debug.setting.ApplicationHeader;
import io.tylerchesley.apped.debug.setting.BuildInfoSectionBuilder;
import io.tylerchesley.apped.debug.setting.DeviceInfoSectionBuilder;
import io.tylerchesley.apped.debug.setting.RetrofitNetworkSectionBuilder;
import io.tylerchesley.apped.debug.setting.Setting;
import retrofit.MockRestAdapter;
import retrofit.RestAdapter;

public class DebugSettingsProvider {

    private final List<Setting> settings;

    public DebugSettingsProvider(Context context) {
        settings = new ArrayList<>();
        settings.add(ApplicationHeader.from(context));
        settings.add(BuildInfoSectionBuilder.from(context).build());
        settings.add(DeviceInfoSectionBuilder.from(context).build());
        final RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint("http://www.test.com").build();
        final MockRestAdapter mockRestAdapter = MockRestAdapter.from(adapter);
        settings.add(RetrofitNetworkSectionBuilder
                .from(context)
                .endpoints(Endpoint.class)
                .mock(mockRestAdapter)
                .logLevel(adapter)
                .build());
    }

    public static DebugSettingsProvider get(Context context) {
        return new DebugSettingsProvider(context);
    }

    public List<Setting> getSettings() {
        return settings;
    }

}
