package io.tylerchesley.apped.debug.setting;

import android.content.Context;

import io.tylerchesley.apped.BuildConfig;
import io.tylerchesley.apped.R;

public class BuildInfoSectionBuilder extends SectionBuilder {

    public static BuildInfoSectionBuilder from(Context context) {
        return new BuildInfoSectionBuilder(context);
    }

    public BuildInfoSectionBuilder(Context context) {
        super(context);
        title(R.string.debug_header_build_information);
    }

    public BuildInfoSectionBuilder addVersionName() {
        add(R.string.debug_build_version_name, BuildConfig.VERSION_NAME);
        return this;
    }

    public BuildInfoSectionBuilder addVersionCode() {
        add(R.string.debug_build_version_code, String.valueOf(BuildConfig.VERSION_CODE));
        return this;
    }

    public BuildInfoSectionBuilder addBuildTime(String date) {
        add(R.string.debug_build_time, date);
        return this;
    }

    public BuildInfoSectionBuilder addCommit(String commit) {
        add(R.string.debug_build_commit, commit);
        return this;
    }

    public BuildInfoSectionBuilder addDefaults() {
        return addVersionName()
                .addVersionCode();
    }

    @Override
    public Section build() {
        if (children.size() == 0) {
            addDefaults();
        }

        return super.build();
    }

}
