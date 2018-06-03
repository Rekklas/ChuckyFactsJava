package com.rekklesdroid.android.chuckyfactsjava.view.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.rekklesdroid.android.chuckyfactsjava.MainContract;
import com.rekklesdroid.android.chuckyfactsjava.R;
import com.rekklesdroid.android.chuckyfactsjava.entity.Value;
import com.rekklesdroid.android.chuckyfactsjava.presenter.MainPresenter;
import com.rekklesdroid.android.chuckyfactsjava.view.adapter.JokesListAdapter;

import java.util.List;

public class MainActivity extends BaseActivity implements MainContract.View {

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
        mAdapter = new JokesListAdapter(null);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.onViewCreated();
    }

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
    protected void onDestroy() {
        mPresenter.onDestroy();
        mPresenter = null;
        super.onDestroy();
    }
}
