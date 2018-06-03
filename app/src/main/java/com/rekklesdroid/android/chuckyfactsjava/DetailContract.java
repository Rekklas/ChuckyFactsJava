package com.rekklesdroid.android.chuckyfactsjava;

import com.rekklesdroid.android.chuckyfactsjava.entity.Value;

public interface DetailContract {

    interface View {
        void showJokeData(String id, String joke);

        void showInfoMessage(String msg);
    }

    interface Presenter {
        // User actions
        void onBackButtonClicked();

        // Model updates
        void onViewCreated(Value joke);

        void onDestroy();
    }
}
