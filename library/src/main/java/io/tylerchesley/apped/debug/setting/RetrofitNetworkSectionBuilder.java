package io.tylerchesley.apped.debug.setting;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import io.tylerchesley.apped.R;
import io.tylerchesley.apped.debug.action.Action;
import io.tylerchesley.apped.debug.action.RestartApplicationAction;
import io.tylerchesley.apped.debug.pref.StringPreference;

public class RetrofitNetworkSectionBuilder extends SectionBuilder {

    public static RetrofitNetworkSectionBuilder from(Context context) {
        return from(context, PreferenceManager.getDefaultSharedPreferences(context));
    }

    public static RetrofitNetworkSectionBuilder from(Context context, SharedPreferences preferences) {
        return new RetrofitNetworkSectionBuilder(context, preferences);
    }

    private final SharedPreferences preferences;

    public RetrofitNetworkSectionBuilder(Context context, SharedPreferences preferences) {
        super(context);
        this.preferences = preferences;
        title(R.string.debug_network_header);
    }

    public <T extends Enum> RetrofitNetworkSectionBuilder endpoints(Class<T> enumClass,
                                                                    String key) {
        endpoints(enumClass, new StringPreference(preferences, key));
        return this;
    }

    public <T extends Enum<T>> RetrofitNetworkSectionBuilder endpoints(Class<T> enumClass,
                                                                    StringPreference preference) {
        final Action restartAction = new RestartApplicationAction(context.getApplicationContext());
        add(new EnumSetting<T>(context.getString(R.string.debug_network_endpoint), enumClass,
                preference,
                new EnumSetting.Callback<T>() {
                    @Override
                    public boolean onItemSelected(EnumSetting<T> setting, T item) {
                        restartAction.execute();
                        return false;
                    }
                }));
        return this;
    }

}
