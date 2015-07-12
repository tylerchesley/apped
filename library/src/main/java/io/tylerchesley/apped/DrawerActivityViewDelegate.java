package io.tylerchesley.apped;

import android.app.Activity;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.ViewGroup;

public class DrawerActivityViewDelegate implements ActivityViewDelegate {

    private final int drawerLayoutRes;
    private final int drawerId;
    private final int contentContainerId;

    private DrawerLayout drawerLayout;

    public DrawerActivityViewDelegate(@LayoutRes int drawerLayoutRes,
                                      @IdRes int drawerId,
                                      @IdRes int contentContainerId) {
        this.drawerLayoutRes = drawerLayoutRes;
        this.drawerId = drawerId;
        this.contentContainerId = contentContainerId;
    }

    @Override
    public void setContentView(Activity activity, @LayoutRes int layoutResource) {
        activity.setContentView(drawerLayoutRes);
        drawerLayout = (DrawerLayout) activity.findViewById(drawerId);
        if (drawerLayout == null) {
            throw new NullPointerException("Layout must contain a DrawerLayout with the ID of drawer!");
        }

        final ViewGroup contentContainer = (ViewGroup) activity.findViewById(contentContainerId);
        if (contentContainer == null) {
            throw new NullPointerException("No content container found");
        }

        LayoutInflater.from(activity).inflate(layoutResource, contentContainer);
    }

}
