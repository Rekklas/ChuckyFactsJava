package com.rekklesdroid.android.chuckyfactsjava.view.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.rekklesdroid.android.chuckyfactsjava.BaseApplication;
import com.rekklesdroid.android.chuckyfactsjava.DetailContract;
import com.rekklesdroid.android.chuckyfactsjava.R;
import com.rekklesdroid.android.chuckyfactsjava.entity.Value;
import com.rekklesdroid.android.chuckyfactsjava.presenter.DetailPresenter;

import java.util.Objects;

import ru.terrakok.cicerone.Navigator;
import ru.terrakok.cicerone.commands.Back;
import ru.terrakok.cicerone.commands.Command;

public class DetailActivity extends BaseActivity implements DetailContract.View {

    private DetailContract.Presenter mPresenter;
    private Toolbar mToolbar;
    private TextView mTvId;
    private TextView mTvJoke;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        mToolbar = findViewById(R.id.toolbar_toolbar_view);
        mTvId = findViewById(R.id.tv_joke_id_activity_detail);
        mTvJoke = findViewById(R.id.tv_joke_activity_detail);

        mPresenter = new DetailPresenter(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mPresenter.onBackButtonClicked();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        // add back arrow to toolbar
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        Intent intent = getIntent();
        mPresenter.onViewCreated((Value) intent.getParcelableExtra("data"));


        BaseApplication.INSTANCE.getNavigatorHolder().setNavigator(mNavigator);
    }

    @Override
    protected void onPause() {
        super.onPause();
        BaseApplication.INSTANCE.getNavigatorHolder().removeNavigator();
    }

    @Override
    protected void onDestroy() {
        mPresenter.onDestroy();
        mPresenter = null;
        super.onDestroy();
    }

    private Navigator mNavigator = new Navigator() {
        @Override
        public void applyCommands(Command[] commands) {
            for (Command command: commands){
                applyCommand(command);
            }
        }

        private void applyCommand(Command command) {
            if (command instanceof Back){
                back();
            }
        }

        private void back() {
            finish();
        }
    };

    @Override
    public void showJokeData(String id, String joke) {
        mTvId.setText(id);
        mTvJoke.setText(joke);
    }

    @Override
    public void showInfoMessage(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public Toolbar getToolbarInstance() {
        return mToolbar;
    }
}
