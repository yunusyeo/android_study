package com.example.firmtask.Activity.SplashActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.firmtask.Activity.MainActivity.MainActivity;
import com.example.firmtask.Presenter.SplashActivityPresenter;
import com.example.firmtask.R;
import com.example.firmtask.Util.Util;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;

public class SplashActivity extends AppCompatActivity implements SplashActivityContract.View {
    private static final String TAG = SplashActivity.class.getSimpleName();
    private static final String TEXT = "text";

    private TextView firmName;

    private FirebaseRemoteConfig mRemoteConfig;
    private SplashActivityPresenter splashActivityPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        splashActivityPresenter = new SplashActivityPresenter();
        splashActivityPresenter.setView(this);
        splashActivityPresenter.create();

    }

    @Override
    public void bindViews() {
        firmName = (TextView) findViewById(R.id.firm_name);
        if (Util.isConnected(SplashActivity.this)) {
            mRemoteConfig = FirebaseRemoteConfig.getInstance();
            mRemoteConfig.setDefaults(R.xml.remote_config_default);
            fetch();
        } else {
            Toast.makeText(SplashActivity.this, getString(R.string.internet_warning),
                    Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void finishActivity() {
        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void fetch() {
        long cacheExpiration = 3600;
        mRemoteConfig.fetch(cacheExpiration)
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "Fetch succeeded.");
                            mRemoteConfig.activateFetched();

                            String text = mRemoteConfig.getString(TEXT);
                            Log.d(TAG, "text: " + text);
                            firmName.setText(text);
                        } else {
                            Log.d(TAG, "Fetch Failed");
                        }
                    }
                });
    }
}
