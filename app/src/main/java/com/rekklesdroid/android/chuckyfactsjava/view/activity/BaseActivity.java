package com.rekklesdroid.android.chuckyfactsjava.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onResume() {
        super.onResume();

        Toolbar toolbar = getToolbarInstance();
        if (toolbar != null) {
            initView(toolbar);
        }
    }

    private void initView(Toolbar toolbar){
        setSupportActionBar(toolbar);
    }

    abstract public Toolbar getToolbarInstance();
}
