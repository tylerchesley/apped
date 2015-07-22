package io.tylerchesley.apped.debug.setting;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import io.tylerchesley.apped.R;
import io.tylerchesley.apped.debug.action.Action;
import io.tylerchesley.apped.debug.action.RestartApplicationAction;
import io.tylerchesley.apped.debug.pref.StringPreference;
import retrofit.MockRestAdapter;

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
        add(new EnumSetting<>(context.getString(R.string.debug_network_endpoint), enumClass,
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

    public RetrofitNetworkSectionBuilder mockDelay(MockRestAdapter adapter) {
        add(new NetworkDelaySetting(context.getString(R.string.debug_network_delay), adapter,
                NetworkDelaySetting.DEFAULT_VALUES));
        return this;
    }

    public RetrofitNetworkSectionBuilder mockVariance(MockRestAdapter adapter) {
        add(new NetworkVarianceSetting(context.getString(R.string.debug_network_variance), adapter,
                NetworkVarianceSetting.VALUES));
        return this;
    }

    public RetrofitNetworkSectionBuilder mockError(MockRestAdapter adapter) {
        add(new NetworkErrorSetting(context.getString(R.string.debug_network_error), adapter,
                NetworkErrorSetting.VALUES));
        return this;
    }

    public RetrofitNetworkSectionBuilder mock(MockRestAdapter adapter) {
        mockDelay(adapter);
        mockVariance(adapter);
        mockError(adapter);
        return this;
    }

    // TODO: refactor these into a composable class
    public static final class NetworkDelaySetting extends SingleChoiceSetting {

        public static final long[] DEFAULT_VALUES = {
                250, 500, 1000, 2000, 3000, 5000
        };

        private static int getPositionForValue(long[] values, long value) {
            for (int i = 0; i < values.length; i++) {
                if (values[i] == value) {
                    return i;
                }
            }
            return 3; // Default to 2000 if something changes.
        }

        private final MockRestAdapter adapter;
        private final long[] values;

        public NetworkDelaySetting(String title, MockRestAdapter adapter, long[] values) {
            super(title);
            this.adapter = adapter;
            this.values = values;
        }

        @Override
        public int getCount() {
            return values.length;
        }

        @Override
        public String getLabel(int position) {
            return values[position] + "ms";
        }

        @Override
        public int getSelectedItem() {
            return getPositionForValue(values, adapter.getDelay());
        }

        @Override
        public void setSelectedItem(int position) {
            adapter.setDelay(values[position]);
        }

    }

    public static final class NetworkVarianceSetting extends SingleChoiceSetting {

        private static final int[] VALUES = {
                20, 40, 60
        };

        // TODO: make this more generic
        public static int getPositionForValue(int[] values, int value) {
            for (int i = 0; i < values.length; i++) {
                if (values[i] == value) {
                    return i;
                }
            }
            return 1; // Default to 40% if something changes.
        }

        private final MockRestAdapter adapter;
        private final int[] values;

        public NetworkVarianceSetting(String title, MockRestAdapter adapter, int[] values) {
            super(title);
            this.adapter = adapter;
            this.values = values;
        }

        @Override
        public int getCount() {
            return values.length;
        }

        @Override
        public String getLabel(int position) {
            return "±" + values[position] + "%";
        }

        @Override
        public int getSelectedItem() {
            return getPositionForValue(values, adapter.getVariancePercentage());
        }

        @Override
        public void setSelectedItem(int position) {
            adapter.setVariancePercentage(values[position]);
        }

    }

    public static final class NetworkErrorSetting extends SingleChoiceSetting {

        private static final int[] VALUES = {
                0, 3, 10, 25, 50, 75, 100
        };

        public static int getPositionForValue(int[] values, int value) {
            for (int i = 0; i < values.length; i++) {
                if (values[i] == value) {
                    return i;
                }
            }
            return 1; // Default to 3% if something changes.
        }

        private final MockRestAdapter adapter;
        private final int[] values;

        public NetworkErrorSetting(String title, MockRestAdapter adapter, int[] values) {
            super(title);
            this.adapter = adapter;
            this.values = values;
        }

        @Override
        public int getCount() {
            return values.length;
        }

        @Override
        public String getLabel(int position) {
            return String.valueOf(values[position]) + "%";
        }

        @Override
        public int getSelectedItem() {
            return getPositionForValue(values, adapter.getErrorPercentage());
        }

        @Override
        public void setSelectedItem(int position) {
            adapter.setErrorPercentage(values[position]);
        }

    }

}
