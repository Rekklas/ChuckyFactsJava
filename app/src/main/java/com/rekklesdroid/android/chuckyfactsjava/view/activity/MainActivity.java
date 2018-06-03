package com.rekklesdroid.android.chuckyfactsjava.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.rekklesdroid.android.chuckyfactsjava.BaseApplication;
import com.rekklesdroid.android.chuckyfactsjava.MainContract;
import com.rekklesdroid.android.chuckyfactsjava.R;
import com.rekklesdroid.android.chuckyfactsjava.Screens;
import com.rekklesdroid.android.chuckyfactsjava.entity.Value;
import com.rekklesdroid.android.chuckyfactsjava.presenter.MainPresenter;
import com.rekklesdroid.android.chuckyfactsjava.presenter.RecyclerViewItemClickListener;
import com.rekklesdroid.android.chuckyfactsjava.view.adapter.JokesListAdapter;

import java.util.List;

import ru.terrakok.cicerone.Navigator;
import ru.terrakok.cicerone.commands.Command;
import ru.terrakok.cicerone.commands.Forward;

public class MainActivity extends BaseActivity implements MainContract.View, RecyclerViewItemClickListener {

    private MainContract.Presenter mPresenter;
    private Toolbar mToolbar;
    private RecyclerView mRecyclerView;
    private ProgressBar mProgressBar;
    private JokesListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mToolbar = findViewById(R.id.toolbar_toolbar_view);
        mRecyclerView = findViewById(R.id.rv_jokes_list_activity_main);
        mProgressBar = findViewById(R.id.prog_bar_loading_jokes_activity_main);

        mPresenter = new MainPresenter(this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mAdapter = new JokesListAdapter(this, null);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.onViewCreated();
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
            for (Command command : commands) applyCommand(command);
        }

        private void applyCommand(Command command) {
            if (command instanceof Forward) {
                forward((Forward) command);
            }
        }

        private void forward(Forward command) {
            switch (command.getScreenKey()) {
                case Screens.DETAIL_ACTIVITY_SCREEN:
                    Value data = (Value) command.getTransitionData();
                    startActivity(new Intent(MainActivity.this, DetailActivity.class)
                            .putExtra("data", data));
                default:
                    Log.e("Cicerone", "Unknown screen: " + command.getScreenKey());
                    break;
            }
        }
    };

    @Override
    public void showLoading() {
        mRecyclerView.setEnabled(false);
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        mRecyclerView.setEnabled(true);
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void publishDataList(List<Value> data) {
        mAdapter.updateData(data);
    }

    @Override
    public void showInfoMessage(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public Toolbar getToolbarInstance() {
        return mToolbar;
    }

    @Override
    public void onItemClickListener(Value joke) {
        mPresenter.onListItemClicked(joke);
    }
}
