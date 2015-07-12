package io.tylerchesley.apped.debug.setting;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;

import io.tylerchesley.apped.R;

public class ApplicationHeader extends Setting {

    public static ApplicationHeader from(Context context) {
        final PackageManager packageManager = context.getPackageManager();
        final ApplicationInfo applicationInfo = context.getApplicationInfo();
        return new ApplicationHeader((String) applicationInfo.loadLabel(packageManager),
                getSubtitle(context), applicationInfo.loadIcon(packageManager));
    }

    private static String getSubtitle(Context context) {
        try {
            return context.getString(R.string.debug_settings_subtitle_version_name,
                    context.getPackageManager().getPackageInfo(context.getPackageName(), 0)
                            .versionName);
        } catch (PackageManager.NameNotFoundException e) {
            return context.getString(R.string.debug_settings_subtitle);
        }
    }

    private final Drawable icon;
    private final String subtitle;

    public ApplicationHeader(String title, String subtitle, Drawable icon) {
        super(title);
        this.subtitle = subtitle;
        this.icon = icon;
    }

    public Drawable getIcon() {
        return icon;
    }

    public String getSubtitle() {
        return subtitle;
    }

}
