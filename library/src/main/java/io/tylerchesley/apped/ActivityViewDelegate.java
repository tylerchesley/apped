package io.tylerchesley.apped;

import android.app.Activity;
import android.support.annotation.LayoutRes;

public interface ActivityViewDelegate {

    public void setContentView(Activity activity, int layoutResource);

    public static final class DefaultActivityViewDelegate implements ActivityViewDelegate {

        @Override
        public void setContentView(Activity activity, @LayoutRes int layoutResource) {
            activity.setContentView(layoutResource);
        }

    }

}
