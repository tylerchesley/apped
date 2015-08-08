package io.tylerchesley.apped.debug.setting;

import android.content.Context;
import android.os.Build;
import android.util.DisplayMetrics;

import io.tylerchesley.apped.debug.R;

public class DeviceInfoSectionBuilder extends SectionBuilder {

    private final DisplayMetrics displayMetrics;

    public DeviceInfoSectionBuilder(Context context) {
        super(context);
        displayMetrics = context.getResources().getDisplayMetrics();
        title(R.string.debug_header_device_information);
        addDefaults();
    }

    private static int getDensityStringRes(DisplayMetrics displayMetrics) {
        switch (displayMetrics.densityDpi) {
            case DisplayMetrics.DENSITY_LOW:
                return R.string.debug_device_density_value_ldpi;
            case DisplayMetrics.DENSITY_MEDIUM:
                return R.string.debug_device_density_value_mdpi;
            case DisplayMetrics.DENSITY_HIGH:
                return R.string.debug_device_density_value_hdpi;
            case DisplayMetrics.DENSITY_XHIGH:
                return R.string.debug_device_density_value_xhdpi;
            case DisplayMetrics.DENSITY_XXHIGH:
                return R.string.debug_device_density_value_xxhdpi;
            case DisplayMetrics.DENSITY_XXXHIGH:
                return R.string.debug_device_density_value_xxxhdpi;
            case DisplayMetrics.DENSITY_TV:
                return R.string.debug_device_density_value_xxxhdpi;
            default:
                return R.string.debug_device_density_value_unknown;
        }
    }

    public static DeviceInfoSectionBuilder from(Context context) {
        return new DeviceInfoSectionBuilder(context);
    }

    public DeviceInfoSectionBuilder addMake() {
        add(R.string.debug_device_make, Build.MANUFACTURER);
        return this;
    }

    public DeviceInfoSectionBuilder addModel() {
        add(R.string.debug_device_model, Build.MODEL);
        return this;
    }

    public DeviceInfoSectionBuilder addResolution() {
        add(R.string.debug_device_resolution, context.getString(
                R.string.debug_device_resolution_value,
                displayMetrics.heightPixels, displayMetrics.widthPixels));
        return this;
    }

    public DeviceInfoSectionBuilder addDensity() {
        add(R.string.debug_device_density, context.getString(R.string.debug_device_density_value,
                displayMetrics.densityDpi, context.getString(getDensityStringRes(displayMetrics))));
        return this;
    }

    public DeviceInfoSectionBuilder addRelease() {
        add(R.string.debug_device_release, Build.VERSION.RELEASE);
        return this;
    }

    public DeviceInfoSectionBuilder addApiLevel() {
        add(R.string.debug_device_api_level, String.valueOf(Build.VERSION.SDK_INT));
        return this;
    }

    public DeviceInfoSectionBuilder addDefaults() {
        return addMake()
                .addModel()
                .addResolution()
                .addDensity()
                .addRelease()
                .addApiLevel();
    }

    @Override
    public Section build() {
        if (children.size() == 0) {
            addDefaults();
        }

        return super.build();
    }
}
