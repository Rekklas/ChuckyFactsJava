package com.rekklesdroid.android.chuckyfactsjava.interactor;

import com.rekklesdroid.android.chuckyfactsjava.MainContract;
import com.rekklesdroid.android.chuckyfactsjava.entity.JsonResult;
import com.rekklesdroid.android.chuckyfactsjava.service.ApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainInteractor implements MainContract.Interactor {

    private MainContract.InteractorOutput mInteractorOutput;

    public MainInteractor(MainContract.InteractorOutput interactorOutput) {
        mInteractorOutput = interactorOutput;
    }

    @Override
    public void loadJokesList(ApiService apiService) {
        apiService.getApi().getJokes().enqueue(new Callback<JsonResult>() {
            @Override
            public void onResponse(Call<JsonResult> call, Response<JsonResult> response) {
                JsonResult result = response.body();

                if (result != null && result.getValue() != null) {
                    mInteractorOutput.onQuerySuccess(result.getValue());
                }
            }

            @Override
            public void onFailure(Call<JsonResult> call, Throwable t) {
                mInteractorOutput.onQueryError();
            }
        });
    }
}
