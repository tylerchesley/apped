package io.tylerchesley.apped.samples;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import io.tylerchesley.apped.ActivityViewDelegate;
import io.tylerchesley.apped.Apped;
import io.tylerchesley.apped.Title;
import io.tylerchesley.apped.debug.DebugDrawerActivityDelegate;
import io.tylerchesley.apped.debug.setting.ApplicationHeader;
import io.tylerchesley.apped.debug.setting.BuildInfoSectionBuilder;
import io.tylerchesley.apped.debug.setting.DeviceInfoSectionBuilder;
import io.tylerchesley.apped.debug.setting.RetrofitNetworkSectionBuilder;
import io.tylerchesley.apped.debug.setting.Setting;
import retrofit.MockRestAdapter;
import retrofit.RestAdapter;

@Title(R.string.app_name)
public class SamplesActivity extends AppCompatActivity {

    enum Endpoint {

        PRODUCTION,

        BETA,

        TEST,

        MOCK

    }

    private ActivityViewDelegate delegate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final List<Setting> settings = new ArrayList<>();
        settings.add(ApplicationHeader.from(this));
        settings.add(BuildInfoSectionBuilder.from(this).build());
        settings.add(DeviceInfoSectionBuilder.from(this).build());
        final MockRestAdapter adapter = MockRestAdapter.from(new RestAdapter.Builder().setEndpoint("http://www.test.com").build());
        settings.add(RetrofitNetworkSectionBuilder
                .from(this).endpoints(Endpoint.class, "endpoint")
                .mock(adapter)
                .build());
        delegate = new DebugDrawerActivityDelegate(settings);

        Apped.configure(this);
        delegate.setContentView(this, R.layout.activity_samples);
    }

}
