package io.tylerchesley.apped;

import android.app.Activity;

public class Apped {

    public static void configure(Activity activity) {
        final Class<? extends Activity> activityClass = activity.getClass();
        final Layout layout = activityClass.getAnnotation(Layout.class);
        if (layout != null) {
            activity.setContentView(layout.value());
        }

        final Title title = activityClass.getAnnotation(Title.class);
        if (title != null) {
            activity.setTitle(title.value());
        }
    }


}
