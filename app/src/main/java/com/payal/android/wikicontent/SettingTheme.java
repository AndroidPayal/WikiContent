package com.payal.android.wikicontent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SwitchCompat;
import android.widget.CompoundButton;

import com.payal.android.wikicontent.sharedPref.InitApplication;

public class SettingTheme extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_theme);

        SwitchCompat switchCompat =findViewById(R.id.switchc);
        switchCompat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                InitApplication.getInstance().setIsNightModeEnabled( isChecked , SettingTheme.this);
                Intent intent = getIntent();
                finish();
                startActivity(intent);

            }
        });
    }
}
