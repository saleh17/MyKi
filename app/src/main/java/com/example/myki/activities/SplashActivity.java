package com.example.myki.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myki.R;

import io.fabric.sdk.android.services.common.CommonUtils;

public class SplashActivity extends AppCompatActivity {

    private static final long DELAY = 1500;

    private Handler handler = new Handler();
    private Runnable runnable = this::redirectToMain;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        handler.postDelayed(runnable, DELAY);
    }

    private void redirectToMain() {
        if (CommonUtils.isRooted(getApplicationContext())) {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        } else {
            showRootedDialog();
        }
    }

    private void showRootedDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getString(R.string.device_rooted))
                .setMessage(getString(R.string.device_rooted_message))
                .setCancelable(false)
                .setPositiveButton(getString(R.string.close),
                        (dialog, which) -> finish()
                );
        builder.create().show();
    }
}

