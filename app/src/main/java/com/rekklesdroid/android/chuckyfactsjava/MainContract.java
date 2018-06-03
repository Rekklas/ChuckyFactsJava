package com.rekklesdroid.android.chuckyfactsjava;

import com.rekklesdroid.android.chuckyfactsjava.entity.Value;
import com.rekklesdroid.android.chuckyfactsjava.service.ApiService;

import java.util.List;

public interface MainContract {
    interface View {
        void showLoading();

        void hideLoading();

        void publishDataList(List<Value> data);

        void showInfoMessage(String msg);
    }

    interface Presenter {
        // User actions
        void onListItemClicked(Value joke);

        // Model updates
        void onViewCreated();

        void onDestroy();
    }

    interface Interactor {
        void loadJokesList(ApiService apiService);
    }

    interface InteractorOutput {
        void onQuerySuccess(List<Value> data);

        void onQueryError();
    }
}
