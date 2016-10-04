package com.hongseokandrewjang.android.shootingboy;

import android.app.IntentService;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.Context;
import android.util.Log;

public class ShootingBullet extends IntentService {

    public ShootingBullet() {
        super("ShootingBullet");
    }
}

    @Override
    protected void onHandleIntent(Intent intent) {

        for (int i = 1; i <= 60; i++) {
            try {
                Intent result = new Intent();
                PendingIntent reply = intent.getParcelableExtra(MainActivity.PENDING_RESULT);
                reply.send(this, MainActivity.RESULT_CODE, result);

                Thread.sleep(1000);
            } catch (Exception e) {
            }
        }
    }

