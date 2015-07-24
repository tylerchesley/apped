package io.tylerchesley.apped.debug.setting;

import android.content.Context;

import io.tylerchesley.apped.R;
import io.tylerchesley.apped.debug.action.Action;
import io.tylerchesley.apped.debug.action.RestartApplicationAction;
import retrofit.MockRestAdapter;
import retrofit.RestAdapter;

public class RetrofitNetworkSectionBuilder extends SectionBuilder {

    public static final Long[] DEFAULT_NETWORK_DELAY_CHOICES = {
            250l, 500l, 1000l, 2000l, 3000l, 5000l
    };
    private static final Integer[] DEFAULT_NETWORK_ERROR_CHOICES = {
            0, 3, 10, 25, 50, 75, 100
    };
    private static final Integer[] DEFAULT_NETWORK_VARIANCE_CHOICES = {
            20, 40, 60
    };

    public RetrofitNetworkSectionBuilder(Context context) {
        super(context);
        title(R.string.debug_network_header);
    }

    public static RetrofitNetworkSectionBuilder from(Context context) {
        return new RetrofitNetworkSectionBuilder(context);
    }

    public <T extends Enum<T>> RetrofitNetworkSectionBuilder endpoints(Class<T> enumClass) {
        // TODO: add support for customizing where the endpoint is stored
        final Action restartAction = new RestartApplicationAction(context.getApplicationContext());
        add(SingleChoiceSetting
                .build(context, enumClass)
                .title(R.string.debug_network_endpoint)
                .listener(new SingleChoiceSetting.OnChangeListener<T>() {
                    @Override
                    public boolean onItemSelected(SingleChoiceSetting<T> setting, T item) {
                        restartAction.execute();
                        return false;
                    }
                }).build());
        return this;
    }

    public RetrofitNetworkSectionBuilder mockDelay(final MockRestAdapter adapter) {
        return mockDelay(adapter, DEFAULT_NETWORK_DELAY_CHOICES, 2000);
    }

    public RetrofitNetworkSectionBuilder mockDelay(final MockRestAdapter adapter,
                                                   Long[] values, long defaultValue) {
        add(SingleChoiceSetting
                .build(context, values)
                .title(R.string.debug_network_delay)
                .defaultValue(defaultValue)
                .persister(new ChoicePersister<Long>() {
                    @Override
                    public Long getSelectedItem() {
                        return adapter.getDelay();
                    }

                    @Override
                    public void setSelectedItem(Long item) {
                        adapter.setDelay(item);
                    }
                })
                .formatter(new ChoiceFormatter<Long>() {
                    @Override
                    public String format(Long item) {
                        return item + "ms";
                    }
                })
                .build());
        return this;
    }

    public RetrofitNetworkSectionBuilder mockVariance(final MockRestAdapter adapter) {
        return mockVariance(adapter, DEFAULT_NETWORK_VARIANCE_CHOICES, 40);
    }

    public RetrofitNetworkSectionBuilder mockVariance(final MockRestAdapter adapter,
                                                      Integer[] values, int defaultValue) {
        add(SingleChoiceSetting
                .build(context, values)
                .title(R.string.debug_network_variance)
                .defaultValue(defaultValue)
                .persister(new ChoicePersister<Integer>() {
                    @Override
                    public Integer getSelectedItem() {
                        return adapter.getVariancePercentage();
                    }

                    @Override
                    public void setSelectedItem(Integer item) {
                        adapter.setVariancePercentage(item);
                    }
                })
                .formatter(new ChoiceFormatter<Integer>() {
                    @Override
                    public String format(Integer item) {
                        return "±" + item + "%";
                    }
                })
                .build());
        return this;
    }

    public RetrofitNetworkSectionBuilder mockError(MockRestAdapter adapter) {
        return mockError(adapter, DEFAULT_NETWORK_ERROR_CHOICES, 3);
    }

    public RetrofitNetworkSectionBuilder mockError(final MockRestAdapter adapter,
                                                   Integer[] values, int defaultValue) {
        add(SingleChoiceSetting
                .build(context, values)
                .title(R.string.debug_network_error)
                .defaultValue(defaultValue)
                .persister(new ChoicePersister<Integer>() {
                    @Override
                    public Integer getSelectedItem() {
                        return adapter.getErrorPercentage();
                    }

                    @Override
                    public void setSelectedItem(Integer item) {
                        adapter.setErrorPercentage(item);
                    }
                })
                .formatter(new ChoiceFormatter<Integer>() {
                    @Override
                    public String format(Integer item) {
                        if (item == 0) {
                            //TODO: localize this
                            return "None";
                        }
                        return item + "%";
                    }
                })
                .build());
        return this;
    }

    public RetrofitNetworkSectionBuilder mock(MockRestAdapter adapter) {
        mockDelay(adapter);
        mockVariance(adapter);
        mockError(adapter);
        return this;
    }

    public RetrofitNetworkSectionBuilder logLevel(final RestAdapter adapter) {
        add(SingleChoiceSetting
                .build(context, RestAdapter.LogLevel.class)
                .title(R.string.debug_network_logging)
                .persister(new ChoicePersister<RestAdapter.LogLevel>() {
                    @Override
                    public RestAdapter.LogLevel getSelectedItem() {
                        return adapter.getLogLevel();
                    }

                    @Override
                    public void setSelectedItem(RestAdapter.LogLevel item) {
                        adapter.setLogLevel(item);
                    }
                }).build());
        return this;
    }

}
