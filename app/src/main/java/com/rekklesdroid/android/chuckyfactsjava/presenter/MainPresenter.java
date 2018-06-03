package com.rekklesdroid.android.chuckyfactsjava.presenter;

import android.util.Log;

import com.rekklesdroid.android.chuckyfactsjava.BaseApplication;
import com.rekklesdroid.android.chuckyfactsjava.MainContract;
import com.rekklesdroid.android.chuckyfactsjava.Screens;
import com.rekklesdroid.android.chuckyfactsjava.entity.Value;
import com.rekklesdroid.android.chuckyfactsjava.interactor.MainInteractor;
import com.rekklesdroid.android.chuckyfactsjava.service.ApiService;

import java.util.List;

import ru.terrakok.cicerone.Router;

public class MainPresenter implements MainContract.Presenter, MainContract.InteractorOutput {

    private MainContract.View mView;
    private MainContract.Interactor mInteractor;
    private Router mRouter;
    private ApiService mApiService;

    public MainPresenter(MainContract.View view) {
        mView = view;
        mInteractor = new MainInteractor(this);
        mRouter = BaseApplication.INSTANCE.getRouter();

        if (mApiService == null){
            mApiService = new ApiService();
        }
    }

    @Override
    public void onListItemClicked(Value joke) {
        mRouter.navigateTo(Screens.DETAIL_ACTIVITY_SCREEN, joke);
        Log.d("joke number - ", joke.getId().toString());
    }

    @Override
    public void onViewCreated() {
        mView.showLoading();
        mInteractor.loadJokesList(mApiService);
    }

    @Override
    public void onDestroy() {
        mView = null;
        mInteractor = null;
    }

    @Override
    public void onQuerySuccess(List<Value> data) {
        mView.hideLoading();
        mView.publishDataList(data);
    }

    @Override
    public void onQueryError() {
        mView.hideLoading();
        mView.showInfoMessage("Error when loading data");
    }
}
