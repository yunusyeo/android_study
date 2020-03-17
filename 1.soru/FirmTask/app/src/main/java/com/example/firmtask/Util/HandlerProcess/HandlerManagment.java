package com.example.firmtask.Util.HandlerProcess;

import android.os.Handler;

public class HandlerManagment {
    public static final Long DEFAULT_HANDLER_DURATION = 3000L;

    public void postDelayedHandler(final HandlerCallback handlerCallback, Long duration) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                handlerCallback.onCompleted();
            }
        }, duration);
    }
}
