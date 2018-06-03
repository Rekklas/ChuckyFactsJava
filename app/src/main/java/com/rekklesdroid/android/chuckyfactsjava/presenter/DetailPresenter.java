package com.rekklesdroid.android.chuckyfactsjava.presenter;

import com.rekklesdroid.android.chuckyfactsjava.BaseApplication;
import com.rekklesdroid.android.chuckyfactsjava.DetailContract;
import com.rekklesdroid.android.chuckyfactsjava.entity.Value;

import ru.terrakok.cicerone.Router;

public class DetailPresenter implements DetailContract.Presenter {
    private DetailContract.View mView;
    private Router mRouter;

    public DetailPresenter(DetailContract.View view) {
        mView = view;
        mRouter = BaseApplication.INSTANCE.getRouter();
    }

    @Override
    public void onBackButtonClicked() {
        mRouter.exit();
    }

    @Override
    public void onViewCreated(Value joke) {
        mView.showJokeData(joke.getId().toString(), joke.getJoke());
    }

    @Override
    public void onDestroy() {
        mView = null;
    }
}
