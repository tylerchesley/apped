package io.tylerchesley.apped.debug.action;

import android.content.Context;

import com.jakewharton.processphoenix.ProcessPhoenix;

public class RestartApplicationAction implements Action {

    private final Context context;

    public RestartApplicationAction(Context context) {
        this.context = context;
    }

    @Override
    public void execute() {
        ProcessPhoenix.triggerRebirth(context);
    }

}
